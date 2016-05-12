<%@include file="header.jsp"%>
		<section class="navbar main-menu">
			<div class="navbar-inner main-menu">				
				<nav id="menu" class="pull-left">
					<ul>
						<li><a href="/presentacion/">Alojamiento</a></li>
						<li><a href="/presentacion/transporte/">Transporte</a>
						<li><a href="/presentacion/alimentacion/">Alimentacion</a></li>
						<li><a href="/presentacion/paseos/paseos">Paseos Turisticos</a></li>
						<li><a href="/presentacion/paquetes/" class="paginaActiva">Paquetes</a></li>
						<c:if test="${not empty usuarioAutenticado and moduloMensajeria}">
						<li><a href="/presentacion/mensajeria/">
							<span class="icon-envelope"></span> Mensajeria</a></li>
						</c:if>																						
					</ul>
				</nav>
			</div>
		</section>
		<section class="header_text sub" style="margin-bottom:2px !important">
		<img class="pageBanner" src="${pageContext.request.contextPath}/resources/themes/images/pageBanner.png" alt="New products" >
		<h4><span>${paquete.nombre}</span></h4>
		</section>
		<section class="main-content">				
			<div class="row">						
				<div class="span9">
					<div class="row">
						<div class="span4">
							<a href="${paquete.servicios.get(0).rutaGaleria}" class="thumbnail"
							data-fancybox-group="group1" title=""><img alt=""
							src="${paquete.servicios.get(0).rutaGaleria}"></a>
							<c:forEach items="${paquete.servicios}" var="servicio">
								<ul class="thumbnails small">								
									<li class="span1">
										<a href="${servicio.rutaGaleria}" class="thumbnail" 
										data-fancybox-group="group1" title="${servicio.nombre}">
											<img src="${servicio.rutaGaleria}" alt="">
										</a>
									</li>	
								</ul>								
							</c:forEach>
						</div>																		
						<div class="span5">
							<h4 class="title">Servicios</h4>
							<address>
								<c:forEach items="${paquete.servicios}" var="servicio">
									<strong>Categoría:</strong> <a href="/presentacion/" class="category">${servicio.categoria.nombre}</a><br>
									<strong>Proveedor:</strong> <span>${servicios.usuario.nombre} ${servicio.usuario.apellido}</span><br>									
									<strong>Nombre:</strong> <span>${servicio.nombre}</span><br>
									<strong>Precio: $${servicio.precio}</strong><br><br>																
								</c:forEach>														
							</address>												
						</div>						
					</div>					
				</div>
				<div class="span3 col">					
					<div class="block">
						<h4 class="title">Precio:<strong> $${paquete.precio}</strong></h4>							
						<br/>
						<form action="#" method="post" class="form-inline">
								<p class="buttons center">
								<label>Cantidad:</label>
								<input type="number" class="input-mini" placeholder="" name="carrito" max="1" min="1" required="required">
								<br/><br/>
								<c:if test="${not empty usuarioAutenticado}">
									<button class="btn btn-inverse" type="submit">Agregar Al Carrito</button>
								</c:if>	
								<c:if test="${empty usuarioAutenticado}">
									<a href="/presentacion/registro"><button class="btn btn-inverse">Agregar Al Carrito</button></a>
								</c:if>	
								</p>
						</form>						
					</div>
				</div>
			</div>
		</section>			
<%@include file="footer.jsp"%>