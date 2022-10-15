<!DOCTYPE html>
<html lang="en">

<head>
    <title>Online Shopping E-Commerce site</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="apple-touch-icon" href="assets/img/apple-icon.png">
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
    <!-- Load Require CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font CSS -->
    <link href="assets/css/boxicon.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600&display=swap" rel="stylesheet">
    <!-- Load Tempalte CSS -->
    <link rel="stylesheet" href="assets/css/templatemo.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="assets/css/custom.css">
<!--
    
TemplateMo 561 Purple Buzz

https://templatemo.com/tm-561-purple-buzz

-->
</head>

<body>
    <!-- Header -->
    <nav id="main_nav" class="navbar navbar-expand-lg navbar-light bg-white shadow">
        <div class="container d-flex justify-content-between align-items-center">
            <a class="navbar-brand h1" href="Home.jsp">
                <i class='bx bx-buildings bx-sm text-dark'></i>
                <span class="text-dark h4">My</span> <span class="text-primary h4">Shop</span>
            </a>
            <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-toggler-success" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="align-self-center collapse navbar-collapse flex-fill  d-lg-flex justify-content-lg-between" id="navbar-toggler-success">
                <div class="flex-fill mx-xl-3 mb-2">
                    <ul class="nav navbar-nav d-flex justify-content-between mx-xl-5 text-center text-dark">
                      <%String username =(String) session.getAttribute("un");
                      String userType=(String) session.getAttribute("userType");
          if(username==null){%> 
                        <div class="navbar align-self-left d-flex">
                        <li class="nav-item mx-lg-4">
                            <a class="nav-link btn-outline-primary rounded-pill px-3" href="Home.jsp">Home</a>
                        </li>
                         <li class="nav-item mx-lg-4">
                        <a class="filter-btn nav-link btn-outline-primary rounded-pill text-dark px-4 dark-300" href="Register.jsp" data-filter=".project">Register</a>
                    </li>
                    <li class="nav-item mx-lg-4">
                        <a class="filter-btn nav-link btn-outline-primary rounded-pill text-dark px-4 dark-300" href="Login.jsp" data-filter=".graphic">LogIn</a>
                    </li>
                      
                        <% }else if (username!=null && userType.equalsIgnoreCase("customer")){%>
                      
                        <li class="nav-item">
                            <a class="nav-link btn-outline-primary rounded-pill px-3" href="register?operation=viewCustomer">Dash Board</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn-outline-primary rounded-pill px-3" href="register?operation=editCustomer">Update Yourself</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn-outline-primary rounded-pill px-3" href="ChangePassword.jsp">Update Password</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn-outline-primary rounded-pill px-3" href="cart?operation=viewAllCart">Your Cart</a>
                        </li>
                        
                        <%} else if (username!=null && userType.equalsIgnoreCase("admin")){%>
                         
                        <li class="nav-item">
                            <a class="nav-link btn-outline-primary rounded-pill px-3" href="register?operation=viewCustomer">Dash Board</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn-outline-primary rounded-pill px-3" href="addProduct.jsp">Add Product</a>
                        </li>
                        
                        <%} 
                        
                        if(username!=null && (userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("customer")) )
                        {%>
                       
                        <li class="nav-item">
                            <a class="nav-link btn-outline-primary rounded-pill px-3" href="product?operation=viewAllProduct">View All Product</a>
                        </li>
                         <li class="nav-item">
                            <a class="nav-link btn-outline-primary rounded-pill px-3" href="order?operation=viewYourOrer">Orders</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn-outline-primary rounded-pill px-3" href="register?operation=logout">Logout</a>
                        </li>
                       
                        </div>
                        <%}  %>
                        
         <!--                 <li class="nav-item">
                            <a class="nav-link btn-outline-primary rounded-pill px-3" href="pricing.html">Pricing</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn-outline-primary rounded-pill px-3" href="contact.html">Contact</a>
                        </li>
           -->             
                    </ul>
                </div>
                <!-- <div class="navbar align-self-center d-flex">
                    <a class="nav-link" href="#"><i class='bx bx-bell bx-sm bx-tada-hover text-primary'></i></a>
                    <a class="nav-link" href="#"><i class='bx bx-cog bx-sm text-primary'></i></a>
                    <a class="nav-link" href="#"><i class='bx bx-user-circle bx-sm text-primary'></i></a>
                </div> -->
            </div>
        </div>
    </nav>
    <!-- Close Header -->

</body>

</html>