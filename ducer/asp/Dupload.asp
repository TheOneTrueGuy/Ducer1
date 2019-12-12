<%

' this code added: necessary to process subs below.
'-------------

byteCount = Request.TotalBytes
RequestBin = Request.BinaryRead(byteCount)
Dim UploadRequest
Set UploadRequest = CreateObject("Scripting.Dictionary")

BuildUploadRequest  RequestBin

'--------------

Sub BuildUploadRequest(RequestBin)
	'Get the boundary
	PosBeg = 1
	PosEnd = InstrB(PosBeg,RequestBin,getByteString(chr(13)))
	boundary = MidB(RequestBin,PosBeg,PosEnd-PosBeg)
	boundaryPos = InstrB(1,RequestBin,boundary)
	'Get all data inside the boundaries
	Do until (boundaryPos=InstrB(RequestBin,boundary & getByteString("--")))
		'Members variable of objects are put in a dictionary object
		Dim UploadControl
		Set UploadControl = CreateObject("Scripting.Dictionary")
		'Get an object name
		Pos = InstrB(BoundaryPos,RequestBin,getByteString("Content-Disposition"))
		Pos = InstrB(Pos,RequestBin,getByteString("name="))
		PosBeg = Pos+6
		PosEnd = InstrB(PosBeg,RequestBin,getByteString(chr(34)))
		Name = getString(MidB(RequestBin,PosBeg,PosEnd-PosBeg))
		PosFile = InstrB(BoundaryPos,RequestBin,getByteString("filename="))
		PosBound = InstrB(PosEnd,RequestBin,boundary)
		'Test if object is of file type
		If  PosFile<>0 AND (PosFile<PosBound) Then
			'Get Filename, content-type and content of file
			PosBeg = PosFile + 10
			PosEnd =  InstrB(PosBeg,RequestBin,getByteString(chr(34)))
			FileName = getString(MidB(RequestBin,PosBeg,PosEnd-PosBeg))
			'Add filename to dictionary object
			UploadControl.Add "FileName", FileName
			Pos = InstrB(PosEnd,RequestBin,getByteString("Content-Type:"))
			PosBeg = Pos+14
			PosEnd = InstrB(PosBeg,RequestBin,getByteString(chr(13)))
			'Add content-type to dictionary object
			ContentType = getString(MidB(RequestBin,PosBeg,PosEnd-PosBeg))
			UploadControl.Add "ContentType",ContentType
			'Get content of object
			PosBeg = PosEnd+4
			PosEnd = InstrB(PosBeg,RequestBin,boundary)-2
			Value = MidB(RequestBin,PosBeg,PosEnd-PosBeg)
			Else
			'Get content of object
			Pos = InstrB(Pos,RequestBin,getByteString(chr(13)))
			PosBeg = Pos+4
			PosEnd = InstrB(PosBeg,RequestBin,boundary)-2
			Value = getString(MidB(RequestBin,PosBeg,PosEnd-PosBeg))
		End If
		'Add content to dictionary object
	UploadControl.Add "Value" , Value	
		'Add dictionary object to main dictionary
	UploadRequest.Add name, UploadControl	
		'Loop to next object
		BoundaryPos=InstrB(BoundaryPos+LenB(boundary),RequestBin,boundary)
	Loop

End Sub

'String to byte string conversion
Function getByteString(StringStr)
 For i = 1 to Len(StringStr)
 	char = Mid(StringStr,i,1)
	getByteString = getByteString & chrB(AscB(char))
 Next
End Function

'Byte string to string conversion
Function getString(StringBin)
 getString =""
 For intCount = 1 to LenB(StringBin)
	getString = getString & chr(AscB(MidB(StringBin,intCount,1))) 
 Next
End Function

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



%>