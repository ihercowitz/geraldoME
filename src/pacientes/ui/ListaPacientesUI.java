/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacientes.ui;

import javax.microedition.lcdui.Displayable;
import pacientes.dto.PacienteDTO;
import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.List;

/**
 *
 * @author igor
 */
public class ListaPacientesUI {

    private final String title;
    private final Vector lista;
    private final CommandListener listener;
    private Command cmdVoltar;
    private Command cmdMostrar;
    private List l;

    public ListaPacientesUI(String title, Vector lista, CommandListener listener) {
        this.title = title;
        this.lista = lista;
        this.listener = listener;
    }

    public List getList() {
        l = new List(title, List.IMPLICIT);

        for (int i = 0; i < lista.size(); i++) {
            PacienteDTO dto = (PacienteDTO) lista.elementAt(i);
            l.append(dto.getNome(), null);
        }

        cmdMostrar = new Command("Mostrar", Command.SCREEN, 0);
        cmdVoltar = new Command("Voltar", Command.BACK, 1);

        l.addCommand(cmdMostrar);
        l.addCommand(cmdVoltar);
        l.setCommandListener(listener);

        return l;
    }

    public Command getCommandVoltar() {
        return cmdVoltar;
    }

    public Command getCommandMostrar() {
        return cmdMostrar;
    }

    public int getSelectedIndex() {
        return l.getSelectedIndex();
    }

}
