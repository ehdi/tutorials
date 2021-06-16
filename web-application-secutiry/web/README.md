# Web
This is a simple web page to show the vulnerability of a web application with XSS.

## Local SSL for development
You can create a locally-certificate with [mkcert](https://github.com/FiloSottile/mkcert) tool and put in the certs directory

```shell
mkcert local.dev 127.0.0.1
```

### Attention
The first thing you need to do when you create a new certificate is to put it in the certs directory and then rename the cert files in the nginx.conf file.
