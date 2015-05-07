#! py -3
from pathlib import Path

base = Path("ExtractedExamples")

def extract(line, token):
    tag = line.split(token)[1]
    return tag.split("}")[0].strip()

class RunnableFile:
    def __init__(self, path, body):
        self.path = path
        self.name = path.stem
        self.relative = path.relative_to(base)
        self.body = body
        self.lines = body.splitlines()
        self.package = ""
        self.args = ""
        self.jvm_args = ""
        for line in self.lines:
            if "{Args:" in line:
                self.args = extract(line,"{Args:")
            if "{JVMArgs:" in line:
                self.jvm_args = extract(line,"{JVMArgs:") + " "
            if line.startswith("package "):
                self.package = line.split("package ")[1].strip()[:-1]
                if self.package.replace('.', '/') not in self.lines[0]:
                    self.package = ""

    def __repr__(self):
        return str(self.relative) + "\n" #+ self.header

    def finaldot(self):
        return '.' if self.package else ''

    def runCommand(self):
        return "java " + self.jvm_args + self.package + self.finaldot() + self.name + " " + self.args

runFiles = []

for java in base.glob("**/*.java"):
    with java.open() as code:
        body = code.read()
        if "static void main(String[] args)" in body:
            runFiles.append(RunnableFile(java, body))



[print("JVM:", f.jvm_args) for f in runFiles if f.jvm_args]
[print("Args:", f.args) for f in runFiles if f.args]
[print("Pkg:", f.package) for f in runFiles if f.package]
[print(f.runCommand()) for f in runFiles]
