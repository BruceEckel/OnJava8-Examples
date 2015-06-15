#! py -3
"""
Append output and error files to Java files
"""
TODO = """
- Test to make sure that None files indeed have no output
- Collect all tests under single flag
"""
from pathlib import Path
import pprint
import textwrap
import os, sys, re
from itertools import chain
from sortedcontainers import SortedSet
from betools import CmdLine, visitDir, ruler, head

maxlinewidth = 59
examplePath = Path(r"C:\Users\Bruce\Dropbox\__Dreaming-in-Java\ExtractedExamples")


class JavaMain:
    max_output_length = 32 # lines beyond which we flag this
    maindef = re.compile("public\s+static\s+void\s+main")
    leader = "_" * 8
    ellipses = [leader + leader.join(["..."] * 4) + leader]
    # ellipses = ["[...]".center(14, '_') * 4]

    class JFile:
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
                        out = "\n".join(lines[:self.first_lines] + [" " * 18 + "..."])
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
        """
        Wrap to line limit and perform other fixups for display and comparison
        """
        output = output.replace('\0', "NUL")
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

#@CmdLine('n')
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


@CmdLine('x')
def showNulBytesInOutput():
    """Look for NUL bytes in output files`"""
    os.chdir(str(examplePath))
    for normal in Path(".").rglob("*-output.txt"):
        with normal.open() as codeFile:
            if "\0" in codeFile.read():
                print(normal)
    for errors in Path(".").rglob("*-erroroutput.txt"):
        with errors.open() as codeFile:
            if "\0" in codeFile.read():
                print(normal)


@CmdLine('s')
def showJavaFiles():
    """Sublime edit all java files in this directory and below"""
    for java in Path(".").rglob("*.java"):
        os.system("subl {}".format(java))


# @CmdLine('w')
def boldWords():
    """
    Create list of bolded words to be used as a Word dictionary
    """
    from bs4 import BeautifulSoup
    import codecs
    import string
    clean = lambda dirty: ''.join(filter(string.printable.__contains__, dirty))
    def flense(word):
        word = clean(word)
        word = word.split('(')[0]
        word = word.split('[')[0]
        return word.strip()

    os.chdir(str(examplePath / ".."))
    spelldict = SortedSet()
    with codecs.open(str(Path("DreamingInJava.htm")),'r', encoding='utf-8', errors='ignore') as book:
        soup = BeautifulSoup(book.read())
        for b in soup.find_all("b"):
            text = (" ".join(b.text.split())).strip()
            if " " in text:
                continue
            text = flense(text)
            if text:
                spelldict.add(text)

    with Path("BoldedWords.txt").open('w') as boldwords:
        for word in spelldict:
            if len(word):
                if word[0] in string.ascii_letters:
                    boldwords.write(word + "\n")


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

exclude_files = [
r"concurrency\ActiveObjectDemo.java",
r"concurrency\AtomicityTest.java",
r"concurrency\CachedThreadPool.java",
r"concurrency\CountDownLatchDemo.java",
r"concurrency\DaemonFromFactory.java",
r"concurrency\DeadlockingDiningPhilosophers.java",
r"concurrency\FastSimulation.java",
r"concurrency\FixedDiningPhilosophers.java",
r"concurrency\FixedThreadPool.java",
r"concurrency\MoreBasicThreads.java",
r"concurrency\NIOInterruption.java",
r"concurrency\SelfManaged.java",
r"concurrency\SemaphoreDemo.java",
r"concurrency\SimpleDaemons.java",
r"concurrency\SleepingTask.java",
r"concurrency\ThreadLocalVariableHolder.java",
r"patterns\PaperScissorsRock.java",
r"patterns\recyclea\RecycleA.java",
r"patterns\visitor\BeeAndFlowers.java",
r"concurrency\EvenGenerator.java",
r"concurrency\GreenhouseScheduler.java",
r"concurrency\OrnamentalGarden.java",
r"concurrency\PipedIO.java",
r"concurrency\SimplePriorities.java",
r"concurrency\SimpleThread.java",
r"concurrency\ThreadVariations.java",
r"generics\DynamicProxyMixin.java",
r"logging\LoggingLevelManipulation.java",
r"logging\SimpleFilter.java",
r"annotations\AtUnitExample4.java",
r"concurrency\BankTellerSimulation.java",
r"concurrency\CarBuilder.java",
r"concurrency\ListComparisons.java",
r"concurrency\MapComparisons.java",
r"concurrency\ReaderWriterList.java",
r"concurrency\restaurant2\RestaurantWithQueues.java",
r"generics\Mixins.java",
r"io\LockingMappedFiles.java",
r"logging\ConfigureLogging.java",
r"logging\LoggingLevels.java",
r"operators\HelloDate.java",
r"annotations\AtUnitComposition.java",
r"annotations\AtUnitExample3.java",
r"annotations\AtUnitExternalTest.java",
r"annotations\HashSetTest.java",
r"annotations\UseCaseTracker.java",
r"concurrency\Interrupting.java",
r"concurrency\SerialNumberChecker.java",
r"concurrency\SimpleMicroBenchmark.java",
r"concurrency\SynchronizationComparisons.java",
r"concurrency\SyncObject.java",
r"containers\ListPerformance.java",
r"io\Logon.java",
r"logging\CustomHandler.java",
r"object\HelloDate.java",
r"annotations\AtUnitExample1.java",
r"annotations\AtUnitExample2.java",
r"concurrency\ExchangerDemo.java",
r"concurrency\ExplicitCriticalSection.java",
r"concurrency\Restaurant.java",
r"containers\MapPerformance.java",
r"containers\SetPerformance.java",
r"exceptions\LoggingExceptions.java",
r"logging\InfoLogging.java",
r"logging\InfoLogging2.java",
r"logging\LogToFile.java",
r"logging\LogToFile2.java",
r"logging\MultipleHandlers.java",
r"logging\MultipleHandlers2.java",
r"annotations\AtUnitExample5.java",
r"concurrency\Daemons.java",
r"concurrency\HorseRace.java",
r"concurrency\ToastOMatic.java",
r"enums\ConstantSpecificMethod.java",
r"exceptions\LoggingExceptions2.java",
r"io\MakeDirectories.java",
r"io\MappedIO.java",
r"io\PreferencesDemo.java",
r"logging\PrintableLogRecord.java",
r"references\Compete.java",

# Keep an eye on:
r"strings\JGrep.java",
]

@CmdLine('c')
def compare_output():
    """Compare attached and newly-generated output"""
    TODO = """
      - Could also compare number of lines
    """
    ratio_target = 1.0
    from difflib import SequenceMatcher
    os.chdir(str(examplePath))
    def generated_output(jfp):
        j_main = JavaMain.create(jfp)
        if not j_main:
            return None
        return j_main.result.strip()
    def embedded_output(jfp):
        find_output = re.compile(r"/\* (Output:.*)\*///:~", re.DOTALL)
        with jfp.open() as java:
            output = find_output.search(java.read())
            assert output, "No embedded output: in {}".format(jfp)
            return "\n".join(output.group(1).strip().splitlines()[1:])
    if Path("CompareExclusions.txt").is_file():
        Path("CompareExclusions.txt").unlink()
    with Path("OutputComparisons.txt").open('w') as comparisions:
        for jfp in Path(".").rglob("*.java"):
            if "gui" in jfp.parts or "swt" in jfp.parts:
                continue
            if str(jfp) in exclude_files:
                continue
            generated = generated_output(jfp)
            if generated is None:
                continue
            embedded = embedded_output(jfp)
            comp = SequenceMatcher(None, embedded, generated)
            ratio = comp.ratio()
            if ratio < ratio_target:
                print(jfp)
                print("ratio: {}\n".format(ratio))
                comparisions.write("\n" + ruler(jfp))
                comparisions.write("ratio: {}\n".format(ratio))
                comparisions.write(ruler("Attached"))
                comparisions.write(embedded)
                comparisions.write("\n" + ruler("Generated"))
                comparisions.write(generated)
                with Path("CompareExclusions.txt").open('a') as exclusions:
                    exclusions.write('r"' + str(jfp) + "\",\n")
    if Path("CompareExclusions.txt").is_file():
        os.system("ed CompareExclusions.txt")
    if Path("OutputComparisons.txt").is_file():
        os.system("ed OutputComparisons.txt")



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
    # os.system("subl AllFilesWithOutput.txt")
    # os.system("subl LongOutput.txt")

if __name__ == '__main__': CmdLine.run()

