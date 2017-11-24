<%-- 
    Document   : index
    Created on : Dec 17, 2016, 3:55:16 PM
    Author     : aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <form action="/Trabalho_Final/Livros/Todos">
            <input type="submit" value="Mostrar Todos" />                   
        </form>
        
        </br>        
        
        <form action="/Trabalho_Final/Livros/Buscar">
            <p>Insira o Id: <input type="text" name="id" value="" required /></p>
            <input type="submit" value="Buscar Livro" />                             
        </form>
            
        </br>        
        
         <form action="/Trabalho_Final/Livros/BuscarNome">
            <p>Insira o Nome: <input type="text" name="nome" value="" required /></p>
            <input type="submit" value="Buscar livro pelo nome" />       
        </form>

        </br>
        
        <form action="/Trabalho_Final/Livros/BuscarPNome">
            <p>Insira um pedaço do Nome: <input type="text" name="nome" value="" required /></p>
            <input type="submit" value="Buscar livro por uma parte do nome" />       
        </form>
        
        </br>

        <shiro:hasRole name="admin">
        <form action="/Trabalho_Final/inserirLivros.html">
            <p><input type="submit" value="Inserir Livro"></p>
        </form>
        </shiro:hasRole>
        
        </br>
        
        <form action="/Trabalho_Final/FazerCompra">
            <input type="hidden" name="idLivro" value="${livro.idLivro}">
            <input type="submit" value="Comprar Livro" />  <br><br>                   
        </form>

        </br>
        
         <form action="/Trabalho_Final/Servlet/Relatorio">
            <input type="submit" value="Gerar Relatorio" />       
        </form>
         
        </br>
        
        <form action="/Trabalho_Final/logout">
            <input type="submit" value="logout" />       
        </form>
    
    </body>
</html>
