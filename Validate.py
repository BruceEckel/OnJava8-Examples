#! py -3
"""
Run all (possible) java files and capture output and errors
"""
from pathlib import Path
import pprint
import textwrap
import os, sys, re
from contextlib import contextmanager
import argparse

parser = argparse.ArgumentParser()
parser.add_argument("-p", "--powershell", action='store_true',
    help="Create Powershell Script to run all programs and capture the output")
parser.add_argument("-c", "--cleanoutput", action='store_true',
    help="Clean output files by removing empty ones, and reporting file sizes")

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


@contextmanager
def visitDir(d):
    d = str(d)
    old = os.getcwd()
    os.chdir(d)
    yield d
    os.chdir(old)

def createPowershellScript():
    assert Path.cwd().stem is "ExtractedExamples"
    runFiles = RunFiles()
    startDir = os.getcwd()
    # [print(f, f.flags) for f in runFiles]
    # sys.exit()
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

    # pprint.pprint(runFiles.runCommands())

###############################################################################
#  Attach Output to Java Files
###############################################################################

class Result:
    """
    Finds result files, compares to output stored in comments at ends of Java files.
    """
    oldOutput = re.compile("/* Output:.*?\n(.*)\n\*///:~(?s)")
    @staticmethod
    def create(javaFilePath):
        "Factory: If the output files exist and are not both empty, produce Result object"
        outfile = javaFilePath.with_name(javaFilePath.stem + "-output.txt")
        errfile = javaFilePath.with_name(javaFilePath.stem + "-erroroutput.txt")
        if outfile.exists():
            assert errfile.exists()
        else:
            return None
        if outfile.stat().st_size or errfile.stat().st_size:
            return Result(javaFilePath, outfile, errfile)
        else:
            return None

    def __init__(self, javaFilePath, outfile, errfile):
        self.javaFilePath = javaFilePath
        self.outFilePath = outfile
        self.outFileSize = self.outFilePath.stat().st_size
        self.errFilePath = errfile
        self.errFileSize = self.errFilePath.stat().st_size
        self.old_output = self.__oldOutput()
        self.new_output = self.__newOutput()

    def __oldOutput(self):
        with self.javaFilePath.open() as code:
            result = self.oldOutput.findall(code.read())
            return "\n".join(result).rstrip()

    def __newOutput(self):
        result =""
        with self.outFilePath.open() as f:
            result += f.read() + "\n"
        with self.errFilePath.open() as f:
            result += f.read()
        return result.rstrip()

    def __repr__(self):
        def center(arg, sep="_"):
            return "[ {} ]".format(str(arg)).center(50, sep) + "\n"
        result = "\n" + center(self.javaFilePath, "=") +\
        str(self.outFilePath) + " " + str(self.outFileSize) + "\n" +\
        str(self.errFilePath) + " " + str(self.errFileSize) + "\n"
        if self.old_output:
            result += center("Previous Output")
            result += self.old_output + "\n"
        else:
            result += center("No Previous Output")
        result += center("New Output")
        result += self.new_output + "\n"

        return result



def checkAndCleanResults():
    print("checkAndCleanResults()")
    assert Path.cwd().stem is "ExtractedExamples"
    results = [r for r in [Result.create(jfp) for jfp in RunFiles.base.rglob("*.java")] if r]
    pprint.pprint(results)




###############################################################################
#  Main execution logic
###############################################################################

def default():
    checkAndCleanResults()

if __name__ == '__main__':
    args = parser.parse_args()

    if not any(vars(args).values()): default()

    if args.powershell:
        createPowershellScript()

    if args.cleanoutput:
        checkAndCleanResults()
