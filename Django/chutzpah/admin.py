from django.contrib import admin

from .models import Programmes


class BookmarkAdmin(admin.ModelAdmin):
	list_display = ('name', 'owner', 'is_public', 'date_updated')
	list_editable = ('is_public',)
	list_filter = ('is_public', 'owner__username')
	search_fields = ['name']
	readonly_fields = ('date_created', 'date_updated')


admin.site.register(Programmes, BookmarkAdmin)