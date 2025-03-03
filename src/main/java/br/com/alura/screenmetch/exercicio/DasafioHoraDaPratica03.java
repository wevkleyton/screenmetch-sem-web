package br.com.alura.screenmetch.exercicio;

import java.io.FilterOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DasafioHoraDaPratica03 {
    public static void main(String[] args) {

        /* 1 - Dada a lista de números inteiros abaixo, filtre apenas os números pares e imprima-os. */
        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6);
        numeros.stream().filter( n -> n % 2 == 0)
                .forEach(System.out::println);
        /* 1 - Dada a lista de números inteiros abaixo, filtre apenas os números pares e imprima-os. */
        List<String> palavras = Arrays.asList("apple", "banana", "apple", "orange", "banana");
        palavras.stream().map(String::toUpperCase).forEach(System.out::println);

        /* 3 - Dada a lista de números inteiros abaixo, filtre os números ímpares, multiplique cada um por
        2 e colete os resultados em uma nova lista. */

        List<Integer> resultado =  numeros.stream().filter(n -> n % 2 == 1)
                .map( n -> n *2 )
                .collect(Collectors.toList());
        System.out.println(resultado);

        /* 4 - Filtrando dados duplicados */
        List<String> unicas = palavras.stream().distinct().collect(Collectors.toList());
        System.out.println(unicas);

        /* 5 - Filtrando e ordenando números primos */

        List<List<Integer>> listaDeNumeros = Arrays.asList(
                Arrays.asList(1,2,3,4),
                Arrays.asList(5,6,7,8),
                Arrays.asList(9,10,11,12)
        );
        List<Integer> numerosPrimos = listaDeNumeros.stream()
                .flatMap(List::stream)
                .filter(DasafioHoraDaPratica03::ehPrimo)
                .sorted()
                .collect(Collectors.toList());


        /* 6 - Filtrando pessoas */

    }

    private static boolean ehPrimo(int numero){
        if(numero < 2) return  false;
        for (int i = 2; i <=Math.sqrt(numero); i++){
            if (numero % i == 0){
                return false;
            }
        }
        return true;
    }

}
