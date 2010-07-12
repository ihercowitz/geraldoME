/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pacientes.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;


/**
 *
 * @author igor
 */
public class OpcoesUI {
    private final String title;
    
    private final CommandListener listener;
    private String text;
    private Command cmdVoltar;

    public OpcoesUI(String title, CommandListener listener) {
        this.title = title;
        this.listener = listener;
    }

    public void setTexto(String text) {
        this.text = text;
    }

    public Form getForm() {
        Form f = new Form(title);

        cmdVoltar = new Command("Voltar",Command.BACK,1);

        StringItem strOpcoes = new StringItem(title,text);
        
        f.append(strOpcoes);

        f.addCommand(cmdVoltar);
        f.setCommandListener(listener);
        return f;
    }

    public Command getCommandVoltar() {
        return cmdVoltar;
    }

}
