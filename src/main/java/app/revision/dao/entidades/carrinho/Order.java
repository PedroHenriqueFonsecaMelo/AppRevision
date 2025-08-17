package app.revision.dao.entidades.carrinho;

import java.util.HashSet;
import java.util.Set;

import app.revision.dao.entidades.usuario.Cartao;
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

    @OneToMany(mappedBy = "order_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderDetails> detalhes = new HashSet<>();

    @OneToMany(mappedBy = "order_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderCartoes> pagamentos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private double valorTotal;

    @Column(length = 100, nullable = false)
    private String status;

    @Column(length = 255)
    private String enderecoEntrega;

    public Order() {
    }

    public Order(Cliente cliente, double valorTotal, String status, String enderecoEntrega) {
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.status = status;
        this.enderecoEntrega = enderecoEntrega;
    }

    public void addDetalhe(OrderDetails detalhe) {
        detalhes.add(detalhe);
        detalhe.setOrder(this);
    }

    public void addPagamento(OrderCartoes pagamento) {
        pagamentos.add(pagamento);
        pagamento.setOrder_id(this);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Set<OrderDetails> getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(Set<OrderDetails> detalhes) {
        this.detalhes = detalhes;
    }

    public Set<OrderCartoes> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(Set<OrderCartoes> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public void adicionarPagamento(Cartao cartao, int vezes) {
        OrderCartoes pagamento = new OrderCartoes();
        pagamento.setOrder_id(this); // link com o pedido
        pagamento.setCartao(cartao); // define o cartão
        pagamento.setParcelas(vezes); // define parcelas
        this.pagamentos.add(pagamento); // adiciona ao conjunto do pedido
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", cliente=" + cliente.getId() +
                ", valorTotal=" + valorTotal +
                ", status=" + status +
                ", enderecoEntrega=" + enderecoEntrega + "]";
    }
}
