#include <stdio.h>
#include <string.h>
#include <stdlib.h>
/*
    Data: 12/09/2026
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
char* formatData(Data d){
    static char formatado[50];
	sprintf(formatado,"%02d/%02d/%04d", d.dia, d.mes, d.ano);
    return formatado;
}
typedef struct Veiculo{
    int id, ano, cilindros, turbo;
    char marca[100], modelo[100], categoria[100], transmissao[100],tracao[100];
    double cilindrada, consumoCidade, consumoEstrada, co2;
    char combustivel[100];
    Data dataRegistro;
}Veiculo;
Veiculo parseVeiculo(char* s[15]){
    Veiculo veiculo;
    veiculo.id = atoi(s[0]);
	strcpy(veiculo.marca,s[1]);
    strcpy(veiculo.modelo,s[2]);
    veiculo.ano=atoi(s[3]);
    strcpy(veiculo.categoria, s[4]);
    strcpy(veiculo.combustivel, s[5]);
    veiculo.cilindros = atoi(s[6]);
    veiculo.cilindrada = atof(s[7]);
    strcpy(veiculo.transmissao,s[8]);
    strcpy(veiculo.tracao,s[9]);
    veiculo.consumoCidade = atof(s[10]);
    veiculo.consumoEstrada = atof(s[11]);
    veiculo.co2 = atof(s[12]);
    if(strcmp(s[13], "true")==0) veiculo.turbo =1;
    else if(strcmp(s[13],"false")==0) veiculo.turbo=0;
    veiculo.dataRegistro = parseData(s[14]);
	return veiculo;
    }
void formatVeiculo (Veiculo v){
    char turbo[10], *tok, combustivel[50];
    tok = strtok(v.combustivel,";");
    strcpy(combustivel,tok);
    tok = strtok(NULL, ";");
    if(tok!=NULL){
        strcat(combustivel,",");
        strcat(combustivel,tok);
    }
    if(v.turbo==1)strcpy(turbo,"true");
    else if(v.turbo==0) strcpy(turbo,"false");
    printf("[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1lf ## %s ## %s ## %.2lf ## %.2lf ## %.1lf ## %s ## %s]\n",v.id,v.marca,v.modelo,v.ano,v.categoria,combustivel,v.cilindros,v.cilindrada,v.transmissao,v.tracao,v.consumoCidade,v.consumoEstrada,v.co2,turbo,formatData(v.dataRegistro));
}
Veiculo* lerCsv(char caminhoArquivo[50]){
    Veiculo *veiculos = malloc(500*sizeof(Veiculo));
    int atual=0;
    FILE *dados = fopen(caminhoArquivo, "r");
    if(dados==NULL) printf("ERRO");
	char linha[1000];
    	fscanf(dados," %[^\n]",linha); //ignora o cabeçalho
	for(int j = 0; j <500 && fscanf(dados," %[^\n]", linha)!=EOF; j++){
        char* infos[15];
        char *token = strtok(linha, ",");
        for(int i=0; i<15;i++)
        {
            infos[i]=token;
            token = strtok(NULL,",");
        }
        *(veiculos+atual) = parseVeiculo(infos);
        atual++;
    }
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
    int entrada, posi=0;
	char caminho[50];
        strcpy(caminho,"veiculos.csv");
	Veiculo *dados = lerCsv(caminho);
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
	for(int k=0; k<posi; k++) formatVeiculo(lidos[k]);
    return 0;
}
