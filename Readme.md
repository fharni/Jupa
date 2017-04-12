# Krip - Kampfrichter Planungstool
Dieses Tool soll bei der Terminplanung von Kampfrichtern helfen. Es ist entwickelt, um auf TomEE 7.0.3 zu laufen.

## tomee.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<tomee>
  <Resource id="KripDS" type="javax.sql.DataSource">
    jdbcUrl = jdbc:hsqldb:hsql://localhost:9001/krip
    jdbcDriver = org.hsqldb.jdbcDriver
  </Resource>
</tomee>
```

## tomcat-users.xml
Hier müssen die Benutzer für die Anmeldung eingetragen werden
```
  <role rolename="loginUser"/>
  <user username="admin" password="admin" roles="loginUser"/>
```

## Datenbank
Die Datenbank liegt im Ordner database. Dort ist auch ein Startskript, um den HSQL Server zu starten. Dieser startet eine DB Instanz für Krip auf dem Port 9001.