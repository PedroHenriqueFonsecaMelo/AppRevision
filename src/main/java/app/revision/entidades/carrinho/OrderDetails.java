package app.revision.entidades.carrinho;

import app.revision.entidades.produto.Livro;
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
    private Order order;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro_details;

    public OrderDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Livro getLivro() {
        return livro_details;
    }

    public void setLivro(Livro livro) {
        this.livro_details = livro;
    }

}
