package app.revision.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_cliente")
public class Cliente {

    @Id
    @GeneratedValue
    @Column(name = "cliente_id")
    private int id;

    @Column(length = 100)
    private String senha;

    @Column(length = 100)
    private String nome;

    @Column
    private String datanasc;

    @Column(length = 1)
    private String gen;

    @Column(length = 100)
    private String email;

    public Cliente(String senha, String nome, String datanasc, String gen, String email) {
        this.senha = senha;
        this.nome = nome;
        this.datanasc = datanasc;
        this.gen = gen;
        this.email = email;
    }

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
