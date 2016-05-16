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
				<a href="/presentacion/admin/nuevo/"> Agregar Proveedor </a>
			</div>
		</div>

		<div class="span9 col">

			<form:form action="#" method="post" class="form-stacked"
				commandName="proveedor">
				<table style="width: 80%; margin: 0px auto !important;">
					<tr>
						<td colspan="3">
							<h5>Agregar Proveedor</h5>

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
								<label class="control-label"><span class="required">*</span>Contraseña</label>
								<form:input type="text" class="input-xlarge"
									required="required" path="password" />
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
						<td align="left">
							<form:button class="btn btn-info pull-center" id="actualizar"
								disabled="false">Crear Proveedor</form:button>
							&nbsp&nbsp&nbsp
							<a class="btn btn-danger" href="<c:url value='/admin/'/>">Cancelar</a>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</section>

<c:if test="${mensaje != null}">
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
					<strong style="color: #eb4800">${mensaje}</strong>
				</h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-inverse" data-dismiss="modal"
					onclick="$('.modal').hide()">Aceptar</button>
			</div>
		</div>

	</div>
</div>
<%@include file="footer.jsp"%>