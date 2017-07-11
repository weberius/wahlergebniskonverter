# Wahlergebnis Konverter

Dieser Service konvertiert Wahlergebnisse in eine zum Service '[wahlergebnis](https://github.com/weberius/wahlergebnis)' kompatible json-Struktur. 

# Status

Die Applikation befindet sich in der Entwicklung

# Service

## /wahlergebniskonverter/service/ping

Diese Schnittstelle wird verwendet um zu prüfen, ob der Service selbst erreichbar ist.

## /wahlergebniskonverter/service/landtagswahl/05/05315000/erststimmen

Dieser Schnittstelle liest die [Landtagswahlergebnisse Erststimmen](https://www.offenedaten-koeln.de/dataset/landtagswahl-2012-erststimmen) von [Offene Daten Köln](https://www.offenedaten-koeln.de/) aus und konvertiert sie in eine zum Service wahlergebnis kompatible JSON - Struktur.

## /wahlergebniskonverter/service/landtagswahl/05/05315000/zweitstimmen

Dieser Schnittstelle liest die [Landtagswahlergebnisse Zweitstimmen](https://www.offenedaten-koeln.de/dataset/landtagswahl-2012-zweitstimmen) von [Offene Daten Köln](https://www.offenedaten-koeln.de/) aus und konvertiert sie in eine zum Service wahlergebnis kompatible JSON - Struktur.

# Datenbank

Es ist keine Datenbank notwendig.

# Installation

1. git clone https://github.com/weberius/wahlergebniskonverter.git
2. cd wahlergebniskonverter
3. mvn clean install
4. mvn jetty:run

# License

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons Lizenzvertrag" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />Dieses Werk ist lizenziert unter einer <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Namensnennung - Weitergabe unter gleichen Bedingungen 4.0 International Lizenz</a>.
