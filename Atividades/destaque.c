#include <stdio.h>
#include <string.h>
typedef struct Aluno{
    int nota, faltas;
    char nome[60];
}Aluno;
void quicksort(Aluno a[], int esq, int dir, int k){
    int i=esq, j=dir;
    Aluno pivo = a[(esq+dir)/2];
    while(i<=j){
        while((a[i].nota > pivo.nota) || (a[i].nota==pivo.nota && a[i].faltas < pivo.faltas) || (a[i].nota == pivo.nota && a[i].faltas == pivo.faltas && strcmp(a[i].nome, pivo.nome) < 0)) i++;
        while((pivo.nota > a[j].nota) || (pivo.nota == a[j].nota && pivo.faltas < a[j].faltas) || (pivo.nota == a[j].nota && pivo.faltas == a[j].faltas && strcmp(pivo.nome, a[j].nome) < 0)) j--;
	if(i<=j){
		Aluno tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
		i++;
		j--;
	}
    }
	if(esq < j) quicksort(a, esq, j, k);
	if(i<k && i<dir) quicksort(a, i,dir, k);
}
int main(){
    int N,K;
    while(scanf("%d%d",&N,&K) == 2){
        Aluno a[N];
        for(int i=0; i<N; i++){
            scanf("%d%d %[^\n]",&a[i].nota,&a[i].faltas, a[i].nome);
	}
        quicksort(a, 0, N-1, K);
	for(int i=0; i<K; i++){
		printf("%d %d %s\n",a[i].nota, a[i].faltas, a[i].nome);
	}
    }
    return 0;
}
