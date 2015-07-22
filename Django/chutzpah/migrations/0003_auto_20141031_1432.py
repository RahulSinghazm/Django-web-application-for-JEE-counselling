# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('chutzpah', '0002_auto_20141031_1418'),
    ]

    operations = [
        migrations.AlterModelOptions(
            name='programmes',
            options={'ordering': ['date_created'], 'verbose_name_plural': 'programmes', 'verbose_name': 'programme'},
        ),
        migrations.AlterField(
            model_name='programmes',
            name='name',
            field=models.CharField(max_length=6),
            preserve_default=True,
        ),
    ]
