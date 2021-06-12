package proj;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import proj.Exception.EquipaNaoExisteException;
import proj.Exception.EquipaSemJogadoresException;
import proj.Exception.EquipasInsuficientesException;
import proj.Exception.RondaNaoValidaException;
import proj.Model.Equipa;
import proj.Model.Jogo;
import proj.Model.Torneio;

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
                    String fileName = View.getNomeFicheiro();
                    FileInputStream fin = new FileInputStream(fileName);
                    ObjectInputStream ois = new ObjectInputStream(fin);
                    Torneio t= (Torneio) ois.readObject();
                    menuTorneio(t);
                    ois.close();
                    break;

                case(0): //Sair
                    sair = false;
                    break;
            }
        }
    }

    public static void startTorneio(Map<String, Equipa> equipas) throws EquipaNaoExisteException, Exception{

        String opcoes[] = {"Escolher uma equipa", "Equipa aleatória"};
        Menu mainMenu = new Menu(opcoes);
        boolean sair = true;

        while(sair){
            mainMenu.executa();

            switch(mainMenu.getOpcao()) {//ver que limite dar nas rondas
                case(1): // Escolher equipa e começar torneio
                    List<String> l = new ArrayList<>(equipas.keySet());
                    int key = View.escolheEquipa(l, 1);

                    String nomeEquipa = l.get(key);
                    if(!equipas.containsKey(nomeEquipa)) {throw new EquipaNaoExisteException("A equipa que inseriu não existe.");
                    } else {   
                        int nRondas = View.getNRondasTorneio();
                        Torneio t = new Torneio(equipas, nRondas, equipas.get(nomeEquipa));
                        menuTorneio(t);
                        sair = false;
                    }
                    break;

                case(2): // Começar torneio com equipa aleatória
                    try {
                        int nRondasA = View.getNRondasTorneio();
                        Torneio tA = new Torneio(equipas, nRondasA);
                        menuTorneio(tA);
                        sair = false;
                    } catch (EquipasInsuficientesException e) {View.handler(4,"");}
                    break;

                case(0): //Sair
                    sair = false;
                    break;
            }
        }
    }
    //verificar se os throws estão bem

    public static void menuTorneio(Torneio t) throws Exception, EquipaSemJogadoresException{ //ver como fazer quando for o ultimo jogo do torneio
        String opcoes[] = {"Jogar próxima ronda", "Ver jogos da próxima ronda", "Ver resultados de uma ronda", "Consultar as equipas do torneio", "Salvar torneio"};
        Menu torneioMenu = new Menu(opcoes);
        boolean sair = true;

        while(sair){
            torneioMenu.executa();
            switch(torneioMenu.getOpcao()) {
                case(1): // Jogar proxima ronda
                    boolean participa = t.jogadorAindaParticipa();
                    String[] jogoProxRonda = new String[2];
                    int eP = 0;
                    List<int[]> rondaG = new ArrayList<>();
                    if(participa){
                        Equipa e1 = t.getEquipas().get(t.getChave().get(t.getRonda()).get(0)[0]);
                        Equipa e2 = t.getEquipas().get(t.getChave().get(t.getRonda()).get(0)[1]);
                        boolean skip = true;

                        try {e1.fillTitulares();}
                        catch(EquipaSemJogadoresException s) {View.handler(8,e1.getNome()); skip = false; sair = false;}
                        try {e2.fillTitulares();}
                        catch(EquipaSemJogadoresException s) {View.handler(8,e2.getNome()); skip = false; sair = false;}
                        
                        if(skip){
                            Jogo j = new Jogo(e1,e2, LocalDate.now());
                            
                            while(j.getGolosCasa() == j.getGolosFora()){
                                int aux = 0;
                                int subs[] = {0,0};

                                subs = View.getSubstituicoes(j.getEquipaCasa());
                                if(subs[1] == -1) aux = -1;
                                while(aux != -1) {
                                    j.substituicao(0, subs[0], subs[1]);
                                    subs = View.getSubstituicoes(j.getEquipaCasa());
                                    if(subs[1] == -1) aux = -1;
                                }

                                j.runMetadePermiteSubs();
                                View.showMetadeJogo(1, j.getMinutosGolosCasa(), j.getMinutosGolosFora(), j.getMinutosOportunidadesCasa(),j.getMinutosOportunidadesFora(), j.getNomeCasa(), j.getNomeFora());

                                subs = View.getSubstituicoes(j.getEquipaCasa());
                                if(subs[1] == -1) aux = -1;
                                for(int i = 0; i < 2 && aux != -1; i++) {
                                    j.substituicao(0, subs[0], subs[1]);
                                    subs = View.getSubstituicoes(j.getEquipaCasa());
                                    if(subs[1] == -1) aux = -1;
                                }

                                j.runMetadePermiteSubs();
                                View.showMetadeJogo(2, j.getMinutosGolosCasa(), j.getMinutosGolosFora(), j.getMinutosOportunidadesCasa(),j.getMinutosOportunidadesFora(), j.getNomeCasa(), j.getNomeFora());

                                System.out.println("\n" + j.getNomeCasa() + " " + j.getGolosCasa() + " - " + j.getGolosFora() + " " + j.getNomeFora() + "\n\n");
                                View.pressAnyKey();

                                if(j.getGolosCasa() == j.getGolosFora())  
                                    View.messages(4);
                                else{
                                    int[] golosJogo = new int[2];
                                    golosJogo[0] = j.getGolosCasa();
                                    golosJogo[1] = j.getGolosFora();
                                    rondaG.add(golosJogo);

                                    eP = 1;
                                    if (golosJogo[0] > golosJogo[1]) jogoProxRonda[0] = e1.getNome();
                                    else jogoProxRonda[0] = e2.getNome();
                                }
                            }
                        }
                        
                        t.simulaRonda(participa, jogoProxRonda, eP, rondaG);
                        try{
                            String rRonda = t.resultadosRondaToString(t.getRonda());
                            View.showRonda(rRonda);
                            View.pressAnyKey();
                        } catch (RondaNaoValidaException r) {View.handler(3,"");}

                        if(t.getChave().get(t.getRonda() - 1).size() == 1){
                            sair = false;
                            System.out.println("O campeão foi " + jogoProxRonda[0] + "\n");
                        }
                    }
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
                    } catch (RondaNaoValidaException r) {View.handler(3,"");}
                    break;
                
                case(4): // Consultar as equipas do torneio
                    try{ControllerEquipa.start(t.getEquipas());}
                    catch(EquipaNaoExisteException s) {View.handler(2,"");}
                    break;
                
                case(5): // Salvar torneio
                    String fileName = View.getNomeFicheiro();
                    FileOutputStream fos = new FileOutputStream(fileName);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(t);
                    oos.close();
                    break;
                case(0): //Sair
                    sair = false;
                    break;
            }
        }
    }
    
}