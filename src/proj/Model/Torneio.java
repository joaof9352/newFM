package proj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    //fazer getters e setters??
    //simular ronda
    //print da chave

}
