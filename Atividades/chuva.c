#include <stdio.h>
int gota(int M, char mat[][M], int i, int j){
	int resp=0;
	if(mat[i-1][j]=='o') resp=1;
	else if(mat[i][j-1] =='o' && mat[i+1][j-1]=='#') resp=1;
	else if (mat[i][j+1]=='o' && mat[i+1][j+1]=='#') resp=1;
	return resp;
}
int main(){
	int N, M;
	scanf("%d%d",&N,&M);
	char mat[N][M];
	for(int i=0; i<N; i++){
		for(int j=0; j<M; j++)
			scanf(" %c",&mat[i][j]);
	}
	for(int m=0; m<N; m++){
		for(int n=0; n<M; n++)
			if(mat[m][n] == '.' && gota(M, mat, m, n)==1) mat[m][n]='o';
		for(int s=M-1; s>=0; s--)
			if(mat[m][s]=='.' && gota(M, mat, m, s)==1) mat[m][s]='o';
	}
	for(int p=0; p<N; p++){
		for(int q=0; q<M; q++)
			printf("%c",mat[p][q]);
		printf("\n");
	}
	return 0;
}
