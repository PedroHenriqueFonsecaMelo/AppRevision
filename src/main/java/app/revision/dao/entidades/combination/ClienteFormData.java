package app.revision.dao.entidades.combination;

import lombok.Data;

// Formulario de Login
@Data
public class ClienteFormData {

    // Cliente
    private String nome;
    private String senha;
    private String datanasc;
    private String gen;
    private String email;

    // Endereço
    private String pais;
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;
    private String tipo_residencia;

    // Cartão
    private long number_cartao;
    private String bandeira;
    private int cv;
    private boolean preferencial;

    public ClienteFormData() {
    }

    public ClienteFormData(String bairro, String bandeira, String cep, String cidade, String complemento, int cv,
            String datanasc, String email, String estado, String gen, String nome, long number_cartao, int numero,
            String pais, boolean preferencial, String rua, String senha, String tipo_residencia) {
        this.bairro = bairro;
        this.bandeira = bandeira;
        this.cep = cep;
        this.cidade = cidade;
        this.complemento = complemento;
        this.cv = cv;
        this.datanasc = datanasc;
        this.email = email;
        this.estado = estado;
        this.gen = gen;
        this.nome = nome;
        this.number_cartao = number_cartao;
        this.numero = numero;
        this.pais = pais;
        this.preferencial = preferencial;
        this.rua = rua;
        this.senha = senha;
        this.tipo_residencia = tipo_residencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTipo_residencia() {
        return tipo_residencia;
    }

    public void setTipo_residencia(String tipo_residencia) {
        this.tipo_residencia = tipo_residencia;
    }

    public long getNumber_cartao() {
        return number_cartao;
    }

    public void setNumber_cartao(long number_cartao) {
        this.number_cartao = number_cartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public boolean isPreferencial() {
        return preferencial;
    }

    public void setPreferencial(boolean preferencial) {
        this.preferencial = preferencial;
    }

}
