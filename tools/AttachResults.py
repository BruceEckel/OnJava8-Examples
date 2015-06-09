#! py -3
"""
Append output and error files to Java files
"""
TODO = """

- Display all files with less than 100% match (rather than putting percentage and "Sample" in)
  Rely on visual inspection of non-matching file?
  Have a list of files to exclude normally, and inspect ocasionally


- Test to make sure that None files indeed have no output

- Ambitious: Allow edits of "AttachedResults.txt" which are
then pulled back into the source files.

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


class JavaMain:
    max_output_length = 32 # lines beyond which we flag this
    maindef = re.compile("public\s+static\s+void\s+main")
    ellipses = ["[...]".center(maxlinewidth, '_')]

    class JFile:
        """Could just manipulate this, then write it at the end"""
        @staticmethod
        def with_main(javaFilePath):
            with javaFilePath.open() as doc:
                code = doc.read()
                if JavaMain.maindef.search(code) or "{Exec:" in code:
                    return JavaMain.JFile(javaFilePath, code)
            return None
        def __init__(self, javaFilePath, code):
            self.javaFilePath = javaFilePath
            self.code = code
            self.lines = self.code.splitlines()
            self.output_line = None
            for line in self.lines:
                if "} /* Output:" in line:
                    self.output_line = line
            self.newcode = ""

    @staticmethod
    def create(javaFilePath):
        j_file = JavaMain.JFile.with_main(javaFilePath)
        if j_file is None:
            return None
        if "{ValidateByHand}" in j_file.code:
            return None
        if "/* Output: (None) */" in j_file.code:
            return None
        if "/* Output: (Execute to see)" in j_file.code:
            return None
        outfile = javaFilePath.with_name(javaFilePath.stem + "-output.txt")
        errfile = javaFilePath.with_name(javaFilePath.stem + "-erroroutput.txt")
        if outfile.exists() or errfile.exists():
            return JavaMain(javaFilePath, j_file, outfile, errfile)
        return None

    def __init__(self, javaFilePath, j_file, outfile, errfile):
        self.javaFilePath = javaFilePath
        self.j_file = j_file
        self.outfile = outfile
        self.errfile = errfile
        self.first_and_last = None
        self.first_lines = None
        self.long_output = False

        ol = self.j_file.output_line
        if ol:
            if "(First and last" in ol:
                self.first_and_last = int(ol.partition("(First and last")[2].split()[0])
            elif "(First" in ol:
                self.first_lines = int(ol.partition("(First")[2].split()[0])

        result =""
        if outfile.exists():
            with outfile.open() as f:
                out = f.read().strip()
                if out:
                    if self.first_and_last:
                        lines = out.splitlines()
                        out = "\n".join(lines[:self.first_and_last] + JavaMain.ellipses + lines[-self.first_and_last:])
                    elif self.first_lines:
                        lines = out.splitlines()
                        out = "\n".join(lines[:self.first_lines] + JavaMain.ellipses)
                    result += out + "\n"
        if errfile.exists(): # Always include all of errfile
            with errfile.open() as f:
                err = f.read().strip()
                if err:
                    result += "___[ Error Output ]___\n"
                    result += err
        self.result = JavaMain.wrapOutput(result) + "\n"

        if len(self.result.splitlines()) > JavaMain.max_output_length:
            self.long_output = True

        for line in self.j_file.lines:
            if line.startswith("} ///:~"):
                self.j_file.newcode += "} /* Output:\n"
                self.j_file.newcode += self.result + "*///:~\n"
                break
            if line.startswith("} /* Output:"):
                line = line.partition("*///:~")[0]
                self.j_file.newcode += line + "\n"
                self.j_file.newcode += self.result + "*///:~\n"
                break
            else:
                self.j_file.newcode += line + "\n"

    def new_code(self):
        return self.j_file.newcode

    @staticmethod
    def wrapOutput(output):
        lines = output.splitlines()
        result = []
        for line in lines:
            result += textwrap.wrap(line.rstrip(), width=maxlinewidth)
        return "\n".join(result)

    def write_modified_file(self):
        with self.j_file.javaFilePath.open('w') as modified:
            modified.write(self.j_file.newcode)


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
        jf = JavaMain.JFile.with_main(jfp)
        if jf is None:
            continue
        if jf.output_line:
            types.add(jf.output_line)
    pprint.pprint(types)


@CmdLine('e')
def extractResults():
    """Test extraction of all results"""
    os.chdir(str(examplePath))
    with Path("AttachedResults.txt").open('w') as results:
        for jfp in Path(".").rglob("*.java"):
            j_main = JavaMain.create(jfp)
            if j_main:
                results.write(ruler(jfp))
                outline = j_main.j_file.output_line
                if outline:
                    results.write(outline + "\n")
                results.write(j_main.result)
    os.system("subl AttachedResults.txt")

@CmdLine('n')
def noOutputFixup():
    """Attach "Output: (None)" lines to empty output files"""
    os.chdir(str(examplePath))
    # test = open("test.txt", 'w')
    for jfp in Path(".").rglob("*.java"):
        if "gui" in jfp.parts or "swt" in jfp.parts:
            continue
        jf = JavaMain.JFile.with_main(jfp)
        if jf is None:
            continue
        if "{ValidateByHand}" in jf.code:
            continue
        if not jf.output_line:
            if JavaMain.create(jfp):
                continue
            newcode = ""
            for line in jf.lines:
                if line.startswith("} ///:~"):
                    newcode += "} /* Output: (None) *///:~\n"
                else:
                    newcode += line + "\n"
            with jfp.open('w') as f:
                f.write(newcode)
            os.system("subl {}".format(jfp))
            # test.write(ruler(jfp))
            # test.write(newcode)

@CmdLine('v')
def viewAttachedFiles():
    """Sublime edit all files containing output in this directory and below"""
    for java in Path(".").rglob("*.java"):
        with java.open() as codefile:
            code = codefile.read()
            if "/* Output:" in code:
                if "/* Output: (None)" in code:
                    continue
                if "/* Output: (Execute to see)" in code:
                    continue
                for n, line in enumerate(code.splitlines()):
                    if "/* Output:" in line:
                        # os.system("subl {}:{}".format(java, n))
                        os.system("subl {}".format(java))
                        continue

@CmdLine('s')
def showJavaFiles():
    """Sublime edit all java files in this directory and below"""
    for java in Path(".").rglob("*.java"):
        os.system("subl {}".format(java))

@CmdLine('b')
def blankOutputFiles():
    """Show java files with expected output where there is none"""
    find_output = re.compile(r"/\* Output:(.*)\*///:~", re.DOTALL)
    os.chdir(str(examplePath))
    for java in Path(".").rglob("*.java"):
        with java.open() as codeFile:
            output = find_output.search(codeFile.read())
            if output:
                # print(output.group(1))
                if not output.group(1).strip():
                    print(java)

@CmdLine('u')
def unexpectedOutput():
    """Show java files with output where none was expected"""
    os.chdir(str(examplePath))
    for java in Path(".").rglob("*.java"):
        with java.open() as codeFile:
            if "/* Output: (None) */" in codeFile.read():
                outfile = java.with_name(java.stem + "-output.txt")
                errfile = java.with_name(java.stem + "-erroroutput.txt")
                if outfile.exists():
                    if outfile.stat().st_size:
                        print("Unexpected output: {}".format(java))
                if errfile.exists():
                    if errfile.stat().st_size:
                        print("Unexpected error output: {}".format(java))

@CmdLine('a')
def attachFiles():
    """Attach standard and error output to all files"""
    os.chdir(str(examplePath))
    test = open("AllFilesWithOutput.txt", 'w')
    longOutput = open("LongOutput.txt", 'w')
    for jfp in Path(".").rglob("*.java"):
        if "gui" in jfp.parts or "swt" in jfp.parts:
            continue
        j_main = JavaMain.create(jfp)
        if j_main is None:
            continue
        j_main.write_modified_file()
        test.write(ruler())
        test.write(j_main.new_code())
        if j_main.long_output:
            longOutput.write(ruler())
            longOutput.write(j_main.new_code())
    os.system("subl AllFilesWithOutput.txt")
    os.system("subl LongOutput.txt")

if __name__ == '__main__': CmdLine.run()

