package proj;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Jogador {

    private String nomeJogador;
    private int numeroJogador;
    private int velocidade, resistencia, destreza, impulsao, cabeca, remate, passe;
    private List<String> historico;

    public static Comparator<Jogador> compJogador = (Jogador j1, Jogador j2) -> (int) (j1.calculaHabilidade() - j2.calculaHabilidade());

    public Jogador(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p){
        nomeJogador = nomeJ;
        numeroJogador = numeroJ;
        velocidade = vel;
        resistencia = res;
        destreza = des;
        impulsao = imp;
        cabeca = cab;
        remate = rem;
        passe = p;
        historico = new ArrayList<>();
    }

    public abstract double calculaHabilidade();

    public Jogador(Jogador j) {
        nomeJogador = j.getNomeJogador();
        numeroJogador = j.getNumeroJogador();
        velocidade = j.getVelocidade();
        resistencia = j.getResistencia();
        destreza = j.getDestreza();
        impulsao = j.getImpulsao();
        cabeca = j.getCabeca();
        remate = j.getRemate();
        passe = j.getPasse();
        historico = j.getHistorico();
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getNumeroJogador() {
        return numeroJogador;
    }

    public void setNumeroJogador(int numeroJogador) {
        this.numeroJogador = numeroJogador;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getImpulsao() {
        return impulsao;
    }

    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    public int getCabeca() {
        return cabeca;
    }

    public void setCabeca(int cabeca) {
        this.cabeca = cabeca;
    }

    public int getRemate() {
        return remate;
    }

    public void setRemate(int remate) {
        this.remate = remate;
    }

    public int getPasse() {
        return passe;
    }

    public void setPasse(int passe) {
        this.passe = passe;
    }
    /*public static Jogador parse(String input){
        String[] campos = input.split(",");
        return new Jogador(campos[0], Integer.parseInt(campos[1]), campos[2],
                                        Integer.parseInt(campos[3]),
                                        Integer.parseInt(campos[4]),
                                        Integer.parseInt(campos[5]),
                                        Integer.parseInt(campos[6]),
                                        Integer.parseInt(campos[7]),
                                        Integer.parseInt(campos[8]));
    }*/

    public abstract Jogador clone();

    public List<String> getHistorico() {
        return historico;
    }

    public void setHistorico(List<String> historico) {
        this.historico = historico;
    }

    public void addHistorial(String clube){
        this.historico.add(clube);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNumeroJogador()).append(" | ").append(this.getNomeJogador()).append(" | ")
                .append(this.calculaHabilidade()).append("\n");

        return sb.toString();
    }

}