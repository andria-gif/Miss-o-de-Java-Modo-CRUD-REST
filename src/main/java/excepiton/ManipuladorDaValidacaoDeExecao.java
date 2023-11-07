package excepiton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ManipuladorDaValidacaoDeExecao {
    @ExceptionHandler(ValidacaoDeExececao.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RespostaErro handleValidationException(ValidacaoDeExececao ve) {

    }
}
