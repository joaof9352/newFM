package proj;

import proj.Model.Equipa;
import proj.Model.Estado;

public class ControllerCriador {
    public static void start(Estado e) throws Exception {

        String opcoes[] = {"Criar Equipa", "Criar Jogador", "Transferir Jogadores"};
        Menu mainMenu = new Menu(opcoes);
        boolean sair = true;

        while(sair){
            View.clear();
            mainMenu.executa();

            switch(mainMenu.getOpcao()) {
                case(1): // Criar Equipa
                    String nome = View.getNomeEquipa();
                    Equipa nEquipa = new Equipa(nome);
                    e.adicionaEquipa(nEquipa);
                    break;

                case(2): // Criar Jogador
                    
                    break;

                case(3): // Transferir jogadores
                    
                    break;

                case(0): //Sair
                sair = false;
                break;
            }
        }
    }
}
