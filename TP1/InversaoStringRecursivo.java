package TP1;
import java.util.*;
/*
    Data: 22/08/26
    Autora: Luana Dantas
    Objetivo: Método recursivo que invere uma string
*/
public class InversaoStringRecursivo {
    public static void inverte (String s, int i)
    {
        char letra;
        int tam = s.length();
        //Condição de parada que significa o fim da string
        if(i==s.length()) return;
        //Não consegui pensar em um jeito de retornar um vetor de char 
        //que recebe as letras alteradas então já printo o resultado no próprio método
        letra = s.charAt(tam-i-1); 
        System.out.print(letra); //Exibe a string, letra a letra, de trás para frente
        inverte(s,i+1); //Chamada recursiva
    }
    public static void chamaInverte (String s)
    {
        inverte(s, 0);
    }
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       String str;
       while(sc.hasNext())
       {
            str = sc.nextLine();
            chamaInverte(str);
            System.out.println();
       }
       sc.close(); 
    }
}
