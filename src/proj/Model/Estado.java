package proj.Model;
import proj.Exception.EquipaNaoExisteException;
import proj.Exception.NumeroSemJogadorException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Estado {
    private Map<String, Equipa> equipas;
    private List<Jogo> jogos;

    public Estado() {
        this.equipas = new HashMap<>();
        this.jogos = new ArrayList<>();

    }

    public Estado(Map<String, Equipa> newEquipas, List<Jogo> newJogos){
        this.equipas = new HashMap<>(newEquipas);
        this.jogos = new ArrayList<>(newJogos);
    }

    public Map<String, Equipa> getEquipas(){
        Map<String, Equipa> result = new HashMap<>();
        for(Map.Entry<String, Equipa> equipa : this.equipas.entrySet()) {
            result.put(equipa.getKey(), equipa.getValue());
        }
        return result;
    }

    public void setEquipas(Map<String, Equipa> equipas){
        this.equipas = equipas; //trocar isto
    }

    public void setJogos(List<Jogo> jogos){
        this.jogos = jogos;
    }

    public List<Jogo> getJogos() {
        return this.jogos.stream().map(Jogo::clone).collect(Collectors.toList());
    }

    public void transferencia(int num, String equipaDe, String equipaPara) throws EquipaNaoExisteException, NumeroSemJogadorException {
        if(!equipas.containsKey(equipaDe) || !equipas.containsKey(equipaPara))
            throw new EquipaNaoExisteException();

        equipas.get(equipaPara).insereJogador(equipas.get(equipaDe).jogadorVaiSair(num));
    }

}



