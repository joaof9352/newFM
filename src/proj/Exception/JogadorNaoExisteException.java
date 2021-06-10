package proj.Exception;

import proj.Jogador;

public class JogadorNaoExisteException extends Exception{
    public JogadorNaoExisteException(){
        super();
    }

    public JogadorNaoExisteException(String msg){
        super(msg);
    }
}
