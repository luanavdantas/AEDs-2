import java.util.*;
import java.io.FileNotFoundException;
/*
    Data: 10/9/26
    Autora: Luana Dantas
*/
public class Bucket{
    static class Data{
	private int dia, mes, ano;
	public void parseData(String s){
	String aux[]=s.split("-");
	this.ano = Integer.parseInt(aux[0]);
	this.mes = Integer.parseInt(aux[1]);
	this.dia = Integer.parseInt(aux[2]);
}	
	public String format(){
	return String.format("%02d/%02d/%04d", dia, mes, ano);
}
}
    static class Veiculo{
    	private int id, ano, cilindros;
	private String marca, modelo, categoria, combustivel[], transmissao, tracao;
	private double cilindrada, consumoCidade, consumoEstrada, co2;
	private boolean turbo;
	private Data dataRegistro = new Data();
	public void parseVeiculo(String s, int row){
	if(row == 1)this.id=Integer.parseInt(s);
	else if(row == 2) this.marca=s;
	else if(row == 3) this.modelo=s;
	else if(row==4)this.ano=Integer.parseInt(s);
	else if(row==5)this.categoria = s;
	else if(row==6)this.combustivel=s.split(";");
	else if(row == 7)this.cilindros=Integer.parseInt(s);
	else if(row==8)this.cilindrada=Double.parseDouble(s);
	else if(row==9)this.transmissao=s;
	else if(row==10)this.tracao=s;
	else if(row==11)this.consumoCidade=Double.parseDouble(s);
	else if(row==12)this.consumoEstrada=Double.parseDouble(s);
	else if(row==13)this.co2=Double.parseDouble(s);
	else if(row==14)this.turbo=Boolean.parseBoolean(s);
	else if(row == 15) this.dataRegistro.parseData(s);
	}
	public int getId(){return this.id;}
	public String getMarca(){return this.marca;}
	public String getModelo(){return this.modelo;}
	public int getAno(){return this.ano;}
	public String getCategoria(){return this.categoria;}
	public String getCombustivel(){
	if(this.combustivel.length==2)return this.combustivel[0]+ " " + this.combustivel[1];
	return this.combustivel[0];
	}
	public int getCilindros(){return this.cilindros;}
	public double getCilindrada(){return this.cilindrada;}
	public String getTransmissao(){return this.transmissao;}
	public String getTracao(){return this.tracao;}
	public double getConsumoCidade(){return this.consumoCidade;}
	public double getConsumoEstrada(){return this.consumoEstrada;}
	public double getCo2(){return this.co2;}
	public boolean getTurbo(){return this.turbo;}	
	public String getData(){return this.dataRegistro.format();}
	public String format(){
	String combustivel;
	if(this.combustivel.length==2) combustivel = this.combustivel[0]+","+this.combustivel[1];
	else combustivel= getCombustivel();
	return "["+getId()+" ## "+getMarca()+" ## "+getModelo()+" ## "+getAno()+" ## "+getCategoria()+" ## ["+combustivel+"] ## "+getCilindros()+" ## "+getCilindrada()+" ## "+getTransmissao()+" ## "+getTracao()+" ## "+String.format(Locale.US, "%.2f", getConsumoCidade())+" ## "+String.format(Locale.US, "%.2f", getConsumoEstrada())+" ## "+getCo2()+" ## "+getTurbo()+" ## "+getData()+"]";

}
}
    static class LeitorCsv{
	public Veiculo[] ler(String caminhoArquivo){
        Veiculo veiculos[] = new Veiculo[500];
        java.io.File dados = new java.io.File(caminhoArquivo);
        try(Scanner sc = new Scanner(dados)){
	String cabecalho = sc.nextLine();
	for(int i=0; i<500; i++)
        {
	    String linha = sc.nextLine();
	    String infos[] = linha.split(",");
            veiculos[i]= new Veiculo();
	    for(int j=0; j<15; j++){
		veiculos[i].parseVeiculo(infos[j], j+1);}
        }
	
	}catch(FileNotFoundException e){System.out.println("ERRO");}
	return veiculos;	
	}
}
public static void insertionSort(Veiculo v[], int tam){
	for(int i=1; i<tam; i++){
		Veiculo temp = v[i];
		int j= i-1;
		while(j>=0 && v[j].getCilindrada() > temp.getCilindrada()){
			v[j+1] = v[j];
			j--;
		}
		v[j+1] = temp;
	}
}
public static void bucketSort(Veiculo v[]){
	Veiculo[][] baldes = new Veiculo[10][50];
	Veiculo[] saida = new Veiculo[50];
	int[] cont = new int[10];
	for(int i=0; i<50; i++){
		double valor = v[i].getCilindrada()/8.1;
		int indice = (int)(valor*10);
		baldes[indice][cont[indice]] = v[i];
		cont[indice]++;
	}
	for(int i=0; i<10; i++)
		insertionSort(baldes[i], cont[i]);
	int posi=0;
	for(int i=0; i<10; i++){
		for(int j=0; j<cont[i]; j++){
			saida[posi] = baldes[i][j];
			posi++;
		}
	}
	for(int i=0; i<50; i++)
		v[i] =saida[i];
}
public static void main(String[] args) {
	int entrada;
	Scanner sc = new Scanner(System.in);
	String caminho = "veiculos.csv";
	LeitorCsv leitor = new LeitorCsv();
	Veiculo[] dados = leitor.ler(caminho);
	Veiculo[] lidos = new Veiculo[50];
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
	bucketSort(lidos);
	for(int k=0; k<posi; k++) System.out.println(lidos[k].format());
	sc.close();
}
}

