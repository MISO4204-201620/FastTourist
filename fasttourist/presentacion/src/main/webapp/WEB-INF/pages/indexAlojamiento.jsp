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
			</ul>
		</nav>
	</div>
</section>
<section class="header_text sub">

	<section class="span3 col">
		<div class="row">
			<div class="block" style="text-align: left !important">
				<h4 class="title">
					<span class="text"><strong>Alojamiento</strong></span>
				</h4>

				<form action="#" method="post" class="form-stacked">

					<div class="control-group">
						<label class="control-label">¿Donde quieres ir?</label>
						<div class="controls">
							<input type="text" name="ciudad"
								placeholder="Ingresa una cuidad, hotel o descripción" class="input-xlarge">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">¿Proveedor?</label>
						<div class="controls">
							<select class="input-large" name="proveedor">
								<option value="">Seleccione..</option>
								<c:forEach items="${proveedores}" var="proveedor"> 
								<option value="${proveedor.idusuario}">${proveedor.nombre}  ${proveedor.apellido}</option>
								</c:forEach>
							</select>
						</div>

					</div>
					<div class="control-group">
						<label class="control-label">¿En qué fecha?</label>
						<div class="controls">
							<div class="input-append date" id="dp1" data-date="2016-04-01"
								data-date-format="yyyy-mm-dd">
								<input class="input-small" size="16" type="text" name="fechaEntrada"
									placeholder="Entrada" readonly=""> <span class="add-on"><i
									class="icon-calendar"></i></span>
							</div>
							&nbsp;
							<div class="input-append date" id="dp2" data-date="2016-04-01"
								data-date-format="yyyy-mm-dd">
								<input class="input-small" size="16" type="text" name="fechaSalida"
									placeholder="Salida" readonly=""> <span class="add-on"><i
									class="icon-calendar"></i></span>
							</div>
						</div>
					</div>

					<div class="control-group">
						<table>
							<tr>
								<td width="100px"><label class="control-label">Habitaciones</label></td>
								<td width="100px"><label class="control-label">Personas</label></td>

							</tr>
							<tr>
								<td><select class="input-mini" name="habitaciones">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
								</select></td>
								<td><select class="input-mini" name=personas>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="4">5</option>
										<option value="4">6</option>
								</select></td>
							</tr>
						</table>
					</div>
					<hr>
					
					<div class="actions">
						<p class="buttons center">
							<input type="submit" value="Buscar.." tabindex="9"
								class="btn btn-inverse large">
						</p>
					</div>

				</form>
			</div>
		</div>
	</section>
	<section class="span0 col" style="margin-left:60px">
  	</section>
	<section class="span4 col">
	<div class="row">
		<div class="span8">
			<ul class="thumbnails listing-products">
				<c:forEach items="${servicios}" var="servicio" begin="0" end="1"> 
					<li class="span4">
						<div class="product-box">
							<span class="sale_tag"></span> 
							<a href="/presentacion/getAlojamiento/${servicio.idservicios}/">
								<img alt="${servicio.nombre}" src="${servicio.rutaGaleria}" style="height:230px; max-with:342px">
							</a>
							<br /> 
							<a href="/presentacion/getAlojamiento/${servicio.idservicios}/" class="title">${servicio.nombre}</a>
							<p class="name">${servicio.alojamiento.nombre}</p>
							<p class="title">$${servicio.precio}</p>
							<p><b>Descuento $${servicio.descuento}</b></p>
												
							
							<c:if test="${servicio.alojamiento.wifi}">
								<span class="title" title="Wifi"><img src="${pageContext.request.contextPath}/resources/themes/images/wifi.png" title="Internet Gratis" width="16px"/></span>
							</c:if>
							<c:if test="${servicio.alojamiento.piscina}">
								<span class="title" title="Piscina"><img src="${pageContext.request.contextPath}/resources/themes/images/pool.png" title="Piscina" width="16px"/></span>
							</c:if>
							<c:if test="${servicio.alojamiento.aire_acondicionado}">
							   <span class="title" title="Aire Acondicionado"><img src="${pageContext.request.contextPath}/resources/themes/images/winter.png" title="Aire Acondicionado"  width="16px"/></span>
							</c:if>
							<p class="category">${servicio.alojamiento.ciudad}</p>
							<p class="buttons center">	
								<a href="/presentacion/getAlojamiento/${servicio.idservicios}/">			
									<button class="btn" type="button" >Ver Detalle</button>
								</a>
							</p>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	</section>
</section>

<section class="main-content">
	<div class="row">
		<div class="span12">
			<ul class="thumbnails listing-products">
				<c:forEach items="${servicios}" var="servicio" begin="2">
					<li class="span4">
						<div class="product-box">
							<span class="sale_tag"></span> 
							<a href="/presentacion/getAlojamiento/${servicio.idservicios}/">
								<img alt="${servicio.nombre}" src="${servicio.rutaGaleria}" style="height:230px; max-with:342px">
							</a>
							<br /> 
							<a href="/presentacion/getAlojamiento/${servicio.idservicios}/" class="title">${servicio.nombre}</a> 
							<p class="name">${servicio.alojamiento.nombre}</p>
							<p class="title">$${servicio.precio}</p>
							<p><b>Descuento $${servicio.descuento}</b></p>
							<c:if test="${servicio.alojamiento.wifi}">
								<span class="title" title="Wifi"><img src="${pageContext.request.contextPath}/resources/themes/images/wifi.png" title="Internet Gratis" width="16px"/></span>
							</c:if>
							<c:if test="${servicio.alojamiento.piscina}">
								<span class="title" title="Piscina"><img src="${pageContext.request.contextPath}/resources/themes/images/pool.png" title="Piscina" width="16px"/></span>
							</c:if>
							<c:if test="${servicio.alojamiento.aire_acondicionado}">
							   <span class="title" title="Aire Acondicionado"><img src="${pageContext.request.contextPath}/resources/themes/images/winter.png" title="Aire Acondicionado"  width="16px"/></span>
							</c:if>
							<p class="category">${servicio.alojamiento.ciudad}</p>
							<p class="buttons center">	
								<a href="/presentacion/getAlojamiento/${servicio.idservicios}/">			
									<button class="btn" type="button" >Ver Detalle</button>
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
