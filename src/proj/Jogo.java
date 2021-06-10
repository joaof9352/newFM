package proj;

import proj.Exception.NumeroSemJogadorException;
import proj.Exception.PosicaoSemJogadoresException;
import proj.Exception.JogadorNaoExisteException;

import java.time.LocalDate;
import java.util.*;

public class Jogo {

    //joga-se sempre em 4-3-3

    private Equipa equipaCasa;
    private Equipa equipaFora;
    private String nomeCasa;
    private String nomeFora;
    private int golosCasa;
    private int golosFora;
    private List<Integer> minutosGolosCasa;
    private List<Integer> minutosGolosFora;
    private List<Integer> minutosOportunidadesCasa;
    private List<Integer> minutosOportunidadesFora;
    private LocalDate date;
    private List<Integer> jogCasa;
    private List<Integer> jogFora;
    private List<Double> poderCasa;
    private List<Double> poderFora;
    Map<Integer, Integer> substituicoesCasa;
    Map<Integer, Integer> substituicoesFora;

    public Jogo(Equipa e1, Equipa e2, LocalDate data){
        golosCasa = 0;
        golosFora = 0;
        date = data;
        jogCasa = new ArrayList<>(e1.getTitulares());
        jogFora = new ArrayList<>(e2.getTitulares());
        poderCasa = new ArrayList<>(5);
        poderFora = new ArrayList<>(5);
        minutosGolosCasa = new ArrayList<>();
        minutosGolosFora = new ArrayList<>();
        minutosOportunidadesCasa = new ArrayList<>();
        minutosOportunidadesFora = new ArrayList<>();
        substituicoesCasa = new HashMap<>();
        substituicoesFora = new HashMap<>();
    }

    public Jogo (String nC, String nF, int gc, int gf, LocalDate d,  List<Integer> jc, Map<Integer, Integer> sc,  List<Integer> jf, Map<Integer, Integer> sf){
        nomeCasa = nC;
        nomeFora = nF;
        golosCasa = gc;
        golosFora = gf;
        date = d;
        jogCasa = new ArrayList<>(jc);
        jogFora = new ArrayList<>(jf);
        poderCasa = new ArrayList<>(5);
        poderFora = new ArrayList<>(5);
        minutosGolosCasa = new ArrayList<>();
        minutosGolosFora = new ArrayList<>();
        minutosOportunidadesCasa = new ArrayList<>();
        minutosOportunidadesFora = new ArrayList<>();
        substituicoesCasa = new HashMap<>(sc);
        substituicoesFora = new HashMap<>(sf);
    }

    public Jogo (Equipa casa, Equipa fora, String nC, String nF, int gc, int gf, LocalDate d,  List<Integer> jc, Map<Integer, Integer> sc,  List<Integer> jf, Map<Integer, Integer> sf){
        nomeCasa = nC;
        nomeFora = nF;
        golosCasa = gc;
        golosFora = gf;
        date = d;
        jogCasa = new ArrayList<>(jc);
        jogFora = new ArrayList<>(jf);
        poderCasa = new ArrayList<>(Collections.nCopies(5,0.0));
        poderFora = new ArrayList<>(Collections.nCopies(5,0.0));
        minutosGolosCasa = new ArrayList<>();
        minutosGolosFora = new ArrayList<>();
        minutosOportunidadesCasa = new ArrayList<>();
        minutosOportunidadesFora = new ArrayList<>();
        substituicoesCasa = new HashMap<>(sc);
        substituicoesFora = new HashMap<>(sf);
    }

    public Jogo (String nC, String nF, LocalDate d,  List<Integer> jc, Map<Integer, Integer> sc,  List<Integer> jf, Map<Integer, Integer> sf){
        nomeCasa = nC;
        nomeFora = nF;
        date = d;
        jogCasa = new ArrayList<>(jc);
        jogFora = new ArrayList<>(jf);
        substituicoesCasa = new HashMap<>(sc);
        substituicoesFora = new HashMap<>(sf);
    }

    public static Jogo parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        Map<Integer, Integer> subsC = new HashMap<>();
        Map<Integer, Integer> subsF = new HashMap<>();
        for (int i = 5; i < 16; i++){
            jc.add(Integer.parseInt(campos[i]));
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            subsC.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        for (int i = 19; i < 30; i++){
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            subsF.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        return new Jogo(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                jc, subsC, jf, subsF);
    }

    public String toString() {
        return  "Jogo:" + nomeCasa + " - " + nomeFora;
        //+ " -> " + substituicoesCasa.toString()
        //+ " -> " + substitucoesFora.toString();
    }

    public String getResultadoRapido(){
        StringBuilder sb = new StringBuilder();
        sb.append(equipaCasa.getNome()).append(" ").append(golosCasa).append(" - ").append(golosFora).append(" ").append(equipaFora.getNome());
        return sb.toString();
    }

    public void runTotalParaSimulacao() throws PosicaoSemJogadoresException, NumeroSemJogadorException {
        runMetadePermiteSubs();
        runMetadePermiteSubs();
    }

    public void runMetadePermiteSubs() throws PosicaoSemJogadoresException, NumeroSemJogadorException {
        calculaPoder();
        for(int i = 0; i < 45; i++){
            Minuto m = new Minuto(poderCasa,poderFora);
            if(m.getOportunidadeParaQuem() == 1) {
                minutosOportunidadesCasa.add(i);
                if (m.getGolo() == 1) {
                    golosCasa++;
                    minutosGolosCasa.add(i);
                }
            } else if(m.getOportunidadeParaQuem() == 2){
                minutosOportunidadesFora.add(i);
                if(m.getGolo() == 1){
                    golosFora++;
                    minutosGolosFora.add(i);
                }

            }
        }
    }

    public void substituicao(int equipa, int nmrSair, int nmrEntrar) throws JogadorNaoExisteException {
        if((equipa == 0 && !this.jogCasa.contains(nmrSair)) || (equipa == 1 && !this.jogFora.contains(nmrSair)))
            throw new JogadorNaoExisteException("O jogador nmr " + nmrSair + " não existe!");

        if((equipa == 0 && !this.equipaCasa.getJogadores().stream().map(Jogador::getNumeroJogador).anyMatch(x -> x == nmrSair)) || (equipa == 1 && !this.equipaCasa.getJogadores().stream().map(Jogador::getNumeroJogador).anyMatch(x -> x == nmrSair)))
            throw new JogadorNaoExisteException("O jogador nmr " + nmrSair + " não existe!");

        if(equipa == 0){
            this.jogCasa.set(this.jogCasa.indexOf(nmrSair),nmrEntrar);
            substituicoesCasa.put(nmrSair,nmrEntrar);
        }else{
            this.jogFora.set(this.jogFora.indexOf(nmrSair),nmrEntrar);
            substituicoesFora.put(nmrSair,nmrEntrar);
        }
    }

    public void calculaPoder() throws PosicaoSemJogadoresException, NumeroSemJogadorException {
        poderCasa.set(0, equipaCasa.calculaHabilidade(GuardaRedes.class));
        poderCasa.set(1, equipaCasa.calculaHabilidade(Lateral.class));
        poderCasa.set(2, equipaCasa.calculaHabilidade(Defesa.class));
        poderCasa.set(3, equipaCasa.calculaHabilidade(Medio.class));
        poderCasa.set(4, equipaCasa.calculaHabilidade(Avancado.class));

        poderFora.set(0, equipaFora.calculaHabilidade(GuardaRedes.class));
        poderFora.set(1, equipaFora.calculaHabilidade(Lateral.class));
        poderFora.set(2, equipaFora.calculaHabilidade(Defesa.class));
        poderFora.set(3, equipaFora.calculaHabilidade(Medio.class));
        poderFora.set(4, equipaFora.calculaHabilidade(Avancado.class));
    }

    public int getGolosCasa() {
        return this.golosCasa;
    }

    public int getGolosFora(){
        return this.golosFora;
    }
}