package app.revision.dao.entidades.carrinho;

import java.util.HashSet;
import java.util.Set;

import app.revision.dao.entidades.usuario.Cliente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_order")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private int id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetails> details = new HashSet<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderCartoes> pagamento = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column
    private double valorTotal;

    @Column(length = 100)
    private String status;

    @Column(length = 100)
    private String enderecoentrega;

    public Order() {
    }

    public Order(Cliente cliente, double valorTotal, String status, String enderecoentrega) {
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.status = status;
        this.enderecoentrega = enderecoentrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnderecoentrega() {
        return enderecoentrega;
    }

    public void setEnderecoentrega(String enderecoentrega) {
        this.enderecoentrega = enderecoentrega;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<OrderDetails> getDetails() {
        return details;
    }

    public void setDetails(Set<OrderDetails> details) {
        this.details = details;
    }

    public Set<OrderCartoes> getPagamento() {
        return pagamento;
    }

    public void setPagamento(Set<OrderCartoes> pagamento) {
        this.pagamento = pagamento;
    }

}
