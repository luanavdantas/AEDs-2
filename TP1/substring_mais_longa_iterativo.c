#include <stdio.h>
#include <stdlib.h>
/*
    Data: 19/08/2026
    Objetivo: Método iterativo que verifica o tamanho da maior substring
    Autora: Luana Dantas
*/
int main()
{
    char stringPrincipal[100], substring[100];
    int maior=1, cont, tamStringPrincipal=0, sinal=0, tamSubstring=0, posiSubstring=1;
    for(int n=0; n<99; n++)
        stringPrincipal[n]='.';
    stringPrincipal[99]='\0';
    for(int p=0; p<99; p++)
        substring[p]='.';
    substring[99]='\0';
    scanf("%s",stringPrincipal);
    //printf("String: %s\n",stringPrincipal);
    //printf("Substring antes de tudo: %s\n",substring);
    while(!(stringPrincipal[0]=='F' && stringPrincipal[1]=='I' && stringPrincipal[2]=='M' && stringPrincipal[3]=='\0'))
    {
        for(int i=0; stringPrincipal[i]!='\0'; i++)
            tamStringPrincipal++;
        substring[0]=stringPrincipal[0];
        tamSubstring = 1;
        //printf("Caractere na primeira posicao: %s\n",substring);
        for(int j=1; j<tamStringPrincipal; j++)
        {
            cont=1;
            for(int k=0; k<tamSubstring; k++)
            {
                if(stringPrincipal[j]!=substring[k]) 
                {
                    cont++;
                    //printf("%c diferente de %c\n",stringPrincipal[j], substring[k]);
                    //printf("Contador atualizado: %d\n",cont);
                }
                else
                {
                    k=j;
                    sinal=1;
                }
            }
            if(sinal==1) 
            {
                //printf("Sinal: %d (caractere igual foi encontrado)\n",sinal);
                for(int m=0; m<99; m++)
                    substring[m]='.';
                substring[99]='\0';
                substring[0] = stringPrincipal[j];
                //printf("Substring recomecou: %s\n",substring);
                tamSubstring=1;
                //printf("Novo tamanho da substring: %d\n", tamSubstring);
                posiSubstring=1;
                //printf("Proxima posicao da substring: %d\n",posiSubstring);
                sinal=0;
            }
            else
            {
                substring[posiSubstring]=stringPrincipal[j];
                //printf("Substring atualizada: %s\n",substring);
                tamSubstring++;
                //printf("Novo tamanho da substring: %d\n", tamSubstring);
                posiSubstring++;
                //printf("Proxima posicao da substring: %d\n",posiSubstring);
                //printf("Caractere na posicao atual: %c\n",substring[posiSubstring-1]);
            }
            if(cont>maior) maior=cont;
        }
        printf("%d\n",maior);
        for(int n=0; n<99; n++)
            stringPrincipal[n]='.';
        stringPrincipal[99]='\0';
        for(int p=0; p<99; p++)
            substring[p]='.';
        substring[99]='\0';
        maior=1;
        tamStringPrincipal=0; 
        sinal=0;
        posiSubstring=1;
        scanf("%s",stringPrincipal);
    }
    return 0;
}