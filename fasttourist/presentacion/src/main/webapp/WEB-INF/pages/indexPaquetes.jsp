<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">				
		<nav id="menu" class="pull-left">
			<ul>
				<li><a href="/presentacion/">Alojamiento</a></li>
				<li><a href="/presentacion/transporte/">Transporte</a>
				<li><a href="/presentacion/alimentacion/">Alimentación</a></li>
				<li><a href="/presentacion/paseos/paseos">Paseos Turísticos</a></li>				
				<li><a href="/presentacion/paquetes/" class="paginaActiva">Paquetes</a></li>
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
<section class="header_text sub">

	<section class="span3 col">				
		<div class="row">
			<div class="block" style="text-align:left !important">					
				<h4 class="title"><span class="text"><strong>Paquetes</strong></span></h4>
				<form action="#" method="post" class="form-stacked">					
					<div class="control-group">
						<label class="control-label">Origen</label>
						<div class="controls">
							<input type="text" name="origen"
								placeholder="¿Desde dónde viajas?" class="input-medium">
						</div>
						<label class="control-label">Destino</label>
						<div class="controls">
							<input type="text" name="destino"
								placeholder="¿Hacia dónde viajas?" class="input-medium">
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
						<label class="control-label">¿Qué tanto quieres comer?</label>
						<select class="input-medium" name=tipoAlimentacion>
							<option value="">Seleccione...</option>
							<c:forEach items="${tiposAlimentacion}" var="tipoAlimentacion">
								<option value="${tipoAlimentacion.idtipoAlimentacion}">${tipoAlimentacion.nombre}</option>
							</c:forEach>
						</select>												        
					</div>
					<div class="control-group">
						<label class="control-label">¿Cómo quieres viajar?</label>
						<select class="input-medium" name=tipoTransporte>
							<option value="">Seleccione...</option>
							<c:forEach items="${tiposTransporte}" var="tipoTransporte">
								<option value="${tipoTransporte.idtipotransporte}">${tipoTransporte.nombre}</option>
							</c:forEach>
						</select>						
					</div>							
					<hr>
					<div class="actions"><input tabindex="9" class="btn btn-inverse large" type="submit" value="Buscar"></div>
						
				</form>					
			</div>				
		</div>
	</section>
</section>
<section class="span0 col" style="margin-left:60px">
</section>
<section class="span4 col">
	<div class="row">
		<div class="span8">
			<ul class="thumbnails listing-products">
				<c:forEach items="${paquetes}" var="paquete">
					<li class="span4">
						<div class="product-box">
							<span class="sale_tag"></span> 
							<a href="/presentacion/paquetes/getPaquete/${paquete.idpaquetes}/">
								<img alt="${paquete.servicios.get(0).nombre}" src="${paquete.servicios.get(0).rutaGaleria}" style="height:230px; max-with:342px">
							</a>
							<br /> 
							<a href="/presentacion/paquetes/getPaquete/${paquete.idpaquetes}/" class="title">${paquete.nombre}</a>
							<p><b>Servicios</b></p>				
							<c:forEach items="${paquete.servicios}" var="servicio">
								<p class="category">${servicio.categoria.nombre}</p>
								<p>${servicio.nombre}</p>
							</c:forEach>							
							<p class="buttons center">	
								<a href="/presentacion/paquetes/getPaquete/${paquete.idpaquetes}/">			
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
<section class="main-content">
	<div class="row">
		<div class="span12">
		</div>
	</div>
</section>
<%@include file="footer.jsp"%>			