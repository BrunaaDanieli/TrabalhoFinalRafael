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
import modelo.Usuario;

/**
 *
 * @author aluno
 */
public class UsuarioDAO {
    
    private final Connection conexao;

    public UsuarioDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public int buscar(String login) throws SQLException {
        Usuario usuario = new Usuario();
        String selecao = "SELECT idUsuario FROM usuario WHERE login = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setString(1, login);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {                    
                    return rs.getInt(1);
                 }
            }
        }
         return -1;
    }
}
