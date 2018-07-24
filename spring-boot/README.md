Spring profiles example using application.yml
- set active profile java -jar -Dspring.profiles.active=test target/jar-name

SSL example
- Create SSL certificate: keytool -genkey -keyalg RSA -alias linkedin -keystore keystore.jks -storepass password -validity 4000 -keysize 2048
- Add SSL related properties in application.properties, see server.ssl*
- start application: notice the logs it says https
- test SSL -> **http https://localhost:8080/api/greeting** (this will give error)
**http --verify=no https://localhost:8080/api/greeting** (this will not give error)