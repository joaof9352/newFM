package proj;

import proj.Model.Equipa;
import proj.Model.Jogador;

import java.util.Map;
import java.util.HashMap;

public class ControllerJogador {

    public static void start(Map<String, Equipa> equipas) {
        View.clear();
        Map<String, Jogador> result = new HashMap<>();
        String nome = View.getNomeJogador();

        for(Map.Entry<String,Equipa> entry : equipas.entrySet()) {
            for(Jogador jog: entry.getValue().getJogadores()) {
                if(jog.getNomeJogador().equals(nome)) result.put(entry.getKey(),jog.clone());
            }
        }

        View.showJogadores(nome, result);
        View.pressAnyKey();
    }
}
