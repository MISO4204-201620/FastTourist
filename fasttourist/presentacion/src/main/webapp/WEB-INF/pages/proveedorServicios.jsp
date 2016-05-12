<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">
		<nav id="menu" class="pull-left">
			<ul>
				<li><a href="/presentacion/adminProveedor/"
					class="paginaActiva"><span class="icon-home"></span> Servicios</a></li>
				<li><a href="/presentacion/transporte/"><span
						class="question-sign"></span> Respuestas</a>
				<li><a href="/presentacion/alimentacion/"><span
						class="icon-file"></span> Historicos</a></li>
				<li><a href="/presentacion/paseos/paseos"><span
						class="icon-refresh"></span> Transacciones</a></li>
				<li><a href="/presentacion/paquetes/"><span
						class="icon-ban-circle"></span> Solicitar Baja</a></li>
				<li><a href="/presentacion/adminProveedor/editar-info/"><span
						class="icon-pencil"></span> Editar Info</a></li>
				<c:if test="${not empty usuarioAutenticado and moduloMensajeria}">	
					<li><a href="/presentacion/mensajeria/">
						<span class="icon-envelope"></span> Mensajeria</a></li>
				</c:if>	
			</ul>
		</nav>
	</div>
</section>
<section class="header_text sub"></section>
<section class="main-content" style="margin-top: -30px">
	<div class="row">
		<%@include file="menuProveedor.jsp"%>
		<div class="span9 col">
			<h4 style="text-align: center;color:#eb4800;">SERVICIOS</h4>
			<table class="table table-striped" id="tablaServicios">
				<thead>
					<tr>
						<th width="10%">Foto</th>
						<th width="10%">Creación</th>
						<th width="10%">Categoría</th>
						<th width="30%">Nombre</th>
						<th width="10%">Precio</th>
						<th width="10%">Descuento</th>
						<th width="10%">Estado</th>
						<th width="5%">Ver/Editar</th>
						<th width="5%">Eliminar</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${servicios}" var="servicio">
						<tr>
							<td><img alt="" src="${servicio.rutaGaleria}" width="80px"
								title="Vista"></td>
							<td><fmt:formatDate type="both"
									value="${servicio.fechaCreacion}" /></td>
							<td><span>${servicio.categoria.nombre}</span></td>
							<td style="text-align: center"><span>${servicio.nombre}</span></td>
							<td><span>${servicio.precio}</span></td>
							<td><span>${servicio.descuento}</span></td>
							<c:choose>
								<c:when test="${servicio.activo}">
									<td><span>Activo</span></td>
								</c:when>
								<c:otherwise>
									<td><span>Inactivo</span></td>
								</c:otherwise>
							</c:choose>

							<td>
								<p class="buttons center">
									<a
										href="/presentacion/adminProveedor/edit/${servicio.idservicios}/"
										class="icon-zoom-in"></a>
								</p>
							</td>
							<td>
								<p class="buttons center">
									<a
										href="/presentacion/adminProveedor/delete/${servicio.idservicios}/"
										class="icon-remove"></a>
								</p>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</section>

<%@include file="footer.jsp"%>
