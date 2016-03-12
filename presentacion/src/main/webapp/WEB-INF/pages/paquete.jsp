<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
	<head>		
		<meta charset="utf-8">
		<title>Bootstrap E-commerce Templates</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
		<!-- bootstrap -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">      
		<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">		
		<link href="themes/css/bootstrappage.css" rel="stylesheet"/>

		<!--[if lt IE 9]>			
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->
		<!-- bootstrap -->
		<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">      
		<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">		
		<link href="${pageContext.request.contextPath}/resources/themes/css/bootstrappage.css" rel="stylesheet"/>
		
		<!-- global styles -->
		<link href="${pageContext.request.contextPath}/resources/themes/css/jquery.fancybox.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/themes/css/main.css" rel="stylesheet"/>

		<!-- scripts -->
		<script src="${pageContext.request.contextPath}/resources/themes/js/jquery-1.7.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>				
		<script src="${pageContext.request.contextPath}/resources/themes/js/superfish.js"></script>	
		<script src="${pageContext.request.contextPath}/resources/themes/js/jquery.scrolltotop.js"></script>
		<script src="${pageContext.request.contextPath}/resources/themes/js/jquery.fancybox.js"></script>
		<!--[if lt IE 9]>			
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->
	</head>
    <body>		
		<div id="top-bar" class="container">
			<div class="row">				
				<div class="span8">
					<div class="account pull-right">
						<ul class="user-menu">				
							<li><a href="#">My Account</a></li>
							<li><a href="cart.html">Your Cart</a></li>
							<li><a href="checkout.html">Checkout</a></li>					
							<li><a href="register.html">Login</a></li>			
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="wrapper" class="container">
			<section class="navbar main-menu">
				<div class="navbar-inner main-menu">				
					<a href="index.html" class="logo pull-left"><img src="themes/images/logo.png" class="site_logo" alt=""></a>
					<nav id="menu" class="pull-right">
						<ul>
							<li><a href="./products.html">Woman</a>					
								<ul>
									<li><a href="./products.html">Lacinia nibh</a></li>									
									<li><a href="./products.html">Eget molestie</a></li>
									<li><a href="./products.html">Varius purus</a></li>									
								</ul>
							</li>															
							<li><a href="./products.html">Man</a></li>			
							<li><a href="./products.html">Sport</a>
								<ul>									
									<li><a href="./products.html">Gifts and Tech</a></li>
									<li><a href="./products.html">Ties and Hats</a></li>
									<li><a href="./products.html">Cold Weather</a></li>
								</ul>
							</li>							
							<li><a href="./products.html">Hangbag</a></li>
							<li><a href="./products.html">Best Seller</a></li>
							<li><a href="./products.html">Top Seller</a></li>
						</ul>
					</nav>
				</div>
			</section>	
			<section class="header_text sub">
			<img class="pageBanner" src="themes/images/pageBanner.png" alt="New products" >
				<h4><span>New products</span></h4>
			</section>
			<section class="main-content">				
				<div class="row">
					<div class="span3 col">						
						<div class="block">
							<h4 class="title"><span class="pull-left"><span class="text">B�SQUEDA</span></span></h4>
							<form method="POST" class="search_form">
								<input type="text" class="input-block-level search-query" Placeholder="eg. T-sirt">
							</form>
						</div>
						<jsp:include page="categoria.jsp"></jsp:include>
						<jsp:include page="proveedor.jsp"></jsp:include>						
						<jsp:include page="ranking.jsp"></jsp:include>												
					</div>						
					<div class="span9">								
						<ul class="thumbnails listing-products">
							<li class="span3">
								<div class="product-box">
									<span class="sale_tag"></span>												
									<a href="product_detail.html"><img alt="" src="themes/images/ladies/9.jpg"></a><br/>
									<a href="product_detail.html" class="title">"{$paq.Nombre}"</a><br/>
									<a href="#" class="category">"{$paq.Descripcion}"</a>
									<p class="price">$341</p>
								</div>
							</li>       
							<li class="span3">
								<div class="product-box">												
									<a href="product_detail.html"><img alt="" src="themes/images/ladies/8.jpg"></a><br/>
									<a href="product_detail.html" class="title">Praesent tempor sem</a><br/>
									<a href="#" class="category">Erat gravida</a>
									<p class="price">$28</p>
								</div>
							</li>
							<li class="span3">
								<div class="product-box">
									<span class="sale_tag"></span>												
									<a href="product_detail.html"><img alt="" src="themes/images/ladies/7.jpg"></a><br/>
									<a href="product_detail.html" class="title">Wuam ultrices rutrum</a><br/>
									<a href="#" class="category">Suspendisse aliquet</a>
									<p class="price">$341</p>
								</div>
							</li>
							<li class="span3">
								<div class="product-box">												
									<span class="sale_tag"></span>
									<a href="product_detail.html"><img alt="" src="themes/images/ladies/6.jpg"></a><br/>
									<a href="product_detail.html" class="title">Praesent tempor sem sodales</a><br/>
									<a href="#" class="category">Nam imperdiet</a>
									<p class="price">$49</p>
								</div>
							</li>
							<li class="span3">
								<div class="product-box">                                        												
									<a href="product_detail.html"><img alt="" src="themes/images/ladies/1.jpg"></a><br/>
									<a href="product_detail.html" class="title">Fusce id molestie massa</a><br/>
									<a href="#" class="category">Congue diam congue</a>
									<p class="price">$35</p>
								</div>
							</li>       
							<li class="span3">
								<div class="product-box">												
									<a href="product_detail.html"><img alt="" src="themes/images/ladies/2.jpg"></a><br/>
									<a href="product_detail.html" class="title">Tempor sem sodales</a><br/>
									<a href="#" class="category">Gravida placerat</a>
									<p class="price">$61</p>
								</div>
							</li>
							<li class="span3">
								<div class="product-box">												
									<a href="product_detail.html"><img alt="" src="themes/images/ladies/3.jpg"></a><br/>
									<a href="product_detail.html" class="title">Quam ultrices rutrum</a><br/>
									<a href="#" class="category">Orci et nisl iaculis</a>
									<p class="price">$233</p>
								</div>
							</li>
							<li class="span3">
								<div class="product-box">												
									<a href="product_detail.html"><img alt="" src="themes/images/ladies/4.jpg"></a><br/>
									<a href="product_detail.html" class="title">Tempor sem sodales</a><br/>
									<a href="#" class="category">Urna nec lectus mollis</a>
									<p class="price">$134</p>
								</div>
							</li>
							<li class="span3">
								<div class="product-box">												
									<a href="product_detail.html"><img alt="" src="themes/images/ladies/5.jpg"></a><br/>
									<a href="product_detail.html" class="title">Luctus quam ultrices</a><br/>
									<a href="#" class="category">Suspendisse aliquet</a>
									<p class="price">$261</p>
								</div>
							</li>
						</ul>								
						<hr>
						<div class="pagination pagination-small pagination-centered">
							<ul>
								<li><a href="#">Prev</a></li>
								<li class="active"><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">Next</a></li>
							</ul>
						</div>
					</div>
					
				</div>
			</section>
			<section id="footer-bar">
				<div class="row">
					<div class="span3">
						<h4>Navigation</h4>
						<ul class="nav">
							<li><a href="./index.html">Homepage</a></li>  
							<li><a href="./about.html">About Us</a></li>
							<li><a href="./contact.html">Contac Us</a></li>
							<li><a href="./cart.html">Your Cart</a></li>
							<li><a href="./register.html">Login</a></li>							
						</ul>					
					</div>
					<div class="span4">
						<h4>My Account</h4>
						<ul class="nav">
							<li><a href="#">My Account</a></li>
							<li><a href="#">Order History</a></li>
							<li><a href="#">Wish List</a></li>
							<li><a href="#">Newsletter</a></li>
						</ul>
					</div>
					<div class="span5">
						<p class="logo"><img src="themes/images/logo.png" class="site_logo" alt=""></p>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. the  Lorem Ipsum has been the industry's standard dummy text ever since the you.</p>
						<br/>
						<span class="social_icons">
							<a class="facebook" href="#">Facebook</a>
							<a class="twitter" href="#">Twitter</a>
							<a class="skype" href="#">Skype</a>
							<a class="vimeo" href="#">Vimeo</a>
						</span>
					</div>					
				</div>	
			</section>
			<section id="copyright">
				<span>Copyright 2013 bootstrappage template  All right reserved.</span>
			</section>
		</div>
		<script src="themes/js/common.js"></script>	
    </body>
</html>