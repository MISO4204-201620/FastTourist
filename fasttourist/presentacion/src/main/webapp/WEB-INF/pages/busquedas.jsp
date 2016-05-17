
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
				<c:if test="${not empty usuarioAutenticado and moduloMensajeria}">
					<li><a href="/presentacion/mensajeria/"> <span
							class="icon-envelope"></span> Mensajeria
					</a></li>
				</c:if>
				<c:if test="${not empty usuarioAutenticado and moduloBusquedas}">
					<li><a href="/presentacion/busquedas/" class="paginaActiva">
							<span class="icon-zoom-in"></span> Mis busquedas
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
							<p><b>Visto el</b>
							<span class="title">
								<b><fmt:formatDate value="${busqueda.fecha}"
										pattern="dd-MM-yyyy hh:mm:ss" /></b>
							</span>
							</p>
							<p>Categoría:
								${busqueda.servicio.categoria.nombre}</p>
							<span class="sale_tag"></span>
							
							<p>${busqueda.servicio.nombre}</p>

							<c:if test="${busqueda.servicio.categoria.idcategorias =='1'}">
								<a href="/presentacion/getAlojamiento/${busqueda.servicio.idservicios}/">
									<img alt="${busqueda.servicio.nombre}"
									src="${busqueda.servicio.rutaGaleria}"
									style="height: 100px; max-with: 150px">
								</a>
								<br />
							</c:if>

							<c:if test="${busqueda.servicio.categoria.idcategorias =='2'}">
								<a href="/presentacion/alimentacion/getAlimentacion/${busqueda.servicio.idservicios}/">
									<img alt="${busqueda.servicio.nombre}"
									src="${busqueda.servicio.rutaGaleria}"
									style="height: 100px; max-with: 150px">
								</a>
								<br />
							</c:if>
							
							<c:if test="${busqueda.servicio.categoria.idcategorias =='3'}">
								<a href="/presentacion/transporte/getTransporte/${busqueda.servicio.idservicios}/">
									<img alt="${busqueda.servicio.nombre}"
									src="${busqueda.servicio.rutaGaleria}"
									style="height: 100px; max-with: 150px">
								</a>
								<br />
							</c:if>
							
							<c:if test="${busqueda.servicio.categoria.idcategorias =='4'}">
								<a href="/presentacion/paseos/getServicio/${busqueda.servicio.idservicios}/">
									<img alt="${busqueda.servicio.nombre}"
									src="${busqueda.servicio.rutaGaleria}"
									style="height: 100px; max-with: 150px">
								</a>
								<br />
							</c:if>
							<span class="sale_tag"></span>
							<p class="buttons center">
								<c:if test="${busqueda.servicio.categoria.idcategorias =='1'}">
									<a href="/presentacion/getAlojamiento/${busqueda.servicio.idservicios}/">
										<button class="btn" type="button">Ver Detalle</button>
									</a>
								</c:if>
								<c:if test="${busqueda.servicio.categoria.idcategorias =='2'}">
									<a href="/presentacion/alimentacion/getAlimentacion/${busqueda.servicio.idservicios}/">
										<button class="btn" type="button">Ver Detalle</button>
									</a>
								</c:if>
								<c:if test="${busqueda.servicio.categoria.idcategorias =='3'}">
									<a href="/presentacion/transporte/getTransporte/${busqueda.servicio.idservicios}/">
										<button class="btn" type="button">Ver Detalle</button>
									</a>
								</c:if>
								<c:if test="${busqueda.servicio.categoria.idcategorias =='4'}">
									<a href="/presentacion/paseos/getServicio/${busqueda.servicio.idservicios}/">
										<button class="btn" type="button">Ver Detalle</button>
									</a>
								</c:if>
							</p>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</section>
<%@include file="footer.jsp"%>