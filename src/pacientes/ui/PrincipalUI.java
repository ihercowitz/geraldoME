package pacientes.ui;


import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author igor
 */
public class PrincipalUI {

    private String title;
    private Command cmdBuscar;
    private final CommandListener listener;
    private Command cmdSair;
    private List l;
    private Command cmdOk;

    public PrincipalUI(String title, CommandListener listener) {
        this.title = title;
        this.listener = listener;
    }

    private void fillList() {
        l = new List("Menu", List.IMPLICIT);

        l.append("Todos Pacientes", null);
        l.append("Busca CPF", null);
        l.append("Busca nome", null);
        l.append("Configurações", null);

        cmdOk = new Command("OK",Command.SCREEN,0);
        cmdSair = new Command("Sair",Command.EXIT,1);

        l.addCommand(cmdOk);
        l.addCommand(cmdSair);
        l.setCommandListener(listener);

    }

    public List getList() {
        if (l == null)
            fillList();
        
        return l;
    }


    public int getSelectedIndex() {
        return l.getSelectedIndex();
    }

    public Command getCommandOk() {
        return this.cmdOk;
    }

    public Command getCommandSair() {
        return this.cmdSair;
    }
}
