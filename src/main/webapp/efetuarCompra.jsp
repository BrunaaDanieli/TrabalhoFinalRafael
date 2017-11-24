<%-- 
    Document   : efetuarCompra
    Created on : Dec 17, 2016, 2:06:32 PM
    Author     : aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar Carrinho</title>
    </head>
    <body>
        
        <form name="adicionarCarrinho" action="/Trabalho_Final/Adicionar" method="POST">
            
            <label>Quantidade:</label><input type="text" name="quantidade" id="quantidade"><select name="idLivro">
                <c:forEach var="livro" items="${livros}" >
                    <option id="${livro.idLivro}" value="${livro.idLivro}">${livro.nome}</option>
                </c:forEach>
            </select><input type="submit" value="Adicionar no Carrinho">
        
        </form>
        <br>
        
        <label>Itens do Carrinho:</label> <br>
        <c:forEach var="item" items="${carrinho.itens}" >
            ${item.quantidade}
            ${item.livro.nome}
            <br>
        </c:forEach>
        <br>
   
        
    <tr><td> Valor: </td> <td> <input type="text" name="valor" value="${valor}" readonly </td></tr>
    
    <br>
    
        <form name="finalizar" action="/Trabalho_Final/FinalizarCompra">
            <input type="submit" value="Finalizar Pedido">
             <input type="hidden" name="valor" value="${valor}">
        </form>

    </body>
</html>
