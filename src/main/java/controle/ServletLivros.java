/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import br.edu.iftm.upt.ads.daw2.controle.trabalho_final.DAOFactory;
import br.edu.iftm.upt.ads.daw2.controle.trabalho_final.LivroDAO;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Livro;


/**
 *
 * @author aluno
 */
public class ServletLivros extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String caminho = request.getServletPath();
        if (caminho.equals("/Livros/Todos")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                LivroDAO dao = factory.criarLivroDAO();
                List<Livro> livros = dao.buscarTodos();
                factory.fecharConexao();
                request.setAttribute("livros", livros);
                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/mostrarLivros.jsp");
                rd.forward(request, response);
            } catch (SQLException e) {
                DAOFactory.mostrarSQLException(e);
            }
        } else if (caminho.equals("/Livros/Buscar")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                int id = Integer.parseInt(request.getParameter("id"));
                LivroDAO dao = factory.criarLivroDAO();
                Livro livro = dao.buscar(id);
                factory.fecharConexao();

                request.getSession().setAttribute("livro", livro);
                RequestDispatcher rd = null;
                if (livro == null) {
                    String mensagem = "Livro não encontrado :( ";
                    request.setAttribute("mensagem", mensagem);
                    rd = request.getRequestDispatcher("/mensagem.jsp");
                    rd.forward(request, response);
                }
                rd = request.getRequestDispatcher("/mostrarLivros.jsp");
                rd.forward(request, response);
            } catch (SQLException e) {
                DAOFactory.mostrarSQLException(e);
            }
        } else if (caminho.equals("/Livros/Inserir")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                String nome = request.getParameter("nome");
                String autor = request.getParameter("autor");
                Integer nropaginas = Integer.parseInt(request.getParameter("nropaginas"));
                Double valor = Double.parseDouble(request.getParameter("valor"));

                LivroDAO dao = factory.criarLivroDAO();
                Livro livro = new Livro();
                livro.setNome(nome);
                livro.setAutor(autor);
                livro.setNropaginas(nropaginas);
                livro.setPreco(valor);
                dao.gravar(livro);

                factory.fecharConexao();

                String mensagem = "Livro inserido com sucesso :) ";
                request.setAttribute("mensagem", mensagem);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/mensagem.jsp");
                rd.forward(request, response);
            } catch (SQLException e) {
                DAOFactory.mostrarSQLException(e);
            }
        } else if (caminho.equals("/Livros/Remover")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                Livro livro = (Livro) request.getSession().getAttribute("livro");
                LivroDAO dao = factory.criarLivroDAO();
                dao.remover(livro);
                factory.fecharConexao();

                String mensagem = "Livro removido com sucesso :) ";
                request.setAttribute("mensagem", mensagem);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/mensagem.jsp");
                rd.forward(request, response);
            } catch (SQLException e) {
                DAOFactory.mostrarSQLException(e);
            }

        } else if (caminho.equals("/Livros/BuscarNome")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();

                String n = request.getParameter("nome");
                LivroDAO dao = factory.criarLivroDAO();
                Livro livro = dao.buscarNome(n);
                factory.fecharConexao();

                request.getSession().setAttribute("livro", livro);
                RequestDispatcher rd = null;
                if (livro == null) {
                    String mensagem = "Livro não encontrado :(";
                    request.setAttribute("mensagem", mensagem);
                    rd = request.getRequestDispatcher("/mensagem.jsp");
                    rd.forward(request, response);

                }

                rd = request.getRequestDispatcher("/mostrarLivros.jsp");
                rd.forward(request, response);
            } catch (SQLException e) {
                DAOFactory.mostrarSQLException(e);
            }
        } else if (caminho.equals("/Livros/BuscarPNome")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();

                String n = request.getParameter("nome");
                LivroDAO dao = factory.criarLivroDAO();
                List<Livro> livros = dao.buscarPNome(n);
                factory.fecharConexao();

                request.getSession().setAttribute("livros", livros);
                RequestDispatcher rd = null;
                if (livros == null) {
                    String mensagem = "Livro não encontrado :( ";
                    request.setAttribute("mensagem", mensagem);
                    rd = request.getRequestDispatcher("/mensagem.jsp");
                    rd.forward(request, response);

                }

                rd = request.getRequestDispatcher("/mostrarLivros.jsp");
                rd.forward(request, response);
            } catch (SQLException e) {
                DAOFactory.mostrarSQLException(e);
            }
        } else if (caminho.equals("/Livros/RemoverSelecionado")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                int id = Integer.parseInt(request.getParameter("id"));

                LivroDAO dao = factory.criarLivroDAO();
                Livro livro = dao.buscar(id);

                dao.remover(livro);

                factory.fecharConexao();

                String mensagem = "Contato removido com sucesso :) ";
                request.setAttribute("mensagem", mensagem);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/mensagem.jsp");
                rd.forward(request, response);
            } catch (SQLException e) {
                DAOFactory.mostrarSQLException(e);
            }

        } else if (caminho.equals("/Livros/AlterarSelecionado")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                int id = Integer.parseInt(request.getParameter("idLivro"));
                String nome = request.getParameter("nome");
                String autor = request.getParameter("autor");
                Integer nropaginas = Integer.parseInt(request.getParameter("nropaginas"));
                Double preco = Double.parseDouble(request.getParameter("preco"));

                LivroDAO dao = factory.criarLivroDAO();
                Livro livro = new Livro();

                livro.setIdLivro(id);
                livro.setNome(nome);
                livro.setAutor(autor);
                livro.setNropaginas(nropaginas);
                livro.setPreco(preco);

                dao.atualizar(livro);

                factory.fecharConexao();

                String mensagem = "Livro atualizado com sucesso :) ";
                request.setAttribute("mensagem", mensagem);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/mensagem.jsp");
                rd.forward(request, response);
            } catch (SQLException e) {
                DAOFactory.mostrarSQLException(e);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
