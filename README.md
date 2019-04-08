# Peer2PeerServer

Runs on the localhost at the default tomcat port (this may be different for each user, check the Tomcat Web.xml for your config)

In order to run properly you will need to install the OJDBC for this project the instructions are as follows:

Run:
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2  -Dpackaging=jar  -Dfile=PATHTO/Maven_Config/ojdbc7.jar
Run:
mvn clean package

replace PATHTO with the appropriate filepath


Configure Tomcat Server:
insert the following filter into your tomcat server web.xml found in %catalina_home%
<filter>
  <filter-name>CorsFilter</filter-name>
  <filter-class>org.apache.catalina.filters.CorsFilter</filter-class>
  <init-param>
    <param-name>cors.allowed.origins</param-name>
    <param-value>http://localhost4200</param-value>
  </init-param>
  <init-param>
        <param-name>cors.allowed.methods</param-name>
        <param-value>GET,POST,PUT,DELETE,HEAD,OPTIONS</param-value>
    </init-param>
    <init-param>
        <param-name>cors.allowed.headers</param-name>
        <param-value>Content-Type,X-Requested-With,Accept,Authorization,Origin,Access-Control-Request-Method,Access-Control-Request-Headers</param-value>
    </init-param>
    <init-param>
        <param-name>cors.exposed.headers</param-name>
        <param-value>Access-Control-Allow-Origin,Access-Control-Allow-Credentials</param-value>
    </init-param>
    <init-param>
    <param-name>cors.support.credentials</param-name>
    <param-value>true</param-value>
    </init-param>
</filter>
<filter-mapping>
  <filter-name>CorsFilter</filter-name>
  <url-pattern>/</url-pattern>
</filter-mapping>


