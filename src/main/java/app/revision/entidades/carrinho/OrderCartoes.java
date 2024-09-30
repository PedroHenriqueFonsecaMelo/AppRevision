package app.revision.entidades.carrinho;

import app.revision.entidades.usuario.Cartao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_order_cartoes")
public class OrderCartoes {

    @Id
    @GeneratedValue
    @Column(name = "order_cartoes_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartoes;

    private int vezes;

    public OrderCartoes() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getVezes() {
        return vezes;
    }

    public void setVezes(int vezes) {
        this.vezes = vezes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cartao getCartoes() {
        return cartoes;
    }

    public void setCartoes(Cartao cartoes) {
        this.cartoes = cartoes;
    }

}
