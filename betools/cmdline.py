#! py -3
"""
Decorator adds a new command-line option and manages argparse.
See http://www.artima.com/weblogs/viewpost.jsp?thread=240845
"""
import argparse


class CmdLine:

    parser = argparse.ArgumentParser()
    commands = dict()
    letterflags = set()

    def __init__(self, letterFlag, wordFlag):
        self.wordFlag = wordFlag
        self.letterFlag = letterFlag
        assert wordFlag not in CmdLine.commands, "Duplicate command argument word flags"
        assert letterFlag not in CmdLine.letterflags, "Duplicate command argument letter flags"
        CmdLine.letterflags.add(letterFlag)

    def __call__(self, func):
        CmdLine.parser.add_argument("-" + self.letterFlag, "--" + self.wordFlag, action='store_true', help=func.__doc__)
        CmdLine.commands[self.wordFlag] = func
        return func # No wrapping needed

    @staticmethod
    def run():
        show_help = True
        args = vars(CmdLine.parser.parse_args())
        for wordFlag, func in CmdLine.commands.items():
            if args[wordFlag]:
                func()
                show_help = False
        if show_help:
            CmdLine.parser.print_help()


