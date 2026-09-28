#include <stdio.h>
typedef struct Corredor{
	char nome[100];
	int horas, min, seg;
}Corredor;
int tempo(Corredor c){
	return (c.seg + 60*c.min + 3600*c.horas);
}
int main(){
	Corredor c[1000];
	int i=0;
	while(scanf(" %s%d%d%d",c[i].nome, &c[i].horas, &c[i].min, &c[i].seg)!=EOF){
//		printf("contador: %d\n", i);
//	scanf(" %s%d%d%d",c[i].nome, &c[i].horas, &c[i].min, &c[i].seg);
//	while(c[i].horas != -1 && c[i].min !=-1 && c[i].seg != -1){
		i++;
//		scanf(" %s%d%d%d", c[i].nome, &c[i].horas, &c[i].min, &c[i].seg);
	}
	for(int j=1; j<i; j++){
		Corredor tmp = c[j];
		int k=j-1;
		while(k>=0 && tempo(c[k]) > tempo(tmp)){
			c[k+1] = c[k];
			k--;
		}
		c[k+1] = tmp;
	}
	for(int p=0; p<i; p++)
		printf("%s %d %d %d\n", c[p].nome, c[p].horas, c[p].min, c[p].seg);
	return 0;
}
