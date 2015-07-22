# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('chutzpah', '0017_auto_20141031_2013'),
    ]

    operations = [
        migrations.AlterField(
            model_name='prediction',
            name='category',
            field=models.CharField(max_length=150, choices=[('1', 'General'), ('2', 'OBC'), ('3', 'SC'), ('4', 'ST'), ('5', 'General-PD'), ('6', 'OBC-PD'), ('7', 'SC-PD'), ('8', 'ST-PD')], verbose_name='Category'),
            preserve_default=True,
        ),
    ]
