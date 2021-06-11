package proj;

public class Avancado extends Jogador {
    public Avancado(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
    }

    public Avancado(Avancado a){
        super(a);
    }

    @Override
    public double calculaHabilidade() {
        return 0.8*(double) ((this.getImpulsao()+this.getCabeca()+this.getRemate() + this.getVelocidade())/4)
                + 0.2*(double) ((this.getPasse() + this.getResistencia() + this.getDestreza())/3);
    }

    public static Avancado parse(String input){
        String[] campos = input.split(",");
        return new Avancado(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]));
    }

    @Override
    public Avancado clone() {
        return new Avancado(this);
    }
}