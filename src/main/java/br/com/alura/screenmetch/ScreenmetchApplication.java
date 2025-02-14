package br.com.alura.screenmetch;

import br.com.alura.screenmetch.model.DadosSerie;
import br.com.alura.screenmetch.service.ConsultaApi;
import br.com.alura.screenmetch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ScreenmetchApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmetchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consultaApi = new ConsultaApi();
		Scanner leitura = new Scanner(System.in);
		System.out.println("Digite um filme para busca: ");
		var busca = leitura.nextLine();

		var json = consultaApi.obterDados("https://www.omdbapi.com/?t=" + busca.replace(" ","+") + "&apikey=2ddd878c");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
	}
}
