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
}

