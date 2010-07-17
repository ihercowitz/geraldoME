/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pacientes.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author igor
 */
public class BuscaUI {
    private final String title;
    private final CommandListener listener;
    private Command cmdBuscar;
    private Command cmdVoltar;
    private TextField txBusca;

    public BuscaUI(String title, CommandListener listener) {
        this.title = title;
        this.listener = listener;
    }

    public Form getForm() {
        Form f = new Form(title);

        cmdBuscar = new Command("Buscar", Command.SCREEN, 0);
        cmdVoltar = new Command("Voltar", Command.BACK, 1);

        System.out.println(title);

        if (title.indexOf("CPF")!=-1) {
           txBusca = new TextField("CPF:", "", 11, TextField.NUMERIC);
        }
        else {
            txBusca = new TextField("Nome:", "", 60,TextField.ANY);
        }

        f.append(txBusca);
        f.addCommand(cmdBuscar);
        f.addCommand(cmdVoltar);
        f.setCommandListener(listener);

        return f;
    }


    public String getTxBusca() {

        return this.txBusca.getString();
    }

    public Command getCommandBuscar() {
        return this.cmdBuscar;
    }

    public Command getCommandVoltar() {
        return this.cmdVoltar;
    }

}
