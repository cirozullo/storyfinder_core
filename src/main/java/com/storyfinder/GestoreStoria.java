import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.*;

public class GestoreStoria {

    private List<Storia> allStories;

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

        ClassLoader classLoader =
            Thread.currentThread().getContextClassLoader();

        File dir = new File(classLoader.getResource("storie").getFile());

        if (dir.isDirectory()) {
            File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));
            if (files != null) {
                for (File file : files) {
                    Storia story = loadStoryFromFile(file);
                    if (story != null) {
                        stories.add(story);
                    }
                }
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
    private Storia loadStoryFromFile(File file) {
        ClassLoader classLoader =
            Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(
            "storie/" + file.getName()
        );

        if (is == null) {
            System.err.println("File not found: " + file.getName());
            return null;
        }

        String result = null;
        try (InputStream inputStream = is) {
            result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Gson gson = new Gson();
        return gson.fromJson(result, Storia.class);
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

        if (bestStory == null || minDistance == Integer.MAX_VALUE) {
            System.out.println(
                "Nessuna storia trovata per l'argomento: " + topicKey
            );
        }

        return bestStory;
    }
}
