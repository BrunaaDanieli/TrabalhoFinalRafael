package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido implements Serializable{

    private long idPedido;
    private String dataCompra;
    private double valor;
    private List<Item> itens = new ArrayList<>();

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public String getData() {
        return dataCompra;
    }

    public void setData(String data) {
        this.dataCompra = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
        valor = 0.0;
        for (Item item : itens) {
            valor += item.getQuantidade() * item.getLivro().getPreco();
        }
    }

    public void adicionarItem(int quantidade, Livro livro) {
        itens.add(new Item(quantidade, livro));
        valor += quantidade * livro.getPreco();
    }
    
    public void gerarAPartirDoCarrinhoCompras(CarrinhoCompras carrinho) {
        for (Item item : carrinho.getItens()) {
            itens.add(item);
            valor += item.getQuantidade() * item.getLivro().getPreco();
        }
    }
}
