#! py -3
"""
Run all (possible) java files and capture output and errors

TODO: 1st and last 10 lines, with ... in between? {FirstAndLast: 10 Lines}

TODO: format __newOutput() for line width using textwrap
"""
from pathlib import Path
import pprint
import textwrap
import os, sys, re
import difflib
from collections import defaultdict
from betools import CmdLine, visitDir, ruler, head


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
            flag = flag[flag.index("{") + 1 : flag.index("}")].strip()
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
        return self.flags["JVMArgs"] if "JVMArgs" in self.flags else ""

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
        return self.flags.jvm_args() + self.package() + self.name + self.flags.cmd_args()

    def runCommand(self):
        return "java " + self.javaArguments()



class RunFiles:
    # RunFirst is temporary?
    not_runnable = ["RunByHand", "TimeOutDuringTesting", "CompileTimeError", 'TimeOut', 'RunFirst']
    skip_dirs = ["gui", "swt"]

    base = Path(".")
    def __init__(self):
        self.runFiles = []
        for java in RunFiles.base.rglob("*.java"):
            with java.open() as code:
                body = code.read()
                if "static void main(String[] args)" in body:
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


@CmdLine("p", "powershell")
def createPowershellScript():
    """
    Create Powershell Script to run all programs and capture the output
    """
    assert Path.cwd().stem is "ExtractedExamples"
    runFiles = RunFiles()
    startDir = os.getcwd()
    with open("runall.ps1", 'w') as ps:
        ps.write('''Start-Process -FilePath "ant" -ArgumentList "build" -NoNewWindow -Wait \n\n''')
        for rf in runFiles:
            with visitDir(rf.rundir()):
                pstext = """\
                Start-Process
                -FilePath "java.exe"
                -ArgumentList "{}"
                -NoNewWindow
                -RedirectStandardOutput {}-output.txt
                -RedirectStandardError {}-erroroutput.txt
                """.format(rf.javaArguments(), rf.name, rf.name)
                pstext = textwrap.dedent(pstext).replace('\n', ' ')
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
            result += f.read() + "\n"
        with self.errFilePath.open() as f:
            result += f.read()
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
            print("{} already has Output: tags:".format(self.javaFilePath))
            print(self.output_tags)
            sys.exit()


@CmdLine("d", "discover")
def discoverOutputTags():
    """
    Discover 'Output:' tags
    """
    results = [r for r in [Result.create(jfp) for jfp in RunFiles.base.rglob("*.java")] if r]
    assert len(results), "Must run runall.ps1 first"
    tagd = defaultdict(list)
    for tagged in [r for r in [Result.create(jfp) for jfp in RunFiles.base.rglob("*.java")] if r and r.output_tags]:
        for tag in tagged.output_tags:
            tagd[tag].append(str(tagged.javaFilePath))
    pprint.pprint(tagd)


@CmdLine("f", "fillin")
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


@CmdLine("e", "exceptions")
def findExceptionsFromRun():
    """
    Find all the exceptions produced by runall.ps1
    """
    errors = [r for r in [Result.create(jfp) for jfp in RunFiles.base.rglob("*.java")]
              if r and r.errFilePath.stat().st_size]
    assert len(errors), "Must run runall.ps1 first"
    for e in errors:
        with e.errFilePath.open() as errfile:
            head(e.errFilePath, "#")
            print(errfile.read())
            head()


@CmdLine("a", "editall")
def editAllJavaFiles():
    """
    Edit all Java files in this directory and beneath
    """
    with Path("editall.bat").open('w') as cmdfile:
        cmdfile.write("subl ")
        for java in Path(".").rglob("*.java"):
            cmdfile.write("{} ".format(java))


@CmdLine("s", "single", "+")
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



if __name__ == '__main__': CmdLine.run()
