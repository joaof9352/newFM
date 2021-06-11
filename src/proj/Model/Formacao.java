package proj;

public class Formacao {
    private int formacao;
    private int numeroDefesas;
    private int numeroLaterais;
    private int numeroMedios;
    private int numeroAvancados;

    public Formacao(int formacao){
        this.formacao = formacao;
        switch(formacao){
            case 0: //4-3-3 - default
                this.numeroDefesas = 2;
                this.numeroLaterais = 2;
                this.numeroMedios = 3;
                this.numeroAvancados = 3;
                break;
            case 1: //4-4-2
                this.numeroDefesas = 2;
                this.numeroLaterais = 2;
                this.numeroMedios = 4;
                this.numeroAvancados = 2;
                break;
            case 2: //3-5-2
                this.numeroDefesas = 3;
                this.numeroLaterais = 0;
                this.numeroMedios = 5;
                this.numeroAvancados = 2;
                break;
            case 3: //5-3-2
                this.numeroDefesas = 3;
                this.numeroLaterais = 2;
                this.numeroMedios = 3;
                this.numeroAvancados = 2;
                break;
        }
    }

    public int getNumeroDefesas() {
        return numeroDefesas;
    }

    public void setNumeroDefesas(int numeroDefesas) {
        this.numeroDefesas = numeroDefesas;
    }

    public int getNumeroLaterais() {
        return numeroLaterais;
    }

    public void setNumeroLaterais(int numeroLaterais) {
        this.numeroLaterais = numeroLaterais;
    }

    public int getNumeroMedios() {
        return numeroMedios;
    }

    public void setNumeroMedios(int numeroMedios) {
        this.numeroMedios = numeroMedios;
    }

    public int getNumeroAvancados() {
        return numeroAvancados;
    }

    public void setNumeroAvancados(int numeroAvancados) {
        this.numeroAvancados = numeroAvancados;
    }
}
