package modelo;

import java.io.Serializable;

public class Item implements Serializable{
    private long idItem;
    private int quantidade;
    private double precoUnitario;
    private Livro livro;

    public Item() {
    }

    public Item(long idItem, int quantidade, double precoUnitario, Livro livro) {
        this.idItem = idItem;
        this.quantidade = quantidade;
        this.livro = livro;
        this.precoUnitario = precoUnitario;
    }

    public Item(int quantidade, Livro livro) {
        this.quantidade = quantidade;
        this.livro = livro;
         this.precoUnitario = livro.getPreco();
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    
    
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    
    
    
}
