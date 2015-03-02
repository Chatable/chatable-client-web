from dockerfile/java:oracle-java8
maintainer henry stevens

# Install prerequisites
run apt-get update
run apt-get install -y software-properties-common

# Install java8
run add-apt-repository -y ppa:webupd8team/java
run apt-get update
run echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
run apt-get install -y oracle-java8-installer

# Install tools
run apt-get install -y git maven


# Clone project
run git clone https://github.com/Chatable/chatable-client-web.git

# Expose the http port
expose 12345

# Install Jetty
workdir /tmp

run wget http://download.eclipse.org/jetty/stable-9/dist/jetty-distribution-9.2.9.v20150224.tar.gz
run tar xzvf jetty-distribution-9.2.9.v20150224.tar.gz

# Compile and move GWT project
workdir ../data/chatable-client-web

run mvn gwt:compile
#cmd ["mvn", "gwt:compile"]
#cmd ["cp", "/data/chatable-client-web/war/web.html", "/data/chatable-client-web/target/chatable-client-web-1.0/"]
#cmd ["cp", "-avr", "/data/chatable-client-web/target/chatable-client-web-1.0/", "/tmp/jetty-distribution-9.2.9.v20150224/webapps/"]

run cp /data/chatable-client-web/war/web.html /data/chatable-client-web/target/chatable-client-web-1.0/
run cp -avr /data/chatable-client-web/target/chatable-client-web-1.0/ /tmp/jetty-distribution-9.2.9.v20150224/webapps/



# Start the server w/ app running
workdir /tmp/jetty-distribution-9.2.9.v20150224/

cmd ["java", "-jar", "start.jar", "jetty.port=12345"]


