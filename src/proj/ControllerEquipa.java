package proj;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ControllerEquipa {

    public static void start(Map<String, Equipa> equipas) {

        boolean bool = true;
        while(bool) {
            int input = -1;

            while(input < 0 || input > 2) {
             input = Menu.menuEquipa();
            }

            switch(input) {
                case 1: // Consultar lista de equipas
                    List<String> l = new ArrayList<>(equipas.keySet());
                    Menu.showListaEquipas(l);
                    Menu.pressAnyKey();
                    break;

                case 2: // Consultar equipa especifica
                    String key = Menu.getNomeEquipa(); // FALTA CONFIRMAR SE EQUIPA EXISTE
                    String jogadores= equipas.get(key).toString();
                    Menu.showEquipa(jogadores);
                    Menu.pressAnyKey();
                    break;

                case 0: // Voltar
                    bool = false;
                    break;
            }
        }
    }
}
