# py -3
# -*- coding: utf8 -*-
"""
Ebook Processor. Part of ebook build chain, along with WordCleaner7
Capture Intro and Quote as <blockquote>
"""
from pathlib import Path
import pprint
import os, sys, re, shutil, time
from itertools import chain
from sortedcontainers import SortedSet
from collections import OrderedDict
from betools import CmdLine, visitDir, ruler, head
import webbrowser
import textwrap


ebookName = "onjava"
rootPath = Path(r"C:\Users\Bruce\Dropbox\___OnJava")
docm = rootPath / "OnJava.docm"
ebookBuildPath = rootPath / "ebook_build"
html = ebookBuildPath / (ebookName + ".html")
ebookResources = rootPath / "ebook_resources"
css = ebookResources / (ebookName + ".css")
fonts = ebookResources.glob("ubuntumono-*")
cover = ebookResources / "cover" / "cover.jpg"
example_path = Path(r"C:\Users\Bruce\Dropbox\___OnJava\ExtractedExamples")
tablepath = ebookBuildPath / "tables"

def start_marker(tag):
    return '[${}$]'.format(tag)

def end_marker(tag):
    return '[$end_{}$]'.format(tag)


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
        blockquote { font-size:130% }
        code { font-size: 85%; font-family:'Ubuntu Mono' }
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



@CmdLine('f')
def fresh_start():
    """
    Create book build directory and copy resources into it
    """
    print("Cleaning ...")
    if ebookBuildPath.exists():
        shutil.rmtree(str(ebookBuildPath))
    time.sleep(1)
    ebookBuildPath.mkdir()
    shutil.copy(str(docm), str(ebookBuildPath))
    def _cp(src):
        shutil.copy(str(src), str(ebookBuildPath))
    for font in fonts:
        _cp(font)
    _cp(cover)
    _cp(css)



count = 0
# @CmdLine('r')
def rewrite_html():
    """
    Pre-processing HTML tagging and fixups.
    """
    codeblock = re.compile('''(<p class="Code">.*?</p>\s*)+''', re.DOTALL)
    codeline = re.compile('''<p class="Code">(.*?)</p>\s*''', re.DOTALL)
    def rewrite_code_line(matchobj):
        return matchobj.group(1).rstrip() + start_marker("br")
    def rewrite_code_block(matchobj):
        global count
        count += 1
        return  start_marker("code") + \
                codeline.sub(rewrite_code_line, matchobj.group(0)) + \
                "\n" + end_marker("code") + "\n"


    intro = re.compile('''<p class="Intro">(.*?)</p>''', re.DOTALL)
    quote = re.compile('''<p class="Quote">(.*?)</p>''', re.DOTALL)

    def rewrite_bq(matchobj):
        return  start_marker("blockquote") + \
                matchobj.group(1).rstrip() + \
                "\n" + end_marker("blockquote") + "\n"

    with html.open(encoding="utf8") as ht:
        rewritten = codeblock.sub(rewrite_code_block, ht.read())
        rewritten = intro.sub(rewrite_bq, rewritten)
        rewritten = quote.sub(rewrite_bq, rewritten)

    with html.with_name(html.stem + "-2.html").open('w', encoding="utf8") as ht:
        ht.write(rewritten)

    print(count)


@CmdLine('x')
def cleanup_stripped_html():
    """
    Clean up stripped HTML -- final housekeeping
    """
    fixes = [
        (start_marker("code"), "<code>\n"),
        (end_marker("code"), "\n</code>"),
        (start_marker("blockquote"), "<blockquote>"),
        (end_marker("blockquote"), "</blockquote>"),
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

@CmdLine('t')
def extract_and_check_tables():
    """
    Extract tables and view them for checking
    """
    # Extract tables:
    print("extracting tables ...")
    if tablepath.exists():
        shutil.rmtree(str(tablepath))
    time.sleep(1)
    tablepath.mkdir()

    os.chdir(str(tablepath))
    with html.with_name(html.stem + "-3.html").open(encoding="utf8") as ht:
        doc = ht.read()
    # doc = doc.replace("<thead>", "")
    # doc = doc.replace("</thead>", "")
    # doc = doc.replace("<tbody>", "")
    # doc = doc.replace("</tbody>", "")
    tables = re.compile("(<table.*?>)(.*?</table>)", re.DOTALL)
    for n, table in enumerate(tables.findall(doc)):
        fname = "%02d_table.html" % n
        # print(fname)
        with (tablepath / fname).open('w', encoding="utf8") as tablefile:
            tablefile.write(table[0])
            tablefile.write(table[1])
        pandoc = "pandoc {} -t markdown -o {}.md".format(fname, fname.split('.')[0])
        print(pandoc)
        os.system(pandoc)

@CmdLine('v')
def view_tables():
    """
    View tables for checking
    """
    os.chdir(str(tablepath))
    # for html in Path(".").glob("*.html"):
    #     webbrowser.open("ed {}".format(html))
    for md in Path(".").glob("*.md"):
        os.system("ed {}".format(md))



@CmdLine('c')
def convert_to_html():
    "Convert to html"
    os.chdir(str(ebookBuildPath))
    print("Convert to HTML")
    os.system('''WordCleaner7''')
    show_all_code_tags()
    rewrite_html()
    print("TEST Clean up existing HTML and remove formatting")
    os.system('''WordCleaner7''')
    cleanup_stripped_html()


@CmdLine('m')
def convert_to_markdown():
    "Convert to markdown"
    os.chdir(str(ebookBuildPath))
    cmd = "pandoc {} -f html -t markdown -o {}.md".format("onjava-3.html", "onjava")
    print(cmd)
    os.system(cmd)
    with Path("onjava.md").open(encoding="utf8") as mdown:
        markdown = mdown.read()
    markdown = markdown.replace("****", "") # Clean out reduntant bolding
    with Path("onjava.md").open('w', encoding="utf8") as mdown:
        mdown.write(markdown)


silly = r"""</div>

\
<div>"""

standalone_start_old = r"""
` """
standalone_start_new = r"""
```java
 """
standalone_end_old = r"""
 `
"""
standalone_end_new = r"""
```"""

@CmdLine('s')
def reconstruct_source_code_files():
    "Reconstruct source code from examples, make sure you attach output first"
    print("reconstruct_source_code_files")
    os.chdir(str(ebookBuildPath))
    example = re.compile(r"` //: (.*?\.(java|txt|cpp|py|prop))(.*?)///:~.*?`", re.DOTALL)

    def restore_example(matchobj):
        ename = matchobj.group(1)
        print(ename.encode("utf8"))
        example_source = example_path / Path(ename)
        # print(str(example_source))
        assert example_source.exists(), "{} doesn't exist".format(example_source)
        with example_source.open() as example_code:
            return "```java\n" + \
                example_code.read() + \
                "```\n"

    with Path("onjava.md").open(encoding="utf8", errors="ignore") as md:
        restored = example.sub(restore_example, md.read())
        restored = restored.replace(start_marker("br"), "\n")

    restored = restored.replace(silly, "")
    restored = restored.replace(standalone_start_old, standalone_start_new)
    restored = restored.replace(standalone_end_old, standalone_end_new)

    ######### This is the new section:
    codeblocks = re.compile("```java\n(.*?)\n```")
    def dedent(matchobj):
        return "```java\n" + textwrap.dedent(matchobj.group(1)) + "\n```"
    restored = codeblocks.sub(dedent, restored)

    with Path("onjava-2.md").open('w', encoding="utf8") as ojmd2:
        ojmd2.write(restored)

@CmdLine('b')
def break_up_markdown_file():
    "turn markdown file into a collection of chapter-based files"
    os.chdir(str(ebookBuildPath))

    def mdfilename(h1, n):
        fn = h1.replace(": ", "_")
        fn = fn.replace(" ", "") + ".md"
        fn = fn.replace("&", "and")
        return "%02d_" % n + fn

    chapters = re.compile(r"\n([A-Za-z\:\& ]*)\n=+\n")
    with Path("onjava-2.md").open(encoding="utf8") as ojmd2:
        book = ojmd2.read()
    parts = chapters.split(book)
    names = parts[1::2]
    bodies = parts[0::2]
    chaps = OrderedDict()
    chaps["Front"] = bodies[0]
    for i, nm in enumerate(names):
        chaps[nm] = bodies[i + 1]

    for i, p in enumerate(chaps):
        print(mdfilename(p, i).encode("utf8"))
        with Path(mdfilename(p, i)).open('w', encoding="utf8") as chp:
            chp.write(p + "\n")
            chp.write("=" * len(p) + "\n")
            chp.write(chaps[p])


@CmdLine('w')
def view_in_texts():
    "Show all separate .md files in wysiwyg markdown editor"
    os.chdir(str(ebookBuildPath))
    for md in Path(".").glob("*.md"):
        os.system("texts {}".format(md))


@CmdLine('e')
def everything():
    """Produce Markdown file from Word doc"""
    # fresh_start()
    convert_to_html()
    convert_to_markdown()
    reconstruct_source_code_files()
    break_up_markdown_file()


@CmdLine('r')
def reassemble_and_convert_to_epub():
    """
    Put markdown files together, then pandoc to epub
    """
    output_name = "onjava-assembled.md"
    os.chdir(str(ebookBuildPath))
    assembled = ""
    for md in Path(".").glob("[0-9][0-9]_*.md"):
        print(str(md))
        with md.open(encoding="utf8") as part:
            assembled += part.read() + "\n"
    with Path(output_name).open('w', encoding="utf8") as book:
        book.write(assembled)
    pandoc = ("pandoc {} -f markdown-native_divs -t epub -o OnJava.epub" + \
        " --epub-cover-image=cover.jpg " + \
        " --epub-embed-font=ubuntumono-r-webfont.ttf " + \
        " --epub-chapter-level=1 " + \
        " --toc-depth=2 " + \
        " --no-highlight " + \
        " --epub-stylesheet=onjava.css "
        ).format(output_name)
    print(pandoc)
    os.system(pandoc)
    shutil.copy("OnJava.epub", "OnJava.zip")
    os.system("unzip OnJava.zip -d epub_files")


if __name__ == '__main__': CmdLine.run()
