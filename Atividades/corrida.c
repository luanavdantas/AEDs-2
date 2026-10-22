#include <stdio.h>
typedef struct Corredor{
    char nome[50];
    int hora, min, seg;
}Corredor;
int tempo(Corredor c){
    return c.seg + c.min*60 + c.hora*3600;
}
int main(){
    int N, K;
    while(scanf("%d%d",&N,&K) ==2){
        Corredor c[N];
    for(int i=0; i<N; i++){
        scanf("%s%d%d%d", c[i].nome, &c[i].hora, &c[i].min, &c[i].seg);
    }
    for(int i=1; i<N; i++){
        Corredor tmp = c[i];
        int j = i<K ? i-1 : K-1;
        while(j>=0 && tempo(c[j]) > tempo(tmp)){
            c[j+1] = c[j];
            j--;
        }
        c[j+1] = tmp;
    }
    for(int i=0; i<K; i++){
        printf("%s %d %d %d\n", c[i].nome, c[i].hora, c[i].min, c[i].seg);
    }
}
    return 0;
}