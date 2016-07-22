#! py -3
# Requires Python 3.5
# Validates output from executable Java programs in "On Java 8"
# (1) Extract output in Java file, keep whole file
# (2) If direct comparison of actual output with output stored in Java file fails:
# (3) Use chain of responsibility to successively try filters until one matches or all fail
from pathlib import Path
import textwrap
import re

def fill_to_width(text):
    result = ""
    for line in text.splitlines():
        result += textwrap.fill(line, width=59) + "\n"
    return result.strip()

def adjust_lines(text):
    text = text.replace("\0", "NUL")
    lines = text.splitlines()
    slug = lines[0]
    if "(First and Last " in slug:
        num_of_lines = int(slug.split()[5])
        adjusted = lines[:num_of_lines + 1] +\
            ["...________...________...________...________..."] +\
            lines[-num_of_lines:]
        return "\n".join(adjusted)
    elif "(First " in slug:
        num_of_lines = int(slug.split()[3])
        adjusted = lines[:num_of_lines + 1] +\
            ["                  ..."]
        return "\n".join(adjusted)
    else:
        return text

def phase1():
    """
    (0) Do first/last lines before formatting to width
    (1) Combine output and error (if present) files
    (2) Format all output to width limit
    (3) Add closing '*/'
    """
    for outfile in Path(".").rglob("*.out"):
        out_text = adjust_lines(outfile.read_text())
        phase_1 = outfile.with_suffix(".p1")
        with phase_1.open('w') as phs1:
            phs1.write(fill_to_width(out_text) + "\n")
            errfile = outfile.with_suffix(".err")
            if errfile.exists():
                phs1.write("___[ Error Output ]___\n")
                phs1.write(fill_to_width(errfile.read_text()) + "\n")
            phs1.write("*/\n")

if __name__ == '__main__':
    phase1()
    find_output = re.compile(r"/\* (Output:.*)\*/", re.DOTALL) # should space be \s+ ??
    for outfile in Path(".").rglob("*.p1"):
        javafile = outfile.with_suffix(".java")
        if not javafile.exists():
            print(str(outfile) + " has no javafile")
        javatext = javafile.read_text()
        if "/* Output:" not in javatext:
            print(str(outfile) + " has no /* Output:")
        embedded_output = find_output.search(javatext).group(0).strip()
        new_output = outfile.read_text().strip()
        if new_output == embedded_output:
            print(str(javafile))
        else:
            with outfile.with_suffix(".nomatch").open('w') as nomatch:
                nomatch.write(str(embedded_output) + "\n\n")
                nomatch.write("=== Actual ===\n\n")
                nomatch.write(str(new_output))
    print(" No Match ".center(45, "="))
    for nomatch in Path(".").rglob("*.nomatch"):
        print(nomatch)
