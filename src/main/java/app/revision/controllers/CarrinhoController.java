package app.revision.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.revision.dao.entidades.produto.Livro;
import app.revision.dao.entidades.usuario.Cartao;
import app.revision.dao.entidades.usuario.Cliente;
import app.revision.dao.repository.produto.LivroRepository;
import app.revision.dao.repository.usuario.CartaoRepository;
import app.revision.dao.repository.usuario.ClienteRepository;
import app.revision.dto.carrinho.CartServicos;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CartServicos cartServicos;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Adiciona livro ao carrinho
    @PostMapping("/adicionar-livro")
    public String adicionarLivro(@RequestParam int livroId, @RequestParam int quantidade) {
        Livro livro = livroRepository.findById(livroId).orElse(null);
        if (livro == null) return "Livro não encontrado";
        cartServicos.addLivro(livro, quantidade);
        return "Livro adicionado ao carrinho";
    }

    // Adiciona cartão ao carrinho (preferencial ou adicional)
    @PostMapping("/adicionar-cartao")
    public String adicionarCartao(@RequestParam int cartaoId) {
        Cartao cartao = cartaoRepository.findById(cartaoId).orElse(null);
        if (cartao == null) return "Cartão não encontrado";
        cartServicos.addCartao(cartao);
        return "Cartão adicionado para pagamento";
    }

    // Lista livros no carrinho
    @GetMapping("/livros")
    public Map<Livro, Integer> listarLivros() {
        // Exponha um getter em CartServicos se necessário
        return cartServicos.getLivros();
    }

    // Lista cartões selecionados
    @GetMapping("/cartoes")
    public List<Cartao> listarCartoes() {
        return cartServicos.getCartoesSelecionados();
    }

    // Processa o pedido
    @PostMapping("/comprar")
    public String comprar(@RequestParam int clienteId, @RequestParam String endereco, @RequestBody(required = false) Map<String, Double> cupons) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if (cliente == null) return "Cliente não encontrado";
        boolean sucesso = cartServicos.ProcessarPedido(cliente, endereco, cupons != null ? cupons : Map.of());
        return sucesso ? "Pedido realizado com sucesso" : "Carrinho ou cartões vazios";
    }
}