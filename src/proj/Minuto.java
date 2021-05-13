package proj;

import java.util.Random;

public class Minuto {

    private double poderGRcasa, poderDEFcasa, poderLATcasa, poderMCcasa, poderATTCasa;
    private double poderGRfora, poderDEFfora, poderLATfora, poderMCfora, poderATTfora;
    private final double fatorCriacao = 0.0028;
    private final double fatorFinalizacao = 0.0059;
    private final double fatorCasa = 0.0005; // 0.0018

    private double probCasaCriar, probForaCriar, probCasaMarcar, probForaMarcar;

    public Minuto(double poderGRcasa, double poderDEFcasa, double poderLATcasa, double poderMCcasa, double poderATTCasa, double poderGRfora, double poderDEFfora, double poderLATfora, double poderMCfora, double poderATTfora) {
        this.poderGRcasa = poderGRcasa;
        this.poderDEFcasa = poderDEFcasa;
        this.poderLATcasa = poderLATcasa;
        this.poderMCcasa = poderMCcasa;
        this.poderATTCasa = poderATTCasa;
        this.poderGRfora = poderGRfora;
        this.poderDEFfora = poderDEFfora;
        this.poderLATfora = poderLATfora;
        this.poderMCfora = poderMCfora;
        this.poderATTfora = poderATTfora;
    }
    //0 se não houver oportunidade, 1 se houver da casa, 2 se houver fora
    public int oportunidade(){

        int casaCriar = (int) (probCasaCriar * 100);
        int foraCriar = casaCriar + (int) (probForaCriar * 100);
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
        this.probCasaCriar = (poderMCcasa + poderLATcasa - poderMCfora - poderLATfora + 20) * (fatorCriacao + fatorCasa);
        this.probForaCriar = (poderMCfora + poderLATfora - poderMCcasa - poderLATcasa + 20) * (fatorCriacao);
        this.probCasaMarcar = (poderATTCasa - poderDEFfora - poderGRfora - poderLATfora + 60) * (fatorFinalizacao + fatorCasa);
        this.probForaMarcar = (poderATTfora - poderDEFcasa - poderGRcasa - poderLATcasa + 60) * (fatorFinalizacao);
  }
}
