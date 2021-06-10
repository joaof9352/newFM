package proj;

import jdk.nashorn.internal.runtime.options.Option;
import proj.Exception.EquipaSemJogadoresException;
import proj.Exception.NumeroSemJogadorException;
import proj.Exception.PosicaoSemJogadoresException;

import java.util.*;
import java.util.stream.Collectors;

public class Equipa {

    private String nome;
    private List<Jogador> jogadores;
    private List<Integer> titulares;

    public List<Integer> getTitulares() {
        return this.titulares;
    }

    public double calculaHabilidade (Class<?> posicao) throws PosicaoSemJogadoresException, NumeroSemJogadorException {
        List<Jogador> titularesJog = new ArrayList<>();
        for(Integer i : titulares) {
                titularesJog.add(getJogadorByNum(i));
        }
        OptionalDouble db = titularesJog.stream().filter(posicao::isInstance).mapToDouble(Jogador::calculaHabilidade).average();

        if(!db.isPresent()){
            throw new PosicaoSemJogadoresException("Erro a calcular habilidade na posição " + posicao.toString());
        }
        return db.getAsDouble();
    }

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

    public Jogador getJogadorByNum(int num) throws NumeroSemJogadorException {

        Optional<Jogador> j = this.jogadores.stream().filter(k -> k.getNumeroJogador() == num).findFirst();
        if(!j.isPresent()){
            throw new NumeroSemJogadorException() ;
        }

        return j.get();
    }

    public List<Jogador> getJogadores(){
        return this.jogadores.stream().map(Jogador::clone).collect(Collectors.toList());
    }

    public List<Jogador> getJogadoresPosicao(Class<?> posicao, int n) throws EquipaSemJogadoresException {
        List<Jogador> p;
        try{
            p = this.jogadores.stream().filter(posicao::isInstance).map(Jogador::clone).collect(Collectors.toList()).subList(0,n-1);
        }catch(IndexOutOfBoundsException e){
            throw new EquipaSemJogadoresException(posicao.toString());
        }

        return p;
    }

    public String getNome(){
        return nome;
    }

    public String toString(){
        StringBuilder r = new StringBuilder("Equipa:" + nome + "\n");
        for (Jogador j : jogadores){
            r.append(j.toString());
        }
        return r.toString();
    }

    public void fillTitulares() throws EquipaSemJogadoresException {
        /* Guarda-Redes */
        List<Jogador> gr = getJogadoresPosicao(GuardaRedes.class, 1);
        List<Jogador> lat = getJogadoresPosicao(Lateral.class, 2);
        List<Jogador> zag = getJogadoresPosicao(Defesa.class, 2);
        List<Jogador> med = getJogadoresPosicao(Medio.class, 3);
        List<Jogador> ava = getJogadoresPosicao(Avancado.class, 3);
    }

    public Jogador jogadorVaiSair(int num) throws NumeroSemJogadorException {
        Jogador j = this.getJogadorByNum(num);
        j.addHistorial(this.getNome());
        Jogador jc = j.clone();
        this.jogadores.remove(j);
        return jc;
    }
}