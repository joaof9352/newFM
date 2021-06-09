package proj;

public class Controller {
    
    public void start() {

        Menu mainMenu = new Menu("Jogar", "Consultar Equipa", "Consultar Jogador por nome", "Consultar Jogos", "Carregar ficheiro", "Salvar");
        boolean sair = true;

        while(sair){
            mainMenu.executa();

            switch(mainMenu.getOpcao()) {
                case(1): // Jogar

                case(2): // Consultar equipa
                    ControllerEquipa.start(Parser.getEquipas());
                    break;
                
                case(3): // Consultar jogador
                    ControllerJogador.start(Parser.getEquipas());
                    break;
            
                case(4): // Consultar jogos
                    List<Jogo> jogos = new ArrayList<>(Parser.getJogos());
                    View.showJogos(jogos);
                    View.pressAnyKey();
                    break;

                case(5): // Carregar ficheiro

                case(6): // Salvar

                case(0): //Sair
                sair = false;
                break;
            }
        }
    }   
}
