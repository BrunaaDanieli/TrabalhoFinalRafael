/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aluno
 */
public class Livro implements Serializable{

    private int idLivro;
    private String nome;
    private String autor;
    private int nropaginas;
    private Double preco;

    public Livro(String nome, String autor, int nropaginas, Double valor) {
        this.nome = nome;
        this.autor = autor;
        this.nropaginas = nropaginas;
        this.preco = valor;
    }

    public Livro() {}

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNropaginas() {
        return nropaginas;
    }

    public void setNropaginas(int nropaginas) {
        this.nropaginas = nropaginas;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "LivroDAO{" + "nome=" + nome + ", autor=" + autor + ", nropaginas=" + nropaginas + ", preço=" + preco + '}';
    }

}
