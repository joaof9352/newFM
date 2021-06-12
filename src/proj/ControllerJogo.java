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

    public static void start(Map<String, Equipa> equipas) throws NumeroSemJogadorException, JogadorNaoExisteException, SubstituicaoImpossivelException, PosicaoSemJogadoresException, EquipaSemJogadoresException, InterruptedException {

    String opcoes[] = {"Jogar 1 vs 1", "Jogar contra o PC"};
    Menu menuJogo = new Menu(opcoes);

        boolean sair = true;
        while(sair) {
            menuJogo.executa();
            switch(menuJogo.getOpcao()) {
                case 1: // Jogar 1 vs 1
                    List<String> l = new ArrayList<>(equipas.keySet());
                    int key1 = View.escolheEquipa(l, 1);
                    Equipa e1 = equipas.get(l.get(key1));
                    equipas.remove(l.get(key1));
                    l.remove(key1);
                    int key2 = View.escolheEquipa(l, 2);
                    Equipa e2 = equipas.get(l.get(key2));
                    equipas.remove(l.get(key2));
                    l.remove(key2);

                    e1.fillTitulares();
                    e2.fillTitulares();
                    Jogo j = new Jogo(e1,e2, LocalDate.now());
                    int aux = 0;
                    int subs[] = {0,0};

                    subs = View.getSubstituicoes(j.getEquipaCasa());
                    if(subs[1] == -1) aux = -1;
                    while(aux != -1) {
                        j.substituicao(0, subs[0], subs[1]);
                        subs = View.getSubstituicoes(j.getEquipaCasa());
                        if(subs[1] == -1) aux = -1;
                    }

                    aux = 0;
                    subs = View.getSubstituicoes(j.getEquipaFora());
                    if(subs[1] == -1) aux = -1;
                    while(aux != -1) {
                        j.substituicao(0, subs[0], subs[1]);
                        subs = View.getSubstituicoes(j.getEquipaFora());
                        if(subs[1] == -1) aux = -1;
                    }

                    j.runMetadePermiteSubs();
                    View.showMetadeJogo(1, j.getMinutosGolosCasa(), j.getMinutosGolosCasa(), j.getMinutosOportunidadesCasa(),j.getMinutosOportunidadesFora(), j.getNomeCasa(), j.getNomeFora());

                    subs = View.getSubstituicoes(j.getEquipaCasa());
                    if(subs[1] == -1) aux = -1;
                    for(int i = 0; i < 2 && aux != -1; i++) {
                        j.substituicao(0, subs[0], subs[1]);
                        subs = View.getSubstituicoes(j.getEquipaCasa());
                        if(subs[1] == -1) aux = -1;
                    }

                    aux = 0;
                    subs = View.getSubstituicoes(j.getEquipaFora());
                    if(subs[0] == -1) aux = -1;
                    for(int i = 0; i < 2 && aux != -1; i++) {
                        j.substituicao(1, subs[0], subs[1]);
                        subs = View.getSubstituicoes(j.getEquipaFora());
                        if(subs[1] == -1) aux = -1;
                    }
                
                    j.runMetadePermiteSubs();
                    View.showMetadeJogo(2, j.getMinutosGolosCasa(), j.getMinutosGolosCasa(), j.getMinutosOportunidadesCasa(),j.getMinutosOportunidadesFora(), j.getNomeCasa(), j.getNomeFora());

                    System.out.println(j.getNomeCasa() + " " + j.getGolosCasa() + " - " + j.getGolosFora() + " " + j.getNomeFora() + "\n\n");
                    View.pressAnyKey();
                    break;

                case 2: // Jogar contra o PC
                    
                    Random random = new Random();

                    l = new ArrayList<>(equipas.keySet());
                    key1 = View.escolheEquipa(l, 1);
                    e1 = equipas.get(l.get(key1));
                    equipas.remove(l.get(key1));
                    l.remove(key1);
                    key2 = random.nextInt(l.size());
                    e2 = equipas.get(l.get(key2));
                    equipas.remove(l.get(key2));
                    l.remove(key2);

                    e1.fillTitulares();
                    e2.fillTitulares();
                    j = new Jogo(e1,e2, LocalDate.now());
                    aux = 0;
                    int subs2[] = {0,0};


                    subs = View.getSubstituicoes(j.getEquipaCasa());
                    if(subs[1] == -1) aux = -1;
                    while(aux != -1) {
                        j.substituicao(0, subs[0], subs[1]);
                        subs = View.getSubstituicoes(j.getEquipaCasa());
                        if(subs[1] == -1) aux = -1;
                    }

                    j.runMetadePermiteSubs();
                    View.showMetadeJogo(1, j.getMinutosGolosCasa(), j.getMinutosGolosCasa(), j.getMinutosOportunidadesCasa(),j.getMinutosOportunidadesFora(), j.getNomeCasa(), j.getNomeFora());

                    subs = View.getSubstituicoes(j.getEquipaCasa());
                    if(subs[1] == -1) aux = -1;
                    for(int i = 0; i < 2 && aux != -1; i++) {
                        j.substituicao(0, subs[0], subs[1]);
                        subs = View.getSubstituicoes(j.getEquipaCasa());
                        if(subs[1] == -1) aux = -1;
                    }

                    j.runMetadePermiteSubs();
                    View.showMetadeJogo(2, j.getMinutosGolosCasa(), j.getMinutosGolosCasa(), j.getMinutosOportunidadesCasa(),j.getMinutosOportunidadesFora(), j.getNomeCasa(), j.getNomeFora());

                    System.out.println(j.getNomeCasa() + " " + j.getGolosCasa() + " - " + j.getGolosFora() + " " + j.getNomeFora() + "\n\n");
                    View.pressAnyKey();
                    break;

                case 0: // Voltar
                    sair = false;
                    break;
            }
        }
    }  
}




        
