Oracle Config for maven
Open CMD Prompt to this location
Run:
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2  -Dpackaging=jar  -Dfile=PATHTO/Maven_Config/ojdbc7.jar
Run:
mvn clean package

While that is happening in your %catalina_home%/conf/web.xml
add the following lines to allow cross application functionality
<filter>
		<filter-name>CorsFilter</filter-name>
		<filter-class>org.apache.catalina.filters.CorsFilter</filter-class>
		<init-param>
			<param-name>cors.allowed.origins</param-name>
			<param-value>*</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>CorsFilter</filter-name>
		<url-pattern>/*</url-pattern>

	</filter-mapping>

If this is not working ensure that your tomcat instance is pointing to a path of p2pl