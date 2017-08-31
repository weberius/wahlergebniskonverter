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

Zur Installation werden Java und Tomcat vorausgesetzt. Die Installation erfolgt z.B. auf einer ubuntu Installation. Folgende Befehle müssen ausgeführt werden:

## Projekt

1. git clone https://github.com/weberius/wahlergebniskonverter.git
2. cd wahlergebniskonverter
3. mvn clean install

## System

Zunächst werden java und maven installiert. Zum Ausführen der Applikation wird weiterhin ein tomcat7 benötigt.

    sudo apt-get install openjdk-8-jdk openjdk-8-demo openjdk-8-doc openjdk-8-jre-headless openjdk-8-source
    sudo apt install maven
    sudo apt-get install tomcat7 tomcat7-admin
    
Zum einfachen deploy der Anwendung kann ein sh-Skript angelegt werden. Dabei wird unterstellt, dass es sich um eine Standartinstallation handelt.

Zunächst muss das Projekt von github kopiert werden:

    git clone https://github.com/weberius/wahlergebniskonverter.git
    
Dann wird das Skript angelegt:

    vi updateWahlergebniskonverter.sh

Folgende Einträge müssen im Skript vorgenommen werden:

    cd wahlergebniskonverter
    git pull
    mvn clean install
    chmod 775 target/wahlergebniskonverter.war
    mv target/wahlergebniskonverter.war /var/lib/tomcat7/webapps/

Das Projekt wird dann mit folgendem Aufruf aktualisiert:

   ./updateWahlergebniskonverter.sh

# License

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons Lizenzvertrag" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />Dieses Werk ist lizenziert unter einer <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Namensnennung - Weitergabe unter gleichen Bedingungen 4.0 International Lizenz</a>.
