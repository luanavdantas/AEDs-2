#include <stdio.h>
#include <stdlib.h>
/*
    Data: 18/08/2026
    Objetivo: Método iterativo que recebe uma string como parâmetro e retorna a string invertida
    Autora: Luana Dantas
*/
int defineTamanho (char string[])
{
    int tam=0;
    //Determinação do tamanho da string de entrada
    for(int i=0; string[i]!='\0'; i++)
            tam++;
    return tam;
}
char* inverteString (char string[])
{
    int tam=defineTamanho(string);;
    static char stringFinal[100];
    //Inicialização do vetor
    for(int m=0; m<100; m++)
            stringFinal[m]='\0';
    //Construção e saída da string invertida
    for(int j=0; j<tam; j++)
        stringFinal[j] = string[tam-j-1];
    return stringFinal;
}
int main()
{
    //Declaração de variáveis
    char stringInicial[100];
    //Leitura da entrada
    scanf(" %[^\n]",stringInicial);
    //Laço de repetição que faz a leitura até a palavra finalizadora
    while(!(stringInicial[0]=='F' && stringInicial[1]=='I' && stringInicial[2]=='M' && stringInicial[3]=='\0'))
    {
        printf("%s\n",inverteString(stringInicial));
        scanf(" %[^\n]",stringInicial);
    }
    return 0;
}