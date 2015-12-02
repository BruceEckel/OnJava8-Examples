# Requires Python 3.5 or greater
# ©2016 MindView LLC: see Copyright.txt
# We make no guarantees that this code is fit for any purpose.
# Visit http://mindviewinc.com/Books/OnJava/ for more book information.
"""
ToDo:
    - Validate errors
"""
import sys
from pathlib import Path
import re
import textwrap
from enum import Enum, unique

maxlinewidth = 59
current_dir_name = Path.cwd().stem

Ignore = Enum('Ignore', 'sorted  digits  mem_and_digits')

strategies = {
    "ToastOMatic.java" : Ignore.sorted,
    "ThreadVariations.java" : Ignore.sorted,
    "ActiveObjectDemo.java" : Ignore.sorted,
    "Interrupting.java" : Ignore.sorted,
    "SyncObject.java" : Ignore.sorted,
    "UseCaseTracker.java" : Ignore.sorted,
    "AtUnitComposition.java" : Ignore.sorted,
    "AtUnitExample1.java" : Ignore.sorted,
    "AtUnitExample2.java" : Ignore.sorted,
    "AtUnitExample3.java" : Ignore.sorted,
    "AtUnitExample5.java" : Ignore.sorted,
    "AtUnitExternalTest.java" : Ignore.sorted,
    "HashSetTest.java" : Ignore.sorted,
    "StackLStringTest.java" : Ignore.sorted,

    "MapComparisons.java" : Ignore.digits,
    "ListComparisons.java" : Ignore.digits,
    "NotifyVsNotifyAll.java" : Ignore.digits,
    "SelfManaged.java" : Ignore.digits,
    "SimpleMicroBenchmark.java" : Ignore.digits,
    "SimpleThread.java" : Ignore.digits,
    "SleepingTask.java" : Ignore.digits,
    "ActiveObjectDemo.java" : Ignore.digits,
    "CachedThreadPool.java" : Ignore.digits,
    "ExchangerDemo.java" : Ignore.digits,
    "Compete.java" : Ignore.digits,
    "MappedIO.java" : Ignore.digits,

    "SimpleDaemons.java" : Ignore.mem_and_digits,
}


translate_file_name = {
    "ApplyTest.java": "Apply.java",
    "FillTest.java": "Fill.java",
    "Fill2Test.java": "Fill2.java",
    "ClassInInterface$Test.java": "ClassInInterface.java",
    "TestBed$Tester.java": "TestBed.java",
}


memlocation = re.compile("@[0-9a-z]{5,7}")
datestamp1 = re.compile("(?:[MTWFS][a-z]{2} ){0,1}[JFMASOND][a-z]{2} \d{1,2} \d{2}:\d{2}:\d{2} [A-Z]{3} \d{4}")
datestamp2 = re.compile("[JFMASOND][a-z]{2} \d{1,2}, \d{4} \d{1,2}:\d{1,2}:\d{1,2} (:?AM|PM)")
varying = [ memlocation, datestamp1, datestamp2 ]


# Result of Duet.validate():
Valid = Enum('Valid', 'exact varying execute_to_see no_output selected_lines fail')


class Duet:
    """Holds embedded and generated output"""

    def __init__(self, out_filename):
        if not str(out_filename).endswith(".out"):
            print("Error: argument to Duet() must end with '.out'")
            print("Argument was {}".format(out_filename))
            sys.exit()
        self.java_file = None # Full contents of Java code file
        self.java_slugline = None # First (marker) line of Java code file
        self.out_path = out_filename
        if not self.out_path.exists():
            print(str(self.out_path), "does not exist.")
            sys.exit()
        self.java_path = self.java_path()
        self.embedded = self.embedded_output()
        self.generated = self.out_path.read_text().strip()
        self.generated = self.fill_to_width(self.generated)
        self.embedded_futzed = self.futz(self.embedded)
        self.generated_futzed = self.futz(self.generated)

    def java_path(self):

        def __java_filename(out_pieces):
            path_components = out_pieces.split(".", out_pieces.count(".") - 1)
            path_components[-1] = path_components[-1].replace(".out", ".java")
            return path_components

        jfn = __java_filename(self.out_path.parts[-1])
        jpath = list(self.out_path.parts[:-1]) + list(jfn)
        if len(jpath) > 1 and jpath[0] == jpath[1]:
            del jpath[0]
        if jpath[0] == current_dir_name:
            del jpath[0]
        if jpath[-1] in translate_file_name:
            jpath[-1] = translate_file_name[jpath[-1]]
        return Path(*jpath)


    def embedded_output(self):
        find_output = re.compile(r"/\* (Output:.*)\*/", re.DOTALL) # should space be \s+ ??
        with self.java_path.open() as java:
            self.java_file = java.read()
            self.java_slugline = self.java_file.strip().splitlines()[0]
            output = find_output.search(self.java_file)
            assert output, "No embedded output: in {}".format(self.java_path)
            lines = output.group(1).strip().splitlines()
            self.output_tag = lines[0]
            return ("\n".join(lines[1:])).strip()


    @staticmethod
    def fill_to_width(text):
        result = ""
        for line in text.splitlines():
            result += textwrap.fill(line, width=maxlinewidth) + "\n"
        return result.strip()


    def __repr__(self):
        # result = "\n" + str(self.output_tag)
        result = "\n" + str(self.java_path).center(60, "=") + "\n" + self.embedded
        result += "\n" + str(self.out_path).center(60, "-") + "\n" + self.generated
        return result


    @staticmethod
    def strip_varying(text):
        for pat in varying:
            text = pat.sub("", text)
        return text


    def futz(self, output):
        output = output.replace("\0", "NUL")
        if self.java_path.name not in strategies:
            return output
        strategy = strategies[self.java_path.name]
        if strategy is Ignore.digits:
            return re.sub("\d", " ", output)
        if strategy is Ignore.mem_and_digits:
            tmp = memlocation.sub("", output)
            return re.sub("\d", " ", tmp)
        if strategy is Ignore.sorted:
            return "\n".join(sorted(output.splitlines())).strip()


    def validate(self):
        if "(Execute to see)" in self.output_tag:
            return Valid.execute_to_see
        if "(None)" in self.output_tag:
            return Valid.no_output
        if "Output: (First" in self.output_tag:          ### This is temporary ###
            return Valid.selected_lines
        if self.generated_futzed == self.embedded_futzed:
            return Valid.exact
        if Duet.strip_varying(self.generated_futzed) == Duet.strip_varying(self.embedded_futzed):
            return Valid.varying
        return Valid.fail
