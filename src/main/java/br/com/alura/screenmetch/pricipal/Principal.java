package br.com.alura.screenmetch.pricipal;

import br.com.alura.screenmetch.model.DadosEpsodio;
import br.com.alura.screenmetch.model.DadosSerie;
import br.com.alura.screenmetch.model.DadosTemporada;
import br.com.alura.screenmetch.model.Episodio;
import br.com.alura.screenmetch.service.ConsultaApi;
import br.com.alura.screenmetch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
        List<String> nomes = Arrays.asList("jacque","Iasmin", "Paulo", "Rodrigo", "Nico");

//        nomes.stream().sorted().limit(3).filter(n -> n.startsWith("N"))
//                .map(n -> n.toUpperCase()).forEach(System.out::println);

        List<DadosEpsodio> dadosEpsodios = temporadas.stream().flatMap(t -> t.epsodios().stream())
                .collect(Collectors.toList());
//                .toList();

        System.out.println("\n top 5 epsodios!");
        dadosEpsodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpsodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.epsodios().stream()
                .map(d -> new Episodio(t.numero(), d))).collect(Collectors.toList());

        episodios.forEach(System.out::println);

        System.out.println("A partir de que ano você deseja ver os episódios? :");
        var ano = leitura.nextInt();
        leitura.nextLine();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
        episodios.stream().filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println("Temporada: " + e.getTemporada() +
                                                         " Eposódio: " + e.getTitulo() +
                                                         "Data Laçamento: " + e.getDataLancamento().format(formatador)));
    }
}
