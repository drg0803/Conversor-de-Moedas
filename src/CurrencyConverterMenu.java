import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverterMenu {

    private static final String API_KEY = System.getenv("API_KEY");
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    // Método para consumir a API e retornar objeto
    public static ExchangeRateResponse getRates(String fromCurrency) {
        try {
            // Monta a URL
            String urlStr = BASE_URL + API_KEY + "/latest/" + fromCurrency;

            // Cria cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Cria requisição
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlStr))
                    .GET()
                    .build();

            // Envia requisição e obtém resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Converte JSON para objeto usando Gson
            Gson gson = new Gson();
            return gson.fromJson(response.body(), ExchangeRateResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // Método para conversão
    public static double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        ExchangeRateResponse exchangeRateResponse = getRates(fromCurrency);
        if (exchangeRateResponse != null && exchangeRateResponse.getConversion_rates().containsKey(toCurrency)) {
            double rate = exchangeRateResponse.getConversion_rates().get(toCurrency);
            return amount * rate;
        } else {
            System.out.println("Erro ao obter taxa de câmbio.");
            return -1;
        }
    }
}