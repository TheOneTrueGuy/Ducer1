Function ReadTextFile(strFileName)
	strContent=""
	On Error Resume Next
	Set objFSO = CreateObject( "Scripting.FileSystemObject" )
	If Err.Number=0 Then Set objFile= objFSO.OpenTextFile(strFileName)
	If Err.Number= 0 Then
		Do While Not objFile.AtEndOfStream
		  strLine=objFile.ReadLine
			intDSPos=InStr(strLine, "  ")
			Do While intDSPos
				strLine=Left(strLine,intDSPos) & Mid(strLine, intDSPos+2)
				intDSPos = InStr(strLine, "  ")
			Loop
			If (Len(strLine)) And (strLine<> "  ") Then strContent=strContent & strLine & CRLF
		    
		Loop
		objFile.Close
	End If
	If Err.Number=0 then ReadTextFile = strContent
End Function

Function WriteNewFile( strFileName, strContent)
	On Error Resume Next
	Set objFSO = CreateObject( "Scripting.FileSystemObject" )
	If Err.Number=0 Then Set ObjFile = objFSO.CreateTextFile(strFilename, True)
	If Err.Number=0 Then objFile.WriteLine strContent
	If Err.Number=0 Then objFile.Close
	If Err.Number=0 Then WriteNewFile = True
End Function
