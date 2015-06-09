# ShowJavaExampleOutputCommand.py
# Must be placed in
# C:\Users\Bruce\AppData\Roaming\Sublime Text 3\Packages\User
import sublime, sublime_plugin

class ShowJavaExampleOutputCommand(sublime_plugin.EventListener):
    def on_load(self, view):
        if view.file_name().endswith(".java"):
            output_region = view.find(r"/\* Output:.*", 0)
            if output_region:
                view.show(output_region)
                # view.show(sublime.Region(view.size(), view.size()))
