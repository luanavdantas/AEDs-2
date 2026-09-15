import java.util.*;
public class PapaiNoel{
public static void insertion(String lista[], int N){
    for(int j=1; j<N; j++)
    {
        String atual;
        atual = lista[j];
        int k = j-1;
        while(k>=0 && lista[k].compareTo(atual)>0){
            lista[k+1] = lista[k];
            k--;
        }
        
        lista[k+1] = atual;
    }
    for(int k=0; k<N; k++) 
        System.out.println(lista[k]);
}
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N, contBoa=0, contMa=0;
    N = sc.nextInt();
    sc.nextLine();
    String criancas[] =  new String[N];
    String linha;
    for(int i=0; i<N; i++)
    {
        linha = sc.nextLine();
        String infos[] = linha.split(" ");
        if(infos[0].compareTo("+") == 0) {
            contBoa++;
            //System.out.println("Boa");
        }
        else if(infos[0].compareTo("-") == 0) {
            contMa++;
            //System.out.println("Ma");
        }
        criancas[i] = infos[1];
    }
    insertion(criancas, N);
    System.out.println("Se comportaram: " + contBoa + " | Nao se comportaram: "+ contMa);
    sc.close();
}
}