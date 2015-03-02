![][logo]

# Chatable Web Client
The official Chatable Web Chat client

[logo]: https://raw.githubusercontent.com/Chatable/chatable.github.io/master/img/chatableclienticon-small.png "Chatable"

[![Build Status](https://drone.io/github.com/Chatable/chatable-client-web/status.png)](https://drone.io/github.com/Chatable/chatable-client-web/latest)

## Instructions

### 1. Compilation

`mvn gwt:compile`

Then copy web.html into the root of the created dir

### 2. Integration

Create an instace of Jetty    
>Or get it via `wget http://download.eclipse.org/jetty/stable-9/dist/jetty-distribution-9.2.9.v20150224.tar.gz` for Jetty v9.2.9

Copy the created target folder from 1. into the webapps/ folder of the Jetty root

### 3. Deployment

In the Jetty root:

`java -jar start.jar jetty.port=<Your Exposed Port>`
