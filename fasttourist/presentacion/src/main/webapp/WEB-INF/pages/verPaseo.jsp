<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">
		<nav id="menu" class="pull-left">
			<ul>
				<li><a href="/presentacion/">Alojamiento</a></li>
				<li><a href="/presentacion/transporte/">Transporte</a>
				<li><a href="/presentacion/alimentacion/">Alimentación</a></li>
				<li><a href="/presentacion/paseos/paseos" class="paginaActiva">Paseos Turísticos</a></li>
				<li><a href="/presentacion/paquetes/">Paquetes</a></li>
				<c:if test="${not empty usuarioAutenticado and moduloMensajeria}">
						<li><a href="/presentacion/mensajeria/">
							<span class="icon-envelope"></span> Mensajeria</a></li>
				</c:if>
			</ul>
		</nav>
	</div>
</section>
<section class="header_text sub" style="margin-bottom: 2px !important">
	
		<img class="pageBanner" src="${pageContext.request.contextPath}/resources/themes/images/banner-ecologico.jpg" alt="New products">
	<h4>
		<span>${servicio.nombre}</span>
	</h4>
</section>
<section class="main-content">
	<div class="row">
		<div class="span9">
			<div class="row">
				<div class="span4">
					<a href="${servicio.paseosecologico.fotos}" class="thumbnail"
						data-fancybox-group="group1" title="Vista 1"><img alt=""
						src="${servicio.paseosecologico.fotos}"></a>
					<ul class="thumbnails small">
						<li class="span1"><a href="${servicio.paseosecologico.fotos}"
							class="thumbnail" data-fancybox-group="group1" title="Vista 2"><img
								src="${servicio.paseosecologico.fotos}" alt=""></a></li>
						<li class="span1"><a href="${servicio.rutaGaleria}"
							class="thumbnail" data-fancybox-group="group1" title="Vista 3"><img
								src="${servicio.rutaGaleria}" alt=""></a></li>
						<li class="span1"><a href="${servicio.rutaGaleria}"
							class="thumbnail" data-fancybox-group="group1" title="Vista 4"><img
								src="${servicio.rutaGaleria}" alt=""></a></li>
						<li class="span1"><a href="${servicio.rutaGaleria}"
							class="thumbnail" data-fancybox-group="group1" title="Vista 5"><img
								src="${servicio.rutaGaleria}" alt=""></a></li>
					</ul>
				</div>
				<div class="span5">

					<address>
						<strong>Nombre:</strong> ${servicio.paseosecologico.nombre}</span> <br>
						<strong>Lugar:</strong> ${servicio.paseosecologico.lugar}</span><br>
						<strong>Duración:</strong> <span>${servicio.paseosecologico.duracion}h</span><br>
						<strong>Requerimientos:</strong> <span>${servicio.paseosecologico.requerimientos}</span><br>
					</address>
					<strong>Descripcion:</strong> <br> <span>${servicio.paseosecologico.descripcion}</span>

				</div>
			</div>
			<%@include file="moduloPreguntas.jsp"%>
			</div>
			<div class="span3 col">

				<div class="block">
					<c:forEach var="i" begin="1" end="${promCalificacion}">
						<span class="icon-star"></span>
					</c:forEach>
					<br />
					<h4 class="title">
						Precio:<strong> $${servicio.precio}</strong>
					</h4>
					<h4 class="title">
						Descuento:<strong> $${servicio.descuento}</strong>
					</h4>
					Otros Servicios:
					<div class="row feature_box" style="margin: 0 0 0 0">
						<c:if test="${servicio.alojamiento.wifi}">
							<img
								src="${pageContext.request.contextPath}/resources/themes/images/wifi.png"
								title="Internet Gratis" width="16px"
								style="padding: 15px !important;" />
						</c:if>
						<c:if test="${servicio.alojamiento.piscina}">
							<img
								src="${pageContext.request.contextPath}/resources/themes/images/pool.png"
								title="Piscina" width="16px" style="padding: 15px !important;" />
						</c:if>
						<c:if test="${servicio.alojamiento.aire_acondicionado}">
							<img
								src="${pageContext.request.contextPath}/resources/themes/images/winter.png"
								title="Aire Acondicionado" width="16px"
								style="padding: 15px !important" />
						</c:if>
					</div>
					<br />
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