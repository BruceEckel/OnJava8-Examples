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




class RunnableFile:

    def __init__(self, path, body):
        self.path = path
        self.name = path.stem
        self.relative = path.relative_to(RunFiles.base)
        self.body = body
        self.lines = body.splitlines()
        self.flags = Flags(self.lines)
        self.package = ""
        # self.args = ""
        # self.jvm_args = ""
        # self.brace_cmds = ""
        for line in self.lines:
            # if "{Args:" in line:
            #     self.args = extract(line,"{Args:")
            # if "{JVMArgs:" in line:
            #     self.jvm_args = extract(line,"{JVMArgs:") + " "
            if line.startswith("package "):
                self.package = line.split("package ")[1].strip()[:-1]
                if self.package.replace('.', '/') not in self.lines[0]:
                    self.package = ""
            # if line.startswith("// {"):
            #     self.brace_cmds += line + "\n"

    def __repr__(self):
        return str(self.relative) + "\n" #+ self.header

    def finaldot(self):
        return '.' if self.package else ''

    def runCommand(self):
        return "java " + self.jvm_args + self.package + self.finaldot() + self.name + " " + self.args


class RunFiles:
    base = Path("ExtractedExamples")
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
    runFiles = RunFiles()
    pprint.pprint(runFiles.allFlags())
    pprint.pprint(runFiles.runCommands())


