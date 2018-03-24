<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:url value="/" var="cssCtx"/>	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${cssCtx }resources/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${cssCtx }resources/css/bootstrap-theme.min.css" />
	<title>Casa do Código - Aulas Alura</title>
	
	<style type="text/css">
        body{
            <!-- padding-top: 5px; -->
            padding: 5px 1px; 
        }
    </style>
	
</head>
<body>
	<nav class="navbar navbar-inverse">
	  <div class="container">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="${s:mvcUrl('HC#index').build()}">Casa do Código</a>
	    </div>
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li><a href="${s:mvcUrl('PC#listar').build() }">Lista de Produtos</a></li>
	        <li><a href="${s:mvcUrl('PC#produtosRequisicao').build() }">Cadastro de Produtos</a></li>
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
			<li><a href="#"><security:authentication property="principal.username"/></a></li>
		  </ul>
	    </div><!-- /.navbar-collapse -->
	  </div>
	</nav>	
	
	<div class="container">
		<h1>Lista de Produtos</h1>
		<div>${retorno }</div>
		<div>${falha }</div>
		<table class="table table-bordered table-striped table-hover">
			<tr>
			<th>Título</th>
			<th>Descrição</th>
			<th>Páginas</th>
			</tr>
			<c:forEach items="${produtos}" var="produto" varStatus="">
			<tr>
				<td><a href="${s:mvcUrl('PC#find').arg(0,produto.id).build()}">${produto.titulo}</a></td>
				<td>${produto.descricao}</td>
				<td>${produto.paginas}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>