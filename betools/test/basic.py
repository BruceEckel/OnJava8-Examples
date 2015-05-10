from betools import CmdLine, visitDir

@CmdLine("t", "test")
def test(arg="default1", arg2="default2"):
    """
    Testing CmdLine
    """
    print("test", arg, arg2)


if __name__ == '__main__': CmdLine.run()
