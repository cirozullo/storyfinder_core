package com.storyfinder;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.*;

public class GestoreStoria {

    private final List<Storia> allStories;

    private static final String[] NOMI_STORIE = {
        "il_labirinto_del_drago.json",
        "il_mistero_della_foresta_incantata.json",
        "il_tesoro_di_atlantide.json",
        "la_citta_sommersa.json",
        "la_leggenda_del_cavaliere_perduto.json",
    };

    public GestoreStoria() {
        this.allStories = this.loadAllStories();
    }

    /**
     * Carica tutte le storie dalla cartella resources/storie
     * e converti in una Lista
     *
     * @return La lista degli oggetti Storia dalla cartella
     */
    private List<Storia> loadAllStories() {
        List<Storia> stories = new ArrayList<>();
        Gson gson = new Gson();
        Storia story;

        for (String fileName : NOMI_STORIE) {
            String filePath = "resources/" + fileName;
            story = this.loadStoryFromFile(filePath, gson);
            if (story != null) {
                stories.add(story);
            }
        }

        return stories;
    }

    /**
     *  Carica una storia specifica e converti l'InputStream in stringa da passare
     *  a gson. Richiede un file json con struttura specifica
     *
     *  @param file Il nome del file della storia da caricare
     *  @return Il file JSON in formato stringa
     */
    private Storia loadStoryFromFile(String filePath, Gson gson) {
        Storia storia = null;
        try (Reader reader = new FileReader(filePath)) {
            storia = gson.fromJson(reader, Storia.class);
        } catch (IOException e) {
            System.err.println(
                "Errore nel caricamento del file JSON : " + e.getMessage()
            );
        }
        return storia;
    }

    /**
     * Algoritmo NLP-style utilizzando la distanza di Levenshtein fornita dalla
     * libreria Apache Commons Lang3, essendo un algoritmo di calcolo di distanza,
     * andra a trovare le paroli che più si avvicinano alla parola fornita dall'utente
     *
     * @param topicKey La parola chiave fornita dall'utente.
     * @return La storia più pertinente
     */
    public Storia getBestStory(String topicKey)
        throws IllegalArgumentException {
        Storia bestStory = null;
        int minDistance = Integer.MAX_VALUE;
        String normalizedTopic = topicKey.toUpperCase();

        if (this.allStories.isEmpty()) {
            System.out.println("Non ci sono storie caricate");
        }

        for (Storia story : allStories) {
            int currentMinDistance = Integer.MAX_VALUE;
            for (String keyword : story.getChiavi()) {
                @SuppressWarnings("deprecation")
                int distance = StringUtils.getLevenshteinDistance(
                    normalizedTopic,
                    keyword.toUpperCase()
                );
                if (distance < currentMinDistance) {
                    currentMinDistance = distance;
                }
            }

            if (currentMinDistance < minDistance) {
                minDistance = currentMinDistance;
                bestStory = story;
            }
        }

        if (bestStory == null) {
            System.out.println(
                "Nessuna storia trovata per l'argomento: " + topicKey
            );
        }

        return bestStory;
    }
}
