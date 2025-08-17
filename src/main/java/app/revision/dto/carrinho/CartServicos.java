package app.revision.dto.carrinho;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import app.revision.dao.entidades.carrinho.Order;
import app.revision.dao.entidades.carrinho.OrderCartoes;
import app.revision.dao.entidades.carrinho.OrderDetails;
import app.revision.dao.entidades.produto.Livro;
import app.revision.dao.entidades.usuario.Cartao;
import app.revision.dao.entidades.usuario.Cliente;
import app.revision.dao.repository.carrinho.OrderRepository;
import app.revision.dao.repository.produto.LivroRepository;
import jakarta.transaction.Transactional;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class CartServicos {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private LivroRepository livroRepository;

    private final Map<Livro, Integer> livros = new HashMap<>();
    private final List<Cartao> cartoes_selecionados = new ArrayList<>();
    private double total = 0.0;
    private double frete = 0.0;

    // Adicionar livro ao carrinho
    public void addLivro(Livro livro, int quantidade) {
        livros.put(livro, livros.getOrDefault(livro, 0) + quantidade);
        recalcularTotais();
    }

    // Remover livro do carrinho
    public void removeLivro(Livro livro) {
        livros.remove(livro);
        recalcularTotais();
    }

    // Adicionar cartão ao carrinho (preferencial ou adicional)
    public void addCartao(Cartao cartao) {
        if (!cartoes_selecionados.contains(cartao)) {
            cartoes_selecionados.add(cartao);
        }
    }

    // Remover cartão do carrinho
    public void removerCartao(Cartao cartao) {
        cartoes_selecionados.remove(cartao);
    }

    private void recalcularTotais() {
        total = livros.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrecificacao() * e.getValue())
                .sum();
        frete = total * 0.05;
    }

    @Transactional
    public boolean ProcessarPedido(Cliente cliente, String enderecoEntrega, Map<String, Double> cupons) {

        if (livros.isEmpty() || cartoes_selecionados.isEmpty()) return false;

        // Criar pedido
        Order order = new Order(cliente, total + frete, "EM PROCESSAMENTO", enderecoEntrega);

        // Criar detalhes do pedido
        for (Map.Entry<Livro, Integer> entry : livros.entrySet()) {
            Livro livro = entry.getKey();
            int quantidade = entry.getValue();

            OrderDetails details = new OrderDetails();
            details.setOrder(order);
            details.setLivro_details(livro);
            details.setQuantidade(quantidade);
            order.getDetalhes().add(details);

            // Atualizar estoque do livro
            livro.setQuant(livro.getQuant() - quantidade);
            livroRepository.save(livro);
        }

        // Aplicar cupons (simples: subtrair do total)
        double desconto = cupons != null ? cupons.values().stream().mapToDouble(Double::doubleValue).sum() : 0.0;
        double totalComDesconto = (total + frete) - desconto;
        order.setValorTotal(totalComDesconto);

        // Criar pagamentos rateados entre cartões
        int n = cartoes_selecionados.size();
        double valorPorCartao = totalComDesconto / n;

        for (Cartao c : cartoes_selecionados) {
            OrderCartoes pagamento = new OrderCartoes();
            pagamento.setOrder_id(order);
            pagamento.setCartao(c);
            pagamento.setParcelas(1);
            pagamento.setValorPago(valorPorCartao);
            order.getPagamentos().add(pagamento);
        }

        // Persistir pedido (cascade salva detalhes e pagamentos)
        orderRepository.save(order);

        // Limpar carrinho
        livros.clear();
        cartoes_selecionados.clear();
        total = 0;
        frete = 0;

        return true;
    }

    // Expor livros do carrinho
    public Map<Livro, Integer> getLivros() {
        return new HashMap<>(livros);
    }

    // Expor cartões selecionados
    public List<Cartao> getCartoesSelecionados() {
        return new ArrayList<>(cartoes_selecionados);
    }
}
