package TP1;
import java.util.*;
/*
    Data: 22/08/26
    Autora: Luana Dantas
    Objetivo: Método iterativo que verifica se uma senha é válida
*/
public class VerificaSenhaIterativo {
    public static boolean verifica(String str)
    {
        boolean resp = false, tam = false;
        int cont=0; //Contador de quantas vezes a condição foi satisfeita
        int sinalLetraMaiuscula = 0, sinalLetraMinuscula = 0, sinalEspecial=0, sinalNumero=0;
        if(str.length()>=8) tam = true; //Verifica se a condição de tamanho foi satisfeita
        if(tam == true)
        {
            for(int i=0; i<str.length(); i++)
            {
                if(str.charAt(i)>='A' && str.charAt(i)<='Z')
                {
                    cont++;
                    sinalLetraMaiuscula=1;
                }
                else if(str.charAt(i)>='a' && str.charAt(i)<='z')
                {
                    cont++;
                    sinalLetraMinuscula=1;
                }
                else if (str.charAt(i)>='0' && str.charAt(i)<='9')
                {
                    cont++;
                    sinalNumero=1;
                }
                else if(str.charAt(i)=='!' || str.charAt(i)=='@' || str.charAt(i)=='#' || str.charAt(i)=='$' || str.charAt(i)=='%' || str.charAt(i)=='&')
                {
                    cont++;
                    sinalEspecial=1;
                }
                else i = str.length();
            }
        }
        if(cont==str.length() && tam == true && sinalLetraMaiuscula>=1 && sinalLetraMinuscula>=1 
            && sinalEspecial>=1 && sinalNumero>=1) resp = true; 
            //A resposta se torna verdadeira se todos os verificadores estão dentro das condições pré-estabelecidas
        return resp;
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
        String senha;
        boolean x;
        senha = sc.nextLine();
        while(comparar(senha))
        {
            x = verifica(senha);
            if(x==true)System.out.println("SIM");
            else System.out.println("NAO");
            senha = sc.nextLine();
        }
        sc.close();
    }
}
