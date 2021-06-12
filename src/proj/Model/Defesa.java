package proj.Model;

public class Defesa extends Jogador {
    public Defesa(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
    }

    public Defesa(Defesa d){
        super(d);
    }

    @Override
    public double calculaHabilidade() {
        return 0.8*(double) ((this.getPasse()+ this.getCabeca() +this.getImpulsao() + this.getVelocidade())/4)
                + 0.2*(double) (this.getDestreza() + this.getRemate())/2;

    }

    public static Defesa parse(String input){
        String[] campos = input.split(",");
        return new Defesa(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]));
    }

    @Override
    public Defesa clone() {
        return new Defesa(this);
    }
}