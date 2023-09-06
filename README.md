# B O doppel N
**Mitglieder:**
* Youssef Abdellaoui
* Philipp Amendt
* Jan Höltje

# Inhaltsverzeichnis
- [1. Systemdokumentation](#1-systemdokumentation)
    - [1.1 Einleitung](#11-einleitung)
    - [1.2 Kontextabgrenzung](#12-kontextabgrenzung)
    	- [1.2.1 Visualisierung](#121-visualisierung) 	
        - [1.2.2 Fachlicher Kontext](#122-fachlicher-kontext)
        - [1.2.3 Technischer Kontext](#123-technischer-kontext)
    - [1.3 Lösungsstrategie](#13-lösungsstrategie)
        - [1.3.1 Technologieentscheidungen](#131-technologieentscheidungen)
        - [1.3.2 Entwurfsmuster](#132-entwurfsmuster)
        - [1.3.3 Qualitätsanforderungen](#133-qualitätsanforderungen)
        - [1.3.4 Organisationsentscheidungen](#134-organisationsentscheidungen)
    - [1.4 Bausteinsicht](#14-bausteinsicht)
    - [1.5 Laufzeitsicht](#15-laufzeitsicht)
    - [1.6 Infrastruktursicht](#16-infrastruktursicht)
    - [1.7 Querschnittliche Konzepte](#17-querschnittliche-konzepte)
    - [1.8 Schnittstellen](#18-schnittstellen)
        - [1.8.1 Translation Bot](#181-translation-bot)
      	- [1.8.2 Weather Bot](#182-weather-bot)
        - [1.8.3 Wiki Bot](#183-wiki-bot)
        - [1.8.4 SQL-Datenbank](#184-sql-datenbank)
    - [1.9 Risiken und technische Schulden](#19-risiken-und-technische-schulden)
    - [1.10 Erweiterungen](#110-erweiterungen)
    - [1.11 Fehlerbehebung](#111-fehlerbehebung)
    - [1.12 Installationsanleitung](#112-installationsanleitung)
    - [1.13 Konfiguration](#113-konfiguration)
- [2 Bot Dokumentation](#2-bot-dokumentation)
    - [2.1 Übersetzer-Bot](#21-übersetzer-bot)
        - [2.1.1 Kontextabgrenzung](#211-kontextabgrenzung)
        - [2.1.2 Lösungsstrategie](#212-lösungsstrategie)
        - [2.1.3 Bausteinsicht](#213-bausteinsetz)
        - [2.1.4 Laufzeitsicht](#214-laufzeitsicht)
    - [2.2 Wetter-Bot](#22-wetter-bot)
        - [2.2.1 Kontextabgrenzung](#221-kontextabgrenzung)
        - [2.2.2 Lösungsstrategie](#222-lösungsstrategie)
        - [2.2.3 Bausteinsicht](#223-bausteinsetz)
        - [2.2.4 Laufzeitsicht](#224-laufzeitsicht)
    - [2.3 Wiki-Bot](#23-wiki-bot)
        - [2.3.1 Kontextabgrenzung](#231-kontextabgrenzung)
        - [2.3.2 Lösungsstrategie](#232-lösungsstrategie)
        - [2.3.3 Bausteinsicht](#233-bausteinsetz)
        - [2.3.4 Laufzeitsicht](#234-aufzeitsicht)

# 1. Systemdokumentation

## 1.1 Einleitung

In einer Zeit, in der Technologie und künstliche Intelligenz immer präsenter werden, suchen viele Menschen nach multifunktionalen Lösungen, um ihren Alltag zu erleichtern. In diesem Kontext steht die Software „PERSA“, der PERsönliche Service Assistent, als eine innovative Lösung, die eine Kombination verschiedener Chatbot-Funktionen bietet, um den Anforderungen und Wünschen der Benutzer gerecht zu werden.

Die Software „PERSA“ repräsentiert einen Mehrzweck-Chatbot, der verschiedene Bot-Funktionen integriert, um den Benutzern ein nützliches und vielseitiges Erlebnis zu bieten. Dazu gehören ein Wetterbot, ein Wikibot und ein Übersetzerbot. Zudem besteht die Möglichkeit, weitere Chatbots einfach und nahtlos in das Gesamtsystem zu integrieren.

Nach erfolgreicher Anmeldung werden die Nutzer vom Chatbot Persa begrüßt. Anschließend kann sich der Nutzer für einen der Chatbots entscheiden und diesen direkt ansprechen.

Das integrierte Modul "Wetterbot" ruft Echtzeit-Wetterinformationen sowie Prognosen aus einer verlässlichen Quelle ab und stellt diese dem Benutzer zur Verfügung.

Mithilfe des Wikibots hat der Benutzer die Möglichkeit, unkompliziert Informationen zu bestimmten Themen, Personen, Orten oder Ereignissen aus Wikipedia abzurufen.

Der Übersetzerbot ermöglicht es den Benutzern, Texte zwischen verschiedenen Sprachen zu übersetzen.

Zu den Stakeholdern des Chatbots zählen sowohl die Benutzer als auch die Administratoren. Alle Stakeholder erwarten eine fehlerfreie und störungsfreie Anwendung.


## 1.2 Kontextabgrenzung

### 1.2.1 Visualisierung

Um eine Kontextabgrenzung der Software zu ermöglichen, sollte im Vorhinein eine Darstellung der einzelnen Akteure und deren Zusammenhänge oberflächlich vorgenommen werden. In dieser Arbeit wird dies mithilfe des beigefügten Diagramms visualisiert.

![img.png](Kontextabgrenzung.png)

Im vorliegenden Diagramm sind die beiden Hauptakteure, nämlich die Benutzer und der Administrator, dargestellt. Der Administrator hat die Befugnis, das Gesamtsystem zu initialisieren und zusätzliche Chatbots hinzuzufügen. Sowohl der Administrator als auch der Benutzer haben die Möglichkeit, sich anzumelden und einen bestimmten Chatbot auszuwählen, den sie ansprechen möchten. Die ausgetauschten Chatnachrichten werden in einer SQLite-Datenbank gespeichert und können zu späterem Zeitpunkt abgerufen werden.

Sobald der Benutzer oder der Administrator das Chatsystem nutzt, erfolgt anhand der Nachricht eine Identifizierung des relevanten Chatbots. Auf Grundlage dieser Identifizierung kann eine entsprechende Anfrage an die ausgewählte REST-API gesendet werden. Die REST-API wiederum ruft die erforderlichen Informationen im Webbrowser ab und leitet sie zurück an das Chatsystem, das die Antwort dem Benutzer oder dem Administrator präsentiert.

### 1.2.2 Fachlicher Kontext

Der Nutzer interagiert aktiv mit dem System und erhält im Anschluss entsprechende Rückmeldungen. Diese Kommunikation wird sorgfältig in einer Datenbank protokolliert und kann zu einem späteren Zeitpunkt bei Bedarf eingesehen werden. Die Anfrage des Nutzers wird durch die Verwendung einer Application Programming Interface (API) verarbeitet, wodurch die Erzeugung einer Antwort erfolgt und diese schließlich dem Nutzer zur Verfügung gestellt wird.

### 1.2.3 Technischer Kontext

Der Benutzer interagiert mit dem System über das Control Panel. Das System fungiert dabei nicht nur als Schnittstelle für die Systemantworten, sondern verwendet auch gezielte API-Abfragen, um Daten mit der API auszutauschen. Darüber hinaus erfolgt die Verbindung zwischen dem System und der Datenbank über eine SQL-Verbindung.

## 1.3 Lösungsstrategie

Die Lösungsstrategie umfasst die generellen Entscheidungen und Herangehensweisen bei der Realisierung des Systems.

### 1.3.1 Technologieentscheidungen

Bei Projektbeginn ist es von essenzieller Bedeutung, im Vorfeld sorgfältig über die eingesetzten Technologien nachzudenken.

Für die Entwicklung dieses Projekts wurde die Programmiersprache Java als zentrales Element ausgewählt. Java zeichnet sich insbesondere durch seine objektorientierte Nutzungsoberfläche aus, wodurch eine Struktur aus Klassen und Objekten geschaffen werden kann, die exakt den Anforderungen der geplanten Architektur entspricht.

Hinsichtlich der Datenverwaltung fiel die Wahl auf eine SQLite-Datenbank. Diese Entscheidung gründet sich auf ihrer Einfachheit sowie der Fähigkeit von SQLite, lokal ohne Server zu operieren.

### 1.3.2 Entwurfsmuster

Mit dem vorrangigen Ziel, die Zuverlässigkeit, Skalierbarkeit und Erweiterbarkeit des Systems sicherzustellen, wurden bewährte Design- und Architekturmuster in die Implementierung integriert. Diese Maßnahme trägt maßgeblich zur Verbesserung der Code-Wartbarkeit bei, ermöglicht die flexible Austauschbarkeit einzelner Komponenten und schafft eine klare Zuordnung von Aufgaben zu spezifischen Systemteilen.

Weitere umfassende Informationen über die verwendeten Muster und ihre Anwendung finden sich im Abschnitt "Querschnittliche Konzepte".

### 1.3.3 Qualitätsanforderungen

Um die verschiedenen Qualitätsanforderungen zu erfüllen, wurden verschiedene Schritte unternommen. Der Programmcode wurde gezielt so gestaltet, dass er sauber und übersichtlich ist, was eine unkomplizierte Wartung und Erweiterung ermöglicht. Zusätzlich wurde ein besonderer Fokus auf die Schaffung einer hochwertigen Benutzererfahrung gelegt.

### 1.3.4 Organisationsentscheidungen

Um eine reibungslose und gleichzeitige Zusammenarbeit mehrerer Teammitglieder sicherzustellen, wurden im Vorfeld klare Aufgaben und Teilmodule definiert, die zur erfolgreichen Umsetzung des Systems erforderlich waren. Mit Blick auf die spätere Integration dieser Module wurden präzise Schnittstellen festgelegt, die jedem Modul geplante Eingaben und Ausgaben zuwiesen.

Für die koordinierte Zusammenarbeit wurde das Tool GitHub eingesetzt, was jedem Teammitglied stets Zugriff auf den aktuellen Code ermöglichte. Darüber hinaus konnte bei Bedarf auch auf ältere Versionen des Codes zugegriffen werden.

## 1.4 Bausteinsicht

![image](https://github.com/FHDW-23SS-SMA-IFWS421B/BonnYPJ/assets/128595643/8c019632-8ae3-4a86-acf0-b80615614ae3)

Das Programm besteht aus insgesamt 17 Bausteinen, die in einer hierarchischen Struktur zusammenarbeiten. Als zentraler Baustein fungiert die Klasse "Session", die als Einstiegspunkt des Programms dient. Der "Authenticator" ist dafür zuständig, die Überprüfung von Benutzername und Passwort durchzuführen.

Der "IOHandler" wird verwendet, um Benutzereingaben zu erfassen und alle Ausgaben zu verarbeiten.

Die "DBBuilding"-Klasse hat die Aufgabe, beim Programmstart zu überprüfen, ob die erforderlichen Datenbanken bereits vorhanden sind. Falls nicht, werden sie mit den erforderlichen Daten erstellt. Die "DBHandler"-Klasse liest und schreibt in die Datenbankprotokolle, liest Benutzernamen und Passwörter, aktiviert und deaktiviert Chatbots und zeigt die Chatbots und ihren Status an. Die "DBImplementation"-Klasse stellt die Verbindung zur Datenbank her und führt alle SQL-Anweisungen aus den Klassen "DBBuilding" und "DBHandler" aus.

Die "BotCaller"-Klasse analysiert die Benutzereingabe und ruft die entsprechenden Chatbots auf, darunter der Wiki-Bot, der Wetter-Bot, der Übersetzungs-Bot und der Persa-Bot. Von diesen Chatbots, mit Ausnahme des Persa-Bots, da es sich bei diesem um das Verwaltungssystem handelt, wird eine Verbindung zu externen Schnittstellen hergestellt. Die "APIConnect"-Klasse ermöglicht es den einzelnen Chatbots, auf diese externen Schnittstellen zuzugreifen und die benötigten Informationen abzurufen.

In einer zusammenfassenden Betrachtung wird deutlich, dass die Bausteinsicht eine hochgradig abstrakte Darstellungsform darstellt. Diese Abstraktion erlaubt es, Informationen über den zugrundeliegenden Quellcode auszutauschen, ohne dabei allzu viele technische Implementierungsdetails offenlegen zu müssen. Diese Herangehensweise erleichtert erheblich das Verständnis der strukturellen Komponenten und fördert die effektive Kommunikation über das Gesamtsystem auf einer übergeordneten, konzeptionellen Ebene.

## 1.5 Laufzeitsicht

Die Laufzeitsicht kann in zwei Aspekte unterteilt werden: die Interaktionen des Benutzers und den Ablauf des Programms.

Der Ablauf der Benutzerinteraktion bezieht sich insbesondere auf den Anmeldevorgang. Um eine Verbindung zwischen beiden Sequenzdiagrammen herzustellen, wird angenommen, dass der Login erfolgreich war, sodass mit dem ersten Schritt der Benutzereingabe begonnen werden kann.

![image](https://github.com/FHDW-23SS-SMA-IFWS421B/BonnYPJ/assets/128595643/32baaefc-3ea3-4304-a0db-1ad3d936ceb8)

Im Sequenzdiagramm wird der Ablauf mit dem Benutzer gestartet, der versucht, sich anzumelden. Dieser Vorgang wird in der "Session"-Klasse durchgeführt, wobei die eingegebenen Daten (Benutzername und Passwort) zur Überprüfung an den "Authenticator" weitergeleitet werden. Der "Authenticator" prüft, ob die eingegebenen Daten in der Datenbank, in der die Benutzernamen und die zugehörigen Passwörter gespeichert sind, vorhanden sind. Das Ergebnis dieser Überprüfung wird dann wieder vom "Authenticator" an die "Session" zurückgegeben, und die "Session" informiert daraufhin den Benutzer, ob der Login erfolgreich war oder fehlgeschlagen ist.

![image](https://github.com/FHDW-23SS-SMA-IFWS421B/BonnYPJ/assets/128595643/fed8b30f-dc1b-4039-a3dc-1f6526b42a45)

Der Ablauf des Programms beginnt in der "Session"-Klasse, die den "IOHandler" verwendet, um eine Willkommensnachricht auszugeben und anschließend auf die Benutzereingabe zu warten. Nachdem der "IOHandler" die Benutzereingabe erhalten hat, wird diese an die "Session" zurückgegeben und von dort aus an den "BotCaller" weitergeleitet.

Der "BotCaller" überprüft dann die empfangene Nachricht anhand eines festgelegten Präfixes und erkennt auf diese Weise, welchem Chatbot die Benutzereingabe zugeordnet werden soll. Der ausgewählte Chatbot identifiziert dann den spezifischen Befehl und leitet gegebenenfalls die Anfrage an die "APIConnect"-Klasse weiter, um die erforderlichen Informationen abzurufen. Die Antwort von der "APIConnect" wird anschließend in einen geeigneten Textformat umgewandelt und an den "IOHandler" zurückgegeben.

Schließlich gibt der "IOHandler" die Antwort aus.

## 1.6 Infrastruktursicht

![image](https://github.com/FHDW-23SS-SMA-IFWS421B/BonnYPJ/assets/128595643/747bf195-9970-491e-bf26-f418735de9d8)

Das Programm wird auf der Ausführungsebene unter Verwendung des Java Runtime Environment SE als ausführbares Artefakt mit dem Namen "persa.jar" ausgeführt. Benutzereingaben können unter dem Artefakt "script" mit dem Namen "persa.bat" eingegeben werden.

Die Ausführungsebene ermöglicht zum einen die Verbindung zur Komponente "APIConnect" zur Durchführung von API-Anfragen durch die Chatbots und zum anderen zur "SQL Connection" für Datenbankabfragen.

## 1.7 Querschnittliche Konzepte

Die Software nutzt das abstrakte Fabrik-Muster und das SOLID Pattern als Design Patterns, welche in verschiedenen Situationen nützlich sein können.

Das Abstrakte Fabrik-Muster ermöglicht die Erzeugung von Objektfamilien, ohne die konkreten Klassen dieser Objekte zu spezifizieren. Es definiert eine abstrakte Fabrik-Schnittstelle, die konkrete Fabriken implementieren müssen. Jede konkrete Fabrik ist in der Lage, eine Familie von zusammengehörenden Objekten zu erzeugen, ohne Details über die tatsächlichen Klassen dieser Objekte preiszugeben.

In Bezug auf die Software könnte das Abstrakte Fabrik-Muster verwendet werden, um die verschiedenen Api Verbindungen und Chatbots zu erzeugen. Sie könnten eine abstrakte Fabrik definieren, die die Methoden zur Erstellung dieser Komponenten vorgibt. Dann würden konkrete Fabriken für jede unterstützte Plattform diese abstrakte Fabrik implementieren und die spezifischen Komponenten für ihre Plattform erzeugen.

Das Abstrakte Fabrik-Muster ermöglicht es, Code zu schreiben, der unabhängig von der konkreten Implementierung der Objekte ist, was die Wartung und Erweiterung erleichtert. Es ist besonders nützlich, wenn eine Anwendung plattformübergreifend ist und verschiedene Varianten derselben Komponenten benötigt.

Dieses Programm wurde auch unter Verwendung der SOLID-Prinzipien entwickelt, um eine saubere und robuste Softwarearchitektur zu gewährleisten. Die SOLID-Prinzipien sind eine Sammlung von fünf grundlegenden Designprinzipien in der objektorientierten Programmierung, die dazu beitragen, den Code wartbar, erweiterbar und gut strukturiert zu halten.

**Single Responsibility Principle (SRP)**
Jede Klasse in diesem Programm hat eine einzige klare Verantwortlichkeit. Dies erleichtert die Wartung und Änderung des Codes, da Änderungen nur an einer Stelle vorgenommen werden müssen, wenn sich die Anforderungen ändern.

**Open/Closed Principle (OCP)**
Das Programm folgt dem OCP, indem es für Erweiterungen offen, aber für Modifikationen geschlossen ist. Neue Funktionalitäten werden durch die Erstellung neuer Klassen oder Schnittstellen hinzugefügt, ohne den vorhandenen Code zu ändern.

**Liskov Substitution Principle (LSP)**
Objekte abgeleiteter Klassen können nahtlos anstelle von Objekten ihrer Basisklasse verwendet werden, ohne die Funktionalität des Programms zu beeinträchtigen. Dies erleichtert die Erweiterbarkeit und Wiederverwendung von Code.

**Interface Segregation Principle (ISP)**
Schnittstellen wurden speziell für die Bedürfnisse der Clients erstellt, um zu verhindern, dass Implementierungen gezwungen sind, unnötige Methoden zu implementieren. Dies hält Schnittstellen schlank und vermeidet unnötige Abhängigkeiten.

**Dependency Inversion Principle (DIP)**
Abhängigkeiten von höheren Ebenen sind auf abstrakte Schnittstellen oder abstrakte Klassen gerichtet, nicht auf konkrete Implementierungen. Dies fördert die Entkopplung von Modulen und ermöglicht eine einfachere Ersetzbarkeit von Implementierungen.

Zusammenfassend bieten Design Patterns wie das Abstrakte Fabrik-Muster und das SOLID Pattern bewährte Lösungen für wiederkehrende Probleme in der Softwareentwicklung und fördern die Wiederverwendbarkeit, die Wartbarkeit und die Erweiterbarkeit des Codes.


## 1.8 Schnittstellen

| Schnittstelle       | Zweck und Funktion                                   | Operationen | Kommunikationsprotokoll | Datenformat      | Authentifizierung                                  |
|---------------------|------------------------------------------------------|-------------|-------------------------|------------------|----------------------------------------------------|
| DeepL API           | Übersetzungsdienst für Texte                         | GET         | HTTP                    | JSON             | API-Key 				             |
| Wikipedia API       | Abruf von Informationen aus Wikipedia                | GET         | HTTP                    | JSON             | N/A                                                |
| OpenWeather API     | Wetterdatenabruf für bestimmte Standorte             | GET         | HTTP                    | JSON             | API-Key				             |
| Datenbank Connector | Kommunikation mit der Datenbank 		     | Lesen       | SQL	             | Tabellen, Objekte| N/A			    

Die Chatbot-Anwendung interagiert mit externen Systemen mithilfe von APIs, die als Schnittstellen dienen. Diese Schnittstellen ermöglichen die Bereitstellung einer Vielzahl von Diensten wie beispielsweise Textübersetzung, Wetterinformationen und Wissensabfragen. Um diese Verbindung zu den APIs herzustellen, nutzt die Anwendung die Klasse "APIConnect". Innerhalb dieser Klasse steht die Methode "connectToApi" zur Verfügung, die eine HTTP-Verbindung zu den jeweiligen APIs aufbaut und JSON-formatierte Antworten empfängt. Im Kontext der Klasse "SqlOperations" wird eine Verbindung zu einer internen Datenbank hergestellt, die dazu dient, die Verlaufsdokumentation der Konversationen mit den Nutzern zu speichern.
	
### 1.	Translation Bot
Bei dem Translation-Bot, der die DeepL API nutzt, erhält der Chatbot durch diese Technologie Zugriff auf fortschrittliche maschinelle Übersetzungsfunktionen von DeepL. Das ermöglicht dem Chatbot, hochwertige Übersetzungen in Echtzeit anzubieten. Die Software sendet eine HTTP-Anfrage an die DeepL API, um eine Übersetzung zu erhalten. Diese Anfrage enthält den zu übersetzenden Text und die gewünschte Zielsprache. Die API antwortet daraufhin mit der übersetzten Version des Textes. 
Die entsprechende API-URL ist: https://api-free.deepl.com/v2/translate.

### 2.	 Weather Bot
Im Falle des Weather-Bots, welcher die Openweathermap API nutzt, ermöglicht diese Schnittstelle den Abruf von aktuellen Wetterinformationen für eine bestimmte Stadt. Die Anfrage an die Openweathermap API erfolgt ebenfalls per HTTP-Anfrage, wobei der Name der Stadt angegeben wird. Die API stellt daraufhin relevante Wetterdaten wie Temperatur und Windgeschwindigkeit für die angegebene Stadt zur Verfügung. Es ist außerdem möglich über die API eine Wetterprognose zu erhalten, um das Wetter einer Stadt für die kommenden fünf Tage ausgeben zu lassen.
Die entsprechende API-URL ist: http://api.openweathermap.org/data/2.5.

### 3.   Wiki Bot
Der Wiki-Bot, der die offizielle Wikipedia API nutzt, eröffnet dem Chatbot Zugriff auf die umfassende Wikipedia-Datenbank, um Nutzern gezielte Informationen zu speziellen Themen bereitzustellen. Die Kommunikation mit der Wikipedia API erfolgt durch die Software mittels einer HTTP-Anfrage, bei der der gewünschte Suchbegriff übermittelt wird. Als Antwort erhält die Software eine Liste relevanter Informationen, die mit dem angegebenen Suchbegriff in Verbindung stehen. 
Die entsprechende API-URL lautet: https://de.wikipedia.org/w/rest.php/v1/search/page.

### 4.	 SQL-Datenbank
Die Schnittstelle zur SQL-Datenbank in der Anwendung ermöglicht einen nahtlosen Zugriff auf die darin gespeicherten Daten. Sie stellt eine Verbindung zur SQL-Datenbank über den Hostnamen der SQLite-Datenbank her. Nach der erfolgreichen Herstellung dieser Verbindung kann die Anwendung SQL-Anfragen an die Datenbank senden, um Daten abzurufen, einzufügen, zu aktualisieren oder zu löschen. Diese Anfragen werden als SQL-Statements übermittelt und von der Datenbank verarbeitet, um Daten hinzuzufügen, zu lesen oder zu ändern. Die Schnittstelle zur SQL-Datenbank spielt somit eine zentrale Rolle in der Anwendung und ermöglicht einen effizienten Umgang mit den gespeicherten Daten.

Für den eigentlichen Austausch von Informationen mit den oben genannten APIs ist die Klasse APIConnect verantwortlich. Diese nimmt die zusammengestellten URLs aus den jeweiligen Bots als Parameter entgegen und ermöglicht mithilfe dieser Verbindungen zu den APIs. Innerhalb dieser Klasse steht eine generische Methode zur Verfügung, mittels derer GET-Anfragen an die entsprechenden APIs gesendet werden können. Die Antworten der APIs werden als JSONObject zurückgeliefert, das die von der API empfangenen Daten enthält. Falls es während der Kommunikation mit der API zu Fehlern kommt, werden vordefinierte Texte abgegeben oder Ausnahmen (Exceptions) geworfen, abhängig von der Art des Fehlers.

## 1.9 Risiken und technische Schulden

In der Software sind verschiedene kritische Risikofaktoren und technische Herausforderungen zu identifizieren, die erheblichen Einfluss auf die Entwicklungs- und Sicherheitsbemühungen haben können. Diese Risiken betreffen hauptsächlich:

**1. Abhängigkeiten zu den App-Verbindungen und der SQL-Datenbank:**
Die Software verlässt sich stark auf externe Abhängigkeiten wie App-Verbindungen und die SQL-Datenbank. Änderungen in diesen Abhängigkeiten, sei es durch API-Änderungen oder Datenbankschemamodifikationen, könnten unvorhersehbare Auswirkungen auf die Funktionalität der Software haben. Die begrenzte Kontrolle über diese externen Abhängigkeiten stellt ein Risiko für die Stabilität und die langfristige Wartbarkeit des Systems dar.

**2. Fehlen von Unit Tests im Code:**
Das Fehlen von Unit Tests im Code erhöht das Risiko von Fehlern und Bugs erheblich. Ohne eine umfassende Testabdeckung können Fehler schwer zu identifizieren und zu beheben sein, was zu unerwarteten Ausfällen und Leistungsproblemen führen kann. Dies beeinträchtigt die Gesamtqualität der Software.

**3. SQL-Injektionsrisiko:**
Die Software weist eine potenzielle Schwachstelle in Bezug auf SQL-Injektionen auf. Ohne ausreichende Schutzmechanismen könnten Angreifer schädlichen SQL-Code in die Anwendung einschleusen und Datenbankzugriffe manipulieren. Dies stellt ein erhebliches Sicherheitsrisiko dar und kann zu Datenverlust, Datenschutzverletzungen oder der Kompromittierung der Anwendung führen.

**4. Administratorrechte ohne angemessene Differenzierung:**
Die derzeitige Implementierung der Administratorrechte in der Software bietet keine ausreichende Differenzierung gegenüber normalen Benutzern. Dies führt dazu, dass Administratoren und normale Benutzer ähnliche Zugriffsrechte und Privilegien haben. Dies kann zu mangelnder Kontrolle und möglichen Sicherheitsrisiken führen, da Administratoren Zugang zu sensiblen Daten und Funktionen haben, die normalen Benutzern möglicherweise nicht zur Verfügung stehen sollten.

Um diese Risiken zu minimieren, ist es entscheidend, eine umfassende Risikobewertung und Sicherheitsüberprüfung durchzuführen. Es sollten Maßnahmen ergriffen werden, um die Abhängigkeiten zu externen Komponenten zu verwalten, Unit-Tests zu implementieren, um die Codequalität sicherzustellen, Sicherheitslücken durch sorgfältige Validierung und die Verwendung von Prepared Statements in der Datenbank zu verhindern. Außerdem ist es wichtig, die Rollen und Berechtigungen von Administratoren und normalen Benutzern genau zu überdenken und zu differenzieren. Eine kontinuierliche Überwachung und Aktualisierung dieser Sicherheits- und Entwicklungspraktiken ist von entscheidender Bedeutung, um einen reibungslosen Betrieb und die Sicherheit der Software zu gewährleisten.

## 1.10 Erweiterungen

Das System ermöglicht das Hinzufügen neuer Bots. Dies erfordert jedoch Anpassungen an drei spezifischen Stellen im Code:

1. Am Anfang wird eine neue Klasse für den zu erstellenden Bot angelegt, die von der BotTemplate-Klasse abgeleitet ist. In der Methode "processRequest" erhalten Sie den vollständigen Eingabeinput. Diesen Input können Sie nach Belieben verarbeiten und ihn anschließend mit der geerbten Methode "answer" ausgeben, die einen einfachen Text als Ergebnis erwartet.

2. In der Methode "setBotObjects" in der BotCaller-Klasse erstellen Sie ein neues Objekt vom Typ "BotTemplate" und fügen dieses Objekt der HashMap "botObjects" hinzu, wobei Sie den Botnamen in Kleinbuchstaben als Schlüssel verwenden.

3. In der Methode "insertBots" der DBBuilding-Klasse müssen Sie dem verschachtelten String-Array 'bots' ein neues Array mit dem Botnamen und dem Wert "False" hinzufügen.

Stellen Sie sicher, dass Sie diesen Prozess für jeden neuen Bot wiederholen, den Sie in Ihr System integrieren möchten. Auf diese Weise können Sie erfolgreich neue Bots in Ihr System integrieren.

## 1.11 Fehlerbehebung

**Abhängigkeitsmanagement:** Die Überwachung und Sicherstellung externer Abhängigkeiten ist entscheidend, um die Software gegenüber Veränderungen in den App-Verbindungen und der SQL-Datenbank kompatibel zu halten.

**Implementierung von Unit Tests:** Die Integration von Unit Tests in den Code ist ein aktiver Schritt zur Verbesserung der Codequalität und -stabilität. Dies trägt dazu bei, Fehler frühzeitig zu erkennen und die Gesamtqualität der Software zu steigern.

**Sicherheitsüberprüfung und Schutz vor SQL-Injektionen:** Die Einführung von Sicherheitsmaßnahmen wie Prepared Statements und Datenvalidierung ist unerlässlich, um das Risiko von SQL-Injektionen zu minimieren und die Integrität der Datenbank zu gewährleisten.

**Administratorrechte:** Die Überarbeitung der Rollen und Berechtigungen von Administratoren und normalen Benutzern ist ein wichtiger Schritt, um die Sicherheit und den Datenschutz zu gewährleisten und sicherzustellen, dass Zugriffsrechte angemessen verteilt sind.

Es ist von größter Bedeutung, diese Risiken und Herausforderungen während des gesamten Projekts aktiv zu überwachen und geeignete Maßnahmen zu ergreifen, um die Software stabil, sicher und funktionsfähig zu halten.


## 1.12 Installationsanleitung

Um das Programm erfolgreich auszuführen, sind bestimmte technische Anforderungen und Schritte erforderlich. Im Folgenden werden diese Anforderungen ausführlich erläutert:

**Systemvoraussetzungen:**
Für die reibungslose Ausführung des Chatbots ist mindestens OpenJDK Version 17 oder Java Version 8 erforderlich. Diese Java-Umgebung wird benötigt, um den Code des Chatbots zu interpretieren und auszuführen.

**Speicherplatz:**
Es sollte mindestens 80 Megabyte freier Speicherplatz auf dem Rechner verfügbar sein. Dieser Speicherplatz wird benötigt, um den Chatbot und seine begleitenden Dateien zu speichern und auszuführen.

**Erforderliche Dateien:**
Zur Ausführung des Chatbots sind bestimmte Dateien erforderlich:

- Die Datei "persa.jar": Dies ist das ausführbare JAR-Archiv des Programms, welches seine Funktionalität enthält.

- Die Datei "persa.bat" (für Windows) oder "persa.sh" (für Linux): Diese Startskripte ermöglichen die Usereingabe.

- Der Ordner "database": Dieser Ordner enthält die Datenbanken und Daten, die der Chatbot während seiner Ausführung benötigt.

**Verzeichnisstruktur:**
Es ist von entscheidender Bedeutung, dass die bereitgestellte Verzeichnisstruktur unverändert bleibt. Dies stellt sicher, dass der Chatbot alle notwendigen Ressourcen und Dateien findet und korrekt ausführt.

**Starten des Chatbots:**
Um den Chatbot zu starten, wird entweder die Datei "persa.bat" (für Windows) oder "persa.sh" (für Linux) ausgeführt. Dadurch wird ein Terminal- oder Befehlszeilenfenster geöffnet, und der Chatbot wird initialisiert.

Die Einhaltung dieser Schritte und Anforderungen gewährleistet, dass der Chatbot auf dem System ordnungsgemäß funktioniert und eine reibungslose Interaktion ermöglicht. Bitte beachten Sie die spezifischen Anweisungen für das Betriebssystem (Windows oder Linux) und die Versionsanforderungen für Java.

## 1.13 Konfiguration

Der Benutzer gibt seinen Benutzernamen und sein Passwort ein. Bei korrekten Anmeldedaten erfolgt eine erfolgreiche Anmeldung, und der Benutzer erhält Zugriff auf die Chatbots. Im Falle eines falschen Passworts wird der Benutzer aufgefordert, die Daten erneut einzugeben, bis die korrekten Anmeldedaten vorliegen.

Unser Chatsystem bietet eine breite Palette von Befehlen, die es Benutzern ermöglichen, auf verschiedene Funktionen zuzugreifen und ihr Chaterlebnis individuell anzupassen. Hier ist eine ausführliche Liste der Konfigurationen und ihrer jeweiligen Funktionen:

**!Persa info:**
Dieser Befehl gibt umfassende Informationen über das PERSA Chatsystem. Wenn Sie Fragen zur Funktionsweise des Chatsystems haben oder mehr über seine Möglichkeiten erfahren möchten, ist dieser Befehl hilfreich.

**!Persa credits:**
Mit diesem Befehl können Sie herausfinden, wer an der Entwicklung und Umsetzung des PERSA Chatsystems beteiligt war. Wir möchten die Gelegenheit nutzen, um unseren talentierten Teammitgliedern für ihre Arbeit und ihr Engagement zu danken.

**!Persa log:**
Wenn Sie den Verlauf der letzten Anfragen und Antworten im Chatsystem überprüfen möchten, ist dieser Befehl genau das Richtige. Der Log liefert Einblicke in vergangene Konversationen und Interaktionen.

**!Persa botliste:**
Mit diesem Befehl können Sie eine detaillierte Liste der verfügbaren Bots und ihren aktuellen Status abrufen. Dies ist nützlich, um zu erfahren, welche Bots derzeit aktiv sind und welche nicht.

**!Persa status [botname]:**
Wenn Sie Informationen zum Status eines bestimmten Bots benötigen, verwenden Sie diesen Befehl. Geben Sie den Namen des Bots als Parameter an, um seinen aktuellen Status zu erfahren.

**!Persa aktivieren [botname]:**
Sie haben die Möglichkeit, Bots individuell zu aktivieren, wenn Sie ihre Dienste nutzen möchten. Mit diesem Befehl können Sie einen bestimmten Bot aktivieren, damit er in Ihren Chats verfügbar ist.

**!Persa deaktivieren [botname]:**
Wenn Sie einen Bot vorübergehend nicht verwenden möchten, können Sie ihn mit diesem Befehl deaktivieren. Dadurch wird der Bot in Ihren Chats inaktiv, bis Sie ihn erneut aktivieren.

Diese Befehle bieten eine Fülle von Möglichkeiten, das PERSA Chatsystem nach Ihren Bedürfnissen anzupassen und zu steuern. Sie wurden sorgfältig entwickelt, um sicherzustellen, dass Sie eine reibungslose und effiziente Kommunikation erleben.

So verwenden Sie die Befehle:

Um einen Befehl in Ihrem Chat einzugeben, tippen Sie einfach das Ausrufezeichen "!" gefolgt von dem Befehl und optionalen Parametern (falls erforderlich). Zum Beispiel:

•	!Persa info zeigt Informationen zum Chatsystem an.

•	!Persa status Bot zeigt den Status des Bots mit dem Namen "Bot" an.


# 2 Bot Dokumentation

## 2.1 Übersetzer-Bot

### 2.1.1 Kontextabgrenzung

**Fachlicher Kontext:**

Der Translator Bot ist ein digitales System, das speziell entwickelt wurde, um Nutzern bei der nahtlosen und präzisen Übersetzung von Texten in verschiedene Sprachen zu unterstützen. Im Rahmen dieses Fachkontextes sind folgende Schlüsselaspekte zu berücksichtigen:

1. **Nutzerinteraktion:** Der Translator Bot ermöglicht es den Nutzern, auf eine intuitive Weise mit ihm zu interagieren. Dies erfolgt durch die Eingabe von Texten, die übersetzt werden sollen, sowie die Auswahl der gewünschten Zielsprache. Die Interaktion zwischen dem Nutzer und dem Bot bildet das Herzstück dieses Systems.

2. **Zielsprachenauswahl:** Um den unterschiedlichen Bedürfnissen der Nutzer gerecht zu werden, bietet der Bot die Möglichkeit, die gewünschte Zielsprache für die Übersetzung festzulegen. Diese Auswahl kann je nach Bedarf und Sprachkenntnissen des Nutzers variieren.

3. **Textverarbeitung und Übersetzung:** Der Bot übernimmt die Aufgabe der Textverarbeitung und Übersetzung mithilfe der DeepL API. Diese fortschrittliche Schnittstelle ermöglicht eine präzise und qualitativ hochwertige Übersetzung von Texten in Echtzeit. Die Texte werden dabei in die gewählte Zielsprache umgewandelt.

4. **Übersetzungsausgabe:** Nachdem die Übersetzungsverarbeitung abgeschlossen ist, stellt der Translator Bot dem Nutzer das übersetzte Ergebnis zur Verfügung. Dies kann in Form von Textausgaben oder anderen geeigneten Formaten erfolgen, um sicherzustellen, dass die Nutzer die gewünschten Informationen in der gewünschten Sprache erhalten.

**Technischer Kontext:**

Im technischen Kontext des Translator Bots sind die folgenden technischen Aspekte von Bedeutung:

1. **Nutzerinteraktion über IOHandler:** Die Kommunikation zwischen dem Nutzer und dem Bot erfolgt über einen speziellen IOHandler, der als Schnittstelle für die Systemantworten dient. Dies gewährleistet eine klare und reibungslose Kommunikation zwischen dem Nutzer und dem Bot, wodurch eine benutzerfreundliche Erfahrung ermöglicht wird.

2. **Einschränkung auf bestimmte Sprachen:** Der Bot ist so konfiguriert, dass er nur bestimmte Sprachen akzeptiert, um die Effizienz und Qualität der Übersetzungen sicherzustellen. Diese Auswahl an unterstützten Sprachen wird sorgfältig verwaltet, um die Ressourcen des Systems optimal zu nutzen.

3. **API-Anfrageerstellung:** Bei der Verarbeitung von Sprach- und Texteingaben wird eine API-Anfrage an die DeepL API erstellt. Dieser Prozess erfolgt automatisch und transparent für den Nutzer. Die Anfrage beinhaltet den zu übersetzenden Text sowie die Ziel- und Ausgangssprachen, um eine präzise Übersetzung sicherzustellen.

4. **JSON-Rückgabeverarbeitung:** Die Antwort von DeepL erfolgt in Form von JSON-Daten. Diese Daten werden vom Translator Bot verarbeitet und in einen formatierten String umgewandelt, um dem Nutzer das übersetzte Ergebnis auf verständliche Weise präsentieren zu können.

In diesem detaillierten Kontext sind die verschiedenen Aspekte des Translator Bots hervorgehoben, sowohl im fachlichen als auch im technischen Bereich, um ein umfassendes Verständnis seiner Funktionsweise zu vermitteln.

### 2.1.2 Lösungsstrategie

Der Übersetzer Bot wird durch den Befehl "!translator" aktiviert, den der Nutzer im IOHandler eingibt. Dabei kann der Nutzer die Zielsprache durch Eingabe des entsprechenden Sprachcodes wählen, z. B. "DE" für Deutsch oder "EN" für Englisch. Anschließend gibt der Nutzer den zu übersetzenden Text ein.

Der Bot verarbeitet die Anfrage, generiert eine Übersetzungsdienst-API-URL und sendet einen HTTP GET-Request an die API über die "APIConnect"-Klasse. Bei einer erfolgreichen Anfrage wird die Übersetzung dem Nutzer über den IOHandler ausgegeben, während bei einem Problem eine Fehlermeldung erscheint.

So ermöglicht der Übersetzer Bot eine benutzerfreundliche Übersetzung mit klarer Ausgabe und Fehlerbehandlung.

### 2.1.3 Bausteinsicht

![image](https://github.com/FHDW-23SS-SMA-IFWS421B/BonnYPJ/assets/128595643/8a6d115b-5cf2-4c29-a41a-764058f9e3ff)


Der Übersetzungs-Bot besteht aus fünf Modulen, wobei die zentrale Komponente die Klasse "Translator" darstellt. Die essenzielle Funktion des Bots liegt in der Methode "connection," welche die Benutzereingabe verarbeitet. Die Grundlage für diesen Bot wird durch die abstrakte Klasse "BotTemplate" gebildet, von der alle weiteren Bot-Implementierungen abgeleitet sind und die die Erstellung neuer Bots erleichtert. "BotTemplate" basiert wiederum auf dem Interface "BotTemplateInterface," welches als Grundlage für die abstrakte Klasse dient.

Die von der "connection" Methode in der "Translator" Klasse generierte Eingabe wird durch die "buildApiUrl" Methode konstruiert und an die "APIConnect" Klasse weitergeleitet. Das zurückgelieferte JSONObject wird anschließend mithilfe der "jsonFormat" Methode in einen String umgewandelt und an die "BotCaller" Klasse übergeben, die für die Weiterleitung der Ausgabe verantwortlich ist. Darüber hinaus fungiert der "BotCaller" als Schnittstelle zwischen dem Nutzer und dem Übersetzungs-Bot, und erlaubt die Aktivierung oder Deaktivierung des Übersetzungs-Bots.

### 2.1.4 Laufzeitsicht

![BPMN Translator (1)](https://github.com/FHDW-23SS-SMA-IFWS421B/BonnYPJ/assets/120190475/ec5b2d42-455a-40e5-9020-49b6c4c1e2ad)

**1. Initialisierung:**
Beim Aufruf des Bots über das Kommando "!translator" im IOHandler wird dieser durch den BotCaller aktiviert. Bei fehlender weiterer Angabe gibt der Bot eine Liste von unterstützten Befehlen aus. Statische Variablen sind definiert, um feste URLs und den Authentifizierungsschlüssel für die API zu speichern.

**2. Sprachauswahl:**
Der Nutzer hat die Möglichkeit, eine Ziel-Sprache für die Übersetzung auszuwählen. Hierfür gibt er Sprachcodes (DE, EN, ES, FR, IT) ein. Die Sprachaktivierung erfolgt beispielsweise über "!translator EN".

**3. Texteingabe:**
Nach der Auswahl eines gültigen Sprachcodes fordert der Bot den Nutzer auf, über den IOHandler den zu übersetzenden Text oder ein Wort einzugeben.

**4. URL-Erstellung:**
Basierend auf dem gewählten Sprachcode und dem eingegebenen Text wird mithilfe der Methode buildApiUrl und dem angegebenen API-Key eine URL erstellt.

**5. API-Verbindung:**
Die erstellte URL wird als Parameter an die Klasse APIConnect übergeben. Diese Klasse sendet dann einen GET-Request an die API. Im Falle eines fehlgeschlagenen Requests wird eine Fehlermeldung ausgegeben.

**6. Antwortverarbeitung:**
Bei einer erfolgreichen Anfrage gibt die APIConnect-Klasse ein JSONObject zurück. Der Bot verarbeitet dieses JSONObject, formatiert es zu einem String und gibt die Übersetzung aus

## 2.2 Wetter-Bot

### 2.2.1 Kontextabgrenzung

**Fachlicher Kontext:**

Der Wetter Bot ist ein spezialisiertes digitales System, das dazu entwickelt wurde, Nutzern detaillierte Wetterinformationen für deutsche Städte bereitzustellen. Im Rahmen dieses fachlichen Kontextes sind folgende Schlüsselaspekte zu berücksichtigen:

1. **Nutzerinteraktion:** Dieser Bot ermöglicht den Nutzern eine interaktive Kommunikation. Nutzer können den Bot ansprechen und Anfragen bezüglich des Wetters in deutschen Städten stellen.

2. **Wetterabfrage für Städte:** Der Bot ist darauf ausgerichtet, Wetterinformationen für Städte weltweit bereitzustellen. Dies ermöglicht es den Nutzern, relevante und präzise Wetterdaten für das gewünschte geografische Gebiet zu erhalten.

3. **Wahl zwischen aktuellem Wetter und Prognose:** Die Benutzer haben die Flexibilität zu entscheiden, ob sie Informationen zum aktuellen Wetter oder zur Wetterprognose abfragen möchten. Diese Option ermöglicht es den Nutzern, ihre Anfragen an ihre spezifischen Bedürfnisse anzupassen.

4. **Textverarbeitung:** Der Bot nutzt die Openweather API, um die eingegebenen Textanfragen zu verarbeiten und in Wetterinformationen umzuwandeln. Dieser Prozess umfasst sowohl die Abfrage der Daten als auch die Formatierung der Ergebnisse.

5. **Wetterausgabe:** Nachdem der Bot die Wetterdaten verarbeitet hat, gibt er die Ergebnisse an den Nutzer aus. Dies kann in Form von textbasierten Antworten erfolgen, um die gewünschten Wetterinformationen klar und verständlich darzustellen.

**Technischer Kontext:**

Im technischen Kontext des Wetter Bots sind die folgenden technischen Aspekte von Bedeutung:

1. **Nutzerinteraktion über IOHandler:** Die Kommunikation zwischen dem Nutzer und dem Bot erfolgt über einen IOHandler, der als Schnittstelle für Systemantworten dient. Dies ermöglicht eine strukturierte und benutzerfreundliche Interaktion.

2. **Textüberprüfung und Prognoseentscheidung:** Der Bot analysiert die vom Nutzer eingegebenen Textanfragen und erkennt, ob der Nutzer nach aktuellen Wetterdaten oder einer Prognose fragt. Diese Unterscheidung ist entscheidend für die korrekte Verarbeitung der Anfrage.

3. **Überprüfung der Stadt:** Bevor der Bot Wetterdaten abruft, überprüft er, ob die angegebene Stadt in seiner Datenbank vorhanden ist. Falls die Stadt nicht gefunden wird, gibt der Bot eine Fehlermeldung aus, um den Nutzer zu informieren.

4. **JSON-Rückgabeverarbeitung:** Die Wetterdaten werden von der Openweather API in Form von JSON-Daten zurückgegeben. Der Bot verarbeitet diese Daten und stellt sie in einem angepassten, gut lesbaren Format dar, abhängig von der Art der angeforderten Informationen.

In diesem ausführlichen Kontext werden die verschiedenen Aspekte des Wetter Bots, sowohl im fachlichen als auch im technischen Bereich, betont, um ein umfassendes Verständnis seiner Funktionsweise und seines Zwecks zu vermitteln.

### 2.2.2 Lösungsstrategie

Der Wetter Bot wird durch den Befehl "!weather" im IOHandler aktiviert. Der Nutzer kann dann zwischen aktuellem Wetter und Wettervorhersage wählen.

Für eine aktuelle Wetterabfrage gibt der Nutzer "!weather" gefolgt von der Stadt ein, während er für eine Wettervorhersage "!weather" gefolgt von der Stadt und "Prognose" eingibt.

Der Bot verarbeitet die Eingabe, erstellt eine URL für die Anfrage an die Wetter-API und sendet einen HTTP GET-Request über die "APIConnect"-Klasse. Bei Problemen wird eine Fehlermeldung angezeigt.

Bei einer erfolgreichen Anfrage erhält der Bot eine JSON-Antwort, die in einen gut formatierten Text umgewandelt wird. Dieser Text zeigt entweder das aktuelle Wetter oder eine Wettervorhersage für die angegebene Stadt an.

Der Wetter Bot ermöglicht so eine präzise Abfrage von Wetterinformationen, mit klaren Ausgaben und Fehlerbehandlung, um eine reibungslose Benutzererfahrung sicherzustellen.

### 2.2.3 Bausteinsicht

![image](https://github.com/FHDW-23SS-SMA-IFWS421B/BonnYPJ/assets/128595643/c69dd100-9708-4883-a06d-62f717de8290)

Der Wetter-Bot basiert, ähnlich wie der Übersetzungs-Bot, auf fünf Modulen, die für seine Funktionalität unerlässlich sind. Die grundlegenden Funktionen ähneln dabei stark denen des Übersetzungs-Bots. Der einzige Unterschied liegt in der "buildApiUrl"-Methode der "connection"-Klasse, da der Nutzer die Möglichkeit hat, festzulegen, ob er eine Wettervorhersage für eine bestimmte Stadt wünscht oder nicht. Abhängig davon wird ein boolean Wert namens "forecast" als Parameter an diese Methode übergeben, um die erforderliche URL zu generieren.

### 2.2.4 Laufzeitsicht

![BPMN Weather](https://github.com/FHDW-23SS-SMA-IFWS421B/BonnYPJ/assets/120190475/edbf6421-3df3-494b-90a5-8b1dda5e0b58)

Der Wiki-Bot verwendet ebenfalls fünf Module, die für die Funktionalität des Bots unverzichtbar sind. Im Gegensatz zu den anderen Bots ist jedoch kein API-Schlüssel erforderlich, was die Implementierung dieses Bots erheblich vereinfacht hat.

**1. Initialisierung:**
Wenn der Nutzer das Kommando "!weather" im IOHandler eingibt, wird der Bot durch den BotCaller aktiviert. Bei fehlender weiterer Angabe gibt der Bot eine Liste von unterstützten Befehlen aus. Statische Variablen sind definiert, um feste URLs und den Authentifizierungsschlüssel für die API zu speichern.

**2.Abfrageart auswählen:**
Der Nutzer kann zwischen einer einfachen Wetterabfrage oder einer Prognose wählen. Für eine einfache Wetterabfrage gibt er "!weather" gefolgt von dem Namen der Stadt ein. Für eine Prognose gibt er "!weather", den Stadtnamen und "Prognose" ein.

**3.Eingabe:**
Über den IOHandler gibt der Nutzer die gewünschte Stadt und gegebenenfalls das Wort "Prognose" ein.

**4.URL-Erstellung:**
Basierend auf der Nutzereingabe – Stadt und ob es eine Prognose ist oder nicht – wird mithilfe der Methode buildApiUrl und dem angegebenen API-Key eine URL erstellt.

**5.API-Verbindung:**
Die erstellte URL wird als Parameter an die Klasse APIConnect übergeben. Diese Klasse sendet einen GET-Request an die API. Im Falle eines fehlgeschlagenen Requests wird eine Fehlermeldung ausgegeben.

**6.Antwortverarbeitung:**
Bei einer erfolgreichen Anfrage gibt die APIConnect-Klasse ein JSONObject zurück. Der Bot verarbeitet dieses JSONObject, formatiert es zu einem String und stellt entweder das aktuelle Wetter für die gewählte Stadt oder eine Fünf-Tages-Prognose dar.

## 2.3 Wiki-Bot

### 2.3.1 Kontextabgrenzung

**Fachlicher Kontext:**

Der Wiki Bot ist ein spezialisiertes digitales System, das es Nutzern ermöglicht, umfassende Informationen zu einer Vielzahl von Themen aus der Wikipedia abzurufen. Im Rahmen dieses fachlichen Kontextes sind folgende Schlüsselaspekte zu berücksichtigen:

1. **Nutzerinteraktion:** Der Wiki Bot ermöglicht den Nutzern, interaktiv mit ihm zu kommunizieren. Nutzer können beliebige Suchanfragen an den Bot weiterleiten, um Informationen zu verschiedenen Themen zu erhalten.

2. **Vielfältige Suchanfragen:** Die Benutzer haben die Freiheit, nahezu jede erdenkliche Suchanfrage an den Wiki Bot zu richten. Dies ermöglicht es den Nutzern, spezifische Informationen zu suchen und ihr Wissen zu erweitern.

3. **Textverarbeitung und Weiterleitung an Wikipedia API:** Wenn der Nutzer eine Suchanfrage stellt, verarbeitet der Bot die eingegebene Textanfrage und leitet sie an die Wikipedia API weiter. Diese Schnittstelle ermöglicht den Zugriff auf die umfangreiche Wissensdatenbank von Wikipedia.

4. **Kurze und prägnante Darstellung:** Nachdem die Informationen von der Wikipedia API abgerufen wurden, extrahiert der Bot die wichtigsten Details und präsentiert sie in einer Stichpunktliste. Diese Darstellung ist kurz, knapp und leicht verständlich, um die Relevanz und Klarheit der bereitgestellten Informationen zu gewährleisten.

**Technischer Kontext:**

Im technischen Kontext des Wiki Bots sind die folgenden technischen Aspekte von Bedeutung:

1. **Nutzerinteraktion über IOHandler:** Die Kommunikation zwischen dem Nutzer und dem Bot erfolgt über einen IOHandler, der als Schnittstelle für Systemantworten dient. Dies ermöglicht eine benutzerfreundliche und strukturierte Interaktion.

2. **Textüberprüfung und Validierung:** Der Bot analysiert die vom Nutzer eingegebene Textanfrage, um sicherzustellen, dass sie sinnvoll ist und verarbeitet werden kann. Wenn die Anfrage nicht verstanden werden kann, gibt der Bot eine entsprechende Fehlermeldung aus.

3. **Suchanfrage an Wikipedia:** Nach der Überprüfung leitet der Bot die Suchanfrage an die Wikipedia API weiter, um relevante Informationen abzurufen. Dieser Prozess erfolgt automatisch und transparent für den Nutzer.

4. **JSON-Rückgabeverarbeitung:** Die Informationen von der Wikipedia API werden in Form von JSON-Daten zurückgegeben. Der Bot verarbeitet diese Daten und stellt sie in einem übersichtlichen, formatierten String dar, der die wichtigsten Informationen in einer Stichpunktliste enthält.

In diesem ausführlichen Kontext werden die verschiedenen Aspekte des Wiki Bots, sowohl im fachlichen als auch im technischen Bereich, hervorgehoben, um ein umfassendes Verständnis seiner Funktionsweise und seines Zwecks zu vermitteln.

### 2.3.2 Lösungsstrategie

Der Wetter Bot wird durch den Nutzer aktiviert, wenn dieser "!wiki" im IOHandler eingibt, was den BotCaller auslöst. Der Nutzer kann sowohl einzelne Begriffe als auch längere Sätze als Suchanfrage eingeben.

Der Bot verarbeitet die Eingabe und generiert eine URL für die Wikipedia-API-Anfrage, ohne einen API-Schlüssel zu benötigen. Diese URL wird an die "APIConnect"-Klasse übergeben, die einen HTTP GET-Request an die Wikipedia-API sendet und die Antwort verarbeitet.

Bei einem erfolgreichen Request erhält die "APIConnect"-Klasse ein JSON-Objekt als Antwort, das der Wetter Bot in einen gut formatierten Text umwandelt. Die Ausgabe enthält den Titel der Suchanfrage und die drei wichtigsten Informationen zum Thema.

Der Wetter Bot ermöglicht so eine effiziente Suche nach Wikipedia-Informationen, mit klaren Ausgaben und Fehlerbehandlung für eine reibungslose Benutzererfahrung.

### 2.3.3 Bausteinsicht

![image](https://github.com/FHDW-23SS-SMA-IFWS421B/BonnYPJ/assets/128595643/7431cc62-046d-48d6-b0aa-dafa190f02cd)

Der Wiki-Bot verwendet ebenfalls fünf Module, die für die Funktionalität des Bots unverzichtbar sind. Im Gegensatz zu den anderen Bots ist jedoch kein API-Schlüssel erforderlich, was die Implementierung dieses Bots erheblich vereinfacht hat.

### 2.3.4 Laufzeitsicht

![BPMN Wiki](https://github.com/FHDW-23SS-SMA-IFWS421B/BonnYPJ/assets/120190475/b90b901d-a6b0-4627-9692-1eed0ba6eacd)


**1. Initialisierung:**
Wenn der Nutzer das Kommando "!wiki" im IOHandler eingibt, wird der Bot durch den BotCaller aktiviert. Bei fehlender weiterer Angabe gibt der Bot eine Liste von unterstützten Befehlen aus.

**2.Suchanfrage:**
Der Benutzer kann nach einzelnen Begriffen oder nach längeren Sätzen suchen. Die Eingabe erfolgt in der Form "!wiki" gefolgt von der gewünschten Suchanfrage.

**3.URL-Erstellung:**
Basierend auf der Nutzereingabe wird mithilfe der Methode buildApiUrl eine URL erstellt. Für diese Anfrage ist kein zusätzlicher API-Key erforderlich.

**4.API-Verbindung:**
Die erstellte URL wird als Parameter an die Klasse APIConnect übergeben. Diese Klasse sendet einen GET-Request an die API. Im Falle eines fehlgeschlagenen Requests wird eine Fehlermeldung ausgegeben.

**5.Antwortverarbeitung:**
Bei einer erfolgreichen Anfrage gibt die APIConnect-Klasse ein JSONObject zurück. Der Bot verarbeitet dieses JSONObject und formatiert es zu einem String.

**6.Ausgabe:**
Die finale Ausgabe enthält den Titel der Suchanfrage sowie die drei wichtigsten Informationen darüber.
