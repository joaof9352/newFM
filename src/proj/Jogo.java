package proj;

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
    Map<Integer, Integer> substitucoesFora;

    public Jogo (String nC, String nF, int gc, int gf, LocalDate d,  List<Integer> jc, Map<Integer, Integer> sc,  List<Integer> jf, Map<Integer, Integer> sf){
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
        substitucoesFora = new HashMap<>(sf);
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
        substitucoesFora = new HashMap<>(sf);
    }

    public Jogo (String nC, String nF, LocalDate d,  List<Integer> jc, Map<Integer, Integer> sc,  List<Integer> jf, Map<Integer, Integer> sf){
        nomeCasa = nC;
        nomeFora = nF;
        date = d;
        jogCasa = new ArrayList<>(jc);
        jogFora = new ArrayList<>(jf);
        substituicoesCasa = new HashMap<>(sc);
        substitucoesFora = new HashMap<>(sf);
    }

    private void calculaPoder(){
        int numDefesa,numLateral,numMedio,numAvancado;
        double poderDefesa, poderLateral, poderMedio, poderAvancado;
        numDefesa = numLateral = numMedio = numAvancado = 0;
        poderDefesa = poderLateral =  poderMedio = poderAvancado = 0;
        Jogador j;
        for(int i : jogCasa) {
            j = equipaCasa.getJogadorByNum(i);
            if(j instanceof GuardaRedes)
                this.poderCasa.set(0,this.poderCasa.get(0) + j.calculaHabilidade());
            else if(j instanceof Lateral) {
                poderLateral += j.calculaHabilidade();
                numLateral++;
            }
            else if(j instanceof Defesa) {
                poderDefesa += j.calculaHabilidade();
                numDefesa++;
            }
            else if(j instanceof Medio) {
                poderMedio += j.calculaHabilidade();
                numMedio++;
            }
            else if(j instanceof Avancado) {
                poderAvancado += j.calculaHabilidade();
                numAvancado++;
            }
        }
        this.poderCasa.set(1,poderLateral/numLateral);
        this.poderCasa.set(2,poderDefesa/numDefesa);
        this.poderCasa.set(3,poderMedio/numMedio);
        this.poderCasa.set(4,poderAvancado/numAvancado);

        numDefesa = numLateral = numMedio = numAvancado = 0;
        poderDefesa = poderLateral =  poderMedio = poderAvancado = 0;

        for(int i : jogFora) {
            j = equipaFora.getJogadorByNum(i);
            if(j instanceof GuardaRedes)
                this.poderFora.set(0,this.poderFora.get(0) + j.calculaHabilidade());
            else if(j instanceof Lateral) {
                poderLateral += j.calculaHabilidade();
                numLateral++;
            }
            else if(j instanceof Defesa) {
                poderDefesa += j.calculaHabilidade();
                numDefesa++;
            }
            else if(j instanceof Medio) {
                poderMedio += j.calculaHabilidade();
                numMedio++;
            }
            else if(j instanceof Avancado) {
                poderAvancado += j.calculaHabilidade();
                numAvancado++;
            }
        }
        this.poderFora.set(1,poderLateral/numLateral);
        this.poderFora.set(2,poderDefesa/numDefesa);
        this.poderFora.set(3,poderMedio/numMedio);
        this.poderFora.set(4,poderAvancado/numAvancado);
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
        StringBuilder sb = new StringBuilder("[" + this.date.toString() + "] ");
        sb.append(this.equipaCasa + " " + this.golosCasa + " : ");
        sb.append(this.golosFora + " " + this.equipaFora + "\n");
        return sb.toString();
    }

    public void run(){
        calculaPoder();
        for(int i = 0; i < 90; i++){
            Minuto m = new Minuto(poderCasa,poderFora);
            if(m.getOportunidadeParaQuem() == 1) {
                minutosOportunidadesCasa.add(i);
                if (m.getGolo() == 1)
                    minutosGolosCasa.add(i);
            } else if(m.getOportunidadeParaQuem() == 2){
                minutosOportunidadesFora.add(i);
                if(m.getGolo() == 1)
                    minutosGolosFora.add(i);
            }
        }
    }

    //equipa -> 0 para casa, 1 para fora
    //posicao -> 0 para GR, 1 para lateral, 2 para defesa, 3 para médio, 4 para avancado
    public int calculaPoder(int equipa, int posicao){
        return 0;
    }

}