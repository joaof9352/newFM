package proj;

public class ControllerJogo {


    Jogo j = new Jogo(Equipa casa, Equipa fora, LocalDate data);

    j.runTotalParaSimulacao(); //Para simular um jogo todo


    /* Para simular Metade, depois subs, depois metade */
    j.runMetadePermiteSubs();
    //substituições ...
    //Se for equipa casa, dá-se o nmr 0, equipa fora, nmr 1
    j.substituicao(int equipa, int nmrSair, int nmrEntrar);
    //final das subs
    j.runMetadePermiteSubs();



        
}
