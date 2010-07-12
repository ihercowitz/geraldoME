/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacientes.net;

import pacientes.Main;
import pacientes.dto.PacienteDTO;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author igor
 */
public class NetService {

    Vector vecPacientes = new Vector();
    boolean isEnd = false;
    private final Main m;
    private String action;

    public NetService(Main m) {
        this.m = m;
    }

    public void setNextAction(String action) {
        this.action = action;
    }

    public void parser(final String url) {
        Thread t = new Thread() {

            public void run() {
                HttpConnection connection = null;
                try {
                    connection = (HttpConnection) Connector.open(url);

                    parse(connection.openInputStream());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (IOException ignored) {
                    }

                }

            }
        };
        t.start();
        
    }

    private void parse(InputStream is) throws Exception {
        Reader reader = new InputStreamReader(is);
        KXmlParser parser = new KXmlParser();
        parser.setInput(reader);

        parser.nextTag();

        parser.require(XmlPullParser.START_TAG, null, "pacientes");



        while (parser.nextTag() != XmlPullParser.END_TAG) {
            parser.require(XmlPullParser.START_TAG, null, "paciente");
            vecPacientes.addElement(parserPaciente(parser));

            

            parser.require(XmlPullParser.END_TAG, null, "paciente");
        }

        parser.require(XmlPullParser.END_TAG, null, "pacientes");

        parser.next();
        parser.require(XmlPullParser.END_DOCUMENT, null, null);

        m.executeAction(action);
    }

    private PacienteDTO parserPaciente(KXmlParser parser) throws Exception {
       PacienteDTO pacienteDTO = new PacienteDTO();
        while (parser.nextTag() != XmlPullParser.END_TAG) {

            String campo = parser.getName();
            String valor = parser.nextText();
            
            if (campo.equals("consultas")) {
                pacienteDTO.setConsultas(valor);
            } else if (campo.equals("cpf")) {
                pacienteDTO.setCpf(valor);
            } else if (campo.equals("idade")) {
                pacienteDTO.setIdade(valor);
            } else if (campo.equals("nome")) {
                pacienteDTO.setNome(valor);
            } else if (campo.equals("prontuario")) {
                pacienteDTO.setPronturario(valor);
            } else if (campo.equals("remedios")) {
                pacienteDTO.setRemedios(valor);
            }
        }
        return pacienteDTO;
    }

    public Vector getResultadoList() {
        System.out.println(vecPacientes.size());
        return vecPacientes;
    }

}
