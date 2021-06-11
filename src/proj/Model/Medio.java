package proj.Model;

public class Medio extends Jogador {

    private int recuperacao;
    public Medio(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p, int rec) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        recuperacao = rec;
    }

    public Medio(Medio m){
        super(m);
        this.recuperacao = m.getRecuperacao();
    }

    public int getRecuperacao(){
        return this.recuperacao;
    }

    @Override
    public double calculaHabilidade() {
        return 0.8*(double) ((this.getPasse()+this.getRecuperacao()+this.getResistencia())/3)
                + 0.2*(double) ((this.getCabeca() + this.getDestreza() + this.getVelocidade() + this.getImpulsao() + this.getRemate())/5);
    }

    public static Medio parse(String input){
        String[] campos = input.split(",");
        return new Medio(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }

    @Override
    public Jogador clone() {
        return new Medio(this);
    }
}