#include <stdio.h>
#include <stdlib.h>
/*
    Data: 19/08/2026
    Objetivo: Método iterativo que faz o ciframento de césar
    Autora: Luana Dantas
*/
void ciframento (char *p, int j)
{
    if(*(p+j)!='\0')
    {
        *(p+j)+=3; //Aumenta 3 bytes em cada caractere
        ciframento(p,j+1); //Chamada recursiva que passa a próxima posição como prâmetro
    }
}
void chamaCiframento (char *p)
{
    ciframento(p,0);
}
int main()
{
    char s[100];
    scanf(" %[^\n]",s);
    while(!(s[0]=='F' && s[1]=='I' && s[2]=='M' && s[3]=='\0'))
    {
        chamaCiframento(s); //Passa a posição do primeiro char como parâmetro
        printf("%s\n",s);
        scanf(" %[^\n]",s);
    }
    return 0;
}