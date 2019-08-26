# JuPa - Ju-Jutsu Passverwaltung
Dieses Tool soll alle Einträge des elektronischen Mitgliedsausweises verwalten. Es ist entwickelt, um auf Wildfly 16 zu laufen.

## standalone.xml
```

```
Hier kann natürlich auch jede andere Datenquelle eingebunden werden, in der das JuPa Schema vorhanden ist. Zusätzlich sollte dann aber auch noch der JDBC Treiber im lib Ordner des TomEE liegen.

## Datenbank
Die Datenbank liegt im Ordner database. Dort ist auch ein Startskript, um den HSQL Server zu starten. Dieser startet eine DB Instanz für Jupa auf dem Port 9001. Tests sollten auf einer anderen Instanz vorgenommen werden und diese nur, um Strukturänderungen zu persistieren. Zusätzlich sind die SQL Skripte für die Tabelle, Indizes und Stammdaten im Order sql abzulegen.
