# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('chutzpah', '0018_auto_20141031_2023'),
    ]

    operations = [
        migrations.DeleteModel(
            name='DumbProgrammes',
        ),
    ]
