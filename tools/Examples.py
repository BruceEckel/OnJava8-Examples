#! py -3
"""
Extract code examples from TIJ4 Refreshed. Extracts from plain text file
"""
from pathlib import Path
import sys, os
import re
import argparse
import shutil
import pprint
destination = Path('.') / "ExtractedExamples"
sourceText = Path('.') / "TIJDirectorsCut.txt"
github = Path(r'C:\Users\Bruce\Documents\GitHub\TIJ-Directors-Cut')

startBuild = """\
<?xml version="1.0" ?>

<project default="run">
  <property name="chapter" value="%s"/>
  <property name="excludedfiles" value="%s"/>
  <import file="../Ant-Common.xml"/>

  <target name="run" description="Compile and run" depends="build">
    <touch file="failures"/>
"""

endBuild = """\
    <delete file="failures"/>
  </target>

</project>
"""

parser = argparse.ArgumentParser()
parser.add_argument("-e", "--extract", action='store_true',
    help="Extract examples from TIJDirectorsCut.txt")
parser.add_argument("-x", "--clean", action='store_true',
    help="Remove ExtractedExamples directory")
parser.add_argument("-c", "--compare", action='store_true',
    help="Compare files from Github repository to extracted examples")
parser.add_argument("-a", "--ant", action='store_true',
    help="Copy ant build files from Github repository to extracted examples")
parser.add_argument("-m", "--makeant", action='store_true',
    help="Make ant files that don't exist")


def extractExamples():
    if not destination.exists():
        destination.mkdir()
    if not sourceText.exists():
        print("Cannot find", sourceText)
        sys.exit()
    with sourceText.open("rb") as book:
        text = book.read().decode("utf-8", "ignore")
        for listing in re.findall("^//:.*?///:~", text, re.DOTALL | re.MULTILINE):
            title = listing.splitlines()[0]
            if "//: as a special marker" in title:
                continue
            title = title.split()[1]
            print(title)
            target = destination / Path(title)
            if not target.parent.exists():
                target.parent.mkdir(parents=True)
            with target.open("w", newline='') as codeListing:
                codeListing.writelines(listing)


def clean():
    print("clean")
    if destination.exists():
        shutil.rmtree(str(destination))


def compareWithGithub():
    leader = len(str(github)) + 1
    githubfiles = [str(file)[leader:] for file in github.glob("**/*")]
    githubfiles = [ghf for ghf in githubfiles if not ghf.startswith(".git")]
    duplicates = { ghf for ghf in githubfiles if githubfiles.count(ghf) > 1 }
    if duplicates:
        print("duplicates = ", duplicates)

    leader2 = len(str(destination)) + 1
    destfiles = [str(file)[leader2:] for file in destination.glob("**/*")]
    duplicates = { ghf for ghf in destfiles if destfiles.count(ghf) > 1 }
    if duplicates:
        print("duplicates = ", duplicates)

    githubfiles = set(githubfiles)
    destfiles = set(destfiles)

    print("in githubfiles but not destfiles:")
    for f in githubfiles.difference(destfiles):
        print("\t", f)

    print("#" * 80)

    print("in destfiles but not githubfiles:")
    for f in destfiles.difference(githubfiles):
        print("\t", f)


def githubDirs():
    leader = len(str(github)) + 1
    buildfiles = [str(file)[leader:] for file in github.glob("**/build.xml")]
    return {str((github / f).parent)[leader:] for f in buildfiles}


def destDirs(pattern="**"):
    leader = len(str(destination)) + 1
    return {str(file)[leader:] for file in destination.glob(pattern)}


def copyAntBuildFiles():
    for common in githubDirs().intersection(destDirs()):
        print("->", common)
        build = github / common / "build.xml"
        target = destination / common
        shutil.copy(str(build), str(target))
    shutil.copy(str(github / "Ant-Common.xml"), str(destination))


class CodeFileOptions(object):
    """docstring for CodeFileOptions"""
    def __init__(self, codeFile):
        self.codeFile = codeFile

        self.cmdargs = None
        if "{Args:" in self.codeFile.code:
            for line in self.codeFile.lines:
                if "{Args:" in line:
                    self.cmdargs = line.split("{Args:")[1].strip()[:-1]

        self.runbyhand = "{RunByHand}" in self.codeFile.code

        self.exclude = None
        if "{CompileTimeError}" in self.codeFile.code:
            self.exclude = self.codeFile.name + ".java"
            if self.codeFile.subdirs:
                self.exclude = '/'.join(self.codeFile.subdirs) + '/' + self.exclude
            print(self.exclude)

        self.throwsexception = "{ThrowsException}" in self.codeFile.code

class CodeFile:
    def __init__(self, javaFile, chapterDir):
        self.chapter_dir = chapterDir
        self.java_file = javaFile
        self.subdirs = str(javaFile.parent).split("\\")[2:]
        with javaFile.open() as j:
            self.code = j.read()
        self.lines = self.code.splitlines()
        self.main = None
        if "public static void main" in self.code:
            self.main = True
        self.package = None
        if "package " in self.code:
            for line in self.lines:
                if line.startswith("package ") and line.strip().endswith(";"):
                    self.package = line
                    break
        self.tagLine = self.lines[0][4:]
        self.relpath = '../' + '/'.join(self.tagLine.split('/')[:-1])
        self.name = javaFile.name.split('.')[0]
        self.options = CodeFileOptions(self)
        # self.cmdargs = None
        # if "{Args:" in self.code:
        #     for line in self.lines:
        #         if "{Args:" in line:
        #             self.cmdargs = line.split("{Args:")[1].strip()[:-1]
        # self.runbyhand = "{RunByHand}" in self.code
        # self.exclude = None
        # if "{CompileTimeError}" in self.code:
        #     self.exclude = self.name + ".java"
        #     if self.subdirs:
        #         self.exclude = '/'.join(self.subdirs) + '/' + self.exclude
        #     print(self.exclude)
        # self.throwsexception = "{ThrowsException}" in self.code

    def run_command(self):
        if not self.main:
            return ""
        if self.package:
            if self.options.cmdargs:
               return """    <jrun cls="%s" dirpath="%s" arguments='%s'/>\n""" % (self.packageName() + '.' + self.name, self.relpath, self.options.cmdargs)
            else:
               return """    <jrun cls="%s" dirpath="%s"/>\n""" % (self.packageName() + '.' + self.name, self.relpath)
        if self.options.cmdargs:
            return """    <jrun cls="%s" arguments='%s'/>\n""" % (self.name, self.options.cmdargs)
        else:
            return """    <jrun cls="%s"/>\n""" % self.name

    def __repr__(self):
        result = self.tagLine
        if self.package:
            result += "\n" + self.package
        result += "\n"
        return result

    def packageName(self):
        return self.package.split()[1][:-1]

    def checkPackage(self):
        if not self.package:
            return True
        path = '.'.join(self.tagLine.split('/')[:-1])
        packagePath = self.packageName()
        return path == packagePath


class Chapter:
    def __init__(self, dir):
        self.dir = dir
        self.code_files = [CodeFile(javaFile, dir) for javaFile in dir.glob("**/*.java")]
        self.excludes = [cf.options.exclude for cf in self.code_files if cf.options.exclude]

    def __repr__(self):
        result = "-" * 80
        result += "\n" + str(self.dir) + "\n"
        result += "-" * 80
        result += "\n"
        for cf in self.code_files:
            result += str(cf.name) + "\n"
        return result

    def checkPackages(self):
        for cf in self.code_files:
            if not cf.checkPackage():
                print("BAD PACKAGE")
                print("\t", cf.tagLine)
                print("\t", cf.package)
                print("\n".join(cf.lines))

    def makeBuildFile(self):
        buildFile = startBuild % (self.dir.name, " ".join(self.excludes))
        for cf in self.code_files:
            if any([cf.name + ".java" in f for f in self.excludes]) or cf.options.runbyhand:
                continue
            buildFile += cf.run_command()
        buildFile += endBuild
        with (self.dir / "build.xml").open("w") as buildxml:
            buildxml.write(buildFile)


def createAntFiles():
    chapters = [Chapter(fd) for fd in destination.glob("*") if fd.is_dir() if not (fd / "build.xml").exists()]
    for chapter in chapters:
        chapter.checkPackages()
        chapter.makeBuildFile()


def default():
    clean()
    extractExamples()
    copyAntBuildFiles()
    createAntFiles()
    os.chdir("ExtractedExamples")


if __name__ == '__main__':
    args = parser.parse_args()

    if not any(vars(args).values()): default()

    if args.extract:
        extractExamples()
        copyAntBuildFiles()
        createAntFiles()

    if args.compare:
        compareWithGithub()

    if args.ant:
        copyAntBuildFiles()

    if args.makeant:
        createAntFiles()

    if args.clean:
        clean()
