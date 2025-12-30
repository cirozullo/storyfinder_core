import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class AvvioGioco {

    public static void main(String[] args) {
        Gson gson = new Gson();

        ClassLoader classloader =
            Thread.currentThread().getContextClassLoader();

        String resourcePath = "storia1.json";
        InputStream is = classloader.getResourceAsStream(resourcePath);

        if (is == null) {
            System.err.println("File inesistente");
            return;
        }

        try (InputStream inputStream = is) {
            String result = IOUtils.toString(
                inputStream,
                StandardCharsets.UTF_8
            );

            Storia storia = gson.fromJson(result, Storia.class);

            System.out.println(storia);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
