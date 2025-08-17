package app.revision.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.revision.dao.entidades.produto.Livro;
import app.revision.dao.entidades.usuario.Cartao;
import app.revision.dto.carrinho.CartServicos;

@Controller
@RequestMapping("/pedido")
public class OrderController {

    @Autowired
    private CartServicos cartServicos;

    /** Adiciona um livro ao carrinho */
    @PostMapping("/livro/{livroId}")
    public String adicionarLivro(@PathVariable int livroId, @RequestParam int quantidade) {
        Livro livro = new Livro(); // ideal seria buscar no repositório
        livro.setId(livroId);
        cartServicos.addLivro(livro, quantidade);
        return "Livro adicionado ao carrinho!";
    }

    /** Remove um livro do carrinho */
    @DeleteMapping("/livro/{livroId}")
    public String removerLivro(@PathVariable int livroId) {
        Livro livro = new Livro();
        livro.setId(livroId);
        cartServicos.removeLivro(livro);
        return "Livro removido do carrinho!";
    }

    /** Adiciona outro cartão para dividir pagamento */
    @PostMapping("/cartao/adicional/{cartaoId}")
    public String adicionarCartaoAdicional(@PathVariable int cartaoId) {
        Cartao cartao = new Cartao();

        cartao.setId(cartaoId);
        cartServicos.addCartao(cartao);
        return "Cartão adicional adicionado!";
    }

    /** Remove um cartão do carrinho */
    @DeleteMapping("/cartao/{cartaoId}")
    public String removerCartao(@PathVariable int cartaoId) {
        Cartao cartao = new Cartao();
        
        cartao.setId(cartaoId);
        cartServicos.removerCartao(cartao);
        return "Cartão removido do carrinho!";
    }
}
