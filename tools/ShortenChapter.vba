Private Function skipSection() As Boolean
    excludes = Array( _
        "What is the Director", _
        "Preface", _
        "Introduction", _
        "Appendix: The Positive Legacy of C++ and Java", _
        "Appendix: Supplements" _
    )
    skipSection = False
    For Each exc In excludes
        If InStr(Selection.Text, exc) = 1 Then
            'MsgBox "Skipping " + Selection.Text
            skipSection = True
            Exit Function
        End If
    Next exc
End Function

Private Function ShortenChapter() As Boolean
'
' ShortenChapter Macro
'
'
    ShortenChapter = True
    Selection.GoToNext (wdGoToSection)
    Selection.Expand Unit:=wdSection
    With Selection.Find
        .ClearFormatting
        .Text = ""
        .MatchWildcards = False
        .Forward = False
        .Style = ActiveDocument.Styles("Heading 1")
        .Execute
    End With
    If InStr(Selection.Text, "Appendix: On Being a Programmer") = 1 Then
        ShortenChapter = False
        'MsgBox "Reached the End"
    End If
    If Not skipSection() Then
        Selection.Expand Unit:=wdSection
        Selection.MoveStart Unit:=wdSentence, Count:= 90
        Selection.MoveEnd Unit:=wdCharacter, Count:= -1  'Don't take section marker
        If Len(Selection.Text) <> 0 Then Selection.Cut 'Doesn't accomplish it's intent
    End If
End Function

Sub CreateSampleBook()
    ChangeFileOpenDirectory "C:\Users\Bruce\Dropbox\__TIJ4-ebook\"
    ActiveDocument.SaveAs2 FileName:="TIJDirectorsCutSample.docm", FileFormat _
        :=wdFormatXMLDocumentMacroEnabled, LockComments:=False, Password:="", _
        AddToRecentFiles:=True, WritePassword:="", ReadOnlyRecommended:=False, _
        EmbedTrueTypeFonts:=True, SaveNativePictureFormat:=False, SaveFormsData:= _
        False, SaveAsAOCELetter:=False, CompatibilityMode:=15
    Selection.HomeKey Unit:=wdStory
    While ShortenChapter()
    Wend
End Sub
