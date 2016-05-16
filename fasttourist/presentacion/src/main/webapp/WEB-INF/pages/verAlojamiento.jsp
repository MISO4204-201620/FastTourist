<%@include file="header.jsp"%>
		<section class="navbar main-menu">
			<div class="navbar-inner main-menu">				
				<nav id="menu" class="pull-left">
					<ul>
						<li><a href="/presentacion/" class="paginaActiva">Alojamiento</a></li>
						<li><a href="/presentacion/transporte/">Transporte</a>
						<li><a href="/presentacion/alimentacion/">Alimentación</a></li>
						<li><a href="/presentacion/paseos/paseos">Paseos Turísticos</a></li>
						<li><a href="/presentacion/paquetes/">Paquetes</a></li>
						<c:if test="${not empty usuarioAutenticado and moduloMensajeria}">
						<li><a href="/presentacion/mensajeria/">
							<span class="icon-envelope"></span> Mensajeria</a></li>
						</c:if>	
						<c:if test="${not empty usuarioAutenticado and moduloBusquedas}">
							<li><a href="/presentacion/busquedas/">
									<span class="icon-zoom-in"></span> Mis busquedas
							</a></li>
						</c:if>																		
					</ul>
				</nav>
			</div>
		</section>
		<section class="header_text sub" style="margin-bottom:2px !important">
		<img class="pageBanner" src="${pageContext.request.contextPath}/resources/themes/images/pageBanner.png" alt="New products" >
		<h4><span>${servicio.nombre}</span></h4>
		</section>
		<section class="main-content">				
			<div class="row">						
				<div class="span9">
					<div class="row">
						<div class="span4">
							<a href="${servicio.rutaGaleria}" class="thumbnail" data-fancybox-group="group1" title="Vista 1"><img alt="" src="${servicio.rutaGaleria}"></a>												
							<ul class="thumbnails small">								
								<li class="span1">
									<a href="${servicio.rutaGaleria}" class="thumbnail" data-fancybox-group="group1" title="Vista 2"><img src="${servicio.rutaGaleria}" alt=""></a>
								</li>								
								<li class="span1">
									<a href="${servicio.rutaGaleria}" class="thumbnail" data-fancybox-group="group1" title="Vista 3"><img src="${servicio.rutaGaleria}" alt=""></a>
								</li>													
								<li class="span1">
									<a href="${servicio.rutaGaleria}" class="thumbnail" data-fancybox-group="group1" title="Vista 4"><img src="${servicio.rutaGaleria}" alt=""></a>
								</li>
								<li class="span1">
									<a href="${servicio.rutaGaleria}" class="thumbnail" data-fancybox-group="group1" title="Vista 5"><img src="${servicio.rutaGaleria}" alt=""></a>
								</li>
							</ul>
						</div>												
						<div class="span5">
							
							<address>
								<strong>Del</strong> <span style="color:#eb4800"> <fmt:formatDate type="date" value="${servicio.alojamiento.fechaEntrada}"/></span> 	
								<strong>Al</strong> <span style="color:#eb4800"> <fmt:formatDate type="date" value="${servicio.alojamiento.fechaSalida}"/></span><br>
								<strong>Hotel:</strong> <span>${servicio.alojamiento.nombre}</span><br>
								<strong>Ciudad:</strong> <span>${servicio.alojamiento.ciudad}</span><br>
								<strong>Proveedor:</strong> <span>${servicio.usuario.nombre} ${servicio.usuario.apellido}</span><br>
								<strong>Categoría:</strong> <a href="/presentacion/" class="category">${servicio.categoria.nombre}</a><br>
								<strong>Ubicación:</strong> <span>${servicio.alojamiento.direccion}</span><br>
								<strong>Teléfono:</strong> <span>${servicio.alojamiento.telefono}</span><br>
								<strong>Habitaciones disponibles:</strong> <span>${servicio.alojamiento.habitaciones}</span><br>							
							</address>	
							<strong>Descripcion:</strong>
							<br>
							<span>${servicio.descripcion}</span>
																
						</div>					
					</div>
					
					
				<%@include file="moduloPreguntas.jsp"%>	
					
					
				</div>
				<div class="span3 col">
					
					<div class="block">
					<c:if test="${moduloCalificaciones == 'true'}">
						<c:forEach var="i" begin="1" end="${promCalificacion}">
						  <span class="icon-star"></span>
						</c:forEach>
					</c:if>
				<br/>
						<h4 class="title">Precio:<strong> $${servicio.precio}</strong></h4>	
						<h4 class="title">Descuento:<strong> $${servicio.descuento}</strong></h4>
						Otros Servicios:
						<div class="row feature_box" style="margin:0 0 0 0">
							<c:if test="${servicio.alojamiento.wifi}">													
								<img src="${pageContext.request.contextPath}/resources/themes/images/wifi.png" title="Internet Gratis" width="16px" style="padding:15px !important;"/>
							</c:if>
							<c:if test="${servicio.alojamiento.piscina}">
								<img src="${pageContext.request.contextPath}/resources/themes/images/pool.png" title="Piscina" width="16px" style="padding:15px !important;"/>				
							</c:if>
							<c:if test="${servicio.alojamiento.aire_acondicionado}">
								<img src="${pageContext.request.contextPath}/resources/themes/images/winter.png" title="Aire Acondicionado"  width="16px" style="padding:15px !important"/>
							</c:if>
						</div>		
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
									<a href="/presentacion/registro"><button class="btn btn-inverse" type="button">Agregar Al Carrito</button></a>
								</c:if>	
								</p>
						</form>
						
					</div>
				</div>
			</div>
		</section>			
<%@include file="footer.jsp"%>