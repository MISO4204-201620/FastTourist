<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">				
		<nav id="menu" class="pull-left">
			<ul>
				<li><a href="/presentacion/">Alojamiento</a></li>
				<li><a href="./products.html">Transporte</a>
				<li><a href="/presentacion/alimentacion" class="paginaActiva">Alimentacion</a></li>	
				<li><a href="./products.html">Paseos Turisticos</a></li>
				<li><a href="./products.html">Paquetes</a></li>																					
			</ul>
		</nav>
	</div>
</section>
<section class="header_text sub">		
	<section class="span4 col">				
		<div class="row">
			<jsp:include page="alimentacion.jsp"></jsp:include>
		</div>
	</section>		
</section>
<section class="main-content">				
	<div class="row">						
		<div class="span9">
			<div class="row">
				<div class="span4">
					<a href="/presentacion/producto/get/1/">Ver Producto</a> 
					<h3><span>${servicio.nombre}</span></h3>
														
				</div>					
			</div>
		</div>
	</div>
</section>
<%@include file="footer.jsp"%>