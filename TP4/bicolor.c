#include <stdio.h>
#include <string.h>
#include <stdlib.h>
/*
    Data: 23/11/2026
    Autora: Luana
*/
typedef struct Data{
	int dia, mes, ano;
}Data;
Data parseData(char *s){
	Data data;
    sscanf(s, "%d-%d-%d", &data.ano, &data.mes, &data.dia);
    return data;
}	
void formatData(Data d, char *buffer){
	sprintf(buffer,"%02d/%02d/%04d", d.dia, d.mes, d.ano);
}
typedef struct Veiculo{
    int id, ano, cilindros, turbo;
    char marca[100], modelo[100], categoria[100], transmissao[100],tracao[100];
    double cilindrada, consumoCidade, consumoEstrada, co2;
    char combustivel[2][100];
    Data dataRegistro;
}Veiculo;
void parseCombustivel(char *linha, char combustivel[2][100]){
    char *tok = strtok(linha, ";");
    strcpy(combustivel[0],tok);
    tok = strtok(NULL, ";");
    if(tok!=NULL)strcpy(combustivel[1], tok);
    else strcpy(combustivel[1],"");
}
Veiculo* parseVeiculo(char *s){
    char *infos[15]; 
    Veiculo *veiculo = malloc(sizeof(Veiculo));
    char *tok = strtok(s, ",");
    infos[0] = tok;
    for(int i=1; i<15; i++){
        tok = strtok(NULL, ",");
        infos[i] =tok;
    }
    veiculo->id = atoi(infos[0]);
	strcpy(veiculo->marca,infos[1]);
    strcpy(veiculo->modelo,infos[2]);
    veiculo->ano=atoi(infos[3]);
    strcpy(veiculo->categoria, infos[4]);
    parseCombustivel(infos[5],veiculo->combustivel);
    veiculo->cilindros = atoi(infos[6]);
    veiculo->cilindrada = atof(infos[7]);
    strcpy(veiculo->transmissao,infos[8]);
    strcpy(veiculo->tracao,infos[9]);
    veiculo->consumoCidade = atof(infos[10]);
    veiculo->consumoEstrada = atof(infos[11]);
    veiculo->co2 = atof(infos[12]);
    if(strcmp(infos[13], "true")==0) veiculo->turbo =1;
    else if(strcmp(infos[13],"false")==0) veiculo->turbo=0;
    veiculo->dataRegistro = parseData(infos[14]);
	return veiculo;
    }
void formatVeiculo (Veiculo v, char *buffer){
    char turbo[10];
    char *bufferData = (char*)malloc(20*sizeof(char));
    strcpy(buffer,v.combustivel[0]);
    if(v.combustivel[1][0]!='\0'){
        strcat(buffer,",");
        strcat(buffer,v.combustivel[1]);
    }
    if(v.turbo==1)strcpy(turbo,"true");
    else if(v.turbo==0) strcpy(turbo,"false");
    formatData(v.dataRegistro, bufferData);
    printf("[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1lf ## %s ## %s ## %.2lf ## %.2lf ## %.1lf ## %s ## %s]\n",v.id,v.marca,v.modelo,v.ano,v.categoria,buffer,v.cilindros,v.cilindrada,v.transmissao,v.tracao,v.consumoCidade,v.consumoEstrada,v.co2,turbo, bufferData);
    free(bufferData);
}
Veiculo* lerCsv(char caminhoArquivo[50], int *n){
    Veiculo *veiculos = malloc((*n)*sizeof(Veiculo));
    FILE *dados = fopen(caminhoArquivo, "r");
    if(dados==NULL) printf("ERRO");
	char *linha = (char*)malloc(1000*sizeof(char));
    fscanf(dados," %[^\n]",linha); //ignora o cabeçalho
	for(int j = 0; j < (*n) && fscanf(dados," %[^\n]",linha)!=EOF; j++){
        *(veiculos+j) = *(parseVeiculo(linha));
    }
    fclose(dados);
    return veiculos;
    } 
typedef struct No{
	Veiculo elemento;
	struct No *esq, *dir;
    int cor;
}No;
No* raiz;
void start(){
	raiz = NULL;
}
No* newNo (Veiculo x){
	No* novo = malloc(sizeof(No));
	novo->elemento = x;
	novo->esq = novo->dir = NULL;
    novo->cor = 1;
    return novo;
}
int isNoTipoQuatro(No* i){
    int resp = 0;
    if(i->esq != NULL && i->dir !=NULL && i->cor == 0 && i->esq->cor == 1 && i->dir->cor == 1) resp = 1;
    return resp;
}
void fragmentar(No* i){
    i->cor = 1;
    i->esq->cor = i->dir->cor = 0;
}
No* rotacionarEsq(No* i){
	No* filho = i->dir;
	i->dir = filho->esq;
	filho->esq = i;
	return filho;
}
No* rotacionarDir(No* i){
	No* filho = i->esq;
	i->esq = filho->dir;
	filho->dir = i;
	return filho;
}
void balancear(No* bisavo, No* avo, No* pai, No* i){
    if(strcmp(pai->elemento.modelo, avo->elemento.modelo)>0){
        if(strcmp(pai->elemento.modelo, i->elemento.modelo)>0) avo->dir = rotacionarDir(pai);
        avo = rotacionarEsq(avo);
    }
    else{
        if(strcmp(pai->elemento.modelo, i->elemento.modelo)<0) avo->esq = rotacionarEsq(pai);
        avo = rotacionarDir(avo);
    }
    if(bisavo == NULL) raiz = avo;
    else if (strcmp(bisavo->elemento.modelo, avo->elemento.modelo)>0) bisavo->esq = avo;
    else bisavo->dir = avo;
    avo->cor = 0;
    avo->esq->cor = avo->dir->cor = 1;
}
void inserir(Veiculo x, No* bisavo, No* avo, No* pai, No* i){
	if(i==NULL){
        if(strcmp(pai->elemento.modelo, x.modelo)>0) i = pai->esq = newNo(x);
        else i = pai->dir = newNo(x);
        if(pai->cor == 1) balancear(bisavo, avo, pai, i);
    }
	else{
        if(isNoTipoQuatro(i) == 1){
            fragmentar(i);
            if(i == raiz) i->cor = 0;
            else if(pai->cor == 1) balancear(bisavo, avo, pai, i);
        }
        if(strcmp(i->elemento.modelo, x.modelo)>0) inserir(x, avo, pai, i, i->esq);
        else if(strcmp(i->elemento.modelo, x.modelo)<0) inserir(x, avo, pai, i, i->dir);
    }
}
int pesquisar(char modelo[100], No* i){
	if(i==NULL) return 0;
	else if(strcmp(i->elemento.modelo, modelo)==0) return 1;
	else if(strcmp(i->elemento.modelo, modelo)>0) {
		printf("esq ");
		return pesquisar(modelo, i->esq);
	}
	else{
		printf("dir ");
		return pesquisar(modelo, i->dir);
	}
}
void caminharEm(No* i){
	if(i!=NULL){
		caminharEm(i->esq);
		char *buffer = (char*)malloc(200*sizeof(char));
        	formatVeiculo(i->elemento, buffer);
            	free(buffer);
		caminharEm(i->dir);
	}
}
int main(){
	start();
    int entrada, qnt = 500;
	char caminho[50];
    strcpy(caminho,"veiculos.csv");
	Veiculo *dados = lerCsv(caminho, &qnt);
	scanf("%d",&entrada);
	while(entrada>0){
	for(int i=0; i<500; i++) 
		if(entrada == dados[i].id) 
        {
            if(raiz == NULL) raiz = newNo(dados[i]);
            else inserir(dados[i], NULL, NULL, NULL, raiz);
            raiz->cor = 0;
            i=500;
        }
	scanf("%d",&entrada);
	}
	char busca[200];
	scanf(" %[^\n]",busca);
	while(strcmp(busca, "FIM")!=0){
		printf("raiz ");
		int resp = pesquisar(busca, raiz);
		if(resp == 1) printf("SIM\n");
		else printf("NAO\n");	
		scanf(" %[^\n]",busca);
	}
	caminharEm(raiz);
    return 0;
}

