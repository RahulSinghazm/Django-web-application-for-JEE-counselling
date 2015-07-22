from django.contrib.auth.models import User
from django.db import models
from django.utils.encoding import python_2_unicode_compatible
from django.utils.timezone import now
import csv
data = []
with open('./chutzpah/data_u-2012.csv' , 'r') as csvfile :
	reader = csv.reader(csvfile, delimiter = ';')
	for row in reader :
		data.append(row)

for x in range(len(data)) :
	data[x][0] = data[x][2] + " - " + data[x][0] + " - " + data[x][1]


p_choice = (('b1','Bomb'),('c1','Cat'),)
@python_2_unicode_compatible
class PublicProgrammesManager(models.Manager):
	def get_queryset(self):
		qs = super(PublicProgrammesManager, self).get_queryset()
		return qs.filter(is_public=False)

@python_2_unicode_compatible
class Programmes(models.Model):
	progs = [''] * len(data)
	PROGRAMME_CHOICES =()
	for x in range(len(progs)):
		PROGRAMME_CHOICES = ((data[x][0], data[x][0]),) + PROGRAMME_CHOICES
	PROGRAMME_CHOICES = PROGRAMME_CHOICES[::-1]
	
	name = models.CharField( 'name',max_length=150,choices=PROGRAMME_CHOICES)
	#name = models.CharField('name', max_length=255)
	is_public = models.BooleanField('public', default=False)
	date_created = models.DateTimeField('date created')
	date_updated = models.DateTimeField('date updated')
	owner = models.ForeignKey(User, verbose_name='owner',
		related_name='programmes')

	objects = models.Manager()
	public = PublicProgrammesManager()

	class Meta:
		verbose_name = 'programme'
		verbose_name_plural = 'programmes'
		ordering = ['date_created']

	def __str__(self):
		return '%s' % (self.name)

	def save(self, *args, **kwargs):
		if not self.id:
			self.date_created = now()
		self.date_updated = now()
		super(Programmes, self).save(*args, **kwargs)
'''
class Programmes(models.Model):
	progs = [''] * len(data)
	PROGRAMME_CHOICES =()
	for x in range(len(progs)):
		PROGRAMME_CHOICES = ((progs[x], data[x][0]),) + PROGRAMME_CHOICES
	PROGRAMME_CHOICES = PROGRAMME_CHOICES[::-1]
	
	name = models.CharField( 
		max_length=6,choices=PROGRAMME_CHOICES,
		default='B4110')
	owner = models.ForeignKey(User)
'''
CATEGORY_CHOICES =(
		('1','General'),
		('2','OBC'),
		('3','SC'),
		('4','ST'),
		('5','General-PD'),
		('6','OBC-PD'),
		('7','SC-PD'),
		('8','ST-PD'),
		)
class Query(models.Model):
	progs = [''] * len(data)
	PROGRAMME_CHOICES =()
	for x in range(len(progs)):
		PROGRAMME_CHOICES = ((data[x][0], data[x][0]),) + PROGRAMME_CHOICES
	PROGRAMME_CHOICES = PROGRAMME_CHOICES[::-1]
	
	query = models.CharField( 'Institute',max_length=150,choices=PROGRAMME_CHOICES)
	category=models.CharField('Category', max_length=10,choices = CATEGORY_CHOICES)
	
class Prediction(models.Model):
	rank = models.CharField(max_length=150)
	INSTITUTE_CHOICES = (('Indian Institute of Technology Bhubaneswar' , 'Indian Institute of Technology Bhubaneswar'),
('Indian Institute of Technology Bombay' , 'Indian Institute of Technology Bombay'),
('Indian Institute of Technology Delhi' , 'Indian Institute of Technology Delhi'),
('Indian Institute of Technology Gandhinagar' , 'Indian Institute of Technology Gandhinagar'),
('Indian Institute of Technology Guwahati' , 'Indian Institute of Technology Guwahati'),
('Indian Institute of Technology Hyderabad' , 'Indian Institute of Technology Hyderabad'),
('Indian Institute of Technology Indore' , 'Indian Institute of Technology Indore'),
('Indian Institute of Technology Kanpur' , 'Indian Institute of Technology Kanpur'),
('Indian Institute of Technology Kharagpur' , 'Indian Institute of Technology Kharagpur'),
('Indian Institute of Technology Madras' , 'Indian Institute of Technology Madras'),
('Indian Institute of Technology Mandi' , 'Indian Institute of Technology Mandi'),
('Indian Institute of Technology Patna' , 'Indian Institute of Technology Patna'),
('Indian Institute of Technology Rajasthan' , 'Indian Institute of Technology Rajasthan'),
('Indian Institute of Technology Roorkee' , 'Indian Institute of Technology Roorkee'),
('Indian Institute of Technology Ropar' , 'Indian Institute of Technology Ropar'),
('Indian School of Mines, Dhanbad' , 'Indian School of Mines, Dhanbad'),
('Institute of Technology, Banaras Hindu University,Varanasi' , 'Institute of Technology, Banaras Hindu University,Varanasi'),)
	institute = models.CharField( 'Institute',max_length=150,choices=INSTITUTE_CHOICES)
	category = models.CharField( 'Category',max_length=150,choices=CATEGORY_CHOICES)
'''
i = 0
data_ = []
with open('./chutzpah/closingRankData-2012.csv' , 'r') as csvfile :
	reader = csv.reader(csvfile)
	for row in reader :
		if(i != 0) :
			data_.append(row)
		i = 1


class DumbProgrammes(models.Model):
	name = models.CharField( max_length=150)
	institute = models.CharField( max_length=150)
	code = models.CharField( max_length=150)
	rank_ge_open = models.CharField(max_length=150)
	rank_obc_open = models.CharField(max_length=150)
	rank_sc_open = models.CharField(max_length=150)
	rank_st_open = models.CharField(max_length=150)
	rank_ge_pd_open = models.CharField(max_length=150)
	rank_obc_pd_open = models.CharField(max_length=150)
	rank_sc_pd_open = models.CharField(max_length=150)
	rank_st_pd_open = models.CharField(max_length=150)
	rank_ge_close = models.CharField(max_length=150)
	rank_obc_close = models.CharField(max_length=150)
	rank_sc_close = models.CharField(max_length=150)
	rank_st_close = models.CharField(max_length=150)
	rank_ge_pd_close = models.CharField(max_length=150)
	rank_obc_pd_close = models.CharField(max_length=150)
	rank_sc_pd_close = models.CharField(max_length=150)
	rank_st_pd_close = models.CharField(max_length=150)

for x in range(len(data)):
	p=DumbProgrammes(
		name = data[x][0],
		institute = data[x][1],
		code = data_[x][0],
		rank_ge_open = data_[x][1],
		rank_ge_close = data_[x][2],
		rank_obc_open = data_[x][3],
		rank_obc_close = data_[x][4],
		rank_sc_open = data_[x][5],
		rank_sc_close = data_[x][6],
		rank_st_open = data_[x][7],
		rank_st_close = data_[x][8],
		rank_ge_pd_open = data_[x][9],
		rank_ge_pd_close = data_[x][10],
		rank_obc_pd_open = data_[x][11],
		rank_obc_pd_close = data_[x][12],
		rank_sc_pd_open = data_[x][13],
		rank_sc_pd_close = data_[x][14],
		rank_st_pd_open = data_[x][15],
		rank_st_pd_close = data_[x][16],
		)
	p.save()
'''
