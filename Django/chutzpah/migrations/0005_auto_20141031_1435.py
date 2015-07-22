# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('chutzpah', '0004_auto_20141031_1435'),
    ]

    operations = [
        migrations.AlterField(
            model_name='programmes',
            name='name',
            field=models.CharField(max_length=6),
            preserve_default=True,
        ),
    ]
