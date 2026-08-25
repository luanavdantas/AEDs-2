#include <stdio.h>
#include <stdlib.h>
/*
    Data: 21/08/2026
    Objetivo: Métodos recursivos que verificam se a string é composto apenas de vogais, consoantes, inteiros ou reais
    Autora: Luana Dantas
*/
int isVogal (char s[], int i)
{
    int resp;
    //Condição de parada
    if(s[i] == '\0') return 1; //Fim da string
    //Verifica se é vogal
    if(s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u') resp = isVogal(s,i+1); //Chamada recursiva que verifica a próxima posição
    //Verifica se o caractere é letra
    else if(!(s[i]>='a' && s[i]<='z')) resp = 0;
    else resp = 0;
    return resp;
}
int chamaIsVogal (char s[])
{
    return isVogal(s,0); //Inicia o contador
}
int isConsoante (char s[], int i)
{
    int resp;
    //Condição de parada
    if(s[i] == '\0') return 1;
    //Verifica se é consoante
    if(s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u') resp = 0;
    //Verifica se o caractere é letra
    else if(!(s[i]>='a' && s[i]<='z')) resp = 0;
    //Chamada recursiva que verifica a próxima posição
    else resp = isConsoante(s,i+1);
    return resp;
}
int chamaIsConsoante (char s[])
{
    return isConsoante(s,0); //Inicia o contador
}
int isInteiro (char s[], int i)
{
    int resp;
    //Condição de parada
    if(s[i] == '\0') return 1;
    //Verifica se o caractere é número
    if(!(s[i]>='0' && s[i]<='9')) resp = 0;
    //Chamada recursiva que verifica a próxima posição
    else resp = isInteiro(s, i+1);
    return resp;
}
int chamaIsInteiro(char s[])
{
    return isInteiro(s,0); //Inicia o contador
}
int isReal(char s[], int i, int cont)
{
    int resp;
    //Cont guarda a quantidade de separadores "." ou "," da string, que simbolizam as casas decimais do número real
    //Um número real tem 0 ou 1 separador
    //Condição de parada
    if(s[i] == '\0' && cont<2) return 1;
    //Verifica se tem separador e faz a chamada recursiva que verifica a próxima posição e incrementa 1 no contador
    if(s[i] == '.' || s[i] == ',') resp = isReal(s, i+1, cont+1);
    //Verifica se o caractere é número
    else if(!(s[i]>='0' && s[i]<='9')) resp = 0;
    //Chamada recursiva que verifica a próxima posição
    else resp = isReal(s, i+1, cont);
    return resp;
}
int chamaIsReal(char s[])
{
    return isReal(s,0,0); //Inicia os contadores
}
int main()
{
    char string[100];
    int x1, x2, x3, x4;
    scanf(" %[^\n]",string);
    while(!(string[0]=='F' && string[1]=='I' && string[2]=='M' && string[3]=='\0'))
    {
        //Conversão para letra minúscula
        for(int i=0; string[i]!='\0'; i++)
            if(string[i]>='A' && string[i]<='Z') string[i] += 32; //aumenta 32 bytes para cada caratere maiúsculo
        //Verifica o valor booleano das variáveis
        x1 = chamaIsVogal(string);
        if(x1 == 1) printf("SIM ");
        else printf("NAO ");
        x2 = chamaIsConsoante(string);
        if(x2 == 1) printf("SIM ");
        else printf("NAO ");
        x3 = chamaIsInteiro(string);
        if(x3 == 1) printf("SIM ");
        else printf("NAO ");
        x4 = chamaIsReal(string);
        if(x4 == 1) printf("SIM\n");
        else printf("NAO\n");
        scanf(" %[^\n]",string);
    }
    return 0;
}
