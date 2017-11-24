<%-- 
    Document   : mostrarLivros
    Created on : Dec 5, 2016, 9:36:53 PM
    Author     : aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciador de Livros</title>
    </head>
    <body>
        <h1>Todos os Livros</h1>

        <c:forEach var="livro" items="${livros}">      

            <form action="/Trabalho_Final/Livros/AlterarSelecionado">
                
                <table width="400">
                    <tr><td> ID:</td> <td> <input type="text" name="idLivro" value="${livro.idLivro}" readonly </td></tr>
                    <tr><td> Nome:</td> <td> <input type="text" name="nome" value="${livro.nome}"</td></tr>
                    <tr><td> Autor: </td><td> <input type="text" name="autor" value="${livro.autor}"</td></tr>
                    <tr><td> Numero de Paginas: </td> <td> <input type="text" name="nropaginas" value="${livro.nropaginas}"</td></tr>
                    <tr><td> Valor: </td> <td> <input type="text" name="preco" value="${livro.preco}"</td></tr>
                </table> 
                
                <shiro:hasRole name="admin">
                    <input type="submit" value="Alterar"/>
                </shiro:hasRole>
            </form>

            </br>
            
            <shiro:hasRole name="admin">
            <form action="/Trabalho_Final/Livros/RemoverSelecionado">
                <input type="hidden" name="id" value="${livro.idLivro}">
                <input type="submit" value="Remover" />  <br><br>                   
            </form>
           </shiro:hasRole>

        </c:forEach>   
        </br>
        
        <form action="/Trabalho_Final/index.jsp">
            <input type="submit" value="Voltar" /> 
        </form>      

    </body>
</html>
