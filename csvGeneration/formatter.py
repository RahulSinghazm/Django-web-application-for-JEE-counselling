import re
skippingKeywords = ["Table" , "Course" , "Code"]
instKeywords = ["Indian Institute" , "Indian School" , "Institute of Technology,"]
regex = re.compile("[A-Z][0-9][0-9][0-9][0-9]?")
regexnum = re.compile("[0-9][0-9]([0-9])?")
regtable = re.compile("^[A-Z][A-Z][A-Z]")
raw = open("programmeCode.txt",'rb')
formattedFile = open("Formatted.txt" , 'a')
leftColoumn =[]
rightColoumn = []
for line in raw.readlines():
    p = regtable.search(line)
    if p!=None:
        break
    words = line.split()
    if len(words) == 0 or (len(words) == 1 and words[0].isdigit()):
        continue
    else:
        if words[0] in skippingKeywords:
            continue
    leftColoumn.append(re.sub("^\s" , "",re.sub("\s\s+" , " ", line[0:72])))
    rightColoumn.append(re.sub("^\s" , "",re.sub("\s\s+" , " ", line[72:])))

for i in leftColoumn:
    formattedFile.write(i)
    formattedFile.write('\n')
for j in rightColoumn:
    formattedFile.write(j)
    formattedFile.write('\n')
formattedFile.close()