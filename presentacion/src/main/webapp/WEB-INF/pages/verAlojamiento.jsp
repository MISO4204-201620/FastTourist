<%@include file="header.jsp"%>
		<section class="navbar main-menu">
			<div class="navbar-inner main-menu">				
				<nav id="menu" class="pull-left">
					<ul>
						<li><a href="/presentacion/" class="paginaActiva">Alojamiento</a></li>
						<li><a href="/presentacion/transporte/">Transporte</a>
						<li><a href="./products.html">Alimentacion</a></li>	
						<li><a href="./products.html">Paseos Turisticos</a></li>
						<li><a href="./products.html">Paquetes</a></li>																					
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
								<strong>Categor�a:</strong> <a href="/presentacion/" class="category">${servicio.categoria.nombre}</a><br>
								<strong>Ubicaci�n:</strong> <span>${servicio.alojamiento.direccion}</span><br>
								<strong>Tel�fono:</strong> <span>${servicio.alojamiento.telefono}</span><br>
								<strong>Habitaciones disponibles:</strong> <span>${servicio.alojamiento.habitaciones}</span><br>							
							</address>	
							<strong>Descripcion:</strong>
							<br>
							<span>${servicio.descripcion}</span>
																
						</div>					
					</div>
					<div class="row">
						<div class="span9">
							<ul class="nav nav-tabs" id="myTab">
								<li class="active"><a href="#home">Preguntas</a></li>
								<li class=""><a href="#profile">Calificaci�n</a></li>
							</ul>							 
							<div class="tab-content">
								<div class="tab-pane active" id="home">Sed ut perspiciatis unde omnis iste natus error sit 
								voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo 
								inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. 
								Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, 
								sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.
								 Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur,
								  adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et 
								  dolore magnam aliquam quaerat voluptatem</div>
								<div class="tab-pane" id="profile">
									<table class="table table-striped shop_attributes">	
										<tbody>
											<tr class="">
												<th>Size</th>
												<td>Large, Medium, Small, X-Large</td>
											</tr>		
											<tr class="alt">
												<th>Colour</th>
												<td>Orange, Yellow</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>							
						</div>						
						
					</div>
				</div>
				<div class="span3 col">
					
					<div class="block">	
						<c:forEach var="i" begin="1" end="${promCalificacion}">
						  <span class="icon-star"></span>
						</c:forEach>
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
						<form class="form-inline">
								<p class="buttons center">
								<label>Cantidad:</label>
								<input type="number" class="input-mini" placeholder="1">
								<br/><br/>
								<button class="btn btn-inverse" type="submit">Agregar Al Carrito</button>
								</p>
						</form>
						
					</div>
				</div>
			</div>
		</section>			
<%@include file="footer.jsp"%>