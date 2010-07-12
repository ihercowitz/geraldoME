package pacientes.ui;


import java.util.Vector;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import pacientes.dto.PacienteDTO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author igor
 */
public class ResultadoUI {
    private final String title;
    private final CommandListener listener;
    private ChoiceGroup itemExibicao;

    private StringItem strNome;
    private StringItem strIdade;
    private StringItem strCpf;
    private Command cmdVoltar;
    private Command cmdMais;
    private Form f;
    private PacienteDTO resultado;

    public ResultadoUI(String title, CommandListener listener) {
        this.title = title;
        this.listener = listener;
    }

    public void setResultadoDTO(Object resultado) {
        this.resultado = (PacienteDTO)resultado;
    }

    private void fillForm() {
        f  = new Form(title);

        cmdVoltar = new Command("Voltar",Command.BACK,1);
        cmdMais = new Command("Mais",Command.SCREEN,0);

        strNome = new StringItem("Nome:", resultado.getNome());

        strIdade = new StringItem("Idade:",resultado.getIdade());

        strCpf = new StringItem("CPF:", resultado.getCpf());

        itemExibicao = new ChoiceGroup("Opções:", Choice.EXCLUSIVE);

        itemExibicao.append("Prontuarios", null);
        itemExibicao.append("Remedios", null);
        itemExibicao.append("Consultas", null);

        f.append(strNome);
        f.append(strIdade);
        f.append(strCpf);

        f.append(itemExibicao);

        f.addCommand(cmdMais);
        f.addCommand(cmdVoltar);

        f.setCommandListener(listener);

    }

    public Form getForm() {
        if (f == null)
            fillForm();
        
        return f;
    }

    public int getSelectedItem() {
        return itemExibicao.getSelectedIndex();
    }

    public Command getCommandMais() {
        return cmdMais;
    }

    public Command getCommandVoltar() {
        return cmdVoltar;
    }


}
