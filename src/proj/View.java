package proj;
import java.util.Scanner;
import java.util.List;
import java.util.Map;


public class View {

    public static String pressAnyKey() {
        StringBuilder sb = new StringBuilder("Pressione qualquer tecla para continuar: \n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();
        return s;
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
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();
        return s;
    }

    public static void showEquipa(String jogadores) {
        System.out.println(jogadores);
    }

    public static String getNomeJogador() {
        StringBuilder sb = new StringBuilder("Insira o nome da jogador: \n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();
        return s;
    }

    public static String getNomeFicheiro() {
        StringBuilder sb = new StringBuilder("Insira o nome do ficheiro a carregar: \n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();
        return s;
    }

    public static void showJogadores(String nome, Map<String,Jogador> jogadores) {

        StringBuilder sb = new StringBuilder("------- Jogadores com o nome " + nome + "-------\n");

        for(Map.Entry<String,Jogador> entry : jogadores.entrySet()) {
            sb.append(entry.getKey() + ": \n");
            sb.append(entry.getValue().toString() + "\n");
        }
        System.out.println(sb.toString());
    }

    public static void showJogos(List<Jogo> jogos) {
        for(Jogo j : jogos) {
            System.out.println(j.toString());
        }
    }

    public static void messages(int message) {
        StringBuilder sb = new StringBuilder();
        switch(message) {
            case(1): sb.append("Ficheiro lido com sucesso.\n"); break;
        }
        System.out.println(sb.toString());
    }

    public static void handler(int error) {
        StringBuilder sb = new StringBuilder();
        switch(error) {
            case(1): sb.append("Ficheiro com linha incorreta.\n"); break;
            case(2): sb.append("A equipa não existe.\n");
        }
        System.out.println(sb.toString());
    }
}

