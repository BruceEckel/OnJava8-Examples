from pathlib import Path

for j in Path("ExtractedExamples").glob("**/*.java"):
    print(str(j))
    with j.open() as f:
        code = f.read().strip()
    with j.open('w') as w:
        w.write(code)
