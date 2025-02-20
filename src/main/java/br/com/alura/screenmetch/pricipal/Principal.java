package br.com.alura.screenmetch.pricipal;

import br.com.alura.screenmetch.model.DadosEpsodio;
import br.com.alura.screenmetch.model.DadosSerie;
import br.com.alura.screenmetch.model.DadosTemporada;
import br.com.alura.screenmetch.service.ConsultaApi;
import br.com.alura.screenmetch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO ="https://www.omdbapi.com/?t=" ;
    private final String API_KEY= "&apikey=2ddd878c";
    private ConsultaApi consumo = new ConsultaApi();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu(){
        System.out.println("Digite o nome da Serie para busca!");
        var nomeSerie = leitura.nextLine();
        var json =consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println("Dados Serie -> " + dados);


        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= dados.totalTemporadas(); i++){
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season="+ i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            System.out.println(dadosTemporada);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);

//        for (int i = 0; i < dados.totalTemporadas(); i++){
//            List<DadosEpsodio> epsodiosTemporada = temporadas.get(i).epsodios();
//            for (int j =0; j < epsodiosTemporada.size(); j++){
//                System.out.println(epsodiosTemporada.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.epsodios().forEach(e -> System.out.println(e.titulo())));
    }
}
