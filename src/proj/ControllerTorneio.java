package proj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import proj.Exception.EquipaNaoExisteException;
import proj.Exception.RondaNaoValidaException;

public class ControllerTorneio {

    public static void start(Map<String, Equipa> equipas) throws Exception {

        String opcoes[] = {"Novo torneio", "Carregar torneio"};
        Menu mainMenu = new Menu(opcoes);
        boolean sair = true;

        while(sair){
            mainMenu.executa();

            switch(mainMenu.getOpcao()) {
                case(1): // Novo torneio 
                    startTorneio(equipas);
                    break;

                case(2): // Carregar torneio
                    //carrega torneio já existente
                    break;

                case(0): //Sair
                    sair = false;
                    break;
            }
        }
    }

    public static void startTorneio(Map<String, Equipa> equipas) throws EquipaNaoExisteException{

        String opcoes[] = {"Escolher uma equipa", "Equipa aleatória"};
        Menu mainMenu = new Menu(opcoes);
        boolean sair = true;

        while(sair){
            mainMenu.executa();

            switch(mainMenu.getOpcao()) {//ver que limite dar nas rondas
                case(1): // Escolher equipa e começar torneio
                    List<String> l = new ArrayList<>(equipas.keySet());
                    View.showListaEquipas(l);

                    String nomeEquipa = View.getNomeEquipa();
                    if(!equipas.containsKey(nomeEquipa)) {throw new EquipaNaoExisteException("A equipa que inseriu não existe.");
                    } else {   
                        int nRondas = View.getNRondasTorneio();
                        Torneio t = new Torneio(equipas, nRondas, equipas.get(nomeEquipa));
                        //menu do torneio
                        sair = false;
                    }
                    break;

                case(2): // Começar torneio com equipa aleatória
                    int nRondasA = View.getNRondasTorneio();
                    Torneio tA = new Torneio(equipas, nRondasA);
                    //menu torneio
                    sair = false;
                    break;

                case(0): //Sair
                    sair = false;
                    break;
            }
        }
    }
    //verificar se os throws estão bem

    public static void menuTorneio(Torneio t) throws Exception{ //ver como fazer quando for o ultimo jogo do torneio
        String opcoes[] = {"Jogar próxima ronda", "Ver jogos da próxima ronda", "Ver resultados de uma ronda", "Consultar as equipas do torneio", "Salvar torneio"};
        Menu mainMenu = new Menu(opcoes);
        boolean sair = true;

        while(sair){
            mainMenu.executa();

            switch(mainMenu.getOpcao()) {//colocar exception para número de rondas
                case(1): // Jogar proxima ronda
                    //correr a ronda, ver se está pronto
                    break;

                case(2): // Ver jogos proxima ronda
                    String pRonda = t.proxRondaToString();
                    View.showRonda(pRonda);
                    View.pressAnyKey();
                    break;
                
                case(3): // Ver resultados de uma ronda
                    int ronda = View.getNRonda();
                    try{
                        String rRonda = t.resultadosRondaToString(ronda);
                        View.showRonda(rRonda);
                        View.pressAnyKey();
                    } catch (RondaNaoValidaException r) {View.handler(3);}
                    break;
                
                case(4): // Consultar as equipas do torneio
                    try{ControllerEquipa.start(t.getEquipas());}
                    catch(EquipaNaoExisteException s) {View.handler(2);}
                    break;
                
                case(5): // Salvar torneio
                    break;
                case(0): //Sair
                    sair = false;
                    break;
            }
        }
    }
    
}
