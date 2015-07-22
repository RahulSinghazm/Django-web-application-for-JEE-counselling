import csv
i = 0
data = []
with open('./closingRankData-2012.csv' , 'rb') as csvfile :
	reader = csv.reader(csvfile)
	for row in reader :
		if(i != 0) :
			data.append(row)
		i = 1

#now the "data" list contains the entire JEE 2012 opening - closing rank data and can be used directly
#data[i][0] = programme code
#data[i][1] = General opening rank
#data[i][2] = General closing rank
#data[i][3] = OBC opening rank
#data[i][4] = OBC closing rank
#data[i][5] = SC opening rank
#data[i][6] = SC closing rank
#data[i][7] = ST opening rank
#data[i][8] = ST closing rank
#data[i][9] = GEN-PD opening rank
#data[i][10] = GEN-PD closing rank
#data[i][11] = OBC-PD opening rank
#data[i][12] = OBC-PD closing rank
#data[i][13] = SC-PD opening rank
#data[i][14] = SC-PD closing rank
#data[i][15] = ST-PD opening rank
#data[i][16] = ST-PD closing rank

