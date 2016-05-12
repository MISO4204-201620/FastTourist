<%@include file="header.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		$('#typeCategoria').change(function() {
			if ($('#typeCategoria').val() == '1') {
				$('#alojamiento').show();
				$("#alojamiento :input").attr("disabled", false);
				$('#alimentacion').hide();
				$("#alimentacion :input").attr("disabled", true);
				$('#transporte').hide();
				$("#transporte :input").attr("disabled", true);
				$('#paseo').hide();
				$("#paseo :input").attr("disabled", true);
				$('#crearButton').prop('disabled', false);
			}else if ($('#typeCategoria').val() == '2') {
				$('#alojamiento').hide();
				$("#alojamiento :input").attr("disabled", true);
				$('#alimentacion').show();
				$("#alimentacion :input").attr("disabled", false);
				$('#transporte').hide();
				$("#transporte :input").attr("disabled", true);
				$('#paseo').hide();
				$("#paseo :input").attr("disabled", true);
				$('#crearButton').prop('disabled', false);
			}else if ($('#typeCategoria').val() == '3') {
				$('#alojamiento').hide();
				$("#alojamiento :input").attr("disabled", true);
				$('#alimentacion').hide();
				$("#alimentacion :input").attr("disabled", true);
				$('#transporte').show();
				$("#transporte :input").attr("disabled", false);
				$('#paseo').hide();
				$("#paseo :input").attr("disabled", true);
				$('#crearButton').prop('disabled', false);
			}else if ($('#typeCategoria').val() == '4') {
				$('#alojamiento').hide();
				$("#alojamiento :input").attr("disabled", true);
				$('#alimentacion').hide();
				$("#alimentacion :input").attr("disabled", true);
				$('#transporte').hide();
				$("#transporte :input").attr("disabled", true);
				$('#paseo').show();
				$('#crearButton').prop('disabled', false);
			} else {
				$('#alojamiento').hide();
				$('#alimentacion').hide();
				$('#transporte').hide();
				$('#paseo').hide();
				$('#crearButton').prop('disabled', true);
				
			}
		});
	});
</script>

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

			<form:form action="#" method="post" class="form-stacked"
				commandName="servicio" >
				<table style="width: 80%; margin: 0px auto !important;">
					<tr>
						<td>
						<label class="control-label"><span class="required">*</span>Categoría</label>
						<form:select class="input-medium" path="categoria.idcategorias" required="required"
							id="typeCategoria">
							<option value="">Seleccione....</option>
							<option value="1">Alojamiento</option>
							<option value="2">Alimentación</option>
							<option value="3">Transporte</option>
							<option value="4">Paseos Ecológicos</option>
						</form:select>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div class="control-group">
								<label class="control-label"><span class="required">*</span>Nombre
									Servicio</label>
								<form:input type="text" class="input-xxlarge"
									required="required" path="nombre"
									style="width: 680px !important;" />
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div class="control-group">
								<label class="control-label"><span class="required">*</span>Descripción</label>
								<form:textarea class="input-xxlarge" required="required"
									rows="4" path="descripcion" style="width: 680px !important;" maxlength="500"/>

							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label"><span class="required">*</span>Precio</label>
								<form:input type="number" class="input-small"
									required="required" path="precio" />
							</div>
						</td>
						<td>
							<div class="control-group">
								<label class="control-label"><span class="required">*</span>Descuento</label>
								<form:input type="number" class="input-small"
									required="required" path="descuento"/>
							</div>
						</td>
						<td>
							<div class="control-group">
								<label class="control-label"><span class="required">*</span>Estado</label>
								<form:select class="input-medium" path="activo">

									<option value="0"
										<c:if test="${servicio.activo != 'true'}">selected</c:if>>Inactivo</option>
									<option value="1"
										<c:if test="${servicio.activo == 'true'}">selected</c:if>>Activo</option>
								</form:select>
							</div>
						</td>
					</tr>
				</table>
				
				<span id="alojamiento" hidden="true"> <%@include file="camposAlojamiento.jsp"%></span>
				<span id="alimentacion" hidden="true"> <%@include file="camposAlimentacion.jsp"%></span>
				<span id="transporte" hidden="true"> <%@include file="camposTransporte.jsp"%></span>
				<span id="paseo" hidden="true"> <%@include file="camposPaseo.jsp"%></span>
				
				<table style="width: 80%; margin: 0px auto !important;">
					<tr>
						<td colspan="3">
							<div class="control-group">
								<label class="control-label" style="color: #eb4800 !important;"><span
									class="required">*</span>Imagenes</label>
								<form:input type="text" class="input-xxlarge"
									style="width: 680px !important;" required="required"
									path="rutaGaleria" />
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center"><form:button
								class="btn btn-info pull-center" id="crearButton" disabled="true">Crear Servicio</form:button>
						</td>
					</tr>
				</table>
				
			</form:form>
		</div>
	</div>
</section>

<c:if test="${crearExitoso}">
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
					<strong style="color: #eb4800;">El servicio se ha creado exitosamente! 
				</h5>
			</div>
			<div class="modal-footer">
				<a href="/presentacion/adminProveedor/"><button class="btn btn-inverse" type="button">Aceptar</button></a>
			</div>
		</div>

	</div>
</div>
<%@include file="footer.jsp"%>