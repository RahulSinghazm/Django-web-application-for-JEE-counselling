import re
skippingKeywords = ["Table" , "Course" , "Code"]
instKeywords = ["Indian Institute" , "Indian School" , "Institute of Technology,"]
raw = open("programmeCode.txt",'rb')
regex = re.compile("[A-Z][0-9][0-9][0-9][0-9]")
regexnum = re.compile("[0-9]([0-9])?([0-9])?")
regexLastLine = re.compile("^Serial")
lastInsti =""
positionOne=0
positionTwo=0
count=0
flag = "true"
institutes = []
def toString(words):
	string = ""
	for i in range(0 , len(words) ):
		string = string +" "+ words[i]

	if len(string) == 0:
		return ""
	return string[1:]
def replace_tab(s, tabstop = 4):
  result = str()
  for c in s:
    if c != '\t':
    	result += c    
  return result

lastAddition = ""
with open("Formatted.txt","r") as data:
	for thisLine in data:
		if not thisLine.strip():
			continue
		if regexLastLine.search(thisLine) != None:
			continue
		p = regexnum.match(thisLine)
		m = regex.search(thisLine)
		program =""
		code=""
		if p!= None and m!= None:
			temp = [lastInsti , re.sub('\n' , '' ,thisLine[p.end():m.start()]) , re.sub('\n' , '' ,thisLine[m.start():] )]
			institutes.append(temp)
			lastAddition = "program"
			#program =  thisLine[p.end():m.start()]
			#institutes[len(institutes)-1].append(thisLine[m.start():])
			#code = thisLine[m.start():]
			
		if p== None:
			words = thisLine.split()
			if len(words)>=2 and (toString(words[0:2]) in instKeywords or toString(words[0:3]) in instKeywords):
				lastInsti = re.sub('\n' , '' , thisLine)
				lastAddition = "insti"
			else:
				if lastAddition == "program":
					program = re.sub('\n' , '' , thisLine)
					institutes[len(institutes)-1][1] += program

				elif lastAddition == "insti":
					lastInsti = lastInsti + re.sub('\n' , '' , thisLine)
	#for list in institutes:
	#	print list		

	for list in institutes:
		for i in list:
			print i + ";",
		print

