package br.com.alura.screenmetch;

import br.com.alura.screenmetch.model.DadosEpsodio;
import br.com.alura.screenmetch.model.DadosSerie;
import br.com.alura.screenmetch.model.DadosTemporada;
import br.com.alura.screenmetch.service.ConsultaApi;
import br.com.alura.screenmetch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class ScreenmetchApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmetchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consultaApi = new ConsultaApi();
//		Scanner leitura = new Scanner(System.in);
//		System.out.println("Digite um filme para busca: ");
//		var busca = leitura.nextLine();

//		var json = consultaApi.obterDados("https://www.omdbapi.com/?t=" + busca.replace(" ","+") + "&apikey=2ddd878c");
		var json = consultaApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=2ddd878c");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		json = consultaApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=2ddd878c");
		DadosEpsodio dadosEpsodio = conversor.obterDados(json, DadosEpsodio.class);
		System.out.println(dadosEpsodio);

		List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i=1; i<=dados.totalTemporadas(); i++){
			json = consultaApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=" + i +"&apikey=2ddd878c");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);
	}
}
