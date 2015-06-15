# py -3
# -*- coding: utf8 -*-
"""
Ebook Processor. Part of ebook build chain, along with WordCleaner7
"""
from pathlib import Path
import pprint
import os, sys, re, shutil, time
from itertools import chain
from sortedcontainers import SortedSet
from betools import CmdLine, visitDir, ruler, head
ebookName = "onjava"
rootPath = Path(r"C:\Users\Bruce\Dropbox\___OnJava")
docm = rootPath / "OnJava.docm"
ebookBuildPath = rootPath / "ebook_build"
html = ebookBuildPath / (ebookName + ".html")
ebookResources = rootPath / "ebook_resources"
css = ebookResources / (ebookName + ".css")
fonts = ebookResources.glob("ubuntumono-*")

start_code_tag = '[$code$]'
end_code_tag = '[$end_code$]'

@CmdLine('s')
def show_all_code_tags():
    """
    Shows all html "Code" tag variations used in book.
    """
    tag = re.compile("<.*?>")
    with html.open(encoding="utf8") as ht:
        tags = SortedSet(tag.findall(ht.read()))
    for t in tags:
        if "Code" in t:
            print(t)

count = 0
@CmdLine('r')
def rewrite_code_blocks():
    """
    Find contiguous blocks of <p class="Code">.
    """
    codeblock = re.compile('''(<p class="Code">.*?</p>\s*)+''', re.DOTALL)
    codeline = re.compile('''<p class="Code">(.*?)</p>\s*''', re.DOTALL)
    def rewrite_code_line(matchobj):
        return matchobj.group(1).rstrip() + "<br>"
    def rewrite_code_block(matchobj):
        global count
        count += 1
        return  start_code_tag + \
                codeline.sub(rewrite_code_line, matchobj.group(0)) + \
                "\n" + end_code_tag + "\n"

    with html.open(encoding="utf8") as ht:
        rewritten = codeblock.sub(rewrite_code_block, ht.read())
    # with html.open('w', encoding="utf8") as ht:
    with html.with_name(html.stem + "-2.html").open('w', encoding="utf8") as ht:
        ht.write(rewritten)

    print(count)


style = """
    <style type="text/css">
        @font-face {
            font-family: Ubuntu Mono;
            src: url('ubuntumono-r-webfont.eot');
            src: url('ubuntumono-r-webfont.eot?#iefix') format('embedded-opentype'),
                 url('ubuntumono-r-webfont.woff') format('woff'),
                 url('ubuntumono-r-webfont.ttf') format('truetype'),
                 url('ubuntumono-r-webfont.svg#ubuntu_monoregular') format('svg');
            font-weight: normal;
            font-style: normal;
        }
        code { line-height:80%; font-family:'Ubuntu Mono' }
        thead {
            font-weight: bold;
            font-size: 120%;
        }
        table, th, td {
            border: 2px solid black;
            border-collapse: collapse;
                    padding-left: 10px;
                  padding-right: 10px;      }
    </style>
    </head>
"""

blank_table_row = """\
                </tbody>
                <tr>
                    <td>
                    </td>
                    <td>
                    </td>
                </tr>
            </table>"""

fixed_table_row = """\
                </tbody>
            </table>"""

@CmdLine('c')
def cleanup_stripped_html():
    """
    Clean up stripped HTML -- final housekeeping
    """
    fixes = [
        (start_code_tag, "<code>"),
        (end_code_tag, "</code>"),
        ("</head>", style),
        ('<table cellspacing="0" cellpadding="0">', '<table align="center">'),
        (blank_table_row, fixed_table_row),
    ]
    with html.with_name(html.stem + "-2.html").open(encoding="utf8") as ht:
        doc = ht.read()
        for fix in fixes:
            doc = doc.replace(*fix)

    with html.with_name(html.stem + "-3.html").open('w', encoding="utf8") as ht:
        ht.write(doc)

    for font in fonts:
        shutil.copy(str(font), str(ebookBuildPath))



def copy_resources():
    """
    Copy resources into book build directory
    """
    shutil.copy(str(css), str(ebookBuildPath))


@CmdLine('f')
def fresh_start():
    "Recreate ebookBuildPath"
    print("Cleaning ...")
    if ebookBuildPath.exists():
        shutil.rmtree(str(ebookBuildPath))
    time.sleep(1)
    ebookBuildPath.mkdir()
    shutil.copy(str(docm), str(ebookBuildPath))
    os.chdir(str(ebookBuildPath))
    print("Convert to HTML")
    os.system('''WordCleaner7''')
    show_all_code_tags()
    rewrite_code_blocks()
    print("TEST Clean up existing HTML and remove formatting")
    os.system('''WordCleaner7''')
    cleanup_stripped_html()




if __name__ == '__main__': CmdLine.run()
