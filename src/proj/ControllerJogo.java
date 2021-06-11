package proj;
import proj.Exception.*;
import proj.Model.Equipa;
import proj.Model.Jogo;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Random;

public class ControllerJogo {

    public static void start(Map<String, Equipa> equipas) throws NumeroSemJogadorException, JogadorNaoExisteException, SubstituicaoImpossivelException, PosicaoSemJogadoresException, EquipaSemJogadoresException {

    String opcoes[] = {"Jogar 1 vs 1", "Jogar contra o PC"};
    Menu menuJogo = new Menu(opcoes);

        boolean sair = true;
        while(sair) {
            menuJogo.executa();
            switch(menuJogo.getOpcao()) {
                case 1: // Jogar 1 vs 1
                    List<String> l = new ArrayList<>(equipas.keySet());
                    int key1 = View.escolheEquipa(l, 1);
                    l.remove(key1);
                    int key2 = View.escolheEquipa(l, 2);
                    Equipa e1 = equipas.get(key1);
                    e1.fillTitulares();
                    Equipa e2 = equipas.get(key2);
                    e2.fillTitulares();
                    Jogo j = new Jogo(e1,e2, LocalDate.now());
                    int aux = 0;
                    int subs[] = {0,0};

                    j.runMetadePermiteSubs();
                    //View.showMetadeJogo();

                    subs = View.getSubstituicoes(e1);
                    if(subs[1] == -1) aux = -1;
                    for(int i = 0; i < 3 || aux == -1; i++) {
                        j.substituicao(0, subs[0], subs[1]);
                        subs = View.getSubstituicoes(e1);
                    }

                    aux = 0;
                    subs = View.getSubstituicoes(e2);
                    if(subs[0] == -1) aux = -1;
                    for(int i = 0; i < 3 || aux == -1; i++) {
                        j.substituicao(1, subs[0], subs[1]);
                        subs = View.getSubstituicoes(e2);
                    }
                
                    j.runMetadePermiteSubs();
                    //View.showMetadeJogo();

                    System.out.println(j.getNomeCasa() + " " + j.getGolosCasa() + " - " + j.getGolosFora() + " " + j.getNomeFora() + "\n\n");
                    View.pressAnyKey();
                    break;

                case 2: // Jogar contra o PC
                    
                    Random random = new Random();

                    l = new ArrayList<>(equipas.keySet());
                    key1 = View.escolheEquipa(l, 1);
                    l.remove(key1);
                    e1 = equipas.get(key1);
                    equipas.remove(key1);
                    key2 = random.nextInt(l.size());
                    e2 = equipas.get(key2);
                    j = new Jogo(e1,e2, LocalDate.now());
                    aux = 0;
                    //subs[0] = subs[1] = 0;

                    j.runMetadePermiteSubs();
                    //View.showMetadeJogo();

                    subs = View.getSubstituicoes(e1);
                    if(subs[0] == -1) aux = -1;
                    for(int i = 0; i < 3 || aux == -1; i++) {
                        j.substituicao(0, subs[0], subs[1]);
                        subs = View.getSubstituicoes(e1);
                    }
                
                    j.runMetadePermiteSubs();
                    //View.showMetadeJogo();

                    //View.showResultadoFinal();
                    View.pressAnyKey();
                    break;

                case 0: // Voltar
                    sair = false;
                    break;
            }
        }
    }  
}



        
