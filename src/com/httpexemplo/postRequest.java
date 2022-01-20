package com.httpexemplo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;

public class postRequest {

	public static final String URL_POST = "http://httpbin.org/forms/post";
	public static final String FILE_JSON ="json file path";
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		// cliente HTTP
		HttpClient client = HttpClient.newHttpClient();
		
		// criando uma requisição
		HttpRequest requisicao = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofFile(Path.of(FILE_JSON)))
				.timeout(Duration.ofSeconds(10))
				.uri(URI.create(URL_POST))
				.build();
		
		// enviando solicitacao assincrona
		client.sendAsync(requisicao, HttpResponse.BodyHandlers.ofString())
				.thenApply(HttpResponse::body)
				.thenAccept(System.out::println)
				.join();

	}

}
