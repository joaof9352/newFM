package proj;
import java.io.IOException;
import java.util.List;
import proj.Exception.EquipaNaoExisteException;
import proj.Exception.LinhaIncorretaException;
import proj.Model.Estado;
import proj.Model.Jogo;

import java.util.ArrayList;

public class Controller {

    private static Estado e = new Estado();


    public static void start() throws Exception {

        String opcoes[] = {"Simular Jogo", "Torneio", "Consultar Equipa", "Consultar Jogador por nome", "Consultar Jogos", "Criador", "Carregar ficheiro", "Carregar Ficheiro Binário", "Salvar em Binário"};
        Menu mainMenu = new Menu(opcoes);
        boolean sair = true;

        while(sair){
            View.clear();
            mainMenu.executa();

            switch(mainMenu.getOpcao()) {
                case(1): // Simular um jogo
                    View.clear();
                    ControllerJogo.start(e.getEquipas());
                    break;

                case(2): // Torneio
                    View.clear();
                    ControllerTorneio.start(e.getEquipas());
                    break;

                case(3): // Consultar equipa
                    View.clear();
                    try{ControllerEquipa.start(e.getEquipas());}
                    catch(EquipaNaoExisteException s) {View.handler(2,""); View.pressAnyKey();}
                    break;
                
                case(4): // Consultar jogador por nome
                    ControllerJogador.start(e.getEquipas());
                    break;
            
                case(5): // Consultar jogos
                    View.clear();
                    List<Jogo> jogos = new ArrayList<>(e.getJogos());
                    View.showJogos(jogos);
                    View.pressAnyKey();
                    break;

                case(6): // Criador
                    ControllerCriador.start(e);
                    break;
                
                case(7): // Carregar ficheiro
                    View.clear();
                    String filePath = View.getNomeFicheiro();
                    try{
                        Parser.parse(filePath, e);
                        //Parser.parse("/Users/joaolourenco/Universidade/2º Ano/2º Semestre/POO/newFM/src/proj/logs.txt", e);
                        //Parser.parse("/home/cristiano/Desktop/Universidade/2º Ano/Programação Orientada a Objetos/newFM/src/proj/logs.txt", e);
                        //Parser.parse("C:\\Users\\joaof\\Desktop\\newFM\\out\\production\\newFM\\proj\\logs.txt", e);
                        View.messages(1);
                    } catch(LinhaIncorretaException s) {
                        View.handler(1,"");
                    }
                    View.pressAnyKey();
                    break;

                case(8):// Carregar em Binário
                    View.clear();
                    String newPath = View.getNomeFicheiro();
                    try { e = Estado.rdBinary(newPath); View.messages(5);}
                    catch (IOException s) {View.handler(10,"");}
                    View.pressAnyKey();
                    break;

                case(9): // Salvar Em Binário
                    View.clear();
                    String path = View.getNomeFicheiro();
                    try {e.wrBinary(path); View.messages(5);}
                    catch (IOException s) {View.handler(9,"");}
                    View.pressAnyKey();
                    break;

                case(0): //Sair
                sair = false;
                break;
            }
        }
    }   
}
