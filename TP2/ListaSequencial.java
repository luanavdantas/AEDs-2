import java.util.*;
import java.io.FileNotFoundException;

/*
    Data: 17/9/26
    Autora: Luana Dantas
*/
public class ListaSequencial {
	static class Data {
		private int dia, mes, ano;
		public Data(String[] aux){
			this.ano = Integer.parseInt(aux[0]);
			this.mes = Integer.parseInt(aux[1]);
			this.dia = Integer.parseInt(aux[2]);
		}
		public static Data parseData(String s) {
			String aux[] = s.split("-");
			Data nova = new Data(aux);
			return nova;
		}

		public String format() {
			return String.format("%02d/%02d/%04d", dia, mes, ano);
		}
	}

	static class Veiculo {
		private int id, ano, cilindros;
		private String marca, modelo, categoria, combustivel[], transmissao, tracao;
		private double cilindrada, consumoCidade, consumoEstrada, co2;
		private boolean turbo;
		private Data dataRegistro;

		public Veiculo(String[] infos) {
			this.id = Integer.parseInt(infos[0]);
			this.marca = infos[1];
			this.modelo = infos[2];
			this.ano = Integer.parseInt(infos[3]);
			this.categoria = infos[4];
			this.combustivel = infos[5].split(";");
			this.cilindros = Integer.parseInt(infos[6]);
			this.cilindrada = Double.parseDouble(infos[7]);
			this.transmissao = infos[8];
			this.tracao = infos[9];
			this.consumoCidade = Double.parseDouble(infos[10]);
			this.consumoEstrada = Double.parseDouble(infos[11]);
			this.co2 = Double.parseDouble(infos[12]);
			this.turbo = Boolean.parseBoolean(infos[13]);
			this.dataRegistro = Data.parseData(infos[14]);
		}

		public static Veiculo parseVeiculo(String s) {
			String infos[] = s.split(",");
			Veiculo novo = new Veiculo(infos);
			return novo;
		}

		public int getId() {
			return this.id;
		}

		public String getMarca() {
			return this.marca;
		}

		public String getModelo() {
			return this.modelo;
		}

		public int getAno() {
			return this.ano;
		}

		public String getCategoria() {
			return this.categoria;
		}

		public String getCombustivel() {
			if (this.combustivel.length == 2)
				return this.combustivel[0] + " " + this.combustivel[1];
			return this.combustivel[0];
		}

		public int getCilindros() {
			return this.cilindros;
		}

		public double getCilindrada() {
			return this.cilindrada;
		}

		public String getTransmissao() {
			return this.transmissao;
		}

		public String getTracao() {
			return this.tracao;
		}

		public double getConsumoCidade() {
			return this.consumoCidade;
		}

		public double getConsumoEstrada() {
			return this.consumoEstrada;
		}

		public double getCo2() {
			return this.co2;
		}

		public boolean getTurbo() {
			return this.turbo;
		}

		public String getData() {
			return this.dataRegistro.format();
		}

		public String format() {
			String combustivel;
			if (this.combustivel.length == 2)
				combustivel = this.combustivel[0] + "," + this.combustivel[1];
			else
				combustivel = getCombustivel();
			return "[" + getId() + " ## " + getMarca() + " ## " + getModelo() + " ## " + getAno() + " ## "
					+ getCategoria() + " ## [" + combustivel + "] ## " + getCilindros() + " ## " + getCilindrada()
					+ " ## " + getTransmissao() + " ## " + getTracao() + " ## "
					+ String.format(Locale.US, "%.2f", getConsumoCidade()) + " ## "
					+ String.format(Locale.US, "%.2f", getConsumoEstrada()) + " ## " + getCo2() + " ## " + getTurbo()
					+ " ## " + getData() + "]";

		}
	}

	static class LeitorCsv {
		public Veiculo[] ler(String caminhoArquivo) {
			Veiculo veiculos[] = new Veiculo[500];
			java.io.File dados = new java.io.File(caminhoArquivo);
			try (Scanner sc = new Scanner(dados)) {
				String linha = sc.nextLine();
				for (int i = 0; i < 500; i++) {
					linha = sc.nextLine();
					veiculos[i] = Veiculo.parseVeiculo(linha);
				}

			} catch (FileNotFoundException e) {
				System.out.println("ERRO");
			}
			return veiculos;
		}
	}
	static class Lista{
	private Veiculo[] lista;
	private int n;
	public Lista(){
		this.lista = new Veiculo[500];
		this.n = 0;
//		System.out.println("Construtor");
	}
	public void incrementaN(){
		n = n+1;
//		System.out.println("Incrementou para o valor: " + n);
	}
	public void decrementaN(){
		n = n-1;
	}
	public void inserirInicio(Veiculo veiculo){
		if(n==500) System.out.println("Lista cheia");
		else{ 
			for(int i = n; i>0; i--)
				lista[i] = lista[i-1];
			lista[0] = veiculo;
//			System.out.println("Adicionou");
			incrementaN();
		}
	}
	public void inserir(Veiculo veiculo, int posicao){
		if(posicao>=n || posicao<0) System.out.println("Posicao indisponivel");
		if(n==500) System.out.println("Lista cheia");
		else if(posicao == 0) inserirInicio(veiculo);
		else{
			for(int i = n; i>posicao; i--)
				lista[i] = lista[i-1];
			lista[posicao] = veiculo;
			incrementaN();
		}
//		System.out.println("Veiculo na posicao " + posicao + ": "+lista[posicao].format());
	}
	public void inserirFim(Veiculo veiculo){
		if(n==500)System.out.println("Lista cheia");
		else lista[n] = veiculo;
//		System.out.println("Veiculo no fim: "+lista[n].format());
		incrementaN();
	}
	public Veiculo removerInicio() throws Exception{
		if(n==0) throw new Exception("Lista vazia");
		Veiculo removido = lista[0];
		for(int i =0; i<n-1 ; i++)
			lista[i] = lista[i+1];
		decrementaN();
		return removido;
	}
	public Veiculo removerFim() throws Exception{
		if(n==0) throw new Exception("Lista vazia");
		Veiculo removido = lista[n-1];
		decrementaN();
		return removido;
	}
	public Veiculo remover(int posicao)throws Exception{
		if(n==0) throw new Exception("Lista vazia");
		else if(posicao<0 || posicao>=n)throw new Exception("Posicao indisponivel");
		Veiculo removido = lista[posicao];
		for(int i =posicao; i<n-1 ; i++)
			lista[i] = lista[i+1];
		decrementaN();
		return removido;	
	}	
	public void mostrar(){
		for(int i=0; i<n; i++){
			System.out.println(lista[i].format());
		}
	}
	}
	public static void main(String[] args) {
		int entrada, N, posi;
		String linha;
		Lista lista = new Lista();
		Scanner sc = new Scanner(System.in);
		String caminho = "veiculos.csv";
		LeitorCsv leitor = new LeitorCsv();
		Veiculo[] dados = leitor.ler(caminho);
		entrada = sc.nextInt();
		while (entrada > 0) {
			for (int i = 0; i < 500; i++)
				if (entrada == dados[i].getId()){
					lista.inserirFim(dados[i]);
					i=500;
				}
			entrada = sc.nextInt();
		}
		N = sc.nextInt();
		String enter = sc.nextLine();
		for(int j=1 ; j<=N; j++){
			linha = sc.nextLine();
			String[] tok = linha.split(" ");
			if(tok[0].compareTo("I*")==0){
				posi = Integer.parseInt(tok[1]);
				entrada = Integer.parseInt(tok[2]);
				for(int k = 0; k<500;k++)
					if(entrada==dados[k].getId()){
						lista.inserir(dados[k],posi);
					k=500;	
				}
			}
			else if(tok[0].compareTo("II")==0){
				entrada = Integer.parseInt(tok[1]);
				for(int m=0; m<500; m++)
					if(entrada==dados[m].getId()){
					lista.inserirInicio(dados[m]);
					m=500;
				}
			}
			else if(tok[0].compareTo("IF")==0){
				entrada = Integer.parseInt(tok[1]);
				for(int n=0; n<500; n++)
					if(entrada==dados[n].getId()){
					lista.inserirFim(dados[n]);
					n=500;
				}
			}
			else if(tok[0].compareTo("RI")==0){
				try{
				Veiculo removido = lista.removerInicio();
				System.out.println("(R)"+removido.getMarca()+" "+removido.getModelo());
				}catch(Exception e){System.out.println(e);}
				

		}
			else if(tok[0].compareTo("RF")==0){
				try{
				Veiculo removido = lista.removerFim();
				System.out.println("(R)"+removido.getMarca()+" "+removido.getModelo());
				}catch(Exception e){System.out.println(e);}
				

		}
			else if(tok[0].compareTo("R*")==0){
				try{
				posi = Integer.parseInt(tok[1]);
				Veiculo removido = lista.remover(posi);
				System.out.println("(R)"+removido.getMarca()+" "+removido.getModelo());
				}catch(Exception e){System.out.println(e);}
				

		}


}
		lista.mostrar();
		sc.close();
	}
}

