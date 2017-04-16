# Krip - Kampfrichter Planungstool
Dieses Tool soll bei der Terminplanung von Kampfrichtern helfen. Es ist entwickelt, um auf TomEE 7.0.3 zu laufen.

## tomee.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<tomee>
  <Resource id="jdbc/KripDS" type="javax.sql.DataSource">
    jdbcUrl = jdbc:hsqldb:hsql://localhost:9001/krip
    jdbcDriver = org.hsqldb.jdbcDriver
  </Resource>
</tomee>
```
Hier kann natürlich auch jede andere Datenquelle eingebunden werden, in der das Krip Schema vorhanden ist. Zusätzlich sollte dann aber auch noch der JDBC Treiber im lib Ordner des TomEE liegen.

## server.xml
Hier müssen für die Anmeldung ein neuer Realm eingetragen werden, der die Benutzer und Rollen aus der Datenbank ausliest.
```
<Realm name="KripDatabaseRealm" className="org.apache.catalina.realm.DataSourceRealm"
  dataSourceName="jdbc/KripDS"
  userTable="user" userNameCol="username" userCredCol="password"
  userRoleTable="user_roles" roleNameCol="role" />
```

## Datenbank
Die Datenbank liegt im Ordner database. Dort ist auch ein Startskript, um den HSQL Server zu starten. Dieser startet eine DB Instanz für Krip auf dem Port 9001. Tests sollten auf einer anderen Instanz vorgenommen werden und diese nur, um Strukturänderungen zu persistieren. Zusätzlich sind die SQL Skripte für die Tabelle, Indizes und Stammdaten im Order sql abzulegen.
