<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">
		<nav id="menu" class="pull-left">
			<ul>
				<li><a href="/presentacion/">Alojamiento</a></li>
				<li><a href="/presentacion/transporte/">Transporte</a>
				<li><a href="/presentacion/alimentacion/">Alimentacion</a></li>
				<li><a href="/presentacion/paseos/paseos">Paseos Turisticos</a></li>
				<li><a href="/presentacion/paquetes/">Paquetes</a></li>
				<c:if test="${not empty usuarioAutenticado and moduloMensajeria}">	
					<li><a href="/presentacion/mensajeria/">
						<span class="icon-envelope"></span> Mensajeria</a></li>
				</c:if>
			</ul>
		</nav>
	</div>
</section>
<section class="main-content">
	<h4 style="text-align: center">
		<span>COMPRAR</span>
	</h4>
	<div class="row">
		<div class="span12">
			<form action="#" method="post" class="form-stacked">
				<div class="accordion" id="accordion2">
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion2" href="#collapseOne">Opciones de
								compra</a>
						</div>
						<div id="collapseOne" class="accordion-body in collapse">
							<div class="accordion-inner">
								<div class="row-fluid">
									<div class="span6">
										<h4>Servicios</h4>
										<p>Listado de servicios que va a adquirir</p>
										<ul>
											<c:forEach items="${carrito}" var="carrito">
												<li>${carrito.servicio.categoria.nombre}:<br />
													${carrito.servicio.nombre} por <strong>$${carrito.servicio.precio}</strong></li>
											</c:forEach>
										</ul>
									</div>
									<div class="span6">
										<h4>Valor a Pagar</h4>
										<table>
											<tr>
												<td>Valor</td>
												<td>$${valor}</td>
											</tr>
											<tr>
												<td>Impuestos</td>
												<td>$${impuestos}</td>
											</tr>
											<tr>
												<td><strong>Total</strong></td>
												<td><strong>$${total}</strong></td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion2" href="#collapseTwo">Cuenta &amp;
								Detalles de Facturación</a>
						</div>
						<div id="collapseTwo" class="accordion-body collapse">
							<div class="accordion-inner">
								<div class="row-fluid">
									<div class="span6">
										<h4>Forma de Pago</h4>
										<div class="control-group">
											<label class="control-label"><span class="required">*</span>
												Tarjeta:</label>
											<div class="controls">
												<select class="input-xlarge">
													<option value="1">Diners Club</option>
													<option value="2">American Express</option>
													<option value="3">Master Card</option>
													<option value="4">Visa</option>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"><span class="required">*</span>
												Número de Tarjeta</label>
											<div class="controls">
												<input type="text" placeholder="" class="input-xlarge"
													required="required">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"><span class="required">*</span>
												Código de Seguridad</label>
											<div class="controls">
												<input type="text" placeholder="" class="input-xlarge"
													required="required">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"><span class="required">*</span>
												Titular de la Tarjeta</label>
											<div class="controls">
												<input type="text" placeholder="" class="input-xlarge"
													required="required">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"><span class="required">*</span>
												Cedula del titular de la tarjeta</label>
											<div class="controls">
												<input type="text" placeholder="" class="input-xlarge"
													required="required">
											</div>
										</div>
									</div>
									<div class="span6">
										<h4>Información de contacto</h4>
										<div class="control-group">
											<label class="control-label"><span class="required">*</span>
												Dirección:</label>
											<div class="controls">
												<input type="text" placeholder="" class="input-xlarge"
													required="required">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"><span class="required">*</span>
												Ciudad:</label>
											<div class="controls">
												<input type="text" placeholder="" class="input-xlarge"
													required="required">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"> Código Postal:</label>
											<div class="controls">
												<input type="text" placeholder="" class="input-xlarge">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"><span class="required">*</span>
												Pais:</label>
											<div class="controls">
												<select class="input-xlarge">
													<option value="1">Colombia</option>
													<option value="2">Ecuador</option>
													<option value="3">México</option>
													<option value="4">Panamá</option>
													<option value="5">Peru</option>
													<option value="6">Venezuela</option>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"><span class="required">*</span>
												Region / Departamento:</label>
											<div class="controls">
												<select name="zone_id" class="input-xlarge">
													<option value="">--- Seleccione ---</option>
													<option value="Antioquia">Antioquia</option>
													<option value=Bolivar>Bolivar</option>
													<option value="Cundinamarca">Cundinamarca</option>
													<option value="Risaralda">Risaralda</option>
													<option value="Valle de Cauca">Valle de Cauca</option>
												</select>
											</div>
										</div>
									</div>
									<button class="btn btn-inverse pull-right">Confirmar
										Orden</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>

<c:if test="${pagoExitoso == 'ok'}">
	<script type="text/javascript">
	    $(window).load(function(){
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
				<h4 class="modal-title">Información de la compra</h4>
			</div>
			<div class="modal-body">
				<h5><strong style="color:#eb4800;">Transacción Exitosa.</strong><br/> 
				Gracias por adquirir los servicios de FasTourist.</h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"
					onclick="$('.modal').hide()">Aceptar</button>
			</div>
		</div>

	</div>
</div>

<%@include file="footer.jsp"%>