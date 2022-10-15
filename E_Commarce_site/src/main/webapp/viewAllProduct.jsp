<%@page import="com.OnlineShopping.model.ProductInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.impl.ProductionDaoImpl"%>
<%@page import="com.OnlineShopping.DAO.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Menu.jsp"/>
<a style ="color: white">! </a>
<c:if test="${un==null}">
	<jsp:forward page="Login.jsp"/> 
</c:if>
<p style ="color: green; text-align: center;"> <c:out value="${ProdUpdateSuccess}"/></p>
<p style ="color: green; text-align: center;"> <c:out value="${cartAddedSuccess}"/></p>
<p style="color:green; text-align: center;"><c:out value="${ProdAddSuccess}"/></p>
<p style ="color: red; text-align: center;"> <c:out value="${ProdUpdateFail}"/></p>

<% List<ProductInfo> allProduct = (List<ProductInfo>) request.getAttribute("allProduct"); 
String userType=(String) session.getAttribute("userType");%>

<!-- Start Service -->
    <section class="service-wrapper py-1">

        <div class="service-tag py-2 bg-secondary">
            <div class="col-md-12">
                <ul class="nav d-flex justify-content-center">
                <h2 class="h5 text-center col-12 py-1 semi-bold-600" style ="color :white">Search with our top product categories</h5>
                    <li class="nav-item mx-lg-4">
                        <a class="filter-btn nav-link btn-outline-primary active shadow rounded-pill text-light px-4 light-300" href="product?operation=All" data-filter=".project">All</a>
                    </li>
                    <li class="nav-item mx-lg-4">
                        <a class="filter-btn nav-link btn-outline-primary rounded-pill text-light px-4 light-300" href="product?operation=mobile" data-filter=".graphic">Mobile</a>
                    </li>
                    <li class="filter-btn nav-item mx-lg-4">
                        <a class="filter-btn nav-link btn-outline-primary rounded-pill text-light px-4 light-300" href="product?operation=Tablet" data-filter=".ui">Tablet</a>
                    </li>
                    <li class="nav-item mx-lg-4">
                        <a class="filter-btn nav-link btn-outline-primary rounded-pill text-light px-4 light-300" href="product?operation=Laptop" data-filter=".branding">Laptop</a>
                    </li>
                </ul>
            </div>
        </div>
         <div class="service-tag py-1 bg-secondary">
            <div class="col-md-12">
                <ul class="nav d-flex justify-content-center">
         <h2 class="h5 text-center col-12 py-1 semi-bold-600" style ="color :white">Search with your preference .....</h5>
          </ul>
            </div>
        </div>
         <div class="service-tag py-4 bg-secondary">
            <div class="col-md-12">
                <ul class="nav d-flex justify-content-center">
                    <form method ="post" action="product">
                    <input type="hidden" name="operation" value="search">
                    <li class="nav-item mx-lg-4">
                        <input type="text" name="Pname" placeholder="Enter Product Name"/>
                         <input type="text" name="Bramd" placeholder="Enter Brand Name"/>                
                    	 <input type="text" name="Category" placeholder="Enter Product Category"/>                   
                    	 <input type="Number" name="Price" required placeholder="Enter max Price"/>                  	 
                    	 <input type="submit" value="Search">
                    </li>
                    </form>
                </ul>
            </div>
        </div>

    </section>

	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">Product ID </th>
				<th scope="col">Product Name</th>
				<th scope="col">Product Brand</th>
				<th scope="col">Product Category</th>
				<th scope="col">Product Price </th>
				<%
				if (userType.equalsIgnoreCase("admin")) {
				%>
				<th scope="col">Edit</th>
				<th scope="col">Delete</th>
				<%}else {%>
				<th scope="col">Action</th>
				<%} %>
			</tr>
		</thead>
		<tbody>
		<%for (ProductInfo each: allProduct) {%>
			<tr scpoe ="row">
				<td><%=each.getProductId() %></td>
				<td><%=each.getProductName() %></td>
				<td><%=each.getProductBrand() %></td>
				<td><%=each.getProductCategory() %></td>
				<td><%=each.getProductPrice() %></td>
				<%if(userType.equalsIgnoreCase("admin")) {%>
				<td><a
					href="product?operation=edit&productid=<%=each.getProductId()%>">Edit</a></td>
				<td><a
					href="product?operation=delete&productid=<%=each.getProductId()%>">Delete</a></td>
				<td></td>
				<%} else {%>
				<td><a
					href="cart?operation=addToCart&productid=<%=each.getProductId()%>">Add to Cart</a></td>
				<td></td>
				<%} %>
			</tr>
			<%} %>
		</tbody>
	</table>
<a style ="color: white">! </a>
<jsp:include page="Footer.jsp"/>
</body>
</html>