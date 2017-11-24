/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.upt.ads.daw2.controle.trabalho_final;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author aluno
 */
public class ConexaoFactory {

    private static Context initContext;
    private static Context environmentContext;
    private static DataSource ds;

    static {
        try {
            // Obtem nosso contexto de nomes do ambiente (environment naming context)
            initContext = new InitialContext();
            environmentContext = (Context) initContext.lookup("java:comp/env");

            // Busca pela nossa fonte de dados (data source)
            ds = (DataSource) environmentContext.lookup("jdbc/gerenciadorlivros");
        } catch (NamingException ex) {
            Logger.getLogger(ConexaoFactory.class.getName()).log(Level.SEVERE,
                    "Erro buscando o recurso JNDI jdbc/gerenciadorlivros", ex);
        }
    }

    public static Connection getConexao() throws SQLException {
        return ds.getConnection();
    }

    private ConexaoFactory() {
    }
}

