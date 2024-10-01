package app.revision.dao.entidades.usuario;

import java.util.HashSet;
import java.util.Set;

import app.revision.dao.entidades.carrinho.OrderCartoes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_cartao")
public class Cartao {

    @Id
    @GeneratedValue
    @Column(name = "cartao_id")
    private int id;

    @Column
    private int number_cartao;

    @Column(length = 100)
    private String bandeira;

    @Column(length = 100)
    private String nome_cliente;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(length = 100)
    private int cv;

    @Column
    private Boolean preferencial;

    @OneToMany(mappedBy = "cartoes")
    private Set<OrderCartoes> order_cartao = new HashSet<OrderCartoes>();

    public int getNumber_cartao() {
        return number_cartao;
    }

    public void setNumber_cartao(int number_cartao) {
        this.number_cartao = number_cartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public Boolean getPreferencial() {
        return preferencial;
    }

    public void setPreferencial(Boolean preferencial) {
        this.preferencial = preferencial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public Set<OrderCartoes> getOrder_cartao() {
        return order_cartao;
    }

    public void setOrder_cartao(Set<OrderCartoes> order_cartao) {
        this.order_cartao = order_cartao;
    }

}
