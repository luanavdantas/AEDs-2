package TP1;
import java.util.*;
/*
    Data: 22/08/26
    Autora: Luana Dantas
    Objetivo: Método iterativo que substitui todas as ocorrências da primeira letra
    pela segunda letra, ambas geradas aleatoriamente
*/
public class AlteracaoAleatoriaIterativo {
    public static void altera(String nova, char primLetra, char segLetra)
    {
        //Não consegui pensar em um jeito de retornar um vetor de char 
        //que recebe as letras alteradas então já exibi o resultado no próprio método
        for(int i=0; i<nova.length(); i++)
        {
            if(nova.charAt(i)==primLetra)System.out.print(segLetra); //Se a letra for a primeira, printa a segunda
            else System.out.print(nova.charAt(i)); //Se não for, mantém o char inalterado
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        Random gerador = new Random();
        gerador.setSeed(4);
        char primeiraLetra, segundaLetra;
        while(sc.hasNext())
        {
            str = sc.nextLine();
            //Geração de letras aleatórias
            primeiraLetra = (char)('a'+(Math.abs(gerador.nextInt()%26)));
            segundaLetra = (char)('a'+(Math.abs(gerador.nextInt()%26)));
            //System.out.println("As letras sorteadas foram " + primeiraLetra + " e " + segundaLetra);
            altera(str, primeiraLetra, segundaLetra); //Chamada do método que altera as letras da string
        }
        sc.close();
    }
}
