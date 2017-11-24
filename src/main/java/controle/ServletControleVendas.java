package controle;

import br.edu.iftm.upt.ads.daw2.controle.trabalho_final.DAOFactory;
import br.edu.iftm.upt.ads.daw2.controle.trabalho_final.LivroDAO;
import br.edu.iftm.upt.ads.daw2.controle.trabalho_final.PedidoDAO;
import br.edu.iftm.upt.ads.daw2.controle.trabalho_final.UsuarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CarrinhoCompras;
import modelo.Item;
import modelo.Livro;
import modelo.Pedido;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author Rafael Godoi Orbolato <rafael@iftm.edu.br>
 */
public class ServletControleVendas extends HttpServlet {

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
        String caminho = request.getServletPath();
        if (caminho.equals("/MostrarPedidos")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                PedidoDAO dao = factory.criarPedidoDAO();
                request.setAttribute("pedidos", dao.buscarTodos());
                RequestDispatcher rd = request.getRequestDispatcher("/mostrarPedidos.jsp");
                rd.forward(request, response);
                factory.fecharConexao();
            } catch (SQLException ex) {
                DAOFactory.mostrarSQLException(ex);
            }
        } else if (caminho.equals("/FazerCompra")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                LivroDAO dao = factory.criarLivroDAO();
                request.getSession().setAttribute("livros", dao.buscarTodos());
                RequestDispatcher rd = request.getRequestDispatcher("/efetuarCompra.jsp");
                factory.fecharConexao();
                rd.forward(request, response);

            } catch (SQLException ex) {
                DAOFactory.mostrarSQLException(ex);
            }
        } else if (caminho.equals("/Adicionar")) {
            Livro livro;
            Item item = new Item();
            Pedido pedido = new Pedido();
            List<Item> itens = new ArrayList<>();
            DAOFactory factory = new DAOFactory();

            double valor;
            int codigo = Integer.parseInt(request.getParameter("idLivro"));
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));

            CarrinhoCompras carrinho = (CarrinhoCompras) request.getSession().getAttribute("carrinho");
            if(carrinho == null){
                carrinho = new CarrinhoCompras();
            }
            
            
            try {
                factory.abrirConexao();
                LivroDAO dao = factory.criarLivroDAO();
                livro = dao.buscar(codigo);
                carrinho.adicionarItem(quantidade, livro);
                request.getSession().setAttribute("carrinho", carrinho);
                
                valor = livro.getPreco() * quantidade;
                
                request.getSession().setAttribute("itens", itens);
                request.getSession().setAttribute("valor", valor);
                
                RequestDispatcher rd = request.getRequestDispatcher("/efetuarCompra.jsp");
                rd.forward(request, response);

            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                DAOFactory.mostrarSQLException(ex);
            } finally {
                try {
                    factory.fecharConexao();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão com o BD.");
                    DAOFactory.mostrarSQLException(ex);
                }

            }
        } else if (caminho.equals("/Remover")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                int posicao = Integer.parseInt(request.getParameter("posicao"));
                CarrinhoCompras carrinho = (CarrinhoCompras) request.getSession().getAttribute("carrinho");
                carrinho.removerItem(posicao - 1);
                request.getSession().setAttribute("carrinho", carrinho);
                LivroDAO dao = factory.criarLivroDAO();
                request.setAttribute("livros", dao.buscarTodos());
                RequestDispatcher rd = request.getRequestDispatcher("/efetuarCompra.jsp");
                rd.forward(request, response);
                factory.fecharConexao();
            } catch (SQLException ex) {
                DAOFactory.mostrarSQLException(ex);
            }
        } else if (caminho.equals("/FinalizarCompra")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                CarrinhoCompras carrinho = (CarrinhoCompras) request.getSession().getAttribute("carrinho");
                                                         
                
                Pedido pedido = new Pedido();
                Double valor = Double.parseDouble(request.getParameter("valor"));
                pedido.setValor(valor);
                pedido.setData("2016/12/17");
                pedido.setItens(carrinho.getItens());
                PedidoDAO dao = factory.criarPedidoDAO();
                
                UsuarioDAO daoU = factory.criarUsuarioDAO(); 
                Subject usuario = SecurityUtils.getSubject();
                dao.gravar(pedido, daoU.buscar(usuario.getPrincipal().toString()));
                
                carrinho.esvaziar();
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
                factory.fecharConexao();
            } catch (SQLException ex) {
                DAOFactory.mostrarSQLException(ex);
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
