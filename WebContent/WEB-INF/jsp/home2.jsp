<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Perfex360</title>
</head>
<body>
<!-- <header th:replace="fragments/header :: header"></header> -->
  <jsp:include page="/templates/fragments/banner.jsp" />  
    <!-- <aside id="menu" th:replace="fragments/menu :: menu"></aside> -->
    <jsp:include page="/templates/fragments/common_menu.jsp" />  
    
    <main>
        <!-- main content from other pages goes here -->
        <jsp:include page="/templates/fragments/home_body.jsp" />
        <%-- <div th:replace="${bodyTemplate} :: body"></div> --%>
    </main>
    <jsp:include page="/templates/fragments/credits.jsp" />
    <!-- <footer th:replace="fragments/footer :: footer"></footer>  -->
</body>
</html>


