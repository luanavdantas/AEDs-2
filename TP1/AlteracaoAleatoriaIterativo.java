
import java.util.*;
/*
    Data: 22/08/26
    Autora: Luana Dantas
    Objetivo: Método iterativo que substitui todas as ocorrências da primeira letra
    pela segunda letra, ambas geradas aleatoriamente
*/
public class AlteracaoAleatoriaIterativo {
    public static String altera(String nova, char primLetra, char segLetra)
    {
        String nString = "";
        for(int i=0; i<nova.length(); i++)
        {
            if(nova.charAt(i)==primLetra)nString+=segLetra; //Se a letra for a primeira, adiciona a segunda
            else nString+=nova.charAt(i); //Se não for, mantém o char inalterado
        }
        return nString;
    }
    public static boolean comparar(String nova)
    {
        boolean resp;
        if(nova.charAt(0)=='F' && nova.charAt(1)=='I' && nova.charAt(2)=='M' && nova.length()==3) resp = false;
        else resp = true;
        return resp;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        Random gerador = new Random();
        gerador.setSeed(4);
        char primeiraLetra, segundaLetra;
        str = sc.nextLine();
        while(comparar(str))
        {
            //Geração de letras aleatórias
            primeiraLetra = (char)('a'+(Math.abs(gerador.nextInt()%26)));
            segundaLetra = (char)('a'+(Math.abs(gerador.nextInt()%26)));
            //System.out.println("As letras sorteadas foram " + primeiraLetra + " e " + segundaLetra);
            System.out.println(altera(str, primeiraLetra, segundaLetra)); //Chamada do método que altera as letras da string
            str = sc.nextLine();
        }
        sc.close();
    }
}
