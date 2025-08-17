package app.revision;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import app.revision.dao.entidades.carrinho.Order;
import app.revision.dao.entidades.produto.Livro;
import app.revision.dao.entidades.usuario.Cartao;
import app.revision.dao.entidades.usuario.Cliente;
import app.revision.dao.entidades.usuario.Endereco;
import app.revision.dao.repository.carrinho.OrderRepository;
import app.revision.dao.repository.produto.LivroRepository;
import app.revision.dao.repository.usuario.CartaoRepository;
import app.revision.dao.repository.usuario.ClienteRepository;
import app.revision.dto.carrinho.CartServicos;

@SpringBootTest
@Import(TestSessionConfig.class)
public class CarrinhoIntegrationTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartServicos cartServicos;

    private CartServicos novoCarrinho() {
        CartServicos carrinho = cartServicos;
        try {
            Field orderRepoField = CartServicos.class.getDeclaredField("orderRepository");
            orderRepoField.setAccessible(true);
            orderRepoField.set(carrinho, orderRepository);

            Field livroRepoField = CartServicos.class.getDeclaredField("livroRepository");
            livroRepoField.setAccessible(true);
            livroRepoField.set(carrinho, livroRepository);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            throw new RuntimeException("Erro ao injetar dependências no CartServicos", e);
        }
        return carrinho;
    }

    private Cliente criarClienteComEnderecoECartoes(String nome, String email, String pais, String cep, String estado, String cidade, String rua, int numero, String... numerosCartao) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        clienteRepository.save(cliente);

        Endereco endereco = new Endereco();
        endereco.setPais(pais);
        endereco.setCep(cep);
        endereco.setEstado(estado);
        endereco.setCidade(cidade);
        endereco.setRua(rua);
        endereco.setNumero(numero);
        cliente.adicionarEndereco(endereco);

        for (String num : numerosCartao) {
            Cartao cartao = new Cartao();
            cartao.setNumber_cartao(Integer.parseInt(num));
            cartao.setCliente(cliente);
            cartaoRepository.save(cartao);
            cliente.adicionarCartao(cartao);
        }

        clienteRepository.save(cliente);
        return cliente;
    }

    private Livro criarLivro(String titulo, double preco, int estoque) {
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setPrecificacao(preco);
        livro.setQuant(estoque);
        livroRepository.save(livro);
        return livro;
    }

    private List<Cartao> getCartoesCliente(Cliente cliente) {
        return cartaoRepository.findByCliente(cliente);
    }

    private Endereco getPrimeiroEndereco(Cliente cliente) {
        return (Endereco) cliente.getEnderecos().toArray()[0];
    }

    @Test
    void simulacaoCompraComDoisCartoes() {
        System.out.println("========== INÍCIO DA SIMULAÇÃO ==========");

        // 1. Criar livro
        Livro livro = criarLivro("Livro Java Avançado", 120.0, 10);
        System.out.println("[Livro] Criado: " + livro.getTitulo() + " | Preço: R$" + livro.getPrecificacao() + " | Estoque: " + livro.getQuant());

        // 2. Criar cliente com dois cartões
        Cliente cliente = criarClienteComEnderecoECartoes(
                "Maria Silva", "maria@email.com", "Brasil", "12345-678", "SP", "São Paulo", "Av. Central", 100,
                "111444", "555888"
        );
        System.out.println("[Cliente] Nome: " + cliente.getNome() + " | Email: " + cliente.getEmail());
        List<Cartao> cartoes = getCartoesCliente(cliente);
        System.out.println("[Cartões] Cadastrados:");
        for (Cartao c : cartoes) {
            System.out.println("  - Cartão: " + c.getNumber_cartao());
        }

        // 3. Selecionar endereço
        Endereco endereco = getPrimeiroEndereco(cliente);
        System.out.println("[Endereço] " + endereco.getRua() + ", " + endereco.getNumero() + " - " + endereco.getCidade() + "/" + endereco.getEstado());

        // 4. Simular sessão/carrinho
        CartServicos carrinho = novoCarrinho();
        carrinho.addLivro(livro, 2);
        System.out.println("[Carrinho] Livro adicionado: " + livro.getTitulo() + " | Quantidade: 2");

        // 5. Adicionar dois cartões ao carrinho
        carrinho.addCartao(cartoes.get(0));
        carrinho.addCartao(cartoes.get(1));
        System.out.println("[Carrinho] Cartões usados na compra:");
        System.out.println("  - " + cartoes.get(0).getNumber_cartao());
        System.out.println("  - " + cartoes.get(1).getNumber_cartao());

        // 6. Aplicar cupom
        Map<String, Double> cupons = new HashMap<>();
        cupons.put("DESCONTO20", 20.0);
        System.out.println("[Cupom] Aplicado: DESCONTO20 - R$20,00");

        // 7. Processar pedido
        boolean pedido = carrinho.ProcessarPedido(cliente, endereco.getRua() + ", " + endereco.getNumero(), cupons);
        System.out.println("[Pedido] Processado com sucesso? " + pedido);

        // 8. Verificar persistência e estoque
        Livro livroAtualizado = livroRepository.findById(livro.getId()).orElseThrow();
        System.out.println("[Estoque] Livro após pedido: " + livroAtualizado.getTitulo() + " | Estoque atual: " + livroAtualizado.getQuant());

        // 9. Verificar pedido salvo
        List<Order> pedidos = orderRepository.findByCliente(cliente);
        System.out.println("[Pedidos] Total de pedidos do cliente: " + pedidos.size());
        if (!pedidos.isEmpty()) {
            Order ultimoPedido = pedidos.get(pedidos.size() - 1);
            System.out.println("  - Pedido ID: " + ultimoPedido.getId() + " | Valor total: R$" + ultimoPedido.getValorTotal());
        }

        // 10. Asserts finais
        Assertions.assertTrue(pedido, "Pedido deve ser realizado");
        Assertions.assertEquals(8, livroAtualizado.getQuant(), "Estoque deve ser reduzido em 2");

        System.out.println("========== FIM DA SIMULAÇÃO ==========");
    }
}