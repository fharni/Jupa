# JuPa - Ju-Jutsu Passverwaltung
Dieses Tool soll alle Einträge des elektronischen Mitgliedsausweises verwalten. Es ist entwickelt, um auf TomEE 7.0.3 zu laufen.

## tomee.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<tomee>
  <Resource id="jdbc/JuPaDS" type="javax.sql.DataSource">
    jdbcUrl = jdbc:hsqldb:hsql://localhost:9001/jupa
    jdbcDriver = org.hsqldb.jdbcDriver
  </Resource>
</tomee>
```
Hier kann natürlich auch jede andere Datenquelle eingebunden werden, in der das JuPa Schema vorhanden ist. Zusätzlich sollte dann aber auch noch der JDBC Treiber im lib Ordner des TomEE liegen.

## Authentisierung
Erzeugen Sie eine Datei jaas.conf im Ordner %TOMEE_HOME%\conf mit dem Inhalt:
```
JuPaLogin {
  org.apache.openejb.core.security.jaas.CDILoginModule required    
    delegate="de.falkharnisch.web.jupa.jaas.JuPaLoginModule"
    loginModuleAsCdiBean=false
	debug=true;
};
```

In der Datei standalone.bat fügen Sie hinter :okHome folgendes ein:
```
set JAVA_OPTS=%JAVA_OPTS% -Djava.security.auth.login.config=%CATALINA_HOME%/conf/jaas.conf
```

## Datenbank
Die Datenbank liegt im Ordner database. Dort ist auch ein Startskript, um den HSQL Server zu starten. Dieser startet eine DB Instanz für Jupa auf dem Port 9001. Tests sollten auf einer anderen Instanz vorgenommen werden und diese nur, um Strukturänderungen zu persistieren. Zusätzlich sind die SQL Skripte für die Tabelle, Indizes und Stammdaten im Order sql abzulegen.
