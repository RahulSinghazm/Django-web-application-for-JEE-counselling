# !/bin/bash
if [ -f Formatted.txt ];
then
   rm Formatted.txt
fi

if [ -f data_u-2012.csv ];
then
   rm data_u-2012.csv
fi

pdftotext -f 1 -l 1 -layout programmeCode.pdf 	
python formatter.py
pdftotext -f 2 -l 2 -layout programmeCode.pdf 	
python formatter.py
pdftotext -f 3 -l 3 -layout programmeCode.pdf 	
python formatter.py
python update.py>>data_u-2012.csv

rm -f Formatted.txt programmeCode.txt
