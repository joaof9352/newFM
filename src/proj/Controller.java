package proj;

public class Controller {
    
    public void start() {

        Menu mainMenu = new Menu("Simular Jogo", "Torneio", "Consultar Equipa", "Consultar Jogador por nome", "Consultar Jogos", "Carregar ficheiro", "Salvar");
        boolean sair = true;

        while(sair){
            mainMenu.executa();

            switch(mainMenu.getOpcao()) {
                case(1): // Simular um jogo

                case(2): // Torneio

                case(3): // Consultar equipa
                    ControllerEquipa.start(Parser.getEquipas());
                    break;
                
                case(4): // Consultar jogador por nome
                    ControllerJogador.start(Parser.getEquipas());
                    break;
            
                case(5): // Consultar jogos
                    List<Jogo> jogos = new ArrayList<>(Parser.getJogos());
                    View.showJogos(jogos);
                    View.pressAnyKey();
                    break;

                case(6): // Carregar ficheiro

                case(7): // Salvar

                case(0): //Sair
                sair = false;
                break;
            }
        }
    }   
}
