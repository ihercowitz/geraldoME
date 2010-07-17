package pacientes;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import pacientes.ui.ResultadoUI;
import pacientes.ui.PrincipalUI;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

import javax.microedition.midlet.*;
import pacientes.dto.PacienteDTO;
import pacientes.net.NetService;
import pacientes.ui.BuscaUI;
import pacientes.ui.ListaPacientesUI;
import pacientes.ui.OpcoesUI;

/**
 * @author igor
 */
public class Main extends MIDlet implements CommandListener {

    private Display display;
    private PrincipalUI principalUI;
    private ResultadoUI resultadoUI;
    private OpcoesUI opcoesUI;
    private ListaPacientesUI listaPacientesUI;
    private Vector vecPacientes;
    private BuscaUI buscaUI;
    private Alert a;

    public void startApp() {
        display = Display.getDisplay(this);

        showPrincipal();

    }

    private void showPrincipal() {
        principalUI = new PrincipalUI("Principal", this);

        display.setCurrent(principalUI.getList());

    }

    private void showResultado(int item) {
        resultadoUI = new ResultadoUI("Resultado", this);
        resultadoUI.setResultadoDTO(vecPacientes.elementAt(item));
        display.setCurrent(resultadoUI.getForm());
    }

    private void showListaPacientes() {

        listaPacientesUI = new ListaPacientesUI("Pacientes", vecPacientes, this);

        display.setCurrent(listaPacientesUI.getList());
    }

    private void showOpcoes(String title, String text) {
        opcoesUI = new OpcoesUI(title, this);

        opcoesUI.setTexto(text);

        display.setCurrent(opcoesUI.getForm());
    }

    public void pauseApp() {
    }

    public void showAlert() {
        a = new Alert("Informação","Obtendo dados. Por favor, aguarde.",null,AlertType.INFO);

        display.setCurrent(a);
    }

    
    public void destroyApp(boolean unconditional) {
    }

    private void net(String nextAction) {
        NetService net = new NetService(this);
        net.setNextAction(nextAction);
        net.parser("http://geraldonrails.heroku.com/pacientes.xml");


        vecPacientes = net.getResultadoList();

    }

    private void net(String nextAction, int selectedIndex, String valor) {
        NetService net = new NetService(this);
        net.setNextAction(nextAction);

        switch (selectedIndex) {
            case 1:
                 net.parser("http://geraldonrails.heroku.com/search_cpf/"+valor+"/xml");
                break;

            case 2:
                net.parser("http://geraldonrails.heroku.com/search_nome/"+valor+"/xml");
                break;
        }
//            net.parser("http://geraldonrails.heroku.com/search_cpf/"+valor+"/xml");

        //if (buscar.equals("nome")) {
        //    System.out.println("http://geraldonrails.heroku.com/search_nome/"+valor+"/xml");
        //    net.parser("http://geraldonrails.heroku.com/search_nome/"+valor+"/xml");
        //}

        vecPacientes = net.getResultadoList();

    }

    private void showBuscaUI(String tipo) {
        buscaUI = new BuscaUI("Busca "+tipo,this);

        display.setCurrent(buscaUI.getForm());
    }

    public void executeAction(String action) {
        if (action.equals("listaPacientes")) {
            showListaPacientes();
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (c == principalUI.getCommandOk()) {
            switch (principalUI.getSelectedIndex()) {
                case 0:
                    net("listaPacientes");
                    break;

                case 1:
                    showBuscaUI("CPF");
                    break;

                case 2:
                    showBuscaUI("Nome");
                    break;

            }

        }

        if (c == principalUI.getCommandSair()) {
            destroyApp(true);
            notifyDestroyed();
        }

        if (buscaUI != null) {
            if (c == buscaUI.getCommandVoltar()) {
                display.setCurrent(principalUI.getList());
            }

            if (c == buscaUI.getCommandBuscar()) {
                net("listaPacientes",principalUI.getSelectedIndex(),buscaUI.getTxBusca());
            }
        }

        if (listaPacientesUI != null) {
            if (c == listaPacientesUI.getCommandVoltar()) {
                display.setCurrent(principalUI.getList());
            }

            if (c == listaPacientesUI.getCommandMostrar()) {
                showResultado(listaPacientesUI.getSelectedIndex());
            }
        }

        if (resultadoUI != null) {
            if (c == resultadoUI.getCommandMais()) {
               PacienteDTO pacienteDTO = (PacienteDTO)vecPacientes.elementAt(listaPacientesUI.getSelectedIndex());

                switch (resultadoUI.getSelectedItem()) {

                    case 0:
                        showOpcoes("Prontuario", pacienteDTO.getPronturario());
                        break;

                    case 1:
                        showOpcoes("Remedios", pacienteDTO.getRemedios());
                        break;

                    case 2:
                        showOpcoes("Consultas", pacienteDTO.getConsultas());
                        break;
                }

            }

            if (c == resultadoUI.getCommandVoltar()) {
                display.setCurrent(listaPacientesUI.getList());
            }

        }

        if (opcoesUI != null) {
            if (c == opcoesUI.getCommandVoltar()) {
                display.setCurrent(resultadoUI.getForm());
            }
        }

    }
}
