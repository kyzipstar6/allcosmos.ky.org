
java files/AutoBadImportCleaner
javac -Xlint:unchecked -cp ".;C:\Users\Public\Documents\java\dataplotter\lib\javafx-sdk-23.0.2\lib\javafx.graphics.jar;C:\Users\Public\Documents\java\dataplotter\lib\javafx-sdk-23.0.2\lib\javafx.controls.jar;C:\Users\Public\Documents\java\dataplotter\lib\javafx-sdk-23.0.2\lib\javafx.base.jar" src/application/Movements.java src/application/Methods.java src/application/Main.java -d bin

java --module-path C:\Users\Public\Documents\java\dataplotter\lib\javafx-sdk-23.0.2\lib --add-modules javafx.controls,javafx.fxml -cp ".;bin" application.Main