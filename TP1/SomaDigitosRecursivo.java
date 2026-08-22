package TP1;
import java.util.*;
/*
    Data: 22/08/26
    Autora: Luana Dantas
    Objetivo: Método recursivo que soma os dígitos de um número
*/

public class SomaDigitosRecursivo {
    public static int somaDigitos (int n)
    {
        int resp;
        //Condição de parada
        if(n<10) resp = n;
        //Chamada recursiva que adiciona o último dígito à soma e passa os outros dígitos como parâmetro
        else resp = n%10 + somaDigitos(n/10);
        return resp;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num, soma;
        while(sc.hasNext())
        {
            num = sc.nextInt();
            soma = somaDigitos(num);
            System.out.println(soma);
        }
        sc.close();
    }
}
