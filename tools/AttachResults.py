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

    maindef = re.compile("public\s+static\s+void\s+main")
    ellipses = ["[...]".center(maxlinewidth, '_')]

    class JFile:
        """Could just manipulate this, then write it at the end"""
        @staticmethod
        def with_main(javaFilePath):
            with javaFilePath.open() as doc:
                code = doc.read()
                if JavaMain.maindef.search(code):
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
            # else:
            #     results.write("[ No output for {} ]\n".format(jfp))
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


@CmdLine('a')
def attachFiles():
    """Attach standard and error output to all files"""
    os.chdir(str(examplePath))
    test = open("test.txt", 'w')
    for jfp in Path(".").rglob("*.java"):
        if "gui" in jfp.parts or "swt" in jfp.parts:
            continue
        j_main = JavaMain.create(jfp)
        if j_main is None:
            continue
        test.write(ruler())
        test.write(j_main.new_code())
    os.system("subl test.txt")

if __name__ == '__main__': CmdLine.run()



# def newOutput(javaFilePath):
#     outfile = javaFilePath.with_name(javaFilePath.stem + "-output.txt")
#     errfile = javaFilePath.with_name(javaFilePath.stem + "-erroroutput.txt")
#     result =""
#     if outfile.exists():
#         with outfile.open() as f:
#             out = f.read().strip()
#             if out:
#                 result += out + "\n"
#     if errfile.exists():
#         with errfile.open() as f:
#             err = f.read().strip()
#             if err:
#                 result += "___[ Error Output ]___\n"
#                 result += err
#     result = wrapOutput(result)
#     if result:
#         return result + "\n"
#     return None







# def appendOutputFiles(javaFilePath):
#     jfile = JFile.with_main(javaFilePath)
#     if jfile is None:
#         return
#     output = newOutput(javaFilePath)
#     if not output:
#         return
#     if not self.output_tags.has_output: # no /* Output: at all
#         with self.javaFilePath.open() as jf:
#             code = jf.read()
#             lines = code.splitlines()
#             while lines[-1].strip() is "":
#                 lines.pop()
#             assert lines[-1].rstrip() == "} ///:~"
#             lines[-1] = "} /* Output:"
#             lines.append(self.new_output)
#             lines.append("*///:~")
#             result = "\n".join(lines) + "\n"
#         with self.javaFilePath.open("w") as jf:
#             jf.write(result)
#         return result
#     else:
#         print("{} already has Output!".format(self.javaFilePath))
#         sys.exit()