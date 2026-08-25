#include <stdio.h>
#include <stdlib.h>
/*
    Data: 19/08/2026
    Objetivo: Método iterativo que verifica o tamanho da maior substring
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
int main()
{
    char stringPrincipal[100], substring[100];
    int maior=1, cont, tamStringPrincipal=0, sinal=0, tamSubstring=0, posiSubstring=0, indiceRepetida;
    for(int i=0; i<100; i++)
    {
        stringPrincipal[i] = '\0';
        substring[i]='\0';
    }
    scanf("%s",stringPrincipal);
    printf("String: %s\n",stringPrincipal);
    printf("Substring antes de tudo: %s\n",substring);
    while(!(stringPrincipal[0]=='F' && stringPrincipal[1]=='I' && stringPrincipal[2]=='M' && stringPrincipal[3]=='\0'))
    {
        tamStringPrincipal = defineTamanho(stringPrincipal);
        tamSubstring = defineTamanho(substring);
        for(int j=0; j<tamStringPrincipal; j++)
        {
            cont=0;
            for(int k=0; k<tamSubstring; k++)
            {
                if(stringPrincipal[j]!=substring[k]) 
                {
                    cont++;
                    printf("%c diferente de %c\n",stringPrincipal[j], substring[k]);
                }
                else
                {
                    indiceRepetida = k;
                    printf("A letra repetida esta na posicao %d da substring\n",indiceRepetida);
                    k=tamSubstring;
                }
            }
            if(cont==tamSubstring) 
            {
                printf("Não tem repeticao na substring\n");
                substring[posiSubstring]=stringPrincipal[j];
                posiSubstring++;
                printf("Substring atualizada: %s\n",substring);
                tamSubstring = defineTamanho(substring);
                printf("Novo tamanho da substring: %d\n", tamSubstring);
            }
            else
            {
                for(int i=0; i<100; i++)
                    substring[i]='\0';
                posiSubstring=0;
                substring[posiSubstring] = stringPrincipal[indiceRepetida];
                //posiSubstring++;
                j=indiceRepetida+1;
                printf("Substring recomecou: %s\n",substring);
                printf("J agora eh %d\n",j);
                tamSubstring = defineTamanho(substring);
                printf("Novo tamanho da substring: %d\n", tamSubstring);
            }
            if(tamSubstring>maior) maior=tamSubstring;
        }
        printf("%d\n",maior);
        for(int i=0; i<100; i++)
        {
            stringPrincipal[i] = '\0';
            substring[i]='\0';
        }
        maior=1;
        posiSubstring=0;
        scanf("%s",stringPrincipal);
    }
    return 0;
}
