import java.util.*;
class MatrizEncadeada{
	static class CelulaMat{
		int elemento;
		CelulaMat dir, esq, sup, inf;
		CelulaMat(int x){
			elemento = x;
			dir = esq = sup = inf = null;
		}
	}
	static class Matriz{
		CelulaMat inicio;
		int linha, coluna;
		Matriz(int linha, int coluna){
			inicio = new CelulaMat(0);
			this.linha = linha;
			this.coluna = coluna;

			//inicialização das linhas
			CelulaMat i = inicio;
			for(int j = 1; j<coluna; j++){
				i.dir = new CelulaMat(0);
				i.dir.esq = i;
				i = i.dir;
			}
			i = inicio;
			for(int l = 1; l < linha; l++){
				i.inf = new CelulaMat(0);
				i.inf.sup = i;
				CelulaMat atual = i.inf;
				CelulaMat acima = i;
				for(int c = 1; c< coluna; c++){
					atual.dir = new CelulaMat(0);
					atual.dir.esq = atual;
					acima = acima.dir;
					acima.inf = atual.dir;
					atual.dir.sup = acima;
					atual = atual.dir;
				}
				i = i.inf;
			}
		}
		boolean isQuadrada(){return linha == coluna;}
		void diagPrincipal(){
			if(isQuadrada()){
				CelulaMat i = inicio;
				while(i != null){
					System.out.print(i.elemento + " ");
					i = i.inf != null ? i.inf.dir : null ;
				}
				System.out.println();
			}
		}
		void diagSecundaria(){
			if(isQuadrada()){
				CelulaMat i = inicio;
				while(i.dir != null) i = i.dir;
				while(i!=null){
					System.out.print(i.elemento + " ");
					i = i.inf != null ? i.inf.esq : null;
				}
				System.out.println();
			}
		}
		void mostrarLinhaALinha(){
			CelulaMat i;
			int linhaAtual = 0;
			while(linhaAtual<linha){
				i = inicio;
				int count = 0;
				while(count < linhaAtual){
					i = i.inf;
					count++;
				}
				while(i != null){
					System.out.print(i.elemento + " ");
					i = i.dir;
				}
				System.out.println();
				linhaAtual++;
			}
		}
		void mostrarColunaAColuna(){
			CelulaMat i;
			int colunaAtual = 0;
			while(colunaAtual < coluna){
				i = inicio;
				int count = 0;
				while(count < colunaAtual){
					i = i.dir;
					count++;
				}
				while(i!=null){
					System.out.print(i.elemento + " ");
					i = i.inf;
				}
				System.out.println();
				colunaAtual++;
			}
		}
		Matriz somaCom(Matriz a){
			Matriz result;
			if(linha == a.linha && coluna == a.coluna){
				result = new Matriz(linha, coluna);
				CelulaMat i, j, k;
				int linhaAtual = 0;
				while(linhaAtual<linha){
					i = inicio;
					j = a.inicio;
					k = result.inicio;
					int count = 0;
					while(count < linhaAtual){
						i = i.inf;
						j = j.inf;
						k = k.inf;
						count++;
					}
					while(i != null){
						k.elemento = i.elemento + j.elemento;
						j=j.dir;
						k=k.dir;
						i = i.dir;
					}
					linhaAtual++;
				}
			}
			else result = null;
			return result;
		}
		Matriz multiplicaCom(Matriz a){
			if(coluna != a.linha) return null;
			Matriz resp = new Matriz(linha, a.coluna);
			CelulaMat inicioLinhaThis = inicio;
			CelulaMat inicioLinhaR = resp.inicio;
			for(int i = 0; i < linha; i++){
				CelulaMat r = inicioLinhaR;
				CelulaMat topoColunaA = a.inicio;
				for(int j = 0; j < a.coluna; j++){
					CelulaMat p = inicioLinhaThis;
					CelulaMat q = topoColunaA;
					int soma = 0;
					while(p!=null && q!=null){
						soma+= p.elemento * q.elemento;
						p = p.dir;
						q = q.inf;
					}

					r.elemento = soma;
					r = r.dir;
					topoColunaA = topoColunaA.dir;
				}
				inicioLinhaThis = inicioLinhaThis.inf;
				inicioLinhaR = inicioLinhaR.inf;
			}
			return resp;
		}
	}
}
