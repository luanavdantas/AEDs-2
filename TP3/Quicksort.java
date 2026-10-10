import java.util.*;
import java.io.FileNotFoundException;

/*
    Data: 9/10/26
    Autora: Luana Dantas
*/
public class Quicksort {
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
		
		public void setConsumoCidade(double consumo){this.consumoCidade = consumo;}

		public void setCategoria(String s){this.categoria = s;}

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
	static class Celula{
		private Veiculo elemento;
		private Celula prox, ant;
		public Celula(){
			this.elemento = null;
			this.prox = this.ant = null;
		}
		public Celula(Veiculo veiculo){
			this.elemento = veiculo;
			this.prox = this.ant = null;
		}
	}
	static class Lista{
	private Celula primeira, ultima;
	private int n;
	public Lista(){
		this.primeira = this.ultima = new Celula();
		this.primeira.prox = this.ultima.prox = this.primeira.ant = this.ultima.ant = null;
		this.n = 0;
//		System.out.println("Construtor");
	}
	public void inserirFim(Veiculo veiculo){
		if(n==500)System.out.println("Lista cheia");
		Celula nova = new Celula(veiculo);
			nova.prox = ultima.prox;
			ultima.prox = nova;
			nova.ant = ultima;
			ultima = nova;
//		System.out.println("Veiculo no fim: "+lista[n].format());
		n++;
	}
	public void mostrar(){
		Celula atual = primeira.prox;
		int i=0;
		while (i<n){
			System.out.println(atual.elemento.format());
			atual = atual.prox;	
			i++;
		}
	}
	public void quicksort() {quicksort(primeira.prox,ultima, n);}
	public void quicksort(Celula esq, Celula dir, int tam){		
		Celula ptrPivo = esq, i = esq, j=dir;
		int posiI = 0, posiJ = tam-1;
		int cont = 0;
		while(cont<(tam-1)/2) {
			ptrPivo = ptrPivo.prox;
			cont++;
		}
		Veiculo pivo = ptrPivo.elemento;
		while(posiI<=posiJ){
			while(i.elemento.getConsumoEstrada() < pivo.getConsumoEstrada() || (i.elemento.getConsumoEstrada()==pivo.getConsumoEstrada() && i.elemento.getMarca().compareTo(pivo.getMarca())<0)){
				i = i.prox;
				posiI++;
			}
			while(j.elemento.getConsumoEstrada() > pivo.getConsumoEstrada() || (j.elemento.getConsumoEstrada() == pivo.getConsumoEstrada() && j.elemento.getMarca().compareTo(pivo.getMarca())>0)){
				j = j.ant;
				posiJ--;
			}
			if(posiI<=posiJ){
				Veiculo tmp = i.elemento;
				i.elemento = j.elemento;
				j.elemento = tmp;
				i = i.prox;
				posiI++;
				j = j.ant;
				posiJ--;
			}
		}
		if(posiJ + 1 > 1)  quicksort(esq, j, posiJ + 1); 
    		if(tam - posiI > 1) quicksort(i, dir, tam - posiI);
	}
	}
public static void main(String[] args) {
	int entrada;
	Scanner sc = new Scanner(System.in);
	String caminho = "/tmp/veiculos.csv";
	LeitorCsv leitor = new LeitorCsv();
	Veiculo[] dados = leitor.ler(caminho);
	Lista lista = new Lista();
	entrada=sc.nextInt();
	while(entrada>0){
		for(int i=0; i<500; i++){ 
			if(entrada == dados[i].getId()){
				lista.inserirFim(dados[i]);
				i=500;
			}
		} 
	entrada=sc.nextInt();
	}
	lista.quicksort();
	lista.mostrar();
	sc.close();
}
}
