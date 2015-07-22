from django.conf.urls import patterns, include, url
from django.contrib import admin
admin.autodiscover()
import os
mypath = os.path.abspath(os.path.join(os.path.dirname( __file__ ), '..'))
urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'mysite.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),

    url(r'^admin/', include(admin.site.urls)),
    url(r'^', include('chutzpah.urls')),
    
)
urlpatterns += patterns('django.contrib.auth.views',
    url(r'^login/$', 'login', {'template_name': 'login.html'},
        name='mysite_login'),
    url(r'^logout/$', 'logout', {'next_page': '/'}, name='mysite_logout'),
)
