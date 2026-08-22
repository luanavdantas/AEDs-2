package TP1;
import java.util.*;
/*
    Data: 22/08/26
    Autora: Luana Dantas
    Objetivo: Métodos iterativos que verificam se a string é composto apenas de vogais, consoantes, inteiros ou reais
*/
public class IsIterativo {
    public static boolean isInteiro (String s)
    {
        boolean resp = false;
        int cont=0; //Contador de quantas vezes a condição foi satisfeita
        for(int i=0; i<s.length();i++)
            //Verifica se o caractere é número
            if(s.charAt(i)>='0' && s.charAt(i)<='9') cont++;
        if(cont==s.length()) resp = true; //Significa que a condição é verdadeira para todos os caracteres
        return resp;
    }
    public static boolean isVogal (String s)
    {
        boolean resp = false;
        int cont=0; //Contador de quantas vezes a condição foi satisfeita
        for(int i=0; i<s.length();i++)
            //Verifica se o caractere é vogal
            if(s.charAt(i) == 'a' || s.charAt(i)=='e' || s.charAt(i) == 'i' 
                || s.charAt(i) == 'o' || s.charAt(i) == 'u'
                || s.charAt(i) == 'A' || s.charAt(i) == 'E' || s.charAt(i) == 'I' 
                || s.charAt(i) == 'O' || s.charAt(i) == 'U') cont++;
        if(cont==s.length()) resp = true;//Significa que a condição é verdadeira para todos os caracteres
        return resp;
    }
    public static boolean isConsoante (String s)
    {
        boolean resp = false;
        int cont=0; //Contador de quantas vezes a condição foi satisfeita
        for(int i=0; i<s.length();i++)
        {
            //Verifica se o caractere é número, interrompendo o laço se for
            if(!(s.charAt(i)>='A' && s.charAt(i)<='Z') || !(s.charAt(i)>='a' && s.charAt(i)<='z')) i = s.length();
            //Verifica se o cractere é consoante
            else if(!(s.charAt(i) == 'a' || s.charAt(i)=='e' || s.charAt(i) == 'i' 
                || s.charAt(i) == 'o' || s.charAt(i) == 'u'
                || s.charAt(i) == 'A' || s.charAt(i) == 'E' || s.charAt(i) == 'I' 
                || s.charAt(i) == 'O' || s.charAt(i) == 'U')) cont++;
        }
        if(cont==s.length()) resp = true; //Significa que a condição é verdadeira para todos os caracteres
        return resp;
    }
    public static boolean isReal (String s)
    {
        boolean resp=false;
        int cont=0, tam=0;
        //Cont guarda a quantidade de separadores "." ou "," da string, que simbolizam as casas decimais do número real
        //Um número real tem 0 ou 1 separador
        for(int i=0; i<s.length(); i++)
        {
            //Verifica se tem separador
            if(s.charAt(i) == '.' || s.charAt(i) == ',') 
            {
                cont++;
                tam++;
            }
            //Verifica se o caractere é número e se a condição de casas decimais está dentro
            //do possível para números reais
            if(s.charAt(i)>='0' && s.charAt(i)<='9' && cont<2) tam++;
        }
        if(tam==s.length()) resp = true; //Significa que a condição é verdadeira para todos os caracteres
        return resp;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        boolean x1, x2, x3, x4;
        while(sc.hasNext())
        {
            str = sc.nextLine();
            x1 = isVogal(str);
            if(x1 == true) System.out.print("SIM ");
            else System.out.print("NAO ");
            x2 = isConsoante(str);
            if(x2 == true) System.out.print("SIM ");
            else System.out.print("NAO ");
            x3 = isInteiro(str);
            if(x3 == true) System.out.print("SIM ");
            else System.out.print("NAO ");
            x4 = isReal(str);
            if(x4 == true) System.out.println("SIM ");
            else System.out.println("NAO ");
        }
        sc.close();
    }
}
