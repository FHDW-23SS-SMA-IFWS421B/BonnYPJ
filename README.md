# Systemdokumentation
## Schnittstellen

| Schnittstelle       | Zweck und Funktion                                   | Operationen | Kommunikationsprotokoll | Datenformat      | Authentifizierung                                 |
|---------------------|------------------------------------------------------|-------------|-------------------------|------------------|---------------------------------------------------|
| DeepL API           | Übersetzungsdienst für Texte                         | GET         | HTTP                    | JSONObject       | API-Key                                       	  |
| Wikipedia API       | Abruf von Informationen aus Wikipedia                | GET         | HTTP                    | JSONObject       | N/A                                               |
| OpenWeather API     | Wetterdatenabruf für bestimmte Standorte             | GET         | HTTP                    | JSONObject       | API-Key                                           |
| PHILLIP DATENBANK |  | Lesen       |                |  |  |

Die Chatbot-Anwendung interagiert mit externen Systemen mithilfe von Application Programming Interfaces (APIs), die als Schnittstellen dienen. Diese Schnittstellen ermöglichen die Bereitstellung einer Vielzahl von Diensten wie beispielsweise Textübersetzung, Wetterinformationen und Wissensabfragen. Um diese Verbindung zu den APIs herzustellen, nutzt die Anwendung die Klasse "ApiConnection". Innerhalb dieser Klasse steht die Methode "connectToApi" zur Verfügung, die eine Hypertext Transfer Protocol (HTTP)-Verbindung zu den jeweiligen APIs aufbaut und JSON-formatierte Antworten empfängt. Im Kontext der Klasse "SqlOperations" wird eine Verbindung zu einer internen Datenbank hergestellt, die dazu dient, die Verlaufsdokumentation der Konversationen mit den Nutzern zu speichern.
	
### 1.	Translation Bot
Bei dem Translation-Bot, der die DeepL APInutzt, erhält der Chatbot durch diese Technologie Zugriff auf fortschrittliche maschinelle Übersetzungsfunktionen von DeepL. Das ermöglicht dem Chatbot, hochwertige Übersetzungen in Echtzeit anzubieten. Die Software sendet eine HTTP-Anfrage an die DeepL API, um eine Übersetzung zu erhalten. Diese Anfrage enthält den zu übersetzenden Text und die gewünschte Zielsprache. Die API antwortet daraufhin mit der übersetzten Version des Textes. 
Die entsprechende API-URL ist: https://api-free.deepl.com/v2/translate.

### 2.	 Weather Bot
Im Falle des Weather-Bots, welcher die Openweathermap API nutzt, ermöglicht diese Schnittstelle den Abruf von aktuellen Wetterinformationen für eine bestimmte Stadt. Die Anfrage an die Openweathermap API erfolgt ebenfalls per HTTP-Anfrage, wobei der Name der Stadt angegeben wird. Die API stellt daraufhin relevante Wetterdaten wie Temperatur und Windgeschwindigkeit für die angegebene Stadt zur Verfügung. 
Die entsprechende API-URL ist: http://api.openweathermap.org/data/2.5/weather.

### 3.   Wiki Bot
Der Wiki-Bot, der die offizielle Wikipedia API nutzt, eröffnet dem Chatbot Zugriff auf die umfassende Wikipedia-Datenbank, um Nutzern gezielte Informationen zu speziellen Themen bereitzustellen. Die Kommunikation mit der Wikipedia API erfolgt durch die Software mittels einer HTTP-Anfrage, bei der der gewünschte Suchbegriff übermittelt wird. Als Antwort erhält die Software eine Liste relevanter Wikipedia-Seiten, die mit dem angegebenen Suchbegriff in Verbindung stehen. 
Die entsprechende API-URL lautet: https://de.wikipedia.org/w/rest.php/v1/search/page.

### 4.	 SQL-Datenbank (Phillip)
Erläuterung APIConnect und Datenbank (Phillip)
Für den eigentlichen Austausch von Informationen mit den oben genannten APIs ist die Klasse APIConnect verantwortlich. Diese nimmt die zusammengestellten URLs aus den jeweiligen Bots als Parameter entgegen und etabliert mithilfe dieser Verbindungen zu den APIs. Innerhalb dieser Klasse steht eine generische Methode zur Verfügung, mittels derer GET-Anfragen an die entsprechenden APIs gesendet werden können. Die Antworten der APIs werden als JSONObject zurückgeliefert, das die von der API empfangenen Daten enthält. Falls es während der Kommunikation mit der API zu Fehlern kommt, werden vordefinierte Texte abgegeben oder Ausnahmen (Exceptions) geworfen, abhängig von der Art des Fehlers.

### Motivation???

Neben den Kontext der Schnittstellen ist die Spezifikation zu robuster Kommunikation entscheidend. Diese Spezifikation wird hier festgelegt.

### Form
Tabellenform und/oder Verweis auf Schemata

## Erweiterungen

# Bot Dokumentation

## Übersetzer-Bot

### Kontextabgrenzung

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

### Lösungsstrategie

Die Lösungsstrategie für den Übersetzer Bot beginnt mit der Aktivierung durch den Nutzer über den Befehl "!translator" im IOHandler. Dieser Befehl fungiert als Auslöser für den BotCaller, der den Übersetzer Bot aktiviert. Wenn der Nutzer lediglich den Befehl "!translator" eingibt, wird eine Liste der verfügbaren Befehle und unterstützten Sprachen angezeigt, um dem Nutzer die Auswahl zu erleichtern.

Die Zielsprache kann der Nutzer durch Eingabe des entsprechenden Sprachcodes auswählen, wobei DE für Deutsch, EN für Englisch, ES für Spanisch, FR für Französisch und IT für Italienisch stehen. Ein Beispiel wäre "!translator EN" für die Übersetzung ins Englische. Nach der Sprachauswahl wird der Nutzer aufgefordert, den zu übersetzenden Text oder das zu übersetzende Wort über den IOHandler einzugeben.

Der Übersetzer Bot verarbeitet den Sprachcode und den eingegebenen Text, um eine URL für die Übersetzungsdienst-API zu generieren. Diese URL wird dann an die Klasse "APIConnect" übergeben, die einen HTTP GET-Request an die API sendet und die Antwort verarbeitet.

Im Falle eines fehlgeschlagenen Requests oder eines API-Problems wird eine entsprechende Fehlermeldung an den Nutzer zurückgegeben. Bei einer erfolgreichen Anfrage erhält die APIConnect-Klasse ein JSON-Objekt als Antwort, das der Übersetzer Bot verarbeitet und in einen gut formatierten Text umwandelt. Diese formatierte Übersetzung wird dem Nutzer über den IOHandler präsentiert.

Auf diese Weise ermöglicht der Übersetzer Bot eine effiziente und benutzerfreundliche Übersetzung von Texten und Wörtern in verschiedenen Sprachen, wobei Fehlerbehandlung und klare Ausgabe im Vordergrund stehen.

### Bausteinsicht

### Laufzeitsicht

## Wetter-Bot

### Kontextabgrenzung

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

### Lösungsstrategie

Der Wetter Bot wird aktiviert, wenn der Nutzer den Befehl "!weather" im IOHandler eingibt. Dieser Befehl dient als Auslöser für den BotCaller, um den Wetter Bot zu starten. Wenn der Nutzer "!weather" ohne weitere Angabe eingibt, zeigt der Bot eine Befehlsliste an, die dem Nutzer die Wahl zwischen aktuellem Wetter und Wettervorhersage ermöglicht.

Der Nutzer kann wählen, ob er das aktuelle Wetter oder eine Wettervorhersage abfragen möchte. Für eine einfache Wetterabfrage gibt der Nutzer "!weather" gefolgt von der gewünschten Stadt ein. Wenn er eine Wettervorhersage möchte, fügt er "Prognose" hinzu, also "!weather" gefolgt von der Stadt und "Prognose".

Die Stadteingabe erfolgt über den IOHandler. Der Nutzer gibt den Namen der Stadt ein, für die er das Wetter abfragen möchte. Der Bot wird dann versuchen, die Wetterdaten für diese Stadt zu erhalten.

Der Bot verarbeitet die Eingabe des Nutzers und erstellt eine URL für die Anfrage an die Wetter-API, abhängig davon, ob eine Wetterabfrage oder eine Prognose angefordert wird. Diese URL wird an die Klasse "APIConnect" übergeben, die einen HTTP GET-Request an die API sendet und die Antwort verarbeitet.

Im Falle eines fehlgeschlagenen Requests oder anderer Probleme bei der API-Anfrage wird eine Fehlermeldung an den Nutzer zurückgegeben, um ihn über das Problem zu informieren.

Bei einer erfolgreichen Anfrage an die API erhält die APIConnect-Klasse ein JSON-Objekt als Antwort. Dieses JSON-Objekt wird vom Wetter Bot verarbeitet und in einen gut formatierten Text umgewandelt. Der Text stellt entweder das aktuelle Wetter für die angegebene Stadt oder eine fünftägige Wettervorhersage dar. Auf diese Weise ermöglicht der Wetter Bot eine präzise und benutzerfreundliche Abfrage von Wetterinformationen, sowohl für das aktuelle Wetter als auch für Prognosen, wobei Fehlerbehandlung und klare Ausgabe im Vordergrund stehen, um eine reibungslose Benutzererfahrung sicherzustellen.

### Bausteinsicht

### Laufzeitsicht

## Wiki-Bot

### Kontextabgrenzung

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

### Lösungsstrategie

Der Wetter Bot wird durch den Nutzer aktiviert, wenn dieser den Befehl "!wiki" über den IOHandler eingibt, was den BotCaller auslöst. Wenn der Nutzer "!wiki" ohne weitere Angabe eingibt, wird eine Befehlsliste angezeigt, um dem Nutzer die verschiedenen Optionen zur Suche aufzuzeigen.

Der Benutzer hat die Flexibilität, sowohl einzelne Begriffe als auch längere Sätze als Suchanfrage einzugeben, indem er "!wiki" gefolgt von der gewünschten Suchanfrage eingibt.

Der Bot verarbeitet die Eingabe des Nutzers und generiert eine URL für die Anfrage an die Wikipedia-API. Dies geschieht mithilfe der Methode "buildApiUrl" ohne die Notwendigkeit eines zusätzlichen API-Schlüssels.

Die erstellte URL wird dann an die Klasse "APIConnect" übergeben, die einen HTTP GET-Request an die Wikipedia-API sendet und die Antwort verarbeitet. Im Falle eines fehlgeschlagenen Requests oder anderer Probleme bei der API-Anfrage gibt der Bot eine entsprechende Fehlermeldung aus, um den Nutzer zu informieren.

Bei einer erfolgreichen Anfrage an die API erhält die APIConnect-Klasse ein JSON-Objekt als Antwort. Dieses JSON-Objekt wird vom Wetter Bot verarbeitet und in einen gut formatierten Text umgewandelt. Die Ausgabe enthält den Titel der Suchanfrage sowie die drei wichtigsten Informationen über das gesuchte Thema.

Auf diese Weise ermöglicht der Wetter Bot eine effiziente und benutzerfreundliche Suche nach Informationen in der Wikipedia, sowohl für kurze Begriffe als auch für längere Sätze. Fehlerbehandlung und klare Ausgabe stehen im Mittelpunkt, um eine reibungslose Benutzererfahrung sicherzustellen.

### Bausteinsicht

### Laufzeitsicht
