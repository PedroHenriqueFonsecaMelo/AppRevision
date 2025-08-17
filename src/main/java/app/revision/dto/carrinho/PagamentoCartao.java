package app.revision.dto.carrinho;

import app.revision.dao.entidades.usuario.Cartao;

public class PagamentoCartao {
    private Cartao cartao;
    private double valor;

    public PagamentoCartao(Cartao cartao, double valor) {
        this.cartao = cartao;
        this.valor = valor;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
