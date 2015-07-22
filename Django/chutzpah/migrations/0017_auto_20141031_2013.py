# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('chutzpah', '0016_query'),
    ]

    operations = [
        migrations.CreateModel(
            name='DumbProgrammes',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False, auto_created=True, verbose_name='ID')),
                ('name', models.CharField(max_length=150)),
                ('institute', models.CharField(max_length=150)),
                ('code', models.CharField(max_length=150)),
                ('rank_ge_open', models.CharField(max_length=150)),
                ('rank_obc_open', models.CharField(max_length=150)),
                ('rank_sc_open', models.CharField(max_length=150)),
                ('rank_st_open', models.CharField(max_length=150)),
                ('rank_ge_pd_open', models.CharField(max_length=150)),
                ('rank_obc_pd_open', models.CharField(max_length=150)),
                ('rank_sc_pd_open', models.CharField(max_length=150)),
                ('rank_st_pd_open', models.CharField(max_length=150)),
                ('rank_ge_close', models.CharField(max_length=150)),
                ('rank_obc_close', models.CharField(max_length=150)),
                ('rank_sc_close', models.CharField(max_length=150)),
                ('rank_st_close', models.CharField(max_length=150)),
                ('rank_ge_pd_close', models.CharField(max_length=150)),
                ('rank_obc_pd_close', models.CharField(max_length=150)),
                ('rank_sc_pd_close', models.CharField(max_length=150)),
                ('rank_st_pd_close', models.CharField(max_length=150)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Prediction',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False, auto_created=True, verbose_name='ID')),
                ('rank', models.CharField(max_length=150)),
                ('institute', models.CharField(verbose_name='Institute', choices=[('Indian Institute of Technology Bhubaneswar', 'Indian Institute of Technology Bhubaneswar'), ('Indian Institute of Technology Bombay', 'Indian Institute of Technology Bombay'), ('Indian Institute of Technology Delhi', 'Indian Institute of Technology Delhi'), ('Indian Institute of Technology Gandhinagar', 'Indian Institute of Technology Gandhinagar'), ('Indian Institute of Technology Guwahati', 'Indian Institute of Technology Guwahati'), ('Indian Institute of Technology Hyderabad', 'Indian Institute of Technology Hyderabad'), ('Indian Institute of Technology Indore', 'Indian Institute of Technology Indore'), ('Indian Institute of Technology Kanpur', 'Indian Institute of Technology Kanpur'), ('Indian Institute of Technology Kharagpur', 'Indian Institute of Technology Kharagpur'), ('Indian Institute of Technology Madras', 'Indian Institute of Technology Madras'), ('Indian Institute of Technology Mandi', 'Indian Institute of Technology Mandi'), ('Indian Institute of Technology Patna', 'Indian Institute of Technology Patna'), ('Indian Institute of Technology Rajasthan', 'Indian Institute of Technology Rajasthan'), ('Indian Institute of Technology Roorkee', 'Indian Institute of Technology Roorkee'), ('Indian Institute of Technology Ropar', 'Indian Institute of Technology Ropar'), ('Indian School of Mines, Dhanbad', 'Indian School of Mines, Dhanbad'), ('Institute of Technology, Banaras Hindu University,Varanasi', 'Institute of Technology, Banaras Hindu University,Varanasi')], max_length=150)),
                ('category', models.CharField(verbose_name='Program', choices=[('1', 'General'), ('2', 'OBC'), ('3', 'SC'), ('4', 'ST'), ('5', 'General-PD'), ('6', 'OBC-PD'), ('7', 'SC-PD'), ('8', 'ST-PD')], max_length=150)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.RemoveField(
            model_name='query',
            name='closing',
        ),
        migrations.RemoveField(
            model_name='query',
            name='opening',
        ),
    ]
