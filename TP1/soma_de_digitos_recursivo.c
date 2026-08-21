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
    //Chamada recursiva
    else resp = n%10 + somaDigitos(n/10);
    return resp;
}
int main()
{
    //Declaração de variáveis
    int n, resp;
    //Leitura do input
    scanf("%d",&n);
    //Laço de repetição
    while(n>=0)
    {
        resp = somaDigitos(n);
        printf("%d\n",resp);
        scanf("%d",&n);
    }
    return 0;
}