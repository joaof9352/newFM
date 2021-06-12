package proj;

import proj.Exception.EquipaNaoExisteException;
import proj.Model.Avancado;
import proj.Model.Defesa;
import proj.Model.Equipa;
import proj.Model.Estado;
import proj.Model.GuardaRedes;
import proj.Model.Jogador;
import proj.Model.Lateral;
import proj.Model.Medio;

public class ControllerCriador {
    public static void start(Estado e) throws Exception, EquipaNaoExisteException {

        String opcoes[] = {"Criar Equipa", "Criar Jogador", "Transferir Jogador"};
        Menu mainMenu = new Menu(opcoes);
        boolean sair = true;

        while(sair){
            View.clear();
            mainMenu.executa();

            switch(mainMenu.getOpcao()) {
                case(1): // Criar Equipa
                    String nomeE = View.getNomeEquipa();
                    Equipa nEquipa = new Equipa(nomeE);
                    e.adicionaEquipa(nEquipa);
                    break;

                case(2): // Criar Jogador
                    menuCriaJogador(e);
                    break;

                case(3): // Transferir jogador
                    String key = View.getNomeEquipa();
                    if(!e.getEquipas().containsKey(key)) {throw new EquipaNaoExisteException("A equipa que inseriu não existe.");
                    } else {   
                        String jogadores= e.getEquipas().get(key).toString();
                        View.showEquipa(jogadores);
                    }
                    int num = View.getIntM("Insira o número do jogador que quer transferir", 1, 99);
                    String equipaPara = View.getNomeEquipa();
                    e.transferencia(num, key, equipaPara);
                    break;

                case(0): //Sair
                sair = false;
                break;
            }
        }
    }

    public static void menuCriaJogador(Estado e) throws Exception {
        int numeroJ, vel, res, des, imp, cab, rem, p, elast, cruz, rec;
        String nomeJ, nomeE;
        Jogador j;
        String opcoes[] = {"Guarda Redes", "Defesa", "Lateral", "Médio", "Avançado"};
        Menu mainMenu = new Menu(opcoes);
        boolean sair = true;

        while(sair){
            View.clear();
            mainMenu.executa();

            switch(mainMenu.getOpcao()) {
                case(1): // Guarda Redes
                    nomeJ = View.getNomeJogador();
                    numeroJ = View.getIntM("Número do jogador: (1 a 99)", 1, 99);
                    vel = View.getIntM("Velocidade: (0 a 100)", 0, 100);
                    res = View.getIntM("Resistência: (0 a 100)", 0, 100);
                    des = View.getIntM("Destreza: (0 a 100)", 0, 100);
                    imp = View.getIntM("Impulsão: (0 a 100)", 0, 100);
                    cab = View.getIntM("Cabeceamento: (0 a 100)", 0, 100);
                    rem = View.getIntM("Remate: (0 a 100)", 0, 100);
                    p = View.getIntM("Passe: (0 a 100)", 0, 100);
                    elast = View.getIntM("Elasticidade: (0 a 100)", 0, 100);

                    j = new GuardaRedes(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p, elast);
                    nomeE = View.getNomeEquipa();

                    e.adicionaJogadorAEquipa(j, nomeE);
                    sair = false;
                    break;

                case(2): // Defesa
                    nomeJ = View.getNomeJogador();
                    numeroJ = View.getIntM("Número do jogador: (1 a 99)", 1, 99);
                    vel = View.getIntM("Velocidade: (0 a 100)", 0, 100);
                    res = View.getIntM("Resistência: (0 a 100)", 0, 100);
                    des = View.getIntM("Destreza: (0 a 100)", 0, 100);
                    imp = View.getIntM("Impulsão: (0 a 100)", 0, 100);
                    cab = View.getIntM("Cabeceamento: (0 a 100)", 0, 100);
                    rem = View.getIntM("Remate: (0 a 100)", 0, 100);
                    p = View.getIntM("Passe: (0 a 100)", 0, 100);

                    j = new Defesa(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
                    nomeE = View.getNomeEquipa();

                    e.adicionaJogadorAEquipa(j, nomeE);
                    sair = false;
                    break;

                case(3): // Lateral
                    nomeJ = View.getNomeJogador();
                    numeroJ = View.getIntM("Número do jogador: (1 a 99)", 1, 99);
                    vel = View.getIntM("Velocidade: (0 a 100)", 0, 100);
                    res = View.getIntM("Resistência: (0 a 100)", 0, 100);
                    des = View.getIntM("Destreza: (0 a 100)", 0, 100);
                    imp = View.getIntM("Impulsão: (0 a 100)", 0, 100);
                    cab = View.getIntM("Cabeceamento: (0 a 100)", 0, 100);
                    rem = View.getIntM("Remate: (0 a 100)", 0, 100);
                    p = View.getIntM("Passe: (0 a 100)", 0, 100);
                    cruz = View.getIntM("Cruzamento: (0 a 100)", 0, 100);

                    j = new Lateral(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p, cruz);
                    nomeE = View.getNomeEquipa();

                    e.adicionaJogadorAEquipa(j, nomeE);
                    sair = false;
                    break;
                
                case(4): // Médio
                    nomeJ = View.getNomeJogador();
                    numeroJ = View.getIntM("Número do jogador: (1 a 99)", 1, 99);
                    vel = View.getIntM("Velocidade: (0 a 100)", 0, 100);
                    res = View.getIntM("Resistência: (0 a 100)", 0, 100);
                    des = View.getIntM("Destreza: (0 a 100)", 0, 100);
                    imp = View.getIntM("Impulsão: (0 a 100)", 0, 100);
                    cab = View.getIntM("Cabeceamento: (0 a 100)", 0, 100);
                    rem = View.getIntM("Remate: (0 a 100)", 0, 100);
                    p = View.getIntM("Passe: (0 a 100)", 0, 100);
                    rec = View.getIntM("Recuperação: (0 a 100)", 0, 100);

                    j = new Medio(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p, rec);
                    nomeE = View.getNomeEquipa();

                    e.adicionaJogadorAEquipa(j, nomeE);
                    sair = false;
                    break;
                
                case(5): // Avançado
                    nomeJ = View.getNomeJogador();
                    numeroJ = View.getIntM("Número do jogador: (1 a 99)", 1, 99);
                    vel = View.getIntM("Velocidade: (0 a 100)", 0, 100);
                    res = View.getIntM("Resistência: (0 a 100)", 0, 100);
                    des = View.getIntM("Destreza: (0 a 100)", 0, 100);
                    imp = View.getIntM("Impulsão: (0 a 100)", 0, 100);
                    cab = View.getIntM("Cabeceamento: (0 a 100)", 0, 100);
                    rem = View.getIntM("Remate: (0 a 100)", 0, 100);
                    p = View.getIntM("Passe: (0 a 100)", 0, 100);

                    j = new Avancado(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
                    nomeE = View.getNomeEquipa();

                    e.adicionaJogadorAEquipa(j, nomeE);
                    sair = false;
                    break;

                case(0): //Sair
                sair = false;
                break;
            }
        }
    }
}
