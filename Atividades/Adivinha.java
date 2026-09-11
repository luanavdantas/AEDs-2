import java.util.*;
/*
    Data: 9/9/26
    Autora: Luana Dantas
    Objetivo: Adivinhar a etsrutura de dados
*/
public class Adivinha{
    public static void main(String[] args) {
        int n, comand, x, vetE[], vetS[], posiE, posiS;
        int quant=0, countQ=0, countS=0, countPQ=1;
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            n=sc.nextInt();
            posiE=0;
            posiS=0;
            vetE = new int[n];
            vetS =  new int[n];
            for(int i=0; i<n; i++)
            {
                comand=sc.nextInt();
                if(comand==1)
                {
                    x=sc.nextInt();
                    vetE[posiE]=x;
                    posiE++;
                    quant++;
                }
                else if(comand==2)
                {
                    x=sc.nextInt();
                    vetS[posiS]=x;
                    posiS++;
                }
            }
            for(int j=0; j<quant; j++){
                if(vetE[j]==vetS[j]) countQ++;
                if(vetE[j]==vetS[quant-j-1]) countS++;
            }
            for(int j=0; j<quant-1; j++)
                if(vetS[j]>=vetS[j+1]) countPQ++;

            if(countQ==quant && countS!=quant && countPQ!=quant) System.out.println("queue");
            else if(countQ!=quant && countS==quant && countPQ!=quant) System.out.println("stack");
            else if(countQ!=quant && countS==quant && countPQ==quant) System.out.println("not sure");
            else System.out.println("impossible");
            quant=0;
            countPQ=1;
            countS=0;
            countQ=0;
        }
        sc.close();
    }
}