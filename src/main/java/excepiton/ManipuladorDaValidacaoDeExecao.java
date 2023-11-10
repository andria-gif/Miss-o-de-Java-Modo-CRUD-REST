package excepiton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ManipuladorDaValidacaoDeExecao {
    @ExceptionHandler(ValidacaoDeExececao.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RespostaErro handleValidationException(ValidacaoDeExececao ve) {
        RespostaErro resposta = new RespostaErro();
        resposta.setMessagem("Preencha o campo corretamente!");
        resposta.setCodigo(400);
        resposta.setStatus("Bad Request");

        PresonalizacaoErro erro = new PresonalizacaoErro();
        erro.setMensagem(ve.getMessage());

        return resposta;
    }
}
