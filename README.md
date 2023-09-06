# Systemdokumentation
## Schnittstellen

| Schnittstelle       | Zweck und Funktion                                   | Operationen | Kommunikationsprotokoll | Datenformat      | Authentifizierung                                 |
|---------------------|------------------------------------------------------|-------------|-------------------------|------------------|---------------------------------------------------|
| DeepL API           | Übersetzungsdienst für Texte                         | GET         | HTTP                    | JSONObject       | API-Key                                       	  |
| Wikipedia API       | Abruf von Informationen aus Wikipedia                | GET         | HTTP                    | JSONObject       | N/A                                               |
| OpenWeather API     | Wetterdatenabruf für bestimmte Standorte             | GET         | HTTP                    | JSONObject       | API-Key                                           |
| PHILLIP DATENBANK |  | Lesen       |                |  |  |

Die Chatbot-Anwendung interagiert mit externen Systemen mithilfe von Application Programming Interfaces (APIs), die als Schnittstellen dienen. Diese Schnittstellen ermöglichen 
die Bereitstellung einer Vielzahl von Diensten wie beispielsweise Textübersetzung, Wetterinformationen und Wissensabfragen. Um diese Verbindung zu den APIs herzustellen, nutzt die Anwendung 
die Klasse "ApiConnection". Innerhalb dieser Klasse steht die Methode "connectToApi" zur Verfügung, die eine Hypertext Transfer Protocol (HTTP)-Verbindung zu den jeweiligen APIs aufbaut und 
JSON-formatierte Antworten empfängt. Im Kontext der Klasse "SqlOperations" wird eine Verbindung zu einer internen Datenbank hergestellt, die dazu dient, die Verlaufsdokumentation der 
Konversationen mit den Nutzern zu speichern.
	
### 1.	Translation Bot
Bei dem Translation-Bot, der die DeepL APInutzt, erhält der Chatbot durch diese Technologie Zugriff auf fortschrittliche maschinelle Übersetzungsfunktionen von DeepL. Das ermöglicht dem 
Chatbot, hochwertige Übersetzungen in Echtzeit anzubieten. Die Software sendet eine HTTP-Anfrage an die DeepL API, um eine Übersetzung zu erhalten. Diese Anfrage enthält den zu 
übersetzenden Text und die gewünschte Zielsprache. Die API antwortet daraufhin mit der übersetzten Version des Textes. Die entsprechende API-URL ist: https://api-free.deepl.com/v2/translate.

### 2.	 Weather Bot
Im Falle des Weather-Bots, welcher die Openweathermap API nutzt, ermöglicht diese Schnittstelle den Abruf von aktuellen Wetterinformationen für eine bestimmte Stadt. Die Anfrage an die 
Openweathermap API erfolgt ebenfalls per HTTP-Anfrage, wobei der Name der Stadt angegeben wird. Die API stellt daraufhin relevante Wetterdaten wie Temperatur und Windgeschwindigkeit für die 
angegebene Stadt zur Verfügung. Die entsprechende API-URL ist: http://api.openweathermap.org/data/2.5/weather.

### 3.   Wiki Bot
Der Wiki-Bot, der die offizielle Wikipedia API nutzt, eröffnet dem Chatbot Zugriff auf die umfassende Wikipedia-Datenbank, um Nutzern gezielte Informationen zu speziellen Themen 
bereitzustellen. Die Kommunikation mit der Wikipedia API erfolgt durch die Software mittels einer HTTP-Anfrage, bei der der gewünschte Suchbegriff übermittelt wird. Als Antwort erhält die 
Software eine Liste relevanter Wikipedia-Seiten, die mit dem angegebenen Suchbegriff in Verbindung stehen. 
Die entsprechende API-URL lautet: https://de.wikipedia.org/w/rest.php/v1/search/page.

### 4.	 SQL-Datenbank (Phillip)
Erläuterung APIConnect und Datenbank (Phillip)
Für den eigentlichen Austausch von Informationen mit den oben genannten APIs ist die Klasse APIConnect verantwortlich. Diese nimmt die zusammengestellten URLs aus den jeweiligen Bots als 
Parameter entgegen und etabliert mithilfe dieser Verbindungen zu den APIs. Innerhalb dieser Klasse steht eine generische Methode zur Verfügung, mittels derer GET-Anfragen an die 
entsprechenden APIs gesendet werden können. Die Antworten der APIs werden als JSONObject zurückgeliefert, das die von der API empfangenen Daten enthält. Falls es während der Kommunikation 
mit der API zu Fehlern kommt, werden vordefinierte Texte abgegeben oder Ausnahmen (Exceptions) geworfen, abhängig von der Art des Fehlers.
### Motivation???

Neben den Kontext der Schnittstellen ist die Spezifikation zu robuster Kommunikation entscheidend. Diese Spezifikation wird hier festgelegt.

### Form
Tabellenform und/oder Verweis auf Schemata

## Erweiterungen

# Bot Dokumentation

## Übersetzer-Bot

### Kontextabgrenzung

**Fachlicher Kontext:**

Der Translator Bot ist ein digitales System, das speziell entwickelt wurde, um Nutzern bei der nahtlosen und präzisen Übersetzung von Texten in verschiedene Sprachen zu unterstützen. Im 
Rahmen dieses Fachkontextes sind folgende Schlüsselaspekte zu berücksichtigen:

1. **Nutzerinteraktion:** Der Translator Bot ermöglicht es den Nutzern, auf eine intuitive Weise mit ihm zu interagieren. Dies erfolgt durch die Eingabe von Texten, die übersetzt werden
2. sollen, sowie die Auswahl der gewünschten Zielsprache. Die Interaktion zwischen dem Nutzer und dem Bot bildet das Herzstück dieses Systems.

3. **Zielsprachenauswahl:** Um den unterschiedlichen Bedürfnissen der Nutzer gerecht zu werden, bietet der Bot die Möglichkeit, die gewünschte Zielsprache für die Übersetzung festzulegen.
4. Diese Auswahl kann je nach Bedarf und Sprachkenntnissen des Nutzers variieren.

5. **Textverarbeitung und Übersetzung:** Der Bot übernimmt die Aufgabe der Textverarbeitung und Übersetzung mithilfe der DeepL API. Diese fortschrittliche Schnittstelle ermöglicht eine
6. präzise und qualitativ hochwertige Übersetzung von Texten in Echtzeit. Die Texte werden dabei in die gewählte Zielsprache umgewandelt.

7. **Übersetzungsausgabe:** Nachdem die Übersetzungsverarbeitung abgeschlossen ist, stellt der Translator Bot dem Nutzer das übersetzte Ergebnis zur Verfügung. Dies kann in Form von
8. Textausgaben oder anderen geeigneten Formaten erfolgen, um sicherzustellen, dass die Nutzer die gewünschten Informationen in der gewünschten Sprache erhalten.

**Technischer Kontext:**

Im technischen Kontext des Translator Bots sind die folgenden technischen Aspekte von Bedeutung:

1. **Nutzerinteraktion über IOHandler:** Die Kommunikation zwischen dem Nutzer und dem Bot erfolgt über einen speziellen IOHandler, der als Schnittstelle für die Systemantworten dient.
2. Dies gewährleistet eine klare und reibungslose Kommunikation zwischen dem Nutzer und dem Bot, wodurch eine benutzerfreundliche Erfahrung ermöglicht wird.

3. **Einschränkung auf bestimmte Sprachen:** Der Bot ist so konfiguriert, dass er nur bestimmte Sprachen akzeptiert, um die Effizienz und Qualität der Übersetzungen sicherzustellen. Diese
4. Auswahl an unterstützten Sprachen wird sorgfältig verwaltet, um die Ressourcen des Systems optimal zu nutzen.

5. **API-Anfrageerstellung:** Bei der Verarbeitung von Sprach- und Texteingaben wird eine API-Anfrage an die DeepL API erstellt. Dieser Prozess erfolgt automatisch und transparent für den
6. Nutzer. Die Anfrage beinhaltet den zu übersetzenden Text sowie die Ziel- und Ausgangssprachen, um eine präzise Übersetzung sicherzustellen.

7. **JSON-Rückgabeverarbeitung:** Die Antwort von DeepL erfolgt in Form von JSON-Daten. Diese Daten werden vom Translator Bot verarbeitet und in einen formatierten String umgewandelt, um
8. dem Nutzer das übersetzte Ergebnis auf verständliche Weise präsentieren zu können.

In diesem detaillierten Kontext sind die verschiedenen Aspekte des Translator Bots hervorgehoben, sowohl im fachlichen als auch im technischen Bereich, um ein umfassendes Verständnis seiner
Funktionsweise zu vermitteln.


### Lösungsstrategie

### Bausteinsicht

### Laufzeitsicht

## Wetter-Bot

### Kontextabgrenzung

### Lösungsstrategie

### Bausteinsicht

### Laufzeitsicht

## Wiki-Bot

### Kontextabgrenzung

### Lösungsstrategie

### Bausteinsicht

### Laufzeitsicht
