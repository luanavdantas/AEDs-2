import java.util.*;
import java.io.FileNotFoundException;
/*
    Data: 10/9/26
    Autora: Luana Dantas
*/
public class Modelagem{
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
	public String getData(){return this.dataRegistro.format();};
}
    static class LeitorCsv{
	Veiculo[] ler(String caminhoArquivo){
        Veiculo veiculos[] = new Veiculo[500];
        java.io.File dados = new java.io.File(caminhoArquivo);
        try(Scanner sc = new Scanner(dados)){
	String cabecalho = sc.nextLine();
	for(int i=0; i<5; i++)
        {
            String linha = sc.nextLine();
	    String infos[] = linha.split(",");
            veiculos[i]= new Veiculo();
	    for(int j=0; j<15; j++){
		veiculos[i].parseVeiculo(infos[j], j+1);}
		System.out.println("ID lido: " + veiculos[i].getId());
		System.out.println("Marca: " + veiculos[i].getMarca());
		System.out.println("Modelo: " + veiculos[i].getModelo());
		System.out.println("Ano: " + veiculos[i].getAno());
		System.out.println("Categoria: " + veiculos[i].getCategoria());
	        System.out.println("Combustivel: "+ veiculos[i].getCombustivel());
		System.out.println("Cilindros: " + veiculos[i].getCilindros());
		System.out.println("Cilindrada: "+veiculos[i].getCilindrada());
		System.out.println("Transmissao: "+veiculos[i].getTransmissao());
		System.out.println("Tracao: "+ veiculos[i].getTracao());
		System.out.println("Consumo cidade: " + veiculos[i].getConsumoCidade());
		System.out.println("Consumo estrada: "+veiculos[i].getConsumoEstrada());
		System.out.println("CO2: "+veiculos[i].getCo2());
		System.out.println("Turbo: "+veiculos[i].getTurbo());
		System.out.println("Data: "+veiculos[i].getData());
        }
	}catch(FileNotFoundException e){System.out.println("ERRO");}
	return veiculos;	
}
    ;}

    public static void main(String[] args) {
        String caminho = "veiculos.csv";
	LeitorCsv leitor = new LeitorCsv();
	leitor.ler(caminho);
    }
    }

