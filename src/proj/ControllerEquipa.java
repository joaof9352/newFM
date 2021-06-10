package proj;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import proj.Exception.EquipaNaoExisteException;


public class ControllerEquipa {

    public static void start(Map<String, Equipa> equipas) throws EquipaNaoExisteException {
        
    String opcoes[] = {"Consultar lista de equipas", "Consultar equipa por nome"};
    Menu menuEquipa = new Menu(opcoes);

        boolean sair = true;
        while(sair) {
            menuEquipa.executa();
            switch(menuEquipa.getOpcao()) {
                case 1: // Consultar lista de equipas
                    List<String> l = new ArrayList<>(equipas.keySet());
                    View.showListaEquipas(l);
                    View.pressAnyKey();
                    break;

                case 2: // Consultar equipa por nome
                    String key = View.getNomeEquipa();
                    if(!equipas.containsKey(key)) {throw new EquipaNaoExisteException("A equipa que inseriu não existe.");
                    } else {   
                        String jogadores= equipas.get(key).toString();
                        View.showEquipa(jogadores);
                        View.pressAnyKey();
                    }
                    break;

                case 0: // Voltar
                    sair = false;
                    break;
            }
        }
    }
}
