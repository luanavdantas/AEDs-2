import java.util.*;
class Oficina{
	static class Celula{
		private String pasta;
		private Celula prox;
		public Celula(String s) {this.pasta = s; this.prox = null;}
	}
	static class Pilha{
		private Celula topo;
		private Celula inicio;
		public Pilha(){this.topo = this.inicio = new Celula("/");}
		public void inserir(String s){
			Celula nova = new Celula(".");
			if(inicio==topo) nova.pasta = s;
			else nova.pasta = "/" + s;
			nova.prox = topo.prox;
			topo.prox = nova;
			topo = nova;
		}
		public void mostrar(){
			Celula i = inicio;
			while(i!=null){
				System.out.print(i.pasta);
				i=i.prox;
			}
			System.out.println();
		}
		public void sair(){
			if(inicio==topo) System.out.println("ERRO");
			else{
				Celula i = inicio;
				while(i.prox!=topo)
					i=i.prox;
				i.prox = topo.prox;
				topo = i;
			}
		}
	}
	public static void main(String[] args){
		Scanner sc =  new Scanner(System.in);
		while(sc.hasNext()){
			int N = sc.nextInt();
			String enter = sc.nextLine();
			Pilha caminho = new Pilha();
			for(int i=0; i<N; i++){
				String linha = sc.nextLine();
				String[] infos = linha.split(" ");
				if(infos[0].equals("ENTRA")) caminho.inserir(infos[1]);
				else if(infos[0].equals("SAI")) caminho.sair();
				else if(infos[0].equals("CAMINHO")) caminho.mostrar();
			}
		}		
		sc.close();
	}
}
