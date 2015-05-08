#! py -3
from pathlib import Path
import pprint
import os, sys
from contextlib import contextmanager

# Powershell: https://gist.github.com/diyan/2850866

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
        return str(self.relative) + ": " + self.name

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
        self.runFiles = [f for f in self.runFiles if not [nr for nr in self.not_runnable if nr in f]]
        self.runFiles = [f for f in self.runFiles if not [nd for nd in self.skip_dirs if nd in f.path.parts[0]]]

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


if __name__ == '__main__':
    assert Path.cwd().stem is "ExtractedExamples"
    runFiles = RunFiles()
    startDir = os.getcwd()
    # [print(f, f.flags) for f in runFiles]
    # sys.exit()
    with open("runall.ps1", 'w') as ps:
        for rf in runFiles:
            with visitDir(rf.rundir()):
                ps.write("cd {}\n".format(os.getcwd()))
                ps.write('Start-Process -FilePath "java.exe" -ArgumentList "{}" -NoNewWindow -RedirectStandardOutput {}-output.txt -RedirectStandardError {}-erroroutput.txt \n'.format(rf.javaArguments(), rf.name, rf.name))
                ps.write('Write-Host [{}] {}\n'.format(rf.relative, rf.name))
                ps.write("cd {}\n".format(startDir))

    # pprint.pprint(runFiles.runCommands())

