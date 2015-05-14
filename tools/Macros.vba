Public Const basedir = "C:\Users\Bruce\Dropbox\__TIJ4-ebook\ExtractedExamples\"

Sub AutoOpen()
    ActiveWindow.View.Draft = True
End Sub

Function fileExists(fname As String) As Boolean
    On Error GoTo fail
    Dim fn As String
    fn = basedir & fname

    Open basedir & fname For Input As #1
    Close #1
    fileExists = True
    Exit Function
fail:
    fileExists = False
End Function

Private Sub updateFile()
    Dim oldCode As String, i As Integer
    Dim fname As String, ln As String
    Dim newCode As String

    ' Get the file name
    oldCode = Selection
    i = InStr(5, oldCode, ".java")
    fname = Mid(oldCode, 5, i)
    fname = Replace(fname, "/", "\")

    If Len(fname) = 0 Then ' No .java file found
        Exit Sub
    End If

    ' Does the file exist?
    If Not fileExists(fname) Then
        If MsgBox(fname & " could not be found! Continue replace?", vbYesNo + vbExclamation, "UpdateCode") = vbNo Then
            Exit Sub
        End If
    End If

    'Open the file
    Open basedir & fname For Input As #1
        While Not EOF(1)
            Line Input #1, ln
            newCode = newCode & ln & vbCrLf
        Wend
    Close #1

    'Add the new code and change the style
    Selection = Left(newCode, Len(newCode) - 2)
    Selection.Style = ActiveDocument.Styles("Code")
End Sub

Sub UpdateCode()
' Update a single code listing
    Selection.Find.ClearFormatting
    With Selection.Find
        .Text = "//: ?@///:~"
        .Forward = True
        .Wrap = wdFindContinue
        .Format = False
        .MatchCase = False
        .MatchWholeWord = False
        .MatchAllWordForms = False
        .MatchSoundsLike = False
        .MatchWildcards = True
    End With
    Selection.Find.Execute
    Call updateFile
    Selection.MoveRight wdCharacter, 1
    ' All this is just to clear the formatting:
    ' Selection.Find.ClearFormatting
    With Selection.Find
        .ClearFormatting
        .Text = ""
        .Forward = True
        .Wrap = wdFindContinue
        .Format = False
        .MatchCase = False
        .MatchWholeWord = False
        .MatchAllWordForms = False
        .MatchSoundsLike = False
        .MatchWildcards = False
    End With
    Selection.Find.Execute
End Sub

Sub FreshenAllCode_StripTrailingNewlinesFirst()
' Update the entire book's code listings

    'Application.ScreenUpdating = False
    Application.EnableCancelKey = xlInterrupt

    Selection.HomeKey Unit:=wdStory

    Selection.Find.ClearFormatting
    With Selection.Find
        .Text = "//: ?@///:~"
        .Forward = True
        .Wrap = wdFindContinue
        .Format = False
        .MatchCase = False
        .MatchWholeWord = False
        .MatchAllWordForms = False
        .MatchSoundsLike = False
        .MatchWildcards = True
    End With
    Selection.Find.Execute

    While Selection.Find.Found
        Call updateFile
        Selection.MoveRight wdCharacter, 1
        Selection.Find.Execute
    Wend
End Sub

Sub SaveAsText()
'
' SaveAsText Macro
'
'
    ChangeFileOpenDirectory "C:\Users\Bruce\Dropbox\__TIJ4-ebook\"
    ActiveDocument.SaveAs2 FileName:="TIJDirectorsCut.txt", FileFormat:= _
        wdFormatText, LockComments:=False, Password:="", AddToRecentFiles:=True, _
        WritePassword:="", ReadOnlyRecommended:=False, EmbedTrueTypeFonts:=True, _
        SaveNativePictureFormat:=False, SaveFormsData:=False, SaveAsAOCELetter:= _
        False, Encoding:=1252, InsertLineBreaks:=False, AllowSubstitutions:=False _
        , LineEnding:=wdCRLF, CompatibilityMode:=0
    Application.Quit
End Sub


Sub NextCodeItemOfInterest()
'
' NextCodeItemOfInterest Macro
'
'
    Selection.Find.ClearFormatting
    Selection.Find.Style = ActiveDocument.Styles("Code")
    With Selection.Find
        .Text = "->"
        .Replacement.Text = ""
        .Forward = True
        .Wrap = wdFindContinue
        .Format = True
        .MatchCase = False
        .MatchWholeWord = False
        .MatchWildcards = False
        .MatchSoundsLike = False
        .MatchAllWordForms = False
    End With
    Selection.Find.Execute
End Sub
