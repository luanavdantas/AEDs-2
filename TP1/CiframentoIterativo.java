package TP1;
import java.util.*;
/*
    Data: 22/08/26
    Autora: Luana Dantas
    Objetivo: Método iterativo que faz o ciframento de césar
*/
public class CiframentoIterativo
{
    public static void ciframento (String p)
    {
        //Não consegui pensar em um jeito de retornar um vetor de char 
        //que recebe as letras alteradas então já exibi o resultado no próprio método
        char letra;
        for(int i=0; i<p.length(); i++)
        {
            letra = p.charAt(i); //Letra recebe o char a ser alterado
            letra += 3; //Soma-se 3 bytes
            System.out.print(letra); 
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1;
        while(sc.hasNext())
        {
            s1 = sc.nextLine();
            ciframento(s1);
        }
        sc.close();
    }
}