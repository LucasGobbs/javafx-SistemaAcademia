/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.model;

/**
 *
 * @author llcos_000
 */
import java.io.Serializable;
import java.sql.Date;
public class Cliente implements Serializable {
   private String nome;
   private Date dataNascimento;
   private String cpf;

    /**
     * @return the nome
     */
    public Cliente(){}
    public Cliente(String nome, Date dataNascimento, String cpf){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the dataNascimento
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    @Override
    public String toString() {
        return this.nome;
    }
}
/*
→ ID
→ Nome
→ Data nascimento
→ Cpf


*/
