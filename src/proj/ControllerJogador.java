package proj;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ControllerJogador {

    public static void start(Map<String, Equipa> equipas) {

        Map<String,Jogador> result = new HashMap<>();
        String nome = Menu.getNomeJogador();

        for(Map.Entry<String,Equipa> entry : equipas.EntrySet()) {
            for(Jogador jog: entry.getValue().getJogadores()) {
                if(jog.getNomeJogador().equals(nome)) result.put(entry.getKey(),jog.clone());
            }
        }

        View.showJogadores(result);
        View.pressAnyKey();
    }
}
