package app.revision.dao.entidades.carrinho;

import app.revision.dao.entidades.usuario.Cartao;
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
    private Order order_id;

    @ManyToOne
    @JoinColumn(name = "cartao_id", nullable = false)
    private Cartao cartao_id;

    @Column(nullable = false)
    private double valorPago;

    @Column
    private int parcelas;

    public OrderCartoes() {
    }

    public OrderCartoes(Order order, Cartao cartao, double valorPago, int parcelas) {
        this.order_id = order;
        this.cartao_id = cartao;
        this.valorPago = valorPago;
        this.parcelas = parcelas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Order order) {
        this.order_id = order;
    }

    public Cartao getCartao_id() {
        return cartao_id;
    }

    public void setCartao(Cartao cartao) {
        this.cartao_id = cartao;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    @Override
    public String toString() {
        return "OrderCartoes [id=" + id + ", cartao=" + cartao_id.getId() + ", valorPago=" + valorPago + ", parcelas="
                + parcelas + "]";
    }
}
