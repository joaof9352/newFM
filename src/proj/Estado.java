package proj;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Estado {
    private Map<String, Equipa> equipas;
    private Map<Integer, Jogador> jogadores;
    private List<Jogo> jogos;

    public Estado(){
        this.equipas = new HashMap<>();
        this.jogadores = new HashMap<>();
        this.jogos = new ArrayList<>();
    }

    public Estado(Map<String, Equipa> newEquipas, Map<Integer, Jogador> newJogadores, List<Jogo> newJogos){
        this.equipas = new HashMap<>(newEquipas);
        this.jogadores = new HashMap<>(newJogadores);
        this.jogos = new ArrayList<>(newJogos);
    }

    public Map<String, Equipa> getEquipas(){
        Map<String, Equipa> result = new HashMap<>();
        for(Map.Entry<String, Equipa> equipa : this.equipas.entrySet()) {
            result.put(equipa.getKey(), equipa.getValue());
        }
        return result;
    }

    public List<Jogo> getJogos() {
        return this.jogos;
    }
}



