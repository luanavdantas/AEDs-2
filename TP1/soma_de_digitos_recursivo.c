#include <stdio.h>
#include <stdlib.h>
/*
    Data: 18/08/2026
    Objetivo: Método recursivo que soma os dígitos de um número
    Autora: Luana Dantas
*/
int somaDigitos (int n)
{
    int resp;
    //Condição de parada
    if(n==0) resp = 0;
    //Chamada recursiva que adiciona o último dígito à soma e passa os outros dígitos como parâmetro
    else resp = n%10 + somaDigitos(n/10);
    return resp;
}
int main()
{
    //Declaração de variáveis
    int n, resp, x;
    //Laço de repetição
    while((x = scanf("%d",&n))!=EOF)
    {
        resp = somaDigitos(n);
        printf("%d\n",resp);
    }
    return 0;
}