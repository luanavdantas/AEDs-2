#include <stdio.h>
#include <string.h>
typedef struct Filme{
	int premios, ano;
	char nome [100];
}Filme;
int determinaOrdem(Filme f[], int i, int j){
	return (f[i].premios > f[j].premios) || (f[i].premios==f[j].premios && f[i].ano< f[j].ano) || (f[i].premios == f[j].premios && f[i].ano == f[j].ano && strcmp(f[i].nome, f[j].nome)<0);
}
void construir(Filme f[], int tam){
	for(int i=tam; i>1; i/=2)
		if(determinaOrdem(f, i/2, i) == 1){
			Filme tmp = f[i];
			f[i] = f[i/2];
			f[i/2] = tmp;
		}
}
int hasFilho(int i, int tam){
	return (i <= tam/2);
}
int getMaiorFilho(Filme f[], int i, int tam){
	int filho = -1;
	if(2*i == tam || determinaOrdem(f, 2*i+1, 2*i) == 1) filho = 2*i;
	else filho = 2*i+1;
	return filho;
}
void reconstruir(Filme f[], int tam){
	int i = 1;
	while(hasFilho(i, tam) == 1){
		int filho = getMaiorFilho(f, i,tam);
		if(determinaOrdem(f, i, filho) == 1){
			Filme tmp = f[i];
			f[i] = f[filho];
			f[filho] = tmp;
			i=filho;
		}
		else i=tam;
	}
}
int main(){
	int N, K;
	while(scanf("%d%d",&N,&K) == 2){
		Filme f[N+1];
		for(int i=1; i<=N; i++)
			scanf("%d %d %[^\n]", &f[i].premios, &f[i].ano, f[i].nome);
		for(int tam=2; tam<=K; tam++)
			construir(f, tam);
		for(int i=K+1; i<=N; i++)
			if(determinaOrdem(f, i, 1) == 1){
				Filme tmp = f[i];
				f[i] = f[1];
				f[1] = tmp;
				reconstruir(f, K);
			}
		int tam = K;
		while(tam > 1){
			Filme tmp = f[tam];
			f[tam] = f[1];
			f[1] = tmp;
			tam--;
			reconstruir(f, tam);
		}
		for(int i=1; i<=K; i++)
			printf("%d %d %s\n", f[i].premios, f[i].ano, f[i].nome);
	}
	return 0;
}
