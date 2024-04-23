import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ConsultaMoneda {

    public static double buscaMoneda(String monedaOrigen, String monedaAConvertir) throws IOException, InterruptedException {
        URI url = URI.create("https://v6.exchangerate-api.com/v6/0a294b86fe8fd4bc67e620e5/latest/"+ monedaOrigen);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();

        //System.out.println("Respuesta JSON: " + responseBody); // Mensaje de depuración

        Gson gson = new Gson();
        Tasa tasa = gson.fromJson(responseBody, Tasa.class);

        if (tasa == null || tasa.getConversion_rates() == null) {
            throw new IllegalStateException("No se pudo obtener la tasa de cambio.");
        }

        Map<String, Double> tasas = tasa.getConversion_rates();

        System.out.println("Tasas de cambio: " + tasas); // Mensaje de depuración

        Double cambio = tasas.get(monedaAConvertir);
        if (cambio == null) {
            throw new IllegalArgumentException("La moneda objetivo no está en la respuesta JSON.");
        }

        return cambio;




        }


    }








