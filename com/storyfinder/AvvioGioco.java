package com.storyfinder;

public class AvvioGioco {

    public static void main() {}
}
  Scanner scanner = new Scanner(System.in);
  System.out.println("Che cosa vuoi cercare?");
  String argomento = scanner.nextline();
  GestoreStoria gestorestoria = new Gestorestoria();
  Storia storia = gestoreStoria.getBestStory();
  System.out.println(storia);