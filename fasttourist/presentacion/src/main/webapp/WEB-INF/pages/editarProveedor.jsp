<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">
		<nav id="menu" class="pull-left">
			<c:choose>
				<c:when test="${not empty esProveedor}">
					<ul>
						<li><a href="/presentacion/adminProveedor/"><span class="icon-home"></span>
								Servicios</a></li>
						<li><a href="/presentacion/transporte/"><span
								class="question-sign"></span> Respuestas</a>
						<li><a href="/presentacion/alimentacion/"><span
								class="icon-file"></span> Historicos</a></li>
						<li><a href="/presentacion/paseos/paseos"><span
								class="icon-refresh"></span> Transacciones</a></li>
						<li><a href="/presentacion/paquetes/"><span
								class="icon-ban-circle"></span> Solicitar Baja</a></li>
						<li><a href="/presentacion/adminProveedor/editar-info/"
							class="paginaActiva"><span class="icon-pencil"></span> Editar Info</a></li>
						<c:if test="${not empty usuarioAutenticado and moduloMensajeria}">	
							<li><a href="/presentacion/mensajeria/">
								<span class="icon-envelope"></span> Mensajeria</a></li>
						</c:if>	
					</ul>

				</c:when>
				<c:otherwise>

					<ul>
						<li><a href="/presentacion/admin/" class="paginaActiva"><span
								class="icon-home"></span>Proveedores</a></li>
						<li><a href="/presentacion/transporte/"><span
								class="question-sign"></span> Respuestas</a>
						<li><a href="/presentacion/alimentacion/"><span
								class="icon-file"></span> Historicos</a></li>
						<li><a href="/presentacion/admin/transacciones"><span
								class="icon-refresh"></span> Transacciones</a></li>
						<li><a href="/presentacion/admin/solicitudes-baja"><span
								class="icon-ban-circle"></span> Solicitudes de Baja</a></li>
						<c:if test="${not empty usuarioAutenticado}">	
							<li><a href="/presentacion/mensajeria/">
								<span class="icon-envelope"></span> Mensajeria</a></li>
						</c:if>	
					</ul>
				</c:otherwise>
			</c:choose>
		</nav>
	</div>
</section>
<section class="header_text sub"></section>
<section class="main-content" style="margin-top: -30px">
	<div class="row">

		<c:choose>
			<c:when test="${not empty esProveedor}">
				<%@include file="menuProveedor.jsp"%>

			</c:when>
			<c:otherwise>
				<div class="span2 col">
					<div class="block" style="text-align: left !important">
						<h4 class="title">
							<span class="text"><strong>Opciones</strong></span>
						</h4>
						<a href="/presentacion/admin/nuevo/"> Agregar Proveedor </a>
					</div>
				</div>

			</c:otherwise>
		</c:choose>



		<div class="span9 col">

			<form:form action="#" method="post" class="form-stacked"
				commandName="proveedor">
				<table style="width: 80%; margin: 0px auto !important;">
					<tr>
						<td colspan="3">
							<h5>Editar Proveedor</h5>

						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div class="control-group">
								<label class="control-label"><span class="required">*</span>Nombre</label>
								<form:input type="text" class="input-xlarge"
									required="required" path="nombre"/>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div class="control-group">
								<label class="control-label"><span class="required">*</span>Apellido</label>
								<form:input type="text" class="input-xlarge" 
									required="required" path="apellido"/>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label"><span class="required">*</span>Email</label>
								<form:input type="text" class="input-xlarge"
									required="required" path="email" />
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label"><span class="required">*</span>Dirección</label>
								<form:input type="text" class="input-xlarge"
									required="required" path="direccion" style="width: 400px" />
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label"><span class="required">*</span>Teléfono</label>
								<form:input type="number" class="medium"
									required="required" path="telefono" style="width: 400px" />
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label"><span class="required">*</span>Activo</label>
								<form:select class="input-medium" path="activo">

									<option value="0"
										<c:if test="${proveedor.activo != 'true'}">selected</c:if>>Inactivo</option>
									<option value="1"
										<c:if test="${proveedor.activo == 'true'}">selected</c:if>>Activo</option>
 								</form:select> 
							</div>
						</td>
					</tr>
					<tr>
						<td align="left"><form:button
								class="btn btn-info pull-center" id="actualizar"
								disabled="false">Actualizar Proveedor</form:button>
							&nbsp&nbsp&nbsp
							<c:choose>
								<c:when test="${not empty esProveedor}">
									<a class="btn btn-danger"
										href="<c:url value='/adminProveedor/'/>">Cancelar</a>
								</c:when>
								<c:otherwise>
									<a class="btn btn-danger" href="<c:url value='/admin/'/>">Cancelar</a>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</section>

<c:if test="${editExitoso}">
	<script type="text/javascript">
		$(window).load(function() {
			$('#myModal').modal('show');
		});
	</script>
</c:if>
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					onclick="$('.modal').hide()">&times;</button>
				<h4 class="modal-title">Información</h4>
			</div>
			<div class="modal-body">
				<h5>
					<strong style="color: #eb4800;">El servicio se ha
						actualizado exitosamente! </strong>
				</h5>
			</div>
			<div class="modal-footer">
				<a href="/presentacion/adminProveedor/"><button class="btn btn-inverse" type="button">Aceptar</button></a>
			</div>
		</div>

	</div>
</div>
<%@include file="footer.jsp"%>