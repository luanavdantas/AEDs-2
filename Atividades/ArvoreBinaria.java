import java.util.*;

class ArvoreBinaria {
	static class No {
		int elemento;
		No esq, dir;

		No(int x) {
			elemento = x;
			esq = dir = null;
		}
	}

	static class Arvore {
		No raiz;
		int k, resp;
		Arvore() {
			raiz = null;
		}

		public void inserir(int x) {
			raiz = inserir(x, raiz);
		}

		public No inserir(int x, No i) {
			if (i == null) {
				No novo = new No(x);
				return novo;
			} else if (x < i.elemento)
				i.esq = inserir(x, i.esq);
			else if (x > i.elemento)
				i.dir = inserir(x, i.dir);
			else
				System.out.println("Elemento já existe");
			return i;
		}

		public boolean pesquisar(int x) {
			return pesquisar(x, raiz);
		}

		public boolean pesquisar(int x, No i) {
			if (i == null)
				return false;
			else if (x == i.elemento) {
				System.out.print(i.elemento + " ");
				return true;
			} else if (x < i.elemento) {
				System.out.print(i.elemento + " ");
				return pesquisar(x, i.esq);
			} else {
				System.out.print(i.elemento + " ");
				return pesquisar(x, i.dir);
			}
		}

		public void caminharPre() {
			if (raiz == null)
				System.out.print("V");
			else
				caminharPre(raiz);
		}

		public void caminharPre(No i) {
			if (i != null) {
				System.out.print(i.elemento + " ");
				caminharPre(i.esq);
				caminharPre(i.dir);
			}
		}

		public void caminharPos() {
			if (raiz == null)
				System.out.print("V");
			else
				caminharPos(raiz);
		}

		public void caminharPos(No i) {
			if (i != null) {
				caminharPos(i.esq);
				caminharPos(i.dir);
				System.out.print(i.elemento + " ");
			}
		}

		public void caminharEm() {
			if (raiz == null)
				System.out.print("V");
			else
				caminharEm(raiz);
		}

		public void caminharEm(No i) {
			if (i != null) {
				caminharEm(i.esq);
				System.out.print(i.elemento + " ");
				caminharEm(i.dir);
			}
		}

		public int altura() {
			return altura(raiz);
		}

		public int altura(No i) {
			if (i == null)
				return -1;
			int hEsq = 1 + altura(i.esq);
			int hDir = 1 + altura(i.dir);
			if (hEsq >= hDir)
				return hEsq;
			else
				return hDir;
		}

		public int soma() {
			return soma(raiz);
		}

		public int soma(No i) {
			if (i != null) {
				return i.elemento + soma(i.esq) + soma(i.dir);
			}
			return 0;
		}

		public int pares() {
			return pares(raiz);
		}

		public int pares(No i) {
			if (i != null) {
				if (i.elemento % 2 == 0)
					return 1 + pares(i.esq) + pares(i.dir);
				else
					return 0 + pares(i.esq) + pares(i.dir);
			}
			return 0;
		}

		public int folhas() {
			return folhas(raiz);
		}

		public int folhas(No i) {
			if (i != null) {
				if (i.esq == null && i.dir == null)
					return 1;
				else
					return folhas(i.esq) + folhas(i.dir);
			}
			else return 0;
		}
		public void inverte(){inverte(raiz);}
		public void inverte(No i){
			if(i!=null){
				if(i.esq!=null) inverte(i.esq);
				if(i.dir!=null) inverte(i.dir);
				No tmp = i.esq;
				i.esq = i.dir;
				i.dir = tmp; 
			}
		}
		public int retornaK(int k){
			this.k = k;
			retornaK(raiz);
			return resp;
		}
		public void retornaK(No i){
			if(i!=null && k>0){
				retornaK(i.esq);
				k--;
				if(k==0) resp = i.elemento;
				else resp = -1;
				retornaK(i.dir);
			}
		}
		public No menorAncestral(int a, int b){
			if(a > b)return menorAncestral(b, a, raiz);
			else return menorAncestral(a, b, raiz);
		}
		public No menorAncestral(int m, int n, No i){
			if(i!=null){
				if(m < i.elemento && n > i.elemento) return i;
				else if(m < i.elemento && n < i.elemento) return menorAncestral(m, n, i.esq);
				else return menorAncestral(m, n, i.dir);
			}
			else return null;
		}
	}

	public static boolean igual(Arvore a, Arvore b) {
		return igual(a.raiz, b.raiz);
	}

	public static boolean igual(No a, No b) {
		if (a != null && b != null) {
			if (a.elemento == b.elemento)
				return true && igual(a.esq, b.esq) && igual(a.dir, b.dir);
			else
				return false;
		} 
		else if (a == null && b == null)
			return true;
		return false;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Arvore a = new Arvore();
		while (sc.hasNext()) {
			String letra = sc.next();
			int n;
			switch (letra) {
				case "P":
					n = sc.nextInt();
					boolean resp = a.pesquisar(n);
					if (resp)
						System.out.println('S');
					else
						System.out.println('N');
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
				case "A":
					System.out.println(a.altura());
					break;
				case "S":
					System.out.println(a.soma());
					break;
				case "PAR":
					System.out.println(a.pares());
					break;
				case "IN":
					a.inverte();
					break;
			}
		}
		sc.close();
	}
}
