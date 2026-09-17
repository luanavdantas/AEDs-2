import java.util.*;
import java.io.FileNotFoundException;

/*
    Data: 17/9/26
    Autora: Luana Dantas
*/
public class PilhaFlexivel{
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
		public void setId(int x) {this.id =x;}
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
	static class Celula{
		Veiculo elemento;
		Celula prox;
		public Celula(){
			this.elemento = null;
			this.prox = null;
		}
		public Celula(Veiculo veiculo){
			this.elemento = veiculo;
			this.prox = null;
		}
	}
	static class Pilha{
		private Celula primeira, ultima;
		public Pilha()
		{
			primeira = new Celula();
			ultima = new Celula();
			primeira.prox = ultima;
		}
		public int tamanho(){
			int tamanho = 0;
			Celula i = primeira.prox;
			while(i!=ultima){
				i = i.prox;
				tamanho++;
			}
			return tamanho;
		}
		public void inserir(Veiculo veiculo){
			Celula nova = new Celula(veiculo);
			if(ultima.elemento == null){
				nova.prox = ultima.prox;
				ultima = nova;
				primeira.prox = nova;
			}
			else{
				nova.prox = ultima.prox;
				ultima.prox = nova;
				ultima = nova;
			}
		}
		public Veiculo remover()throws Exception{
			if(ultima.elemento == null) throw new Exception("Pilha vazia");
			int tamanho = tamanho();
			Veiculo removido = ultima.elemento;
			Celula tmp = primeira;
			for(int i=0; i<tamanho; i++)
				tmp = tmp.prox;
			tmp.prox = ultima.prox;
			ultima = tmp;
			return removido;
		}
		public void mostrar()throws Exception{
			 Celula atual = ultima;
			if(ultima.elemento == null) throw new Exception("Pilha vazia");
			while(atual!=primeira){
				Celula tmp = primeira;
				System.out.println(atual.elemento.format());
				while(tmp.prox!=atual)
					tmp=tmp.prox;
				atual.prox = tmp.prox;
				atual=tmp;
			}
		}
	}
	public static void main(String[] args) {
		int entrada, N, posi;
		String linha;
		Pilha pilha = new Pilha();
		Scanner sc = new Scanner(System.in);
		String caminho = "veiculos.csv";
		LeitorCsv leitor = new LeitorCsv();
		Veiculo[] dados = leitor.ler(caminho);
		entrada = sc.nextInt();
		while (entrada > 0) {
			for (int i = 0; i < 500; i++)
				if (entrada == dados[i].getId()){
					pilha.inserir(dados[i]);
					i=500;
				}
			entrada = sc.nextInt();
		}
		N = sc.nextInt();
		String enter = sc.nextLine();
		for(int j=1 ; j<=N; j++){
			linha = sc.nextLine();
			String[] tok = linha.split(" ");
			if(tok[0].compareTo("I")==0){
				entrada = Integer.parseInt(tok[1]);
				for(int n=0; n<500; n++)
					if(entrada==dados[n].getId()){
					pilha.inserir(dados[n]);
					n=500;
				}
			}
			else if(tok[0].compareTo("R")==0){
				try{
				Veiculo removido = pilha.remover();
				System.out.println("(R)"+removido.getMarca()+" "+removido.getModelo());
				}catch(Exception e){System.out.println(e);}
			}
		}
		try{pilha.mostrar();}catch(Exception e){System.out.println(e);}
		sc.close();
	}
}


