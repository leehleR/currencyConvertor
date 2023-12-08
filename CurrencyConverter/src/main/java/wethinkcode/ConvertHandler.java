package wethinkcode;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Scanner;

public class ConvertHandler implements HttpHandler {
    public static final String API_URL = "https://api.currencyapi.com/v3/latest?apikey=cur_live_aSae0nW12HILpIQ40V6YWjs5304fot8UTgepQO14";
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("hello world");
        if("GET".equalsIgnoreCase(httpExchange.getRequestMethod())){
            handleGetMethod(httpExchange);
        }else {
            System.out.println("WHY WORLD?");
        }
    }

    private void handleGetMethod(HttpExchange httpExchange) throws IOException {
        String exchange = getApiResponse(API_URL);
        double amount = 100.0;
        double convertedAmount = performConversion(amount, 0.85); // Placeholder

        // Send the response
        String response = "Converted amount: " + convertedAmount;
        sendResponse(httpExchange, response, 200);
    }

    private double performConversion(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }

    private String getApiResponse(String apiUrl) throws IOException {
        try (Scanner scanner = new Scanner(new URL(apiUrl).openStream(), "UTF-8")) {
            return scanner.useDelimiter("\\A").next();
        }
    }

    private void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
