
Velkommen til RickFlix!
**********************
S�dan k�res programmet:

1) S�rg for at JavaFX er installeret - https://openjfx.io/
2) S�rg for at Java 11.0 eller nyere udgave er installeret - https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html (RickFlix er compilet i Java 11.0)
3) Navig�r til mappen som indeholder controller.jar-filen (ligger i stien hvor .zip-filen med denne README er udpakket) 
4) Find stien til Java (hedder noget ala "jdk-11.xx.xx\bin\java")
5) Find stien til JavaFX (hedder noget ala "javafx-sdk-11.xx.xx\lib")
6) Skriv "cmd" oppe i adressefeltet og tryk enter. Dette vil �bne command prompt i ovenn�vnte mappe.
7) Indtast: "sti til jdk-11.xx.xx\bin\java" --module-path "sti til javafx-sdk-11.xx.xx\lib" --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media -jar controller.jar
7.1) eks. p� sti: "C:\Program Files\Java\jdk-11.0.5\bin\java" --module-path "C:\Program Files\Java\javafx-sdk-11.0.2\lib" --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media -jar controller.jar
8) Hvis instrukser er fuldt korrekt, er programmet nu �bent!

**********************

For brugervejledning se bilag 10.3. i rapporten - filnavn: Rapport_GruppeT_v02.pdf