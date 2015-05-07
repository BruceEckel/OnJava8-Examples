#! py -3
from pathlib import Path
import pprint


# def extract(line, token):
#     tag = line.split(token)[1]
#     return tag.split("}")[0].strip()

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
        # self.args = ""
        # self.jvm_args = ""
        # self.brace_cmds = ""
        for line in self.lines:
            # if "{Args:" in line:
            #     self.args = extract(line,"{Args:")
            # if "{JVMArgs:" in line:
            #     self.jvm_args = extract(line,"{JVMArgs:") + " "
            if line.startswith("package "):
                self._package = line.split("package ")[1].strip()[:-1]
                if self._package.replace('.', '/') not in self.lines[0]:
                    self._package = ""
            # if line.startswith("// {"):
            #     self.brace_cmds += line + "\n"

    def __repr__(self):
        return str(self.relative) + "\n" #+ self.header

    def package(self):
        return self._package + '.' if self._package else ''

    def runCommand(self):
        return "[" + str(self.path.parent) + "] java " + self.flags.jvm_args() + self.package() + self.name + self.flags.cmd_args()


class RunFiles:
    base = Path(".")
    def __init__(self):
        self.runFiles = []
        for java in RunFiles.base.rglob("*.java"):
            with java.open() as code:
                body = code.read()
                if "static void main(String[] args)" in body:
                    self.runFiles.append(RunnableFile(java, body))

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

if __name__ == '__main__':
    assert Path.cwd().stem is "ExtractedExamples"
    runFiles = RunFiles()
    pprint.pprint(runFiles.allFlags())
    pprint.pprint(runFiles.runCommands())


