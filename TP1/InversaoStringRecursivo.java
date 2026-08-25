package TP1;
import java.util.*;
/*
    Data: 22/08/26
    Autora: Luana Dantas
    Objetivo: Método recursivo que invere uma string
*/
public class InversaoStringRecursivo {
    public static String inverte (String s, int i)
    {
        int tam = s.length();
        //Condição de parada que significa o fim da string
        if(i==s.length()) return "";
        return s.charAt(tam-i-1) + inverte(s,i+1); //Chamada recursiva
    }
    public static String chamaInverte (String s)
    {
        return inverte(s, 0);
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
       str = sc.nextLine();
       while(comparar(str))
       {
            System.out.println(chamaInverte(str));
            str = sc.nextLine();
       }
       sc.close(); 
    }
}
