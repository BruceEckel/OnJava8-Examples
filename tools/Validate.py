#! py -3
"""
Run all (possible) java files and capture output and errors. Integrate into /* Output:
"""
TODO = """
- strip_last_blank_line -> cleanfiles (also strip trailing spaces on each line, remove copyright notice)

- 1st and last 10 lines, with ... in between? {OutputFirstAndLast: 10 Lines}

- {NoOutput}

- format __newOutput() for line width using textwrap
"""
from pathlib import Path
import pprint
import textwrap
import os, sys, re
import difflib
from collections import defaultdict
from itertools import chain
from betools import CmdLine, visitDir, ruler, head

examplePath = Path(r"C:\Users\Bruce\Dropbox\__TIJ4-ebook\ExtractedExamples")

maindef = re.compile("public\s+static\s+void\s+main")

###############################################################################
#  Create Powershell Script to run all programs and capture the output
###############################################################################
# Powershell: https://gist.github.com/diyan/2850866
# http://marxsoftware.blogspot.com/2008/02/windows-powershell-and-java.html

class Flags:
    discard = ["{Requires:"]
    def __init__(self, lines):
        self.flaglines = []
        for line in lines:
            if line.startswith("//"):
                self.flaglines.append(line)
            else:
                break # Only capture top block
        self.flaglines = [line for line in self.flaglines if line.startswith("// {")]
        self.flaglines = [line for line in self.flaglines if not [d for d in Flags.discard if d in line]]
        self.flags = dict()
        for flag in self.flaglines:
            flag = flag[flag.index("{") + 1 : flag.rfind("}")].strip()
            if ":" in flag:
                fl, arg = flag.split(":")
                fl = fl.strip()
                arg = arg.strip()
                self.flags[fl] = arg
            else:
                self.flags[flag] = None # Make an entry, but no arg

    def __contains__(self, elt):
        return elt in self.flags

    def __repr__(self):
        return pprint.pformat(self.flags)

    def  __len__(self):
        return len(self.flaglines)

    def keys(self):
        return {key for key in self.flags.keys()}

    def values(self):
        return str(self.flags.values())

    def jvm_args(self):
        return self.flags["JVMArgs"] + " " if "JVMArgs" in self.flags else ""

    def cmd_args(self):
        return " " + self.flags["Args"] if "Args" in self.flags else ""



class RunnableFile:

    def __init__(self, path, body):
        self.path = path
        self.name = path.stem
        self.relative = path.relative_to(RunFiles.base)
        self.body = body
        self.lines = body.splitlines()
        self.flags = Flags(self.lines)
        self._package = ""
        for line in self.lines:
            if line.startswith("package "):
                self._package = line.split("package ")[1].strip()[:-1]
                if self._package.replace('.', '/') not in self.lines[0]:
                    self._package = ""
        self.main = self.name
        if "main" in self.flags:
            self.main = self.flags.flags["main"]

    def __contains__(self, elt):
        return elt in self.flags

    def __repr__(self):
        return str(self.relative) #+ ": " + self.name

    def package(self):
        return self._package + '.' if self._package else ''

    def rundir(self):
        "Directory to change to before running the command"
        return self.path.parent

    def javaArguments(self):
        return self.flags.jvm_args() + self.package() + self.main + self.flags.cmd_args()

    def runCommand(self):
        return "java " + self.javaArguments()



class RunFiles:
    # RunFirst is temporary?
    not_runnable = ["ValidateByHand", "TimeOutDuringTesting", "CompileTimeError", 'TimeOut', 'RunFirst']
    skip_dirs = ["gui", "swt"]

    base = Path(".")
    def __init__(self):
        self.runFiles = []
        for java in RunFiles.base.rglob("*.java"):
            with java.open() as code:
                body = code.read()
                if maindef.search(body):
                    self.runFiles.append(RunnableFile(java, body))
        allMains = set(self.runFiles)
        self.runFiles = [f for f in self.runFiles if not [nr for nr in self.not_runnable if nr in f]]
        self.runFiles = [f for f in self.runFiles if not [nd for nd in self.skip_dirs if nd in f.path.parts[0]]]
        testedMains = set(self.runFiles)
        self.untested = allMains.difference(testedMains)
        with (RunFiles.base / "Untested.txt").open('w') as utf:
            utf.write(pprint.pformat(self.untested))

    def allFlagKeys(self):
        flagkeys = set()
        for f in [f for f in self.runFiles if f.flags]:
            [flagkeys.add(key) for key in f.flags.keys()]
        return flagkeys

    def allFlagValues(self):
        return [f.flags.values() for f in self.runFiles if f.flags if f.flags.values()]

    def allFlags(self):
        return [f.flags for f in self.runFiles if f.flags]

    def runCommands(self):
        return [f.runCommand() for f in self.runFiles]

    def runData(self):
        return "\n".join(["[{}] {}".format(f.rundir(), f.runCommand()) for f in self.runFiles])

    def __iter__(self):
        return iter(self.runFiles)


@CmdLine("p")
def createPowershellScript():
    """
    Create Powershell Script to run all programs and capture the output
    """
    os.chdir(str(examplePath))
    runFiles = RunFiles()
    startDir = os.getcwd()
    with open("runall.ps1", 'w') as ps:
        ps.write('''Start-Process -FilePath "ant" -ArgumentList "build" -NoNewWindow -Wait \n\n''')
        for rf in runFiles:
            with visitDir(rf.rundir()):
                argquote = '"'
                if '"' in rf.javaArguments() or '$' in rf.javaArguments():
                    argquote = "'"
                pstext = """\
                Start-Process
                -FilePath "java.exe"
                -ArgumentList {}{}{}
                -NoNewWindow
                -RedirectStandardOutput {}-output.txt
                -RedirectStandardError {}-erroroutput.txt
                """.format(argquote, rf.javaArguments(), argquote, rf.name, rf.name)
                pstext = textwrap.dedent(pstext).replace('\n', ' ')
                if "ThrowsException" in rf:
                    pstext += " -Wait\n"
                    pstext += "Add-Content {}-erroroutput.txt '---[ Exception is Expected ]---'".format(rf.name)
                ps.write("cd {}\n".format(os.getcwd()))
                ps.write(pstext + "\n")
                ps.write('Write-Host [{}] {}\n'.format(rf.relative, rf.name))
                ps.write("cd {}\n\n".format(startDir))


###############################################################################
#  Attach Output to Java Files
###############################################################################

# Tags:
# (XX% Match)
# (Sample)
# (First XX Lines)

class OutputTags:
    tagfind = re.compile("\(.*?\)")

    def __init__(self, javaFilePath):
        self.javaFilePath = javaFilePath
        self.has_output = False
        self.tags = []
        with self.javaFilePath.open() as code:
            for line in code.readlines():
                if "/* Output:" in line:
                    self.has_output = True
                    for tag in self.tagfind.findall(line):
                        self.tags.append(tag[1:-1])

    def __repr__(self):
        return "{}\n{}\n".format(self.javaFilePath, pprint.pformat(self.tags))

    def __bool__(self):
        return bool(self.has_output and self.tags)

    def __iter__(self):
        return iter(self.tags)



class Result:
    """
    Finds result files, compares to output stored in comments at ends of Java files.

    If there's output, and no flag that says otherwise, add /* Output:

    """
    excludefiles = [
        "object/ShowProperties.java",
    ]
    oldOutput = re.compile("/* Output:.*?\n(.*)\n\*///:~(?s)")
    @staticmethod
    def create(javaFilePath):
        "Factory: If the output files exist and are not both empty, produce Result object"
        for p in Result.excludefiles:
            if javaFilePath.match(p):
                return None
        outfile = javaFilePath.with_name(javaFilePath.stem + "-output.txt")
        errfile = javaFilePath.with_name(javaFilePath.stem + "-erroroutput.txt")
        if outfile.exists():
            assert errfile.exists()
            with javaFilePath.open() as jf:
                if "{CheckOutputByHand}" in jf.read():
                    return None
            if outfile.stat().st_size or errfile.stat().st_size:
                return Result(javaFilePath, outfile, errfile)
        return None

    def __init__(self, javaFilePath, outfile, errfile):
        self.javaFilePath = javaFilePath
        self.outFilePath = outfile
        self.errFilePath = errfile
        self.output_tags = OutputTags(javaFilePath)
        self.old_output = self.__oldOutput()
        self.new_output = self.__newOutput()
        self.difference = difflib.SequenceMatcher(None, self.old_output, self.new_output).ratio()

    def __oldOutput(self):
        with self.javaFilePath.open() as code:
            body = code.read()
            result = self.oldOutput.findall(body)
            return "\n".join(result).rstrip()

    def __newOutput(self):
        result =""
        with self.outFilePath.open() as f:
            out = f.read().strip()
            if out:
                result += out + "\n"
        with self.errFilePath.open() as f:
            err = f.read().strip()
            if err:
                result += err
        return result.rstrip()

    def __repr__(self):
        result = "\n" + ruler(self.javaFilePath, "=") +"\n"
        with self.javaFilePath.open() as jf:
            for line in jf.readlines():
                if "/* Output:" in line:
                    result += line + "\n"
                    break
            else:
                result += "no prior /* Output:\n"
        if self.old_output:
            result += ruler("Previous Output")
            result += self.old_output + "\n\n"
        else:
            result += ruler("No Previous Output")
        result += ruler("New Output")
        result += self.new_output + "\n\n"
        result += ruler("Difference: {}".format(self.difference), '+') + "\n"

        if self.difference == 1.0: return '.'

        return result

    def appendOutputFiles(self):
        if not self.output_tags.has_output: # no /* Output: at all
            with self.javaFilePath.open() as jf:
                code = jf.read()
                lines = code.splitlines()
                while lines[-1].strip() is "":
                    lines.pop()
                assert lines[-1].rstrip() == "} ///:~"
                lines[-1] = "} /* Output:"
                lines.append(self.new_output)
                lines.append("*///:~")
                result = "\n".join(lines) + "\n"
            with self.javaFilePath.open("w") as jf:
                jf.write(result)
            return result
        else:
            print("{} already has Output!".format(self.javaFilePath))
            sys.exit()


@CmdLine("d")
def discoverOutputTags():
    """
    Discover 'Output:' tags
    """
    results = [r for r in [Result.create(jfp) for jfp in RunFiles.base.rglob("*.java")] if r]
    assert len(results), "Must run runall.ps1 first"
    tagd = defaultdict(list)
    for tagged in [r for r in [Result.create(jfp) for jfp in RunFiles.base.rglob("*.java")]
                   if r and r.output_tags]:
        for tag in tagged.output_tags:
            tagd[tag].append(str(tagged.javaFilePath))
    pprint.pprint(tagd)


@CmdLine("f")
def fillInUnexcludedOutput():
    """
    Find the files that aren't explicitly excluded AND have no 'Output:'. Show them and their output.
    """
    results = [r for r in [Result.create(jfp) for jfp in RunFiles.base.rglob("*.java")] if r]
    assert len(results), "Must run runall.ps1 first"
    nonexcluded = []
    for r in results:
        if r.output_tags:
            for t in r.output_tags:
                if t in RunFiles.not_runnable:
                    break
        else:
            if not r.old_output:
                nonexcluded.append(r)
    for ne in nonexcluded:
        print(ne)


@CmdLine("e")
def findExceptionsFromRun():
    """
    Put the exceptions produced by runall.ps1 into errors.txt
    """
    errors = [r for r in [Result.create(jfp) for jfp in RunFiles.base.rglob("*.java")]
              if r and r.errFilePath.stat().st_size]
    assert len(errors), "Must run runall.ps1 first"
    with (examplePath / "errors.txt").open('w') as errors_txt:
        for e in errors:
            with e.errFilePath.open() as errfile:
                errors_txt.write("\n" + ruler(e.errFilePath, width=80))
                errors_txt.write(errfile.read())
                errors_txt.write("<-:->")
    showProblemErrors()

@CmdLine("b")
def showProblemErrors():
    """
    Show unexpected errors inside errors.txt
    """
    with (examplePath / "errors.txt").open() as errors_txt:
        for err in errors_txt.read().split("<-:->"):
            if "_[ logging\\" in err:
                continue
            if "LoggingException" in err:
                continue
            if "---[ Exception is Expected ]---" in err:
                continue
            print(err)

# @CmdLine('w')
def findAndEditAllCompileTimeError():
    "Find all files tagged with {CompileTimeError} and edit them"
    os.chdir(str(examplePath))
    with open("CompileTimeError.bat", 'w') as cte:
        cte.write("subl ")
        for j in Path(".").rglob("*.java"):
            code = j.open().read()
            if "//: reusing/Lisa.java" in code:
                continue # Checked
            if "//: initialization/OverloadingVarargs2.java" in code:
                continue # Checked
            if "//: generics/UseList.java" in code:
                continue # Checked
            if "//: generics/NonCovariantGenerics.java" in code:
                continue # Checked
            if "//: generics/MultipleInterfaceVariants.java" in code:
                continue # Checked
            if "//: generics/Manipulation.java" in code:
                continue # Checked
            if "generics/HijackedInterface.java" in code:
                continue # Checked
            if "//: generics/Erased.java" in code:
                continue # Checked
            if "{CompileTimeError}" in code:
                cte.write(str(j) + " ")
                print(j)
    os.system("CompileTimeError.bat")

@CmdLine("a")
def editAllJavaFiles():
    """
    Edit all Java files in this directory and beneath
    """
    for java in Path(".").rglob("*.java"):
        os.system("ed {}".format(java))


@CmdLine("s", num_args="+")
def attachToSingleFile():
    """
    Attach output to selected file(s).
    """
    for jfp in sys.argv[2:]:
        javafilepath = Path(jfp)
        if not javafilepath.exists():
            print("Error: cannot find {}".format(javafilepath))
            sys.exit(1)
        result = Result.create(javafilepath)
        if not result:
            print("Error: no output or error files for {}".format(javafilepath))
            sys.exit(1)
        print(result.appendOutputFiles())


@CmdLine("m")
def findAllMains():
    """
    Find all main()s in java files, using re
    """
    for jf in Path('.').rglob("*.java"):
        with jf.open() as java:
            code = java.read()
            for m in maindef.findall(code):
                head(jf)
                print(m)

def createChecklist():
    """
    Make checklist of chapters and appendices
    """
    from bs4 import BeautifulSoup
    import codecs
    os.chdir(str(examplePath / ".."))

    with codecs.open(str(Path("TIJDirectorsCut.htm")),'r', encoding='utf-8', errors='ignore') as book:
        soup = BeautifulSoup(book.read())
        with Path("Checklist-generated.txt").open('wb') as checklist:
            for h1 in soup.find_all("h1"):
                text = " ".join(h1.text.split())
                checklist.write(codecs.encode(text + "\n"))

@CmdLine('w')
def checkWidth():
    "Check line width on code examples"
    os.chdir(str(examplePath))
    for example in chain(Path(".").rglob("*.java"), Path(".").rglob("*.cpp"), Path(".").rglob("*.py")):
        displayed = False
        with example.open() as code:
            for n, line in enumerate(code.readlines()):
                if "///:~" in line or "/* Output:" in line:
                    break
                if len(line) > 60:
                    if not displayed:
                        #print(str(example).replace("\\", "/"))
                        displayed = True
                        os.system("ed {}:{}".format(str(example), n+1))
                    # print("{} : {}".format(n, len(line)))

@CmdLine("c")
def clean_files():
    "Strip trainling blank lines in all Java files -- prep for reintigration into book"
    os.chdir(str(examplePath))
    for j in Path(".").rglob("*.java"):
        print(str(j), end=" ")
        with j.open() as f:
            code = f.read().strip()
        with j.open('w') as w:
            w.write(code)


if __name__ == '__main__': CmdLine.run()
