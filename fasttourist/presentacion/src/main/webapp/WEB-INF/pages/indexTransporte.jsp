<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">
		<nav id="menu" class="pull-left">
			<ul>
				<li><a href="/presentacion/">Alojamiento</a></li>
				<li><a href="/presentacion/transporte/" class="paginaActiva">Transporte</a>
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
<section class="header_text sub">

	<section class="span4 col">
		<div class="row">
			<div class="block" style="text-align: left !important">
				<h4 class="title">
					<span class="text"><strong>Transporte</strong></span>
				</h4>

				<form action="#" method="post" class="form-stacked">

					<div class="control-group">
						<table>
							<tr>
								<td width="180px"><label class="control-label">Origen</label></td>
								<td><label class="control-label">Destino</label></td>
							</tr>
							<tr>
								<td><input type="text" name="origen"
									placeholder="¿Desde donde viajas?" class="input-medium"></td>
								<td><input type="text" name="destino"
									placeholder="¿Hacia donde viajas?" class="input-medium"></td>
							</tr>

						</table>
					</div>


					<div class="control-group">


						<label class="radio" for="soloIda"> <input name="account"
							id="soloIda" type="radio" checked="checked" value="register">Solo
							Ida
						</label> <label class="control-label">¿En qué fecha?</label>
						<div class="input-append date" id="dp1" data-date="2016-04-01"
							data-date-format="yyyy-mm-dd">
							<input class="input-small" size="16" type="text"
								name="fechaSalida" placeholder="Salida" readonly=""> <span
								class="add-on"><i class="icon-calendar"></i></span>
						</div>

					</div>
					<div class="control-group">
						<table>
							<tr>
								<td width="180px"><label class="control-label">Tipo
										de Transporte</label></td>
								<td><label class="control-label">Personas</label></td>
							</tr>
							<tr>
								<td><select class="input-medium" name=tipo>
										<option value="">Seleccione..</option>
										<c:forEach items="${tipo}" var="tipo">
											<option value="${tipo.idtipotransporte}">${tipo.nombre}</option>
										</c:forEach>
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


					<div class="control-group">
						<label class="control-label">¿Proveedor?</label>
						<div class="controls">
							<select class="input-large" name="proveedor">
								<option value="">Seleccione..</option>
								<c:forEach items="${proveedores}" var="proveedor">
									<option value="${proveedor.idusuario}">${proveedor.nombre}
										${proveedor.apellido}</option>
								</c:forEach>
							</select>
						</div>

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

	<section class="span4 col">
		<div class="row">
			<div class="span8">

				<table class="table table-striped" style="width:97% !important;margin-left:-15px">
					<thead>
						<tr>
							<th>Agencia</th>
							<th>Fecha</th>
							<th>Origen</th>
							<th>Destino</th>
							<th>Tipo</th>
							<th>Precio</th>
							<th>Ver Detalles</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${servicios}" var="servicio">
							<tr>
								<td><img alt="" src="${servicio.rutaGaleria}" width="80px"
									title="Vista 1"></td>
								<td><fmt:formatDate type="both"
										value="${servicio.transporte.fechaSalida}" /></td>
								<td><span>${servicio.transporte.origen}</span></td>
								<td><span>${servicio.transporte.destino}</span></td>
								<td><span>${servicio.transporte.tipotransporte.nombre}</span></td>
								<td><span>$${servicio.precio}</span></td>
								<td>
									<p class="buttons center">
										<a href="/presentacion/transporte/getTransporte/${servicio.idservicios}/">
											<button class="btn" type="button">Ver Detalle</button>
										</a>
									</p>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</section>
</section>

<section class="main-content">
	<div class="row">
		<div class="span12">
		</div>
	</div>
</section>

<%@include file="footer.jsp"%>
