package proj;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import proj.Exception.NumeroSemJogadorException;
import proj.Model.Equipa;
import proj.Model.Jogador;
import proj.Model.Jogo;


public class View {

    public static String pressAnyKey() {
        StringBuilder sb = new StringBuilder("Pressione qualquer tecla para continuar: \n");
        System.out.println(sb.toString());
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
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        //scanner.close();
        return s;
    }

    public static int getNRondasTorneio() {
        System.out.println("Insira o número de rondas do torneio: ");
        Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt();
        //scanner.close();
        return s;
    }

    public static int getNRonda(){
        StringBuilder sb = new StringBuilder("Insira a ronda que quer observar: \n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt();
        //scanner.close();
        return s;
    }

    public static void showRonda(String ronda){
        System.out.println(ronda);
    }

    public static void showEquipa(String jogadores) {
        System.out.println(jogadores);
    }

    public static String getNomeJogador() {
        StringBuilder sb = new StringBuilder("Insira o nome da jogador: \n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        //scanner.close();
        return s;
    }

    public static String getNomeFicheiro() {
        StringBuilder sb = new StringBuilder("Insira o nome do ficheiro a carregar: \n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        //scanner.close();
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
            case(2): sb.append("A equipa não existe.\n"); break;
            case(3): sb.append("A ronda não é válida."); break;
            case(4): sb.append("Número de rondas não válido para as equipas existentes."); break;
        }
        System.out.println(sb.toString());
    }

    public static int escolheEquipa(List<String> equipas, int nrJog) {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        for(String s : equipas) {
            sb.append(equipas.indexOf(s) + " - " + s + "\n");
        }
        sb.append("Jogador nr" + nrJog + " escolha a equipa com que pretende jogar:");
        System.out.println(sb.toString());
        int s = scanner.nextInt();
        return s;
    }

    public static int[] getSubstituicoes(Equipa equipa) throws NumeroSemJogadorException {

        List<Jogador> suplentes = new ArrayList<>(equipa.getJogadores());
        suplentes.stream().filter(j -> !equipa.getTitulares().contains(j.getNumeroJogador())).collect(Collectors.toList());

        int result[] = {-1,-1};

        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder("---------- Intervalo ----------");
        sb.append(equipa.getNome() + "\n");
        sb.append("Titulares: \n");
        for(int jog : equipa.getTitulares()) {
            sb.append("   " + equipa.getJogadorByNum(jog).toString());
        }
        sb.append("Suplentes: \n");
        for(Jogador jog : suplentes) {
            sb.append("   " + jog.toString());
        }
        sb.append("Jogador a sair: \n(Se não pretende fazer mais alterações, insira -1)");
        System.out.println(sb.toString());
        int sair = scanner.nextInt();
        if(sair == -1) {
            return result;
        }
        result[0] = sair;
        System.out.println("Jogador a entrar: \n");
        int entrar = scanner.nextInt();
        result[1] = entrar;
        return result;
    }


}

