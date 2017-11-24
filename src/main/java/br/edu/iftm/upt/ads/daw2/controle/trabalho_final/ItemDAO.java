package br.edu.iftm.upt.ads.daw2.controle.trabalho_final;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Item;
import modelo.Pedido;
import modelo.Livro;

public class ItemDAO {

    private final Connection conexao;

    public ItemDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void gravar(Item item, Pedido pedido) throws SQLException {

        String insercao = "INSERT INTO itens (precounitario, quantidade, livro_idLivro, pedido_idPedido) VALUES (?, ?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(insercao, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setDouble(1, item.getPrecoUnitario());
            pstmt.setInt(2, item.getQuantidade());
            pstmt.setLong(3, item.getLivro().getIdLivro());
            pstmt.setLong(4, pedido.getIdPedido());
            int resultado = pstmt.executeUpdate();
            if (resultado == 1) {
                System.out.println("\nInserção bem sucedida. :) ");
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        item.setIdItem(rs.getLong(1));
                    }
                }
            } else {
                System.out.println("A inserção não foi feita corretamente. :( ");
            }
        }

    }

    public void buscarTodos(Pedido pedido) throws SQLException {
        Item item;
        Livro livro;
        List<Item> itens = new ArrayList<>();
        LivroDAO dao = new LivroDAO(conexao);
        String selecao = "SELECT * FROM itens WHERE pedido_codigo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setLong(1, pedido.getIdPedido());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    item = new Item();
                    item.setIdItem(rs.getLong(1));
                    livro = dao.buscar(rs.getInt(2));
                    item.setLivro(livro);
                    item.setQuantidade(rs.getInt(4));
                    itens.add(item);
                }
                pedido.setItens(itens);
            }
        }
    }

}
