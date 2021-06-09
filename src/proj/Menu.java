package proj;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Menu {
    
    public static int mainMenu() {
        StringBuilder sb = new StringBuilder("---------- MENU ----------\n");
        sb.append("[1] -> Jogar\n");
        sb.append("[2] -> Consultar equipa\n");
        sb.append("[3] -> Consultar Jogador\n");
        sb.append("[4] -> Consultar jogo\n");
        sb.append("[5] -> Carregar ficheiro\n");
        sb.append("[6] -> Salvar\n");
        sb.append("[0] -> Sair\n");
        sb.append("Selecione a opção pretendida: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static String pressAnyKey() {
        StringBuilder sb = new StringBuilder("Pressione qualquer tecla para continuar: \n");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int menuEquipa() {
        StringBuilder sb = new StringBuilder("-------------------------\n");
        sb.append("[1] -> Consultar lista de equipas\n");
        sb.append("[2] -> Consultar equipa especifica\n");
        sb.append("[0] -> Voltar\n");
        sb.append("Selecione a opção pretendida: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void showListaEquipas(List<String> equipas) {
        StringBuilder sb = new StringBuilder("---------- Lista de Equipas ----------\n");
        for (String e: equipas){
            sb.append(e + "\n");
        }
        System.out.println(sb.toString());
    }

    public static String getNomeEquipa() {
        StringBuilder sb = new StringBuilder("Insira o nome da equipa: \n");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void showEquipa(String jogadores) {
        System.out.println(jogadores);
    }





}
