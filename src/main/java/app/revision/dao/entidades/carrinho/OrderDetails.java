package app.revision.dao.entidades.carrinho;

import app.revision.dao.entidades.produto.Livro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_order_details")
public class OrderDetails {

    @Id
    @GeneratedValue
    @Column(name = "order_details_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order_id;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro_details;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private double precoUnitario;

    public OrderDetails() {
    }

    public OrderDetails(Livro livro, int quantidade, double precoUnitario) {
        this.livro_details = livro;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder_id() {
        return order_id;
    }

    public void setOrder(Order order) {
        this.order_id = order;
    }

    public Livro getLivro_details() {
        return livro_details;
    }

    public void setLivro_details(Livro livro) {
        this.livro_details = livro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getSubtotal() {
        return quantidade * precoUnitario;
    }

    @Override
    public String toString() {
        return "OrderDetails [id=" + id +
                ", livro=" + (livro_details != null ? livro_details.getTitulo() : "null") +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                ", subtotal=" + getSubtotal() + "]";
    }
}
