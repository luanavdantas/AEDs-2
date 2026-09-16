#include <stdio.h>
#include <string.h>
#include <stdlib.h>
/*
    Data: 11/09/2026
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
void selectionSort (Veiculo v[50], int tam){
	for(int i=0; i<tam - 1; i++){
		int min = i;
		for(int j = i+1; j< tam; j++)
			if(strcasecmp(v[j].modelo,v[min].modelo)<0) min = j;
		if(i!=min){
		Veiculo temp = v[i];
		v[i] = v[min];
		v[min] = temp;
		}
	}
}
int main(){
    int entrada, qnt=500, posi=0;
	char caminho[50];
        strcpy(caminho,"veiculos.csv");
	Veiculo *dados = lerCsv(caminho, &qnt);
	Veiculo lidos[50];
	scanf("%d",&entrada);
	while(entrada>0){
	for(int i=0; i<500; i++) 
		if(entrada == dados[i].id){
		lidos[posi] = dados[i];
		posi++;
		i=500;
		}
	scanf("%d",&entrada);
}
	selectionSort(lidos, posi);
	for(int k=0; k<posi; k++) {
        char *buffer = (char*)malloc(200*sizeof(char));
        formatVeiculo(lidos[k], buffer);
        free(buffer);
    }

    return 0;
}
