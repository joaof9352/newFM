package proj;
import java.util.List;
import proj.Exception.EquipaNaoExisteException;
import proj.Exception.LinhaIncorretaException;
import proj.Model.Estado;
import proj.Model.Jogo;

import java.util.ArrayList;

public class Controller {

    private static Estado e = new Estado();


    public static void start() throws Exception {

        String opcoes[] = {"Simular Jogo", "Torneio", "Consultar Equipa", "Consultar Jogador por nome", "Consultar Jogos", "Carregar ficheiro", "Salvar"};
        Menu mainMenu = new Menu(opcoes);
        boolean sair = true;

        while(sair){
            mainMenu.executa();

            switch(mainMenu.getOpcao()) {
                case(1): // Simular um jogo
                    ControllerJogo.start(e.getEquipas());
                    break;

                case(2): // Torneio
                    ControllerTorneio.start(e.getEquipas());
                    break;

                case(3): // Consultar equipa
                    try{ControllerEquipa.start(e.getEquipas());}
                    catch(EquipaNaoExisteException s) {View.handler(2,"");}
                    break;
                
                case(4): // Consultar jogador por nome
                    ControllerJogador.start(e.getEquipas());
                    break;
            
                case(5): // Consultar jogos
                    List<Jogo> jogos = new ArrayList<>(e.getJogos());
                    View.showJogos(jogos);
                    View.pressAnyKey();
                    break;

                case(6): // Carregar ficheiro
                    //String file = View.getNomeFicheiro();
                    try{
                        Parser.parse("/Users/joaolourenco/Universidade/2º Ano/2º Semestre/POO/newFM/src/proj/logs.txt", e);
                        //Parser.parse("/home/cristiano/Desktop/Universidade/2º Ano/Programação Orientada a Objetos/newFM/src/proj/logs.txt", e);
                        //Parser.parse("C:\\Users\\joaof\\Desktop\\newFM\\out\\production\\newFM\\proj\\logs.txt", e);
                        View.messages(1);
                    } catch(LinhaIncorretaException s) {
                        View.handler(1,"");
                    }
                    View.pressAnyKey();
                    break;

                case(7): // Salvar

                case(0): //Sair
                sair = false;
                break;
            }
        }
    }   
}
