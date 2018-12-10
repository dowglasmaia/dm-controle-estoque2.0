<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<body id="LoginForm">
<div class="container">
<div class="login-form">
<div class="main-div">

    <div class="panel">  
      <h4>Entre com seus dados para logar</h4>
    </div>

    <form id="Login">

        <div class="form-group">
            <input type="text" class="form-control" id="inputCpf" placeholder="Digite seu CPF">
        </div>

        <div class="form-group">
            <input type="password" class="form-control" id="inputPassword" placeholder="Digite sua Senha">
        </div>
        
        <button type="submit" class="btn btn-primary " style="margin-top: 20px">
        Login
        <span class="glyphicon glyphicon-log-in"></span>
      </button>

    </form>
    </div>

</div>
</div>

</body>
</html>