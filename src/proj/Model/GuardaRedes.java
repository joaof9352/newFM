package proj.Model;

public class GuardaRedes extends Jogador{

    private int elasticidade;
    public GuardaRedes (String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p, int elast) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        elasticidade = elast;
    }

    public GuardaRedes(GuardaRedes gr){
        super(gr);
        this.elasticidade = gr.getElasticidade();
    }

    public int getElasticidade(){
        return this.elasticidade;
    }

    @Override
    public double calculaHabilidade() {
        return 0.8*(double) ((this.getImpulsao()+this.getDestreza()+this.getElasticidade() + this.getVelocidade())/4)
                + 0.2*(double) ((this.getPasse() + this.getResistencia())/2);
    }

    public static GuardaRedes parse(String input){
        String[] campos = input.split(",");
        return new GuardaRedes(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }

    public GuardaRedes clone(){
        return new GuardaRedes(this);
    }
}