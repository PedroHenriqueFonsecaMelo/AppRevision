package app.revision.entidades.produto;

import java.util.HashSet;
import java.util.Set;

import app.revision.entidades.carrinho.OrderDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_livro")
public class Livro {

    @Id
    @GeneratedValue
    @Column(name = "livro_id")
    private int id;

    @Column(length = 100)
    private String autor;

    @Column
    private int ano;

    @Column(length = 100)
    private String titulo;

    @Column(length = 100)
    private String editora;

    @Column
    private int edicao;

    @Column
    private int isbn;

    @Column
    private int numero_paginas;

    @Column(length = 100)
    private String sinopse;

    @Column
    private double altura;

    @Column
    private double largura;

    @Column(length = 100)
    private String categorias;

    @Column
    private double peso;

    @Column
    private double profundidade;

    @Column
    private double precificacao;

    @Column(length = 100)
    private String barras;

    @Column
    private int quant;

    @OneToMany(mappedBy = "livro_details")
    private Set<OrderDetails> order_details = new HashSet<OrderDetails>();

    public Livro() {
    }

    public Livro(String autor, int ano, String titulo, String editora, int edicao, int isbn, int numero_paginas,
            String sinopse, double altura, double largura, String categorias, double peso, double profundidade,
            double precificacao, String barras, int quant) {
        this.autor = autor;
        this.ano = ano;
        this.titulo = titulo;
        this.editora = editora;
        this.edicao = edicao;
        this.isbn = isbn;
        this.numero_paginas = numero_paginas;
        this.sinopse = sinopse;
        this.altura = altura;
        this.largura = largura;
        this.categorias = categorias;
        this.peso = peso;
        this.profundidade = profundidade;
        this.precificacao = precificacao;
        this.barras = barras;
        this.quant = quant;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getNumero_paginas() {
        return numero_paginas;
    }

    public void setNumero_paginas(int numero_paginas) {
        this.numero_paginas = numero_paginas;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(double profundidade) {
        this.profundidade = profundidade;
    }

    public double getPrecificacao() {
        return precificacao;
    }

    public void setPrecificacao(double precificacao) {
        this.precificacao = precificacao;
    }

    public String getBarras() {
        return barras;
    }

    public void setBarras(String barras) {
        this.barras = barras;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<OrderDetails> getOrder_cetails() {
        return order_details;
    }

    public void setOrder_cetails(Set<OrderDetails> order_cetails) {
        this.order_details = order_cetails;
    }

}
