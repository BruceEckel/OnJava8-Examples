#! py -3
"""
Append output and error files to Java files
"""
TODO = """
- Add:
} /* Output: (None) *///:~
To files that produce no output.

- Test to make sure that None files indeed have no output

- Ambitious: Allow edits of "AttachedResults.txt" which are
then pulled back into the source files.

- 1st and last 10 lines, with ... in between? {OutputFirstAndLast: 10 Lines}


} /* Same output as RoShamBo2.java *///:~
} /* Output: (Same as RoShamBo2.java) *///:~

"""
from pathlib import Path
import pprint
import textwrap
import os, sys, re
from itertools import chain
from betools import CmdLine, visitDir, ruler, head

maxlinewidth = 59
examplePath = Path(r"C:\Users\Bruce\Dropbox\__TIJ4-ebook\ExtractedExamples")
maindef = re.compile("public\s+static\s+void\s+main")

class JFile:

    @staticmethod
    def with_main(javaFilePath):
        with javaFilePath.open() as doc:
            code = doc.read()
            if maindef.search(code):
                return JFile(code)
        return None

    def __init__(self, code):
        self.code = code
        self.lines = self.code.splitlines()

    def has_output(self):
        return "} /* Output:" in self.code

    def output_line(self):
        for line in self.lines:
            if "} /* Output:" in line:
                return line



def wrapOutput(output):
    lines = output.splitlines()
    result = []
    for line in lines:
        result += textwrap.wrap(line.rstrip(), width=maxlinewidth)
    return "\n".join(result)

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
    result = wrapOutput(result)
    if result:
        return result + "\n"
    return None


def appendOutputFiles(javaFilePath):
    jfile = JFile.with_main(javaFilePath)
    if jfile is None:
        return
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

@CmdLine('o')
def allOutputTagLines():
    """Shows all lines starting with } /*"""
    allvariations = set()
    os.chdir(str(examplePath))
    for jfp in Path(".").rglob("*.java"):
        with jfp.open() as code:
            for line in code.readlines():
                if line.startswith("} /*"):
                    allvariations.add(line)
    pprint.pprint(allvariations)


@CmdLine('t')
def outputTagTypes():
    """Show different output tag variations"""
    types = set()
    os.chdir(str(examplePath))
    for jfp in Path(".").rglob("*.java"):
        jf = JFile.with_main(jfp)
        if jf is None:
            continue
        if jf.has_output():
            types.add(jf.output_line())
    pprint.pprint(types)

@CmdLine('e')
def extractResults():
    """Test extraction of all results"""
    os.chdir(str(examplePath))
    with Path("AttachedResults.txt").open('w') as results:
        for jfp in Path(".").rglob("*.java"):
            output = newOutput(jfp)
            if output:
                results.write(ruler(jfp))
                jf = JFile.with_main(jfp)
                if jf is None:
                    continue
                outline = jf.output_line()
                if outline:
                    results.write(outline + "\n")
                results.write(output)
            else:
                results.write("[ No output for {} ]\n".format(jfp))

@CmdLine('n')
def noOutputFixup():
    """Attach no output lines to empty output files"""
    # Exclude gui and swt directories!
    os.chdir(str(examplePath))
    test = open("test.txt", 'w')
    for jfp in Path(".").rglob("*.java"):
        jf = JFile.with_main(jfp)
        if jf is None:
            continue
        if not jf.has_output():
            newcode = ""
            for line in jf.lines:
                if line.startswith("} ///:~"):
                    newcode += "} /* Output: (None) *///:~\n"
                else:
                    newcode += line + "\n"
            test.write(ruler(jfp))
            test.write(newcode)


@CmdLine('a')
def attachFiles():
    """Attach standard and error output to all files"""
    os.chdir(str(examplePath))
    for jfp in Path(".").rglob("*.java"):
        jf = JFile.with_main(jfp)
        if jf is None:
            continue
        if jf.has_output():
            head(jfp)
            print("\t{}".format(jf.output_line()))


if __name__ == '__main__': CmdLine.run()
