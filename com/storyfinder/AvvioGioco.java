package com.storyfinder;

import java.util.Scanner;

public class AvvioGioco {

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scrivi un nome per il tuo personaggio: ");
        String nome = scanner.nextLine();
        System.out.println("Scrivi il luogo dove viene ambientata la storia: ");
        String luogo = scanner.nextLine();
        System.out.println(
            "Indica l'oggetto che il tuo personaggio utilizzerà: "
        );
        String oggetto = scanner.nextLine();
        System.out.println("Indica una parola chiave della tua storia: ");
        String argomento = scanner.nextLine();
        GestoreStoria gestoreStoria = new GestoreStoria();
        Storia storia = gestoreStoria.getBestStory(argomento);
        String testo = storia.getTesto_storia();
        String storiaFinale = testo
            .replace("[nome]", nome)
            .replace("[luogo]", luogo)
            .replace("[oggetto]", oggetto);
        System.out.println("\n" + storiaFinale);
        scanner.close();
    }
}
