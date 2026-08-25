package TP1;
import java.util.*;
/*
    Data: 22/08/26
    Autora: Luana Dantas
    Objetivo: Método iterativo que faz o ciframento de césar
*/
public class CiframentoIterativo
{
    public static String ciframento (String p)
    {
        String nString = "";
        char letra;
        for(int i=0; i<p.length(); i++)
        {
            letra = p.charAt(i); //Letra recebe o char a ser alterado
            letra += 3; //Soma-se 3 bytes
            nString+=letra;
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
        String s1;
        s1 = sc.nextLine();
        while(comparar(s1))
        {
            System.out.println(ciframento(s1));
            s1 = sc.nextLine();
        }
        sc.close();
    }
}
