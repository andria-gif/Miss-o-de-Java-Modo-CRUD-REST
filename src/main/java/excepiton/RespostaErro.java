package excepiton;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RespostaErro {
    private String mensagem;
    private int codigo;
    private String status;
    private List<PresonalizacaoErro> listaerros;

    public RespostaErro() {
        this.listaerros = new ArrayList<>();
    }
}
