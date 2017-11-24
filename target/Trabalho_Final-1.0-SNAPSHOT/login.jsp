<%-- 
    Document   : login
    Created on : Dec 3, 2016, 3:23:24 PM
    Author     : aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form name="loginform" action="" method="post">
            <table align="left" border="0" cellspacing="0" cellpadding="3">
                <tr>
                    <td>Nome: </td>
                    <td><input type="text" name="username" maxlength="30"></td>
                </tr>
                <tr>
                    <td>Senha: </td>
                    <td><input type="password" name="password" maxlength="30"></td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                        <input type="submit" name="submit" value="Login">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="left">
                        <p>admin 12345 -> Administrador pode editar todos os livros.</p>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="left">
                        <p>Grosbilda guest -> Cliente pode comprar livros</p>
                    </td>
                </tr>
                 <tr>
                    <td colspan="2" align="left">
                        <p>Estrobilobaldo guest -> Cliente pode comprar livros</p>
                    </td>
                </tr>
                               
            </table>
        </form>
        
        
        
    </body>
</html>
