from django.forms import ModelForm

from .models import *


class ProgrammeForm(ModelForm):
	class Meta:
		model = Programmes
		exclude = ('date_created', 'date_updated', 'owner', 'is_public')

	def save(self, commit=True, owner=None):
		if not self.instance.pk:
			if not owner:
				raise TypeError("Owner is required to create a Programme.")
			self.instance.owner = owner
		return super(ProgrammeForm, self).save(commit)

class QueryForm(ModelForm):
	class Meta:
		model = Query
		include = ('query','category')

class PredictionForm(ModelForm):
	class Meta:
		model = Prediction
		include = ('rank','institute','category')

