#include <stdio.h>
#include <string.h>
void insertion(char lista[][21], int N){
    for(int j=1; j<N; j++)
    {
        char atual[20];
        strcpy(atual,lista[j]);
        int k = j-1;
        while(k>=0 && strcmp(lista[k], atual)>0){
            strcpy(lista[k+1], lista[k]);
            k--;
        }
        
        strcpy(lista[k+1], atual);
    }
    for(int k=0; k<N; k++) 
        printf("%s\n", lista[k]);
}
int main()
{
    int N, contBoa=0, contMa=0;
    char sinal, nome[21];
    scanf("%d",&N);
    char criancas[N][21];
    for(int i=0; i<N; i++)
    {
        scanf(" %c %s", &sinal, nome);
        if(sinal =='+') {
            contBoa++;
            //printf("Boa\n");
        }
        else if(sinal == '-') {
            contMa++;
            //printf("Ma\n");
        }
        strcpy(criancas[i],nome);
    }
    insertion(criancas, N);
    printf("Se comportaram: %d | Nao se comportaram: %d", contBoa, contMa);
    return 0;
}