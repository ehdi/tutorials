Web
================

##How to load the index.html to Nginx Docker
```
docker run --name was-nginx -v /path/to/index.html:/usr/share/nginx/html:ro -d -p 8181:80 nginx
```
