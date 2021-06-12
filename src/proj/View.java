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
        StringBuilder sb = new StringBuilder("Pressione Enter para continuar:");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void showListaEquipas(List<String> equipas) {
        StringBuilder sb = new StringBuilder("---------- Lista de Equipas ----------\n");
        for (String e: equipas){
            sb.append(e + "\n");
        }
        System.out.println(sb.toString());
    }

    public static String getNomeEquipa() {
        StringBuilder sb = new StringBuilder("Insira o nome da equipa:");
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
            case(1): sb.append("Ficheiro lido com sucesso."); break;
            case(2): sb.append("Se não pretende fazer mais alterações, insira -1. Jogador a sair: "); break;
            case(3): sb.append("Se pretende cancelar a substituição, insira -1. Jogador a entrar: "); break;
            case(4): sb.append("Jogo empatado, haverá outro jogo para desempatar."); break;
        }
        System.out.println(sb.toString());
    }

    public static void handler(int error, String s) {
        StringBuilder sb = new StringBuilder();
        switch(error) {
            case(1): sb.append("Ficheiro com linha incorreta.\n"); break;
            case(2): sb.append("A equipa não existe.\n"); break;
            case(3): sb.append("A ronda não é válida."); break;
            case(4): sb.append("Número de rondas não válido para as equipas existentes."); break;
            case(5): sb.append("O jogador titular com esse número não existe."); break;
            case(6): sb.append("O jogador suplente com esse número não existe."); break;
            case(7): sb.append("Para subsituir 2 jogadores, estes devem ser da mesma posição."); break;
            case(8): sb.append("A equipa " + s + " não tem jogadores suficientes em todas as posições para jogar."); break;
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
        List<Integer> suplentesNr = new ArrayList<>();
        suplentes = suplentes.stream().filter(j -> !equipa.getTitulares().contains(j.getNumeroJogador())).collect(Collectors.toList());
        suplentesNr = suplentes.stream().map(Jogador::getNumeroJogador).collect(Collectors.toList());

        int result[] = {-1,-1};

        Scanner scanner = new Scanner(System.in);
        StringBuilder sEquipa = new StringBuilder();
        sEquipa.append(equipa.getNome() + "\n");
        sEquipa.append("Titulares: \n");
        for(int jog : equipa.getTitulares()) {
            sEquipa.append("   " + equipa.getJogadorByNum(jog).toString());
        }
        sEquipa.append("Suplentes: \n");
        for(Jogador jog : suplentes) {
            sEquipa.append("   " + jog.toString());
        }
        int sair;
        int entrar;
        boolean repetir = true;
        while (repetir) {
            System.out.println(sEquipa.toString());
            messages(2);
            sair = scanner.nextInt();
            if (sair == -1) {
                return result;
            }
            while (!equipa.getTitulares().contains(sair)) {
                System.out.println(sEquipa.toString());
                handler(5,"");
                messages(2);
                sair = scanner.nextInt();
            }
            result[0] = sair;
            messages(3);
            entrar = scanner.nextInt();
            if (entrar != -1) {
                while (!suplentesNr.contains(entrar) && entrar != -1) {
                    System.out.println(sEquipa.toString());
                    handler(6, "");
                    messages(3);
                    entrar = scanner.nextInt();
                }
                if(entrar != -1) {
                    result[1] = entrar;
                    repetir = false;
                }
                if(!equipa.getJogadorByNum(entrar).getClass().toString().equals(equipa.getJogadorByNum(sair).getClass().toString())) {
                    handler(7, "");
                    pressAnyKey();
                    repetir = true;
                }
            }
        }
        return result;
    }

    public static void showMetadeJogo(int parte, List<Integer> minutosGolosCasa,
                                                 List<Integer> minutosGolosFora,
                                                 List<Integer> minutosOportunidadesCasa,
                                                 List<Integer> minutosOportunidadesFora,
                                                 String casa, String fora) throws InterruptedException {

        minutosGolosCasa.stream().forEach(System.out::println);
        pressAnyKey();
        minutosGolosFora.stream().forEach(System.out::println);
        pressAnyKey();

        int init = 0;
        int fim = 45;
        int golosCasa = 0;
        int golosFora = 0;
        if(parte == 2) {
            init = 46;
            fim = 90;
            golosCasa = (int) minutosGolosCasa.stream().filter(k -> k < 46).count();
            golosFora = (int) minutosGolosFora.stream().filter(k -> k < 46).count();
        }

        for(int i = init; i < fim; i++) {
            clear();
            System.out.println("[" + casa + "] " + golosCasa + " vs " + golosFora + " [" + fora + "]\n");
            System.out.print("Minuto [" + i +  "] ");

            if (minutosOportunidadesCasa.contains(i)) {
                System.out.print("Oportunidade para " + casa + "...   ");
                Thread.sleep(1500);
                if (minutosGolosCasa.contains(i)) {
                    System.out.print("GOLOOOO!!!");
                    golosCasa++;
                    Thread.sleep(750);
                } else {
                    System.out.print("Fora...");
                    Thread.sleep(750);
                }
            } else if (minutosOportunidadesFora.contains(i)) {
                System.out.print("Oportunidade para " + fora + "...   ");
                Thread.sleep(1500);
                if (minutosGolosFora.contains(i)) {
                    System.out.print("GOLOOOO!!!");
                    golosFora++;
                    Thread.sleep(750);
                } else {
                    System.out.print("Fora...");
                    Thread.sleep(750);
                }
            }
            Thread.sleep(300);
        }
        if(parte == 2) {
            System.out.println(casa + " " + golosCasa + " - " + golosFora + " " + fora + "\n\n");
        }
    }
}

