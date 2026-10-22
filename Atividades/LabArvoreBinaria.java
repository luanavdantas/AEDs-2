import java.util.*;
class LabArvoreBinaria{
	static class No{
		int elemento;
		No esq, dir;
		No(int x){
			elemento = x;
			esq = dir = null;
		}
	}
	static class Arvore{
		No raiz;
		Arvore(){ raiz = null; }
		public void inserir (int x){ raiz = inserir(x, raiz);}
		public No inserir (int x, No i){
			if(i == null) {
				No novo = new No(x);
				return novo;
			} 
			else if(x < i.elemento) i.esq = inserir(x, i.esq);
			else if(x > i.elemento) i.dir = inserir(x, i.dir);
			else System.out.println("Elemento já existe");
			return i;
		}
		public boolean pesquisar(int x){ return pesquisar(x, raiz);}
		public boolean pesquisar(int x, No i){
			if(i == null) return false;
			else if(x == i.elemento) {
				System.out.print(i.elemento + " ");
				return true;
			}
			else if(x < i.elemento) {
				System.out.print(i.elemento + " ");
				return pesquisar(x, i.esq);
			}
			else{
				System.out.print(i.elemento + " ");
				return pesquisar(x, i.dir);
			}
		}
		public void caminharPre(){
			if(raiz == null) System.out.print("V");
			else caminharPre(raiz);
		}
		public void caminharPre(No i){
			if(i!=null){
				System.out.print(i.elemento + " ");
				caminharPre(i.esq);
				caminharPre(i.dir);
			}
		}
		public void caminharPos(){
			if(raiz == null) System.out.print("V");
			else caminharPos(raiz);
		}
		public void caminharPos(No i){
			if(i!= null){
				caminharPos(i.esq);
				caminharPos(i.dir);
				System.out.print(i.elemento+ " ");
			}
		}
		public void caminharEm(){
			if(raiz == null) System.out.print("V");
			else caminharEm(raiz);
		}
		public void caminharEm(No i){
			if(i!=null){
				caminharEm(i.esq);
				System.out.print(i.elemento + " ");
				caminharEm(i.dir);
			}
		}
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Arvore a = new Arvore();
		while(sc.hasNext()){
			String letra = sc.next();
			int n;
			switch(letra){
				case "P":
					n = sc.nextInt();
					boolean resp = a.pesquisar(n);
					if(resp) System.out.println('S');
					else System.out.println('N');
					break;
				case "EM":
					a.caminharEm();
					System.out.println();
					break;
				case "I":
					n = sc.nextInt();
					a.inserir(n);
					break;
				case "PRE":
					a.caminharPre();
					System.out.println();
					break;
				case "POS":
					a.caminharPos();
					System.out.println();
					break;		
			}
		}
		sc.close();	
	}
 }
