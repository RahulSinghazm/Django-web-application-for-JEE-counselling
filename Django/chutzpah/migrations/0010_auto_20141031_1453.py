# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('chutzpah', '0009_auto_20141031_1452'),
    ]

    operations = [
        migrations.AlterField(
            model_name='programmes',
            name='name',
            field=models.CharField(choices=[('b1', 'Bomb'), ('c1', 'Cat')], verbose_name='name', max_length=6, blank=True),
            preserve_default=True,
        ),
    ]
