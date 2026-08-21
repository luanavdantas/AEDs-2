#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
/*
    Data: 19/08/2026
    Objetivo: Método iterativo que verifica se duas strings são anagramas
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
bool verificaAnagrama (char s1[], char s2[])
{
    int tam1=defineTamanho(s1), tam2=defineTamanho(s2);
    bool resp=false;
    //Conversão para letra minúscula
        for(int i=0; s1[i]!='\0'; i++)
            if(s1[i]>='A' && s1[i]<='Z') s1[i] += 32;
        for(int i=0; s2[i]!='\0'; i++)
            if(s2[i]>='A' && s2[i]<='Z') s2[i] += 32;
        //Comparação dos caracteres
        for(int k=0; k<tam1; k++)
        {
            for(int m=0; m<tam2; m++)
            {
                if(s1[k]==s2[m])
                {
                    resp=true;
                    s2[m] = '.'; //Caractere já encontrou seu correspondente
                    m=tam2; //Sai do for interno
                }
                else resp=false;
            }
            if(resp==false) k=tam1; //Se um dos caracteres do vetor 1 não encontrar correspondentes, as string não são anagramas
        }
        return resp;
}
int main()
{
    //Declaração das variáveis
    char s1[100],s2[100];
    bool resp;
    //Leitura da entrada
    scanf("%s",s1);
    //Laço de repetição com condição de parada
    while(!(s1[0]=='F' && s1[1]=='I' && s1[2]=='M' && s1[3]=='\0'))
    {
        scanf("%s",s2);
        resp=verificaAnagrama(s1,s2);
        if(resp==true) printf("SIM\n");
        else printf("NAO\n");
        scanf("%s",s1);
    }
    return 0;
}