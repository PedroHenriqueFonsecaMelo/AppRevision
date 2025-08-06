package app.revision.dao.entidades.usuario;

import java.util.HashSet;
import java.util.Set;

import app.revision.dao.entidades.carrinho.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "cliente")
    private Set<Cartao> cartoes = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<Endereco> enderecos = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<Order> orders = new HashSet<>();

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

    public Set<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(Set<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("id=").append(id);
        sb.append(", senha=").append(senha);
        sb.append(", nome=").append(nome);
        sb.append(", datanasc=").append(datanasc);
        sb.append(", gen=").append(gen);
        sb.append(", email=").append(email);
        sb.append(", cartoes=").append(cartoes);
        sb.append(", enderecos=").append(enderecos);
        sb.append(", orders=").append(orders);
        sb.append('}');
        return sb.toString();
    }

}
