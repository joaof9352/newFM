package proj.Model;

import java.util.List;
import java.util.Random;

public class Minuto {
    private List<Double> poderCasa, poderFora;
    private final double fatorCriacao = 0.000680875533226558;
    private final double fatorFinalizacao = 0.000485482599307636;
    private final double fatorCasa = 0.0000717926719073958; // 0.0018

    private int oportunidadeParaQuem, golo;

    public int getOportunidadeParaQuem(){
        return this.oportunidadeParaQuem;
    }

    public int getGolo(){
        return this.golo;
    }

    private double probCasaCriar, probForaCriar, probCasaMarcar, probForaMarcar;

    public Minuto(List<Double> poderCasa, List<Double> poderFora) {
        this.poderCasa = poderCasa;
        this.poderFora = poderFora;
        calculaProbs();
        this.oportunidadeParaQuem = oportunidade();
        if(this.oportunidadeParaQuem == 1)
            this.golo = vaiMarcar(probCasaMarcar);
        else if(this.oportunidadeParaQuem == 2)
            this.golo = vaiMarcar(probForaMarcar);
    }
    //0 se não houver oportunidade, 1 se houver da casa, 2 se houver fora
    public int oportunidade(){

        int casaCriar = (int) (probCasaCriar * 100); //25
        int foraCriar = casaCriar + (int) (probForaCriar * 100); // 26
        int temp = 0, ngerador;

        Random gerador = new Random();
        ngerador = gerador.nextInt(100);

        if(ngerador <= casaCriar)
            temp = 1;
        else if(ngerador <= foraCriar)
            temp = 2;

        return temp;
    }
    // 1 se marcar, 0 se não
    public int vaiMarcar(double probMarcar){
        int normalizadoProbMarcar = (int) (probMarcar * 100);
        int temp = 0, ngerador;

        Random gerador = new Random();
        ngerador = gerador.nextInt(100);
        if(ngerador <= normalizadoProbMarcar)
            temp = 1;

        return temp;
    }

    private void calculaProbs(){
        this.probCasaCriar = (poderCasa.get(3) + poderCasa.get(1) - poderFora.get(3) - poderFora.get(1) + 200) * (fatorCriacao + fatorCasa);
        this.probForaCriar = (poderFora.get(3) + poderFora.get(1) - poderCasa.get(3) - poderCasa.get(1) + 200) * (fatorCriacao);
        this.probCasaMarcar = (poderCasa.get(4) - poderFora.get(2) - poderFora.get(0) - poderFora.get(1) + 300) * (fatorFinalizacao + fatorCasa);
        this.probForaMarcar = (poderFora.get(4) - poderCasa.get(2) - poderCasa.get(0) - poderCasa.get(1) + 300) * (fatorFinalizacao);
  }
}
