#! Py -3
from pathlib import Path
from filecmp import cmpfiles
import sys, os
from sortedcontainers import SortedSet
from betools import *
from pprint import *

gitpath = Path(r"C:\Users\Bruce\Documents\GitHub\TIJ-Directors-Cut")
examplePath = Path(r"C:\Users\Bruce\Dropbox\__TIJ4-ebook\ExtractedExamples")

def ignore(lst):
    result = [f for f in lst if not str(f).startswith(".git")]
    return result

os.chdir(str(examplePath))
book = SortedSet([f for f in Path(".").rglob("*")])
os.chdir(str(gitpath))
git = SortedSet([f for f in Path(".").rglob("*")])
git = ignore(git)


@CmdLine('g')
def copy_to_git():
    "Write batch file to copy missing files to git directory"
    exclude = ["Book.txt", "Git.txt", "togit.bat"]
    os.chdir(str(examplePath))
    with Path("togit.bat").open("w") as togit:
        tocopy = [e for e in book if e not in git]
        for e in exclude:
            tocopy = [t for t in tocopy if not str(t).endswith(e)]
        for tc in tocopy:
            togit.write("copy " + str(tc) + " " + str(gitpath / tc) + "\n")
    if Path("togit.bat").stat().st_size == 0:
        Path("togit.bat").unlink()


def retain(lst):
    keep = ["README.md", "go.bat", "clean.bat"]
    result = [f for f in lst if not str(f).startswith("tools")]
    result = [f for f in result if not f.is_dir()]
    for k in keep:
        result = [f for f in result if not str(f).endswith(k)]
    return result


@CmdLine('x')
def clean():
    "Write batch file to remove unused files from git directory"
    os.chdir(str(gitpath))
    with Path("clean.bat").open("w") as clean:
        toclean = retain([g for g in git if g not in book])
        for tc in toclean:
            clean.write("del " + str(tc) + "\n")
    if Path("clean.bat").stat().st_size == 0:
        Path("clean.bat").unlink()

@CmdLine('u')
def update_to_git():
    "Write batch file to copy out-of-date files to git directory"
    os.chdir(str(examplePath))
    common = [str(b) for b in book if not b.is_dir()]
    match, mismatch, errors = cmpfiles(str(examplePath), str(gitpath), common, False)
    with Path("update.bat").open('w') as outfile:
        # outfile.write("\n" + ruler("match"))
        # outfile.write(pformat(match))
        # outfile.write("\n" + ruler("mismatch"))
        # outfile.write(pformat(mismatch))
        # outfile.write("\n" + ruler("errors"))
        # outfile.write(pformat(errors))
        head("files to update")
        for f in mismatch:
            outfile.write("copy {} {}\{}\n".format(f, str(gitpath), f))
            print(f)

    head("odd files")
    for f in mismatch:
        if not (f.endswith(".java") or
                f.endswith(".py") or
                f.endswith(".cpp") or
                f.endswith("build.xml")):
            print(f)


def comment_header(lines):
    if lines[0][0] == "#":
        cmt = "#"
    else:
        cmt = "//"
    result = []
    for line in lines:
        if line.startswith(cmt):
            result.append(line)
        else:
            return result


@CmdLine('t')
def tops_file():
    "Create file tops.txt with tops of all book code files"
    os.chdir(str(examplePath))
    candidates = list(Path(".").rglob("*.java")) + list(Path(".").rglob("*.py")) + list(Path(".").rglob("*.cpp"))
    with (examplePath/"tops.txt").open('w') as tops:
        for c in candidates:
            lines = c.open().readlines()
            if lines[0].startswith("//:") or lines[0].startswith("#:"):
                tops.write(ruler(c))
                for line in lines[:8]:
                    tops.write(line)
                # tops.write(ruler())
                # for line in comment_header(lines):
                #     tops.write(line)
                tops.write(ruler())
                for line in insert_copyright(lines)[:9]:
                    tops.write(line)
                tops.write("\n")

def insert_copyright(lines):
    if "Copyright.txt" in lines[1]:
        return lines
    if lines[0][0] == "#":
        cmt = "#"
    else:
        cmt = "//"
    return [lines[0], cmt + " ©2015 MindView LLC: see Copyright.txt\n"] + lines[1:]

@CmdLine('c')
def add_copyright():
    "Add copyright line to all book code files"
    os.chdir(str(examplePath))
    candidates = list(Path(".").rglob("*.java")) + list(Path(".").rglob("*.py")) + list(Path(".").rglob("*.cpp"))
    for c in candidates:
        with c.open() as code:
            lines = code.readlines()
        if lines[0].startswith("//:") or lines[0].startswith("#:"):
            if "Copyright.txt" not in lines[1]:
                copyrighted = insert_copyright(lines)
                with c.open('w') as crighted:
                    crighted.writelines(copyrighted)

if __name__ == '__main__':
    CmdLine.run()
