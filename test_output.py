#! py -3
# Requires Python 3.5
# Validates output from executable Java programs in "On Java 8"
# (1) Extract output in Java file, keep whole file
# (2) If direct comparison of actual output with output stored in Java file fails:
# (3) Use chain of responsibility to successively try filters until one matches or all fail
from pathlib import Path
import re


if __name__ == '__main__':
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
