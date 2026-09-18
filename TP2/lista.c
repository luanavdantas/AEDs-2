#include <stdio.h>
#include <string.h>
#include <stdlib.h>
/*
    Data: 18/09/2026
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
typedef struct Celula{
	Veiculo elemento;
	struct Celula *prox;
}Celula;
Celula* novaCelula(Veiculo veiculo){
	Celula *nova = malloc(sizeof(Celula));
	nova->elemento = veiculo;
	nova->prox = NULL;
	return nova;
}

Celula *primeira;
Celula *ultima;
void start(){
	primeira = malloc(sizeof(Celula));
	ultima = malloc(sizeof(Celula));
	Veiculo vazio = {0};
	primeira->elemento = vazio;
	ultima->elemento = vazio;
	primeira->prox = ultima;
	ultima->prox = NULL;
}
int tamanho(){
	int tam=0;
	Celula *atual = primeira->prox;
	while(atual!=ultima->prox){
		atual = atual->prox;
		tam++;
	}
	return tam;
}
void inserirInicio(Veiculo veiculo){
	if(ultima->elemento.id == 0){
		ultima->elemento = veiculo;
	}
	else{
		Celula* nova = novaCelula(veiculo);
		nova->prox = primeira->prox;
		primeira->prox = nova;
	}
}
void inserirFim(Veiculo veiculo){
	if(ultima->elemento.id == 0){
		ultima->elemento = veiculo;
	}
	else{
		Celula *nova = novaCelula(veiculo);
		nova->prox = ultima->prox;
		ultima->prox = nova;
		ultima = nova;
	}
}
void inserir(int posicao, Veiculo veiculo){
	int tam = tamanho();
	if(posicao<0 || posicao>=tam) exit(1);
	else if(posicao == 0) inserirInicio(veiculo);
	else if(posicao == (tam-1))inserirFim(veiculo);
	Celula *atual = primeira;
	int i=0;
	while(i<posicao){
		atual=atual->prox;
		i++;
	}
	Celula *nova = novaCelula(veiculo);
	nova->prox = atual->prox;
	atual->prox = nova;
}
Veiculo removerInicio(){
	if(ultima->elemento.id == 0)exit(1);
	Veiculo removido = primeira->prox->elemento;
	primeira->prox = primeira->prox->prox;
	return removido;
}
Veiculo removerFim(){
	if(ultima->elemento.id==0)exit(1);
	Veiculo removido = ultima->elemento;
	Celula *atual = primeira;
	int tam = tamanho();
	int i = 0;
	while(i<(tam - 1)){
		atual = atual->prox;
		i++;
	}
	atual->prox = ultima->prox;
	ultima = atual;
	return removido;
}
Veiculo remover(int posicao){
	if(ultima->elemento.id==0)exit(1);
	int tam = tamanho();
	if(posicao<0 || posicao>=tam) exit(1);
	Celula *atual = primeira;
	int i = 0;
	while(i < posicao){
		atual = atual->prox;
		i++;
	}
	Veiculo removido = atual->prox->elemento;
	atual->prox = atual->prox->prox;
	return removido;
}
void mostrar(){
	if(ultima->elemento.id == 0) exit(1);
	int tam = tamanho();
	Celula *atual = primeira->prox;
	int i = 0;
	while(i < tam){
		char *buffer = (char *)malloc(200*sizeof(char));
		formatVeiculo(atual->elemento, buffer);
		free(buffer);
		atual = atual->prox;
		i++;
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
            inserirFim(dados[i]);
            i=500;
        }
	scanf("%d",&entrada);
	}
	int N;
	scanf("%d",&N);
	for(int i=0; i<N; i++){
		char linha[20];
		scanf(" %[^\n]",linha);
		char *tok = strtok(linha, " ");
		if(strcmp(tok, "II")==0){
			tok = strtok(NULL, " ");
			entrada = atoi(tok);
			for(int j=0; j<500; j++)
				if(entrada == dados[j].id){
					inserirInicio(dados[j]);
					j=500;
				}
		}
		else if(strcmp(tok, "I*")==0){
			tok = strtok(NULL, " ");
			int posi = atoi(tok);
			tok = strtok(NULL, " ");
			entrada = atoi(tok);
			for(int j=0; j<500; j++)
				if(entrada == dados[j].id){
					inserir(posi,dados[j]);
					j=500;
				}
		}
		else if(strcmp(tok, "IF")==0){
			tok = strtok(NULL, " ");
			entrada = atoi(tok);
			for(int j=0; j<500; j++)
				if(entrada == dados[j].id){
					inserirFim(dados[j]);
					j=500;
				}
		}
		else if(strcmp(tok,"RI")==0){
			Veiculo removido = removerInicio();
			printf("(R)%s %s\n",removido.marca, removido.modelo);
		}
		else if(strcmp(tok,"RF")==0){
			Veiculo removido = removerFim();
			printf("(R)%s %s\n",removido.marca, removido.modelo);
		}
		else if(strcmp(tok,"R*")==0){
			tok = strtok(NULL, " ");
			int posi = atoi(tok);
			Veiculo removido = remover(posi);
			printf("(R)%s %s\n", removido.marca, removido.modelo);
		}
	
	}
	mostrar();
    return 0;
}

