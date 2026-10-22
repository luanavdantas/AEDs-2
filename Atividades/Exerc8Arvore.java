import java.util.*;
class Exerc8Arvore{
	static class Celula{
		private int elemento;
		private Celula prox;
		public Celula(int x){
			this.elemento = x;
			this.prox = null;
		}
	}
	static class Lista{
	private Celula primeira, ultima;
	private int n;
	public Lista(){
		this.primeira = this.ultima = new Celula(-1); // -1 indica a célula cabeça
		this.primeira.prox = this.ultima.prox = null;
		this.n = 0;
	}
	public void inserir(int x){
		Celula nova = new Celula(x);
		if(primeira==ultima){
			nova.prox= ultima.prox;
			ultima.prox = nova;
			ultima = nova;
		}
		else{ 
			nova.prox = primeira.prox;
			primeira.prox = nova;
		}
		n++;
	}
	public void mostrar(){
		Celula i = primeira.prox;
		while(i!=null){
			System.out.print(i.elemento + " ");
			i=i.prox;
		}
	}
	}
	static class CelulaDupla{
		int elemento;
		CelulaDupla prox, ant;
		CelulaDupla(int x){
			this.elemento = x;
			this.prox = this.ant = null;
		}
	}
	static class ListaDupla{
	private CelulaDupla primeira, ultima;
	private int n;
	public ListaDupla(){
		this.primeira = this.ultima = new CelulaDupla(-1);
		this.primeira.prox = this.ultima.prox = this.primeira.ant = this.ultima.ant = null;
		this.n = 0;
	}
	public void inserir(int x){
		CelulaDupla nova = new CelulaDupla(x);
		if(primeira==ultima){
			nova.prox= ultima.prox;
			nova.ant = ultima;
			ultima.prox = nova;
			ultima = nova;
		}
		else{ 
			nova.prox = primeira.prox;
			nova.ant = primeira;
			nova.prox.ant = nova.ant.prox = nova;
		}
		n++;
	}
	public void mostrar(){
		CelulaDupla i = primeira.prox;
		while(i!=null){
			System.out.print(i.elemento + " ");
			i = i.prox;
		}
	}
	}
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
		No toAB(Celula p1, CelulaDupla p2){
			Celula i = p1.prox;
			CelulaDupla j = p2.prox;
			while(i!=null && j!=null){
				inserir(i.elemento);
				inserir(j.elemento);
				i = i.prox;
				j=j.prox;
			}
			while(i!=null){
				inserir(i.elemento);
				i = i.prox;
			}
			while(j!=null){
				inserir(j.elemento);
				j=j.prox;
			}
			return raiz;
		}
	}
	public static void main(String[] args){
		Lista a = new Lista();
		ListaDupla b = new ListaDupla();
		for(int i = 10; i>= 1; i--)
			a.inserir(i);
		for(int i=20; i>=11; i--)
			b.inserir(i);
		
		a.mostrar();
		System.out.println();
		b.mostrar();
		System.out.println();
	}
}
