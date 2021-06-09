package proj;

public class Controller {
    
    public void start() {

        while(true) {
            int input = -1;
            while(input < 0 || input > 6) {
                input = Menu.mainMenu();
            }
        }

        switch(input) {
            case(1): // Jogar

            case(2): // Consultar equipa
                Map<String, Equipa> equipas = new HashMap<>(Parser.getEquipas());
                ControllerEquipa.start(equipas);
                break;
                
            case(3): // Consultar jogador

            case(4): // Consultar jogo

            case(5): // Carregar ficheiro

            case(6): // Salvar

            case(0): //Sair
        }
    }
}
