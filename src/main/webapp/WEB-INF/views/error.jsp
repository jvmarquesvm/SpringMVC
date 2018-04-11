<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Livros de Java, Android, IOs, Mobile e muito mais...">
	<section id="index-section" class="container middle">
		<h3>Produto não encontrado!! </h3>
		<!-- 
        Mensagem: ${excessao.message}
        <c:forEach items="${excessao.stackTrace}" var="erro">
            ${erro}
        </c:forEach>    
        -->
	</section>
</tags:pageTemplate>