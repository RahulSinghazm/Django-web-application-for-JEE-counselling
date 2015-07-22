# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('chutzpah', '0005_auto_20141031_1435'),
    ]

    operations = [
        migrations.AlterField(
            model_name='programmes',
            name='is_public',
            field=models.BooleanField(verbose_name='public', default=False),
            preserve_default=True,
        ),
    ]
