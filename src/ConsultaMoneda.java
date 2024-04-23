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
    /**
     * Realiza una consulta a la API de tasas de cambio de moneda para obtener la tasa de conversión entre dos monedas.
     *
     * @param monedaOrigen     La moneda de origen para la conversión.
     * @param monedaAConvertir La moneda a la que se desea convertir.
     * @return La tasa de conversión entre las dos monedas.
     * @throws IOException              Si ocurre un error de entrada o salida al realizar la solicitud HTTP.
     * @throws InterruptedException     Si la ejecución es interrumpida mientras espera la respuesta de la solicitud HTTP.
     * @throws IllegalStateException    Si no se pudo obtener la tasa de cambio de la respuesta JSON.
     * @throws IllegalArgumentException Si la moneda objetivo no está en la respuesta JSON.
     */

    public static double buscaMoneda(String monedaOrigen, String monedaAConvertir) throws IOException, InterruptedException {
        // Construye la URL de la API con la moneda de origen especificada
        URI url = URI.create("https://v6.exchangerate-api.com/v6/0a294b86fe8fd4bc67e620e5/latest/" + monedaOrigen);

        // Crea un cliente HTTP para enviar la solicitud a la API
        HttpClient client = HttpClient.newHttpClient();
        // Construye la solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();
        // Envía la solicitud HTTP y obtiene la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Obtiene el cuerpo de la respuesta JSON
        String responseBody = response.body();

        //System.out.println("Respuesta JSON: " + responseBody); // Mensaje de depuración
        // Utiliza Gson para convertir el cuerpo de la respuesta JSON en un objeto Java
        Gson gson = new Gson();
        Tasa tasa = gson.fromJson(responseBody, Tasa.class);

        // Verifica si se pudo obtener la tasa de cambio
        if (tasa == null || tasa.getConversion_rates() == null) {
            throw new IllegalStateException("No se pudo obtener la tasa de cambio.");
        }

        Map<String, Double> tasas = tasa.getConversion_rates();

        // Obtiene la tasa de conversión entre las dos monedas especificadas
        Double cambio = tasas.get(monedaAConvertir);
        if (cambio == null) {
            throw new IllegalArgumentException("La moneda objetivo no está en la respuesta JSON.");
        }

        // Imprime la tasa de conversión específica
        System.out.printf("Tasa de cambio de %s a %s: %.2f\n", monedaOrigen, monedaAConvertir, cambio);

        return cambio;
    }
}








