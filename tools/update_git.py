#! Py -3
"Tools for updating Github repository"
from pathlib import Path
from filecmp import cmpfiles
from filecmp import dircmp
import sys, os
from sortedcontainers import SortedSet
from betools import *
from pprint import *

gitpath = Path(r"C:\Users\Bruce\Documents\GitHub\Dreaming-in-Java")
assert gitpath.is_dir()
examplePath = Path(r"C:\Users\Bruce\Dropbox\__Dreaming-in-Java\ExtractedExamples")
assert examplePath.is_dir()

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
    "Write togit.bat to copy missing files to git directory"
    exclude = ["Book.txt", "Git.txt", "togit.bat"]
    os.chdir(str(examplePath))
    if list(examplePath.rglob("*.class")):
        print("Run Ant clean First")
        sys.exit(1)
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

def print_diff_files(dcmp):
    for name in dcmp.diff_files:
        print("diff_file %s found in %s and %s" % (name, dcmp.left, dcmp.right))
    for sub_dcmp in dcmp.subdirs.values():
        print_diff_files(sub_dcmp)


@CmdLine('d')
def diff():
    "Show differences with git directory"
    os.chdir(str(examplePath))
    os.system("diff -q -r . " + str(gitpath))


@CmdLine('u')
def update_to_git():
    "Write update.bat to copy out-of-date files to git directory"
    os.chdir(str(examplePath))
    os.system("diff -q -r . " + str(gitpath) + " > update.bat")
    lines = [line[len("Files "):-(len("differ") + 1)] for line in open("update.bat").readlines() if line.startswith("Files ")]
    args = [line.split(" and ") for line in lines]
    args = [(str(Path(arg[0].strip())), str(Path(arg[1].strip()))) for arg in args]
    for arg in args:
        if not arg[0].endswith(".java"):
            continue
        if "Copyright.txt" not in open(arg[0]).readlines()[1]:
            print("Missing copyright in {} \nrun g -c".format(arg[0]))
            sys.exit(-1)
    lines = ["copy {} {}".format(arg[0], arg[1]) for arg in args]
    with open("update.bat", 'w') as update:
        update.write("\n".join(lines))


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
