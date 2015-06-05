#! py -3
"""
Append output and error files to Java files
"""
TODO = """
- 1st and last 10 lines, with ... in between? {OutputFirstAndLast: 10 Lines}

- {NoOutput}
"""
from pathlib import Path
import pprint
import textwrap
import os, sys, re
from itertools import chain
from betools import CmdLine, visitDir, ruler, head

maxlinewidth = 60
examplePath = Path(r"C:\Users\Bruce\Dropbox\__TIJ4-ebook\ExtractedExamples")

class JFile:
    def __init__(self, javaFilePath):
        with javaFilePath.open() as doc:
            self.code = doc.read()
            self.lines = self.code.splitlines()
    def has_output(self):
        return "} /* Output:" in self.code
    def output_line(self):
        for line in self.lines:
            if "} /* Output:" in line:
                return line

def newOutput(javaFilePath):
    outfile = javaFilePath.with_name(javaFilePath.stem + "-output.txt")
    errfile = javaFilePath.with_name(javaFilePath.stem + "-erroroutput.txt")
    result =""
    if outfile.exists():
        with outfile.open() as f:
            out = f.read().strip()
            if out:
                result += out + "\n"
    if errfile.exists():
        with errfile.open() as f:
            err = f.read().strip()
            if err:
                result += "--[ Error Output ]--\n"
                result += err
    return textwrap.wrap(result, width=maxlinewidth)


def appendOutputFiles(javaFilePath):
    jfile = JFile(javaFilePath)
    output = newOutput(javaFilePath)
    if not output:
        return
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

@CmdLine('t')
def outputTagTypes():
    """Show different output tag variations"""
    types = set()
    os.chdir(str(examplePath))
    for jfp in Path(".").rglob("*.java"):
        jf = JFile(jfp)
        if jf.has_output():
            types.add(jf.output_line())
    pprint.pprint(types)

@CmdLine('a')
def attachFiles():
    """Attach standard and error output to all files"""
    os.chdir(str(examplePath))
    for jfp in Path(".").rglob("*.java"):
        jf = JFile(jfp)
        if jf.has_output():
            head(jfp)
            print("\t{}".format(jf.output_line()))


if __name__ == '__main__': CmdLine.run()
