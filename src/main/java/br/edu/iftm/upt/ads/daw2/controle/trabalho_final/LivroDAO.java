/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.upt.ads.daw2.controle.trabalho_final;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Livro;

/**
 *
 * @author aluno
 */
public class LivroDAO {

  private final Connection conexao;

    public LivroDAO(Connection conexao) {
        this.conexao = conexao;
    }

        public void gravar(Livro livro) throws SQLException {
        String insercao = "INSERT INTO livro (nome, autor, nropaginas, preco) VALUES (?, ?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(insercao, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, livro.getNome());
            pstmt.setString(2, livro.getAutor());
            pstmt.setInt(3, livro.getNropaginas());
            pstmt.setDouble(4, livro.getPreco());
            int resultado = pstmt.executeUpdate();
            if (resultado == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    livro.setIdLivro(rs.getInt(1));
                }
                System.out.println("\nInserção bem sucedida.");
            } else {
                System.out.println("A inserção não foi feita corretamente.");
            }
        } catch (Exception e) {
            throw (e);
        }
    }

    public Livro buscar(int idLivro) throws SQLException {
        Livro livro = null;
        String selecao = "SELECT * FROM livro WHERE idLivro = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setInt(1, idLivro);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    livro = new Livro();
                    livro.setIdLivro(rs.getInt(1));
                    livro.setNome(rs.getString(2));
                    livro.setAutor(rs.getString(3));
                    livro.setNropaginas(rs.getInt(4));
                    livro.setPreco(rs.getDouble(5));
                }
            }
        }
        return livro;
    }

    public Livro buscarNome(String nome) throws SQLException {
        Livro livro = null;
        String selecao = "SELECT * FROM livro WHERE nome = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setString(1, nome);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    livro = new Livro();
                    livro.setIdLivro(rs.getInt(1));
                    livro.setNome(rs.getString(2));
                    livro.setAutor(rs.getString(3));
                    livro.setNropaginas(rs.getInt(4));
                    livro.setPreco(rs.getDouble(5));
                }
            }
        }
        return livro;
    }
    
     public List<Livro> buscarPNome(String nome) throws SQLException {
        Livro livro = null;
        List<Livro> livros = new ArrayList<>();
        String selecao = "SELECT * FROM livro WHERE nome LIKE ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setString(1, "%"+ nome +"%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                   livro = new Livro();
                    livro.setIdLivro(rs.getInt(1));
                    livro.setNome(rs.getString(2));
                    livro.setAutor(rs.getString(3));
                    livro.setNropaginas(rs.getInt(4));
                    livro.setPreco(rs.getDouble(5));
                    livros.add(livro);
                }
            }
        }
        return livros;
    }
    
    
    
    public List<Livro> buscarTodos() throws SQLException {
        Livro livro;
        List<Livro> livros = new ArrayList<>();
        String selecao = "SELECT * FROM livro";
        try (Statement stmt = conexao.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(selecao)) {
                while (rs.next()) {
                    livro = new Livro();
                    livro.setIdLivro(rs.getInt(1));
                    livro.setNome(rs.getString(2));
                    livro.setAutor(rs.getString(3));
                    livro.setNropaginas(rs.getInt(4));
                    livro.setPreco(rs.getDouble(5));
                    livros.add(livro);
                }
            }
        }
        return livros;
    }

    public void remover(Livro livro) throws SQLException {
        String remocao = "DELETE FROM livro WHERE idLivro = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(remocao)) {
            pstmt.setInt(1, livro.getIdLivro());
            int remocoes = pstmt.executeUpdate();
            if (remocoes == 1) {
                System.out.println("Remoção efetuada com sucesso.");
            } else {
                System.out.println("Não foi possível efetuar a remoção.");
            }
        }
    }

    public void atualizar(Livro livro) throws SQLException {
        String alteracao = "UPDATE livro SET nome = ?, autor = ?, nropaginas = ?, preco = ? WHERE idLivro = ?;";
        try (PreparedStatement pstmt = conexao.prepareStatement(alteracao)) {
            pstmt.setString(1, livro.getNome());
            pstmt.setString(2, livro.getAutor());
            pstmt.setInt(3, livro.getNropaginas());
            pstmt.setDouble(4, livro.getPreco());
            pstmt.setInt(5, livro.getIdLivro());
            int alteracoes = pstmt.executeUpdate();
            if (alteracoes == 1) {
                System.out.println("\nAlteracao bem sucedida.");
            } else {
                System.out.println("A alteracao não foi feita corretamente.");
            }
        }
    }
    
}
