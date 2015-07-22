from django.contrib.auth.decorators import login_required
from django.shortcuts import render
from django.core.exceptions import PermissionDenied
from django.contrib.auth.models import User
from django.shortcuts import get_object_or_404, redirect, render

from .forms import ProgrammeForm
from .models import Programmes


def programme_list(request):
	programmes = Programmes.public.all()
	context = {'programmes': programmes}
	return render(request, 'chutzpah/programme_list.html', context)

def programme_user(request, username):
	user = get_object_or_404(User, username=username)
	if request.user == user:
		programmes = user.programmes.all()
	else:
		programmes = Programmes.public.filter(owner__username=username)
	context = {'programmes': programmes, 'owner': user}
	return render(request, 'chutzpah/programme_user.html', context)

@login_required
def programme_create(request):
	if request.method == 'POST':
		form = ProgrammeForm(data=request.POST)
		if form.is_valid():
			if not Programmes.objects.all().filter(name__exact = form.name , owner = request.user).exists():
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
	