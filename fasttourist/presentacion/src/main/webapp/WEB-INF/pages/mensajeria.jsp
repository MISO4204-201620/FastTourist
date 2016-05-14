
<%@include file="header.jsp"%>

<section class="navbar main-menu">
	<div class="navbar-inner main-menu">
		<nav id="menu" class="pull-left">
			<ul>
				<!-- Menu del usuario -->
				<c:if test="${perfil=='2'}">
					<li><a href="/presentacion/">Alojamiento</a></li>
					<li><a href="/presentacion/transporte/">Transporte</a>
					<li><a href="/presentacion/alimentacion/">Alimentacion</a></li>
					<li><a href="/presentacion/paseos/paseos">Paseos
							Turisticos</a></li>
					<li><a href="/presentacion/paquetes/">Paquetes</a></li>
					<c:if test="${not empty usuarioAutenticado}">
						<li><a href="/presentacion/mensajeria/" class="paginaActiva">
							<span class="icon-envelope"></span> Mensajeria</a></li>
					</c:if>
					<c:if test="${not empty usuarioAutenticado and moduloBusquedas}">
							<li><a href="/presentacion/busquedas/">
									<span class="icon-zoom-in"></span> Mis busquedas
							</a></li>
						</c:if>	
				</c:if>

				<!-- Menu del proveedor -->
				<c:if test="${perfil=='1'}">
					<li><a href="/presentacion/adminProveedor/"><span
							class="icon-home"></span> Servicios</a></li>
					<li><a href="/presentacion/transporte/"><span
							class="icon-question-sign"></span> Respuestas</a>
					<li><a href="/presentacion/alimentacion/"><span
							class="icon-file"></span> Historicos</a></li>
					<li><a href="/presentacion/paseos/paseos"><span
							class="icon-refresh"></span> Transacciones</a></li>
					<li><a href="/presentacion/paquetes/"><span
							class="icon-ban-circle"></span> Solicitar Baja</a></li>
					<li><a href="/presentacion/adminProveedor/editar-info/"><span
							class="icon-pencil"></span> Editar Info</a></li>
					<li><a href="/presentacion/mensajeria/" class="paginaActiva">
							<span class="icon-envelope"></span> Mensajeria</a></li>
				</c:if>
				<!-- Menu del administrador -->
				<c:if test="${perfil=='3'}">
					<li><a href="/presentacion/admin/"><span
							class="icon-home"></span> Proveedores</a></li>
					<li><a href="/presentacion/alimentacion/"><span
							class="icon-file"></span> Historicos</a></li>
					<li><a href="/presentacion/admin/transacciones"><span
							class="icon-refresh"></span> Transacciones</a></li>
					<li><a href="/presentacion/admin/solicitudes-baja"><span
									class="icon-ban-circle"></span> Solicitudes de Baja</a></li>
					<li><a href="/presentacion/mensajeria/" class="paginaActiva">
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
			<div class="block">
				<ul class="nav nav-list">
					<li><a href="#" onclick="$('#myModal').modal('show')">Nuevo
							Mensaje</a></li>
					<li><a href="/presentacion/mensajeria/">Mensajes Recibidos</a></li>
					<li><a href="/presentacion/mensajeria/enviados">Mensajes
							Enviados</a></li>

				</ul>
			</div>
		</div>

		<div class="span9 col">
			<c:choose>
				<c:when test="${remitente}">
					<h5 style="text-align: center; color: #eb4800;">MENSAJES
						ENVIADOS</h5>
				</c:when>
				<c:otherwise>
					<h5 style="text-align: center; color: #eb4800;">MENSAJES
						RECIBIDOS</h5>
				</c:otherwise>
			</c:choose>
			<table class="table table-striped" id="tablaServicios">
				<tbody>
					<c:forEach items="${mensajes}" var="mensaje">
						<tr>
							<c:choose>
								<c:when test="${mensaje.estado or remitente}">
									<td><a href="#"
										onclick="$('#detailModal${mensaje.id}').modal('show')"> <span
											style="color: #333 !important">${mensaje.remitente.nombre}
												${mensaje.remitente.apellido}</span>

									</a></td>
									<td><a href="#"
										onclick="$('#detailModal${mensaje.id}').modal('show')"> <span
											style="color: #333 !important">${mensaje.asunto}</span>
									</a></td>
									<td><a href="#"
										onclick="$('#detailModal${mensaje.id}').modal('show')"> <span
											style="color: #333 !important"><fmt:formatDate
													value="${mensaje.fecha}" pattern="dd-MM-yyyy hh:mm:ss" /></span>
									</a></td>
								</c:when>
								<c:otherwise>
									<td><a href="#"
										onclick="$('#detailModal${mensaje.id}').modal('show')"> <span
											style="color: #333 !important"><b>${mensaje.remitente.nombre}
													${mensaje.remitente.apellido}</b></span>
									</a></td>
									<td><a href="#"
										onclick="$('#detailModal${mensaje.id}').modal('show')"> <span
											style="color: #333 !important"><b>${mensaje.asunto}</b></span>
									</a></td>
									<td><a href="#"
										onclick="$('#detailModal${mensaje.id}').modal('show')"> <span
											style="color: #333 !important"><b> <fmt:formatDate
														value="${mensaje.fecha}" pattern="dd-MM-yyyy hh:mm:ss" /></b></span>
									</a></td>
								</c:otherwise>
							</c:choose>
							<td>
								<div class="modal fade" id="detailModal${mensaje.id}"
									tabindex="-1" role="dialog" aria-labelledby="detailModalLabel"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<!-- Modal Header -->
											<div class="modal-header">
												<h4 class="modal-title" id="myModalLabel">Detalle
													Mensaje</h4>
											</div>

											<!-- Modal Body -->
											<div class="modal-body">


												<div class="form-group">
													<b>De:</b> ${mensaje.remitente.nombre}
													${mensaje.remitente.apellido}
												</div>
												<div class="form-group">
													<b>Para:</b> ${mensaje.destinatario.nombre}
													${mensaje.destinatario.apellido}
												</div>
												<div class="form-group">
													<b>Fecha:</b>
													<fmt:formatDate value="${mensaje.fecha}"
														pattern="dd-MM-yyyy hh:mm:ss" />
												</div>
												<div class="form-group">
													<b>Asunto:</b> ${mensaje.asunto}
												</div>

												<div class="form-group">
													<b>Mensaje:</b> ${mensaje.mensaje}
												</div>



											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</section>
<%@include file="footer.jsp"%>


<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span> <span class="sr-only">Cerrar</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Crear Mensaje</h4>
			</div>

			<!-- Modal Body -->
			<div class="modal-body">

				<form:form action="/presentacion/mensajeria/" method="post"
					class="form-stacked" commandName="mensaje">
					<div class="form-group">
						<form:select class="input-medium" path="destinatario.idusuario"
							required="required" id="destinatario">
							<option value="">Seleccione....</option>
							<c:forEach items="${usuarios}" var="usuario">
								<option value="${usuario.idusuario}">${usuario.nombre}
									${usuario.apellido}</option>
							</c:forEach>
						</form:select>
					</div>
					<div class="form-group">
						<label for="asunto">Asunto</label>
						<form:input type="text" class="form-control" id="asunto"
							path="asunto" />
					</div>

					<div class="form-group">
						<label for="mensaje">Mensaje</label>
						<form:textarea class="input-xxlarge" required="required" rows="4"
							path="mensaje" maxlength="500" style="width:500px" id="mensaje" />
					</div>
					<form:button class="btn btn-primary" id="crearButton">Enviar</form:button>
				</form:form>


			</div>
		</div>
	</div>
</div>

