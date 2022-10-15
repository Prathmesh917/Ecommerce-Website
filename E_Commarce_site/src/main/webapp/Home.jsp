<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<jsp:include page="Menu.jsp"/>
<a style ="color: white">! </a>
<c:if test="${LogOutSuccess!=null}">
<p style ="color: green; text-align: center;"> <c:out value="${LogOutSuccess}"/></p>
</c:if>
 <div class="banner-wrapper bg-light">
        <div id="index_banner" class="banner-vertical-center-index container-fluid pt-3">
        <div class="py-3 row d-flex align-items-center">
                            <div class="banner-content col-lg-8 col-8 offset-2 m-lg-auto text-left py-3 pb-3">
                                <h1 class="banner-heading h1 text-secondary display-3 mb-0 pb-5 mx-0 px-0 light-300 typo-space-line">
                                    ONLINE <br><strong>SHOPPING</strong> 
                              </h1>
                              <p class="banner-body text-muted py-3 mx-0 px-0">
                                    Welcoming you to finest online shopping web-site where you can experience best service for best products
                                    </p>
                              </div>
                               <%String username =(String) session.getAttribute("un");
                      String userType=(String) session.getAttribute("userType");
          if(username==null){%>
                              <div class="banner-content col-lg-8 col-8 offset-2 m-lg-auto text-left py-3 pb-3">
                                
                                <a class="banner-button btn rounded-pill btn-outline-primary btn-lg px-4" href="Register.jsp" role="button">Get Started</a>
                                <a class="banner-button btn rounded-pill btn-outline-primary btn-lg px-4" href="Login.jsp" role="button">Login</a>
                            </div>
                            <%} %>
                        </div>
        </div>
        </div>
<center>
<b><h2>Shop your Tech </h2></b>
<a style ="color: white">! </a>
<a style ="color: white">! </a>
</center>

		 <section class="container overflow-hidden py-5">
		 <div class="row gx-5 gx-sm-3 gx-lg-5 gy-lg-5 gy-3 pb-3 projects">
             <div class="col-xl-3 col-md-4 col-sm-6 project ui branding">
                <a href="product?operation=mobile" class="service-work card border-0 text-white shadow-sm overflow-hidden mx-5 m-sm-0">
                    <img class="service card-img" src="./assets/img/services-02.jpg" alt="Card image">
                    <div class="service-work-vertical card-img-overlay d-flex align-items-end">
                        <div class="service-work-content text-left text-light">
                            <span class="btn btn-outline-light rounded-pill mb-lg-3 px-lg-4 light-300">SmartPhones !!!</span>
                            <p class="card-text">Check out your best mobile here </p>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-xl-3 col-md-4 col-sm-6 project ui graphic">
                <a href="product?operation=Tablet" class="service-work card border-0 text-white shadow-sm overflow-hidden mx-5 m-sm-0">
                    <img class="card-img" src="./assets/img/our-work-04.jpg" alt="Card image">
                    <div class="service-work-vertical card-img-overlay d-flex align-items-end">
                        <div class="service-work-content text-left text-light">
                            <span class="btn btn-outline-light rounded-pill mb-lg-3 px-lg-4 light-300">Tablets</span>
                            <p class="card-text">Mobiles but BIGGGG ones !!!</p>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-xl-3 col-md-4 col-sm-6 project ui graphic">
                <a href="product?operation=Laptop" class="service-work card border-0 text-white shadow-sm overflow-hidden mx-5 m-sm-0">
                    <img class="card-img" src="./assets/img/services-06.jpg" alt="Card image">
                    <div class="service-work-vertical card-img-overlay d-flex align-items-end">
                        <div class="service-work-content text-left text-light">
                            <span class="btn btn-outline-light rounded-pill mb-lg-3 px-lg-4 light-300">Laptop</span>
                            <p class="card-text">Laptops </p>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-xl-3 col-md-4 col-sm-6 project ui graphic">
                <a href="product?operation=Watch" class="service-work card border-0 text-white shadow-sm overflow-hidden mx-5 m-sm-0">
                    <img class="card-img" src="./assets/img/recent-work-04.jpg" alt="Card image">
                    <div class="service-work-vertical card-img-overlay d-flex align-items-end">
                        <div class="service-work-content text-left text-light">
                            <span class="btn btn-outline-light rounded-pill mb-lg-3 px-lg-4 light-300">SmartWatch</span>
                            <p class="card-text">Keep check on your health by wrist</p>
                        </div>
                    </div>
                </a>
            </div>
            </div>
       </section>
<a style ="color: white">! </a>
<jsp:include page="Footer.jsp"/>
</body>
</html>