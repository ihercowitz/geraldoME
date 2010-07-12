/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacientes.dto;

/**
 *
 * @author igor
 */
public class PacienteDTO {

    private String nome;
    private String idade;
    private String cpf;
    private String pronturario;
    private String remedios;
    private String consultas;

    public String getConsultas() {
        return consultas;
    }

    public String getCpf() {
        return cpf;
    }

    public String getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }

    public String getPronturario() {
        return pronturario;
    }

    public String getRemedios() {
        return remedios;
    }

    public void setConsultas(String consultas) {
        this.consultas = consultas;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPronturario(String pronturario) {
        this.pronturario = pronturario;
    }

    public void setRemedios(String remedios) {
        this.remedios = remedios;
    }
}
