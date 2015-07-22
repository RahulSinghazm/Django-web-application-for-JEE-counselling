from django.conf.urls import patterns, include, url


urlpatterns = patterns('chutzpah.views',
	url(r'^user/(?P<username>[-\w]+)/$', 'programme_user',
		name='chutzpah_programme_user'),
	url(r'^create/$', 'programme_create', name='chutzpah_programme_create'),
	url(r'^edit/(?P<pk>\d+)/$', 'programme_edit', name='chutzpah_programme_edit'),
	url(r'^delete/(?P<pk>\d+)/$', 'programme_delete', name='chutzpah_programme_delete'),
	url(r'^$', 'programme_list', name='chutzpah_programme_list'),
	url(r'^lastyear/$', 'lastyear', name='chutzpah_lastyear'),
	url(r'^branchpred/$', 'branchpred', name='chutzpah_branchpred'),
)