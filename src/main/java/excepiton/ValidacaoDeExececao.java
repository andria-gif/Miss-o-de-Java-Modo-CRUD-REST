package excepiton;

import lombok.Data;

@Data
public class ValidacaoDeExececao extends RuntimeException {
    public ValidacaoDeExececao(String mensagem){
        super(mensagem);
    }

}
