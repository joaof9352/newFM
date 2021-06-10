package proj;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class View {

    public static String pressAnyKey() {
        StringBuilder sb = new StringBuilder("Pressione qualquer tecla para continuar: \n");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
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

    public static String getNomeJogador() {
        StringBuilder sb = new StringBuilder("Insira o nome da jogador: \n");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void showJogadores(String nome, HashMap<String,Jogador> jogadores) {

        StringBuilder sb = new StringBuilder("------- Jogadores com o nome " + nome + "-------\n");

        for(Map.Entry<String,Jogador> entry : jogadores.EntrySet()) {
            sb.append(entry.getKey() + ": \n");
            sb.append(entry.getValue().toString() + "\n");
        }
        System.out.println(sb.toString());
    }

    public static void showJogos(ArrayList<Jogo> jogos) {
        for(Jogo j : jogos) {
            System.out.println(j.toString());
        }
    }
}

