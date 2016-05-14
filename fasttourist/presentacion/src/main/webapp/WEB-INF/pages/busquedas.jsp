
<%@include file="header.jsp"%>

<section class="navbar main-menu">
	<div class="navbar-inner main-menu">
		<nav id="menu" class="pull-left">
			<ul>
				<li><a href="/presentacion/">Alojamiento</a></li>
				<li><a href="/presentacion/transporte/">Transporte</a>
				<li><a href="/presentacion/alimentacion/">Alimentacion</a></li>
				<li><a href="/presentacion/paseos/paseos">Paseos Turisticos</a></li>
				<li><a href="/presentacion/paquetes/">Paquetes</a></li>
				<c:if test="${not empty usuarioAutenticado}">
					<li><a href="/presentacion/mensajeria/"> <span
							class="icon-envelope"></span> Mensajeria
					</a></li>
				</c:if>
				<c:if test="${not empty usuarioAutenticado and moduloBusquedas}">
					<li><a href="/presentacion/busquedas/" class="paginaActiva">
							<span class="icon-envelope"></span> Mis busquedas
					</a></li>
				</c:if>
			</ul>
		</nav>
	</div>
</section>


<section class="header_text sub"></section>
<section class="main-content" style="margin-top: -30px">
	<div class="row">
		<section class="span1 col" style="margin-left: 75px">&nbsp;
		</section>

		<div class="span10 col">

			<ul class="thumbnails listing-products">
				<c:forEach items="${busquedas}" var="busqueda">
					<li class="span3">
						<div class="product-box">
							<p class="title">
								<b>Visto el:</b>
							</hp>
							<p class="title" style="color:#eb4800 !important">
								<b><fmt:formatDate value="${busqueda.fecha}"
									pattern="dd-MM-yyyy hh:mm:ss" /></b>
							</p>
							<p class="title">Categoría: ${busqueda.servicio.categoria.nombre}</p>
							<span class="sale_tag"></span> 
							<a href="/presentacion/getAlojamiento/${busqueda.servicio.idservicios}/">
								<img alt="${busqueda.servicio.nombre}"
								src="${busqueda.servicio.rutaGaleria}"
								style="height: 150px; max-with: 200px">
							</a> <br /> 
										
							<p class="title">${busqueda.servicio.nombre}</p>
							
							<p class="buttons center">
								<a
									href="/presentacion/getAlojamiento/${busqueda.servicio.idservicios}/">
									<button class="btn" type="button">Ver Detalle</button>
								</a>
							</p>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</section>
<%@include file="footer.jsp"%>