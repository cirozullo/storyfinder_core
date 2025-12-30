# PROJECT- IA Gioco StoryFinder
---

# Instruzione di Compilazione

Avviare il codice
```cmd
java -cp bin:src/main/resources:lib/commons-io-2.21.0.jar:lib/gson-2.13.1.jar src/main/java/com/storyfinder/AvvioGioco.java
```

Compilare tutto:
```cmd
javac -d bin -cp "lib/commons-io-2.21.0.jar:lib/gson-2.13.1.jar" src/main/java/com/storyfinder/**/*.java
```

## Compilazione in JAR

Per compilare in jar:
```cmd
jar cfm storyfinder-core.jar MANIFEST.MF -C bin . -C src/main/resources .
```

Per eseguire:
```cmd
java -jar storyfinder-core.jar
```
