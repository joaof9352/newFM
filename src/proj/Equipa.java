package proj;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Equipa {

    private String nome;
    private List<Jogador> jogadores;

    public Equipa(String nomeE) {
        nome=nomeE;
        jogadores = new ArrayList<>();
    }

    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }

    public void insereJogador(Jogador j) {
        jogadores.add(j.clone());
    }

    public Jogador getJogadorByNum(int num){
        Jogador j = null;
        for(Jogador k : this.jogadores){
            if(k.getNumeroJogador() == num){
                j = k.clone();
            }
        }
        return j;
    }

    public List<Jogador> getJogadores(){
        return this.jogadores.stream().map(Jogador::clone).collect(Collectors.toList());
    }

    public String getNome(){
        return nome;
    }

    public String toString(){
        String r =  "Equipa:" + nome + "\n";
        for (Jogador j : jogadores){
            r += j.toString();
        }
        return r;
    }

}