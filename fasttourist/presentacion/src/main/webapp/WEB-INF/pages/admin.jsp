<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">
		<nav id="menu" class="pull-left">
			<ul>
				<c:choose>
					<c:when test="${not empty mensaje}">
						<li><a href="/presentacion/admin/"><span
								class="icon-home"></span> Proveedores</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/presentacion/admin/" class="paginaActiva"><span
								class="icon-home"></span> Proveedores</a></li>
					</c:otherwise>
				</c:choose>
				
				<li><a href="/presentacion/alimentacion/"><span
						class="icon-file"></span> Historicos</a></li>
				<li><a href="/presentacion/admin/transacciones"><span
						class="icon-refresh"></span> Transacciones</a></li>

				<c:choose>
					<c:when test="${not empty mensaje}">
						<li><a href="/presentacion/admin/solicitudes-baja" class="paginaActiva"><span
								class="icon-ban-circle"></span> Solicitudes de Baja</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/presentacion/admin/solicitudes-baja"><span
								class="icon-ban-circle"></span> Solicitudes de Baja</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="/presentacion/admin/categorias/"><span
						class="icon-plus"></span> Categorías Adicionales</a></li>
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

		<div class="span2 col">
			<div class="block" style="text-align: left !important">
				<h4 class="title">
					<span class="text"><strong>Opciones</strong></span>
				</h4>
				<a href="/presentacion/admin/nuevo/"> 
					Agregar Proveedor
				</a>
			</div>
		</div>

		<div class="span9 col">
			<c:choose>
				<c:when test="${not empty mensaje}">
					<h4 style="text-align: center; color: #eb4800;">${mensaje}</h4>
				</c:when>
				<c:otherwise>
					<h4 style="text-align: center; color: #eb4800;">Proveedores</h4>
				</c:otherwise>
			</c:choose>
			<table class="table table-striped" id="tablaUsuarios">
				<thead>
					<tr>
						<th width="10%">Nombre</th>
						<th width="10%">Apellido</th>
						<th width="10%">Email</th>
						<th width="20%">Direccion</th>
						<th width="10%">Teléfono</th>
						<th width="10%">Fecha Creación</th>
						<th width="10%">Activo</th>
						<c:choose>
							<c:when test="${not empty mensaje}">
							</c:when>
							<c:otherwise>
								<th width="10%">Editar</th>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${not empty mensaje}">
								<th width="10%">Dar de baja</th>
							</c:when>
							<c:otherwise>
								<th width="10%">Eliminar</th>
							</c:otherwise>
						</c:choose>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${proveedores}" var="proveedor">
						<tr>
							<td><span>${proveedor.nombre}</span></td>
							<td><span>${proveedor.apellido}</span></td>
							<td><span>${proveedor.email}</span></td>
							<td><span>${proveedor.direccion}</span></td>
							<td><span>${proveedor.telefono}</span></td>
							<td><fmt:formatDate type="both"
									value="${proveedor.fechaCreacion}" /></td>
							<c:choose>
								<c:when test="${proveedor.activo}">
									<td><span>Activo</span></td>
								</c:when>
								<c:otherwise>
									<td><span>Inactivo</span></td>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${not empty mensaje}">
								</c:when>
								<c:otherwise>
									<td>
										<p class="buttons center">
											<a href="/presentacion/admin/editar/${proveedor.idusuario}/"
												class="icon-zoom-in"></a>
										</p>
									</td>

								</c:otherwise>
							</c:choose>
							<td>
								<p class="buttons center">
									<a
										href="/presentacion/admin/eliminar/${proveedor.idusuario}/"
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
