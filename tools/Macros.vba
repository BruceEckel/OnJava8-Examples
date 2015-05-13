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
    Selection.Find.ClearFormatting
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


Private Sub OLDUpdateAllCode()
' Update the entire book's code listings
' Macro created 7/7/2002 by Mark Welsh
'
    Dim startLoc As Long, endLoc As Long
    Dim oldCode As String, i As Integer
    Dim fname As String, ln As String
    Dim newCode As String

    'Application.ScreenUpdating = False
    Application.EnableCancelKey = xlInterrupt

    Do
        ' Find the starting location
        Selection.Find.ClearFormatting
        startLoc = Selection.Start
        With Selection.Find
            .Text = "//: "
            .Forward = True
            .Wrap = wdFindContinue
            .Format = False
            .MatchCase = False
            .MatchWholeWord = False
            .MatchWildcards = False
            .MatchSoundsLike = False
            .MatchAllWordForms = False
        End With
        Selection.Find.Execute

        startLoc = Selection.Start
        ' No new code found, so quit
        If startLoc < endLoc Then
            Exit Sub
        End If

        ' Find the end location
        Selection.Find.ClearFormatting
        With Selection.Find
            .Text = "///:~"
            .Forward = True
            .Wrap = wdFindContinue
            .Format = False
            .MatchCase = False
            .MatchWholeWord = False
            .MatchWildcards = False
            .MatchSoundsLike = False
            .MatchAllWordForms = False
        End With
        Selection.Find.Execute
        endLoc = Selection.End

        Selection.MoveRight wdCharacter, 1
        Selection.MoveLeft wdCharacter, endLoc - startLoc, wdExtend

        ' Get the file name
        oldCode = Selection
        i = InStr(5, oldCode, ".java")
        fname = Mid(oldCode, 5, i)
        fname = Replace(fname, "/", "\")

        If Len(fname) = 0 Then
            GoTo skip
        End If

        ' Does the file exist?
        If Not fileExists(fname) Then
            If MsgBox(fname & " could not be found! Continue replace?", vbYesNo + vbExclamation, "UpdateCode") = vbNo Then
                Exit Sub
            End If
            GoTo skip
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
        'Selection.MoveRight wdCharacter, 1
        'Selection.HomeKey wdLine, wdExtend
        'Selection.Style = ActiveDocument.Styles("CodeInline")
        'Selection.MoveDown wdLine, 1
        'Selection.Style = ActiveDocument.Styles("CodeInlineTrailer")
skip:
        Selection.MoveRight wdCharacter, 2
        newCode = ""
    Loop
    Application.ScreenUpdating = True
End Sub

Private Sub oldCodeBody()
            Selection.MoveRight wdCharacter, 1
            Selection.MoveLeft wdCharacter, endLoc - startLoc, wdExtend

            ' Get the file name
            oldCode = Selection
            i = InStr(5, oldCode, ".java")
            fname = Mid(oldCode, 5, i)
            fname = Replace(fname, "/", "\")

            If Len(fname) = 0 Then
                GoTo skip
            End If

            ' Does the file exist?
            If Not fileExists(fname) Then
                If MsgBox(fname & " could not be found! Continue replace?", vbYesNo + vbExclamation, "UpdateCode") = vbNo Then
                    Exit Sub
                End If
                GoTo skip
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
skip:
            Selection.MoveRight wdCharacter, 2
            newCode = ""

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
