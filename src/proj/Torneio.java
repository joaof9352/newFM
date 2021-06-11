package proj;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import proj.Exception.NumeroSemJogadorException;
import proj.Exception.PosicaoSemJogadoresException;
import proj.Exception.RondaNaoValidaException;

public class Torneio {
    private int ronda;
    private String equipaJog;
    private Map<String, Equipa> equipas;
    private List<List<String[]>> chave;
    private List<List<int[]>> resultados;
    //estatisticas?


    //contrutores

    //equipas todas random
    public Torneio(Map<String, Equipa> equipas, int nRondas){
        this.ronda = 0;
        this.equipas = new HashMap<>();
        this.chave = new ArrayList<>();
        this.resultados = new ArrayList<>();

        List<String[]> ronda = new ArrayList<>();
        String[] jogo = new String[2];

        //escolha aleatória de equipas e criação da chave do torneio
        Random random = new Random();
        List<String> keys = new ArrayList<String>(equipas.keySet());
        for(int i = 0; i < Math.pow(2, nRondas - 1); i++){
            for(int j = 0; j < 2; j++){
                String randomKey = keys.get(random.nextInt(keys.size()));
                keys.remove(randomKey);
                Equipa equipa = equipas.get(randomKey);
                this.equipas.put(equipa.getNome(), equipa);
                jogo[j] = randomKey;
            }
            ronda.add(jogo);
        }
        this.chave.add(ronda);
        this.equipaJog = ronda.get(0)[0];
    }

    public Torneio(Map<String, Equipa> equipas, int nRondas, Equipa equipaJ){
        this.ronda = 0;
        this.equipaJog = equipaJ.getNome();
        this.equipas = new HashMap<>();
        this.chave = new ArrayList<>();
        this.resultados = new ArrayList<>();

        List<String[]> ronda = new ArrayList<>();
        String[] jogo = new String[2];

        this.equipas.put(equipaJ.getNome(), equipaJ);
        jogo[0] = equipaJ.getNome();

        //escolha aleatória de equipas e criação da chave do torneio
        Random random = new Random();
        List<String> keys = new ArrayList<String>(equipas.keySet());

        String randomKey = keys.get(random.nextInt(keys.size()));
        keys.remove(randomKey);
        Equipa equipa = equipas.get(randomKey);
        this.equipas.put(equipa.getNome(), equipa);
        jogo[1] = randomKey;
        ronda.add(jogo);

        for(int i = 0; i < Math.pow(2, nRondas - 1) - 1; i++){
            for(int j = 0; j < 2; j++){
                randomKey = keys.get(random.nextInt(keys.size()));
                equipa = equipas.get(randomKey);
                this.equipas.put(equipa.getNome(), equipa);
                jogo[j] = randomKey;
            }
            ronda.add(jogo);
        }
        this.chave.add(ronda);
    }

    //getters e setters
    public int getRonda() {
        return ronda;
    }

    public void setRonda(int nRonda) {
        this.ronda = nRonda;
    }

    public String getEquipaJog() {
        return equipaJog;
    }

    public void setEquipaJog(String equipaJog) {
        this.equipaJog = equipaJog;
    }

    public Map<String, Equipa> getEquipas() {
        Map<String, Equipa> res = new HashMap<>();

        for(Equipa e : this.equipas.values())
            res.put(e.getNome(), e.clone());
        
        return equipas;
    }

    public void setEquipas(Map<String, Equipa> nEquipas) {
        this.equipas = new HashMap<>();

        for(Equipa e : nEquipas.values())
            this.equipas.put(e.getNome(), e.clone());
    }



    public boolean jogadorAindaParticipa(){
        return Arrays.stream(this.chave.get(this.ronda).get(0)).anyMatch(this.equipaJog :: equals);
    }

    //corre jogo do jogador
    public Jogo correJogo(){
        String[] equipasJogo = this.chave.get(this.ronda).get(0);
        Jogo j = new Jogo(this.equipas.get(equipasJogo[0]), this.equipas.get(equipasJogo[1]), LocalDate.now());

        return j;
    }

    //simular ronda
    public void simulaRonda(boolean participa) throws PosicaoSemJogadoresException, NumeroSemJogadorException{
        List<String[]> rondaS = this.chave.get(this.ronda);
        List<String[]> rondaSProx = new ArrayList<>();
        List<int[]> rondaG = new ArrayList<>();
        int[] golosJogo;
        String[] jogoProxRonda = new String[2];
        Jogo j;
        int i = 0, eP = 0;
        
        if (participa) i = 1;

        for(; i < rondaS.size(); i++){
            String[] equipasJogo = rondaS.get(i);
            j = new Jogo(this.equipas.get(equipasJogo[0]), this.equipas.get(equipasJogo[1]), LocalDate.now());

            while(j.getGolosCasa() == j.getGolosFora()){
                j.runTotalParaSimulacao();
            }
            golosJogo = new int[2];
            golosJogo[0] = j.getGolosCasa();
            golosJogo[1] = j.getGolosFora();
            rondaG.add(golosJogo);

            if(eP == 0){
                jogoProxRonda = new String[2];
                if (golosJogo[0] > golosJogo[1]) jogoProxRonda[0] = equipasJogo[0];
                else jogoProxRonda[0] = equipasJogo[1];
                eP = 1; 
            }
            else{
                if (golosJogo[0] > golosJogo[1]) jogoProxRonda[1] = equipasJogo[0];
                else jogoProxRonda[1] = equipasJogo[1];
                rondaSProx.add(jogoProxRonda);
                eP = 0; 
            }
            
        }
        this.ronda++;
    }
    
    //toString da chave
    public String proxRondaToString(){
        StringBuilder sb = new StringBuilder("Próxima ronda:\n");

        for(String[] s : this.chave.get(this.ronda))
            sb.append(s[0]).append(" - ").append(s[1]).append("\n");
        return sb.toString();
    }

    public String resultadosRondaToString(int ronda) throws RondaNaoValidaException{
        if(ronda < 1 || ronda > this.getRonda()) throw new RondaNaoValidaException("A ronda que inseriu ainda não foi jogada, ou não é válida.");
        
        StringBuilder sb = new StringBuilder("Resultados da ronda ");
        sb.append(ronda).append(":\n");

        for(int i = 0; i < this.chave.size(); i++)
            sb.append(this.chave.get(ronda - 1).get(i)[0]).append("  ")
              .append(this.resultados.get(ronda - 1).get(i)[0]).append(" - ")
              .append(this.resultados.get(ronda - 1).get(i)[1]).append("  ")
              .append(this.chave.get(ronda - 1).get(i)[1]).append("\n");
        
        return sb.toString();
    }
    
}
