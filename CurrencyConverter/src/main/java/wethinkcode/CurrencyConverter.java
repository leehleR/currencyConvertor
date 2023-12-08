package wethinkcode;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class CurrencyConverter {
    public static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) {
        try{
            HttpServer server = HttpServer.create(new InetSocketAddress(DEFAULT_PORT),0);
            server.createContext("/", new ConvertHandler());
            server.setExecutor(null);
            server.start();

            System.out.println("Server started on port: "+ DEFAULT_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
