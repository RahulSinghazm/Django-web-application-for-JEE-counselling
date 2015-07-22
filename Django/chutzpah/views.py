from django.contrib.auth.decorators import login_required
from django.shortcuts import render
from django.core.exceptions import PermissionDenied
from django.contrib.auth.models import User
from django.shortcuts import get_object_or_404, redirect, render

from .forms import ProgrammeForm , QueryForm, PredictionForm
from .models import Programmes , Query, Prediction

##add exit button
##already added

def programme_list(request):
	programmes = Programmes.public.all()
	context = {'programmes': programmes}
	return render(request, 'chutzpah/programme_list.html', context)

def programme_user(request, username):
	user = get_object_or_404(User, username=username)
	if request.user == user:
		programmes = Programmes.public.filter(owner__username=username)
	context = {'programmes': programmes, 'owner': user}
	return render(request, 'chutzpah/programme_user.html', context)

@login_required
def programme_create(request):
	if request.method == 'POST':
		form = ProgrammeForm(data=request.POST)
		data=request.POST
		if form.is_valid():
			if not Programmes.objects.all().filter(name__exact = data['name'], owner__username = request.user.username).exists():
				form.save(owner=request.user)
		return redirect('chutzpah_programme_user',
			username=request.user.username)

	else:
		form = ProgrammeForm()
	return render(request, 'chutzpah/form.html',
		{'form': form, 'create': True})

@login_required
def programme_edit(request, pk):
	programme = get_object_or_404(Programmes, pk=pk)
	if programme.owner != request.user and not request.user.is_superuser:
		raise PermissionDenied
	if request.method == 'POST':
		form = ProgrammeForm(instance=programme, data=request.POST)
		if form.is_valid():
			form.save()
			return redirect('chutzpah_programme_user',
				username=request.user.username)
	else:
		form = ProgrammeForm(instance=programme)
	return render(request, 'chutzpah/form.html',
		{'form': form, 'create': False, 'programme': programme})

@login_required
def programme_delete(request, pk):
	programme = get_object_or_404(Programmes, pk=pk)
	if programme.owner != request.user:
		raise PermissionDenied
	programme.delete()
	return redirect('chutzpah_programme_user',
		username=request.user.username)
import csv
database_ = []
with open('./chutzpah/data_u-2012.csv' , 'r') as csvfile :
	reader = csv.reader(csvfile, delimiter = ';')
	for row in reader :
		database_.append(row)

database =[]
for x in range(len(database_)) :
	database.append(database_[x][2] + " - " + database_[x][0] + " - " + database_[x][1])

i = 0
pata = []
with open('./chutzpah/closingRankData-2012.csv' , 'r') as csvfile :
	reader = csv.reader(csvfile)
	for row in reader :
		if(i != 0) :
			pata.append(row)
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




@login_required
def lastyear(request):
	if request.method == 'POST':
		form = QueryForm(data=request.POST)
		data=request.POST
		if form.is_valid():
			query = data['query']
			category = data['category']
			i = database.index(query)
			opening = pata[i][2*int(category) -1]
			closing = pata[i][2*int(category)]
			return render(request,'chutzpah/lastyear.html',{'form': form , 'opening':opening , 'closing':closing })
	else:
		form = QueryForm()
	return render(request, 'chutzpah/lastyear.html',{'form': form})
@login_required
def branchpred(request):
	if request.method == 'POST':
		form = PredictionForm(data=request.POST)
		data=request.POST
		rank = data['rank']
		institute = data['institute']
		category = data['category']
		result = []

		for j in range(0,len(pata)):
			if database_[j][0].find(institute)>=0 or institute.find(database_[j][0])>=0 :
				if int(pata[j][2*int(category)]) > int(rank) :
					result.append(database[j])
		return render(request, 'chutzpah/branchpred.html',{'form':form,'result': result})
	else:
		form = PredictionForm()
	return render(request, 'chutzpah/branchpred.html',{'form': form})
