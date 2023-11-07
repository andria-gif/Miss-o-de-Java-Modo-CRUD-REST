package excepiton;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RespostaErro {
    private String messagem;
    private int code;
    private String status;
    private List<PresonalizacaoErro> listaerros;

    public RespostaErro() {
        this.listaerros = new ArrayList<>();
    }
}
