package proj.Model;

public class Lateral extends Jogador{

    private int cruzamento;
    public Lateral(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p, int cruz) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        cruzamento = cruz;
    }

    public Lateral(Lateral l){
        super(l);
        this.cruzamento = l.getCruzamento();
    }

    public int getCruzamento(){
        return this.cruzamento;
    }

    @Override
    public double calculaHabilidade() {
        return 0.8*(double) ((this.getPasse()+this.getCruzamento()+this.getResistencia() + this.getVelocidade())/4)
                + 0.2*(double) ((this.getCabeca() + this.getDestreza() + this.getImpulsao() + this.getRemate())/4);
    }

    public static Lateral parse(String input){
        String[] campos = input.split(",");
        return new Lateral(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }

    public Lateral clone(){
        return new Lateral(this);
    }
}