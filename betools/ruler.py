#! python
"""
ruler() generates a string ruler with embedded text
head() does the same thing but prints it
"""

def ruler(arg=None, sep="_", print_=False, width=60):
    if arg:
        result = "[ {} ]".format(str(arg)).center(width, sep) + "\n"
    else:
        result = "".center(width, sep) + "\n"
    if print_:
        print(result)
    else:
        return result

def head(arg=None, sep="_", width=60): ruler(arg, sep, True, width)

