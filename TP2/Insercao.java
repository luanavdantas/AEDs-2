import java.util.*;
import java.io.FileNotFoundException;

/*
    Data: 10/9/26
    Autora: Luana Dantas
*/
public class Insercao {
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
public static void insertionSort(Veiculo v[], int tam){
	for(int i=1; i<tam; i++){
		Veiculo temp = v[i];
		int j= i-1;
		while(j>=0 && v[j].getMarca().compareToIgnoreCase(temp.getMarca()) > 0){
			v[j+1] = v[j];
			j--;
		}
		v[j+1] = temp;
	}
}
public static void main(String[] args) {
	int entrada;
	Scanner sc = new Scanner(System.in);
	String caminho = "/tmp/veiculos.csv";
	LeitorCsv leitor = new LeitorCsv();
	Veiculo[] dados = leitor.ler(caminho);
	Veiculo[] lidos = new Veiculo[500];
	int posi = 0;
	entrada=sc.nextInt();
	while(entrada>0){
		for(int i=0; i<500; i++){ 
			if(entrada == dados[i].getId()){
				lidos[posi] = dados[i];
				posi++;
				i=500;
			}
		}
	entrada=sc.nextInt();
	}
	insertionSort(lidos, posi);
	for(int k=0; k<posi; k++) System.out.println(lidos[k].format());
	sc.close();
}
}

