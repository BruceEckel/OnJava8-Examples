Private Sub titleCase()
    For Each wd In Selection.Words
        'MsgBox "[" + wd + "]"
        'MsgBox wd.Characters(1) & " " & (wd.Characters(1).Bold = 0)
        If wd.Characters(1).Bold = 0 Then
            Select Case Trim(wd)
                ' the following group of words will be left
                ' unchanged
                Case "the", "and", "for", "of", "a", "an", _
                "as", "to", "about", "from", "in", "on", "or", _
                "under", "against", "at", "into", "over", "but", "with", "before", "versus", "are", "vs", "is"
                ' the following groups of words will be
                ' changed to lower case
                Case "The", "And", "For", "Of", "A", "An", _
                "As", "To", "From", "Versus", "In", "On", "Or", _
                "Under", "Against", "At", "Into", "With", "Over", "Before", "But", "About", "Are", "Vs", "Is", _
                "THE", "AND", "FOR", "OF", "A", "AN", "OR", _
                "AS", "TO", "FROM", "VERSUS", "IN", "ON", _
                "UNDER", "AGAINST", "AT", "WITH", "OVER", "INTO", "BEFORE", "BUT", "ABOUT", "ARE", "VS", "IS"
                wd.Case = wdLowerCase
                Case Else
                ' Any word that isn't in either of the above groups
                ' will be changed to title case, i.e. initial cap
                wd.Case = wdTitleWord
            End Select
        End If
    Next wd
    If Selection.Words.Item(1).Bold = 0 Then
        Selection.Words.Item(1).Case = wdTitleWord
    End If
End Sub

Sub titlecaseHeader()
    With Selection.Find
        .ClearFormatting
        .Style = ActiveDocument.Styles("Heading 3,H3")
        .Text = ""
        .Replacement.Text = ""
        .Forward = True
        .Wrap = wdFindStop
        .Format = True
        .MatchCase = False
        .MatchWholeWord = False
        .MatchWildcards = False
        .MatchSoundsLike = False
        .MatchAllWordForms = False
        .Execute
    End With
    Call titleCase
    Selection.MoveUp Unit:=wdLine, Count:=2
    Selection.MoveDown Unit:=wdLine, Count:=5
End Sub
