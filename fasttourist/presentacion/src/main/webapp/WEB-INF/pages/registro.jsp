<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">
		<nav id="menu" class="pull-left">
			<ul>
				<li><a href="/presentacion/">Alojamiento</a></li>
				<li><a href="/presentacion/transporte/">Transporte</a>
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
	<img class="pageBanner"
		src="${pageContext.request.contextPath}/resources/themes/images/pageBanner.png"
		alt="New products">
</section>
<section class="main-content" style="margin-top: -25px">
	<h4 class="title" align="center">
		<span class="text">Registro y Autenticación</span>
	</h4>
	<div class="row">
		<div class="span5">
			<h4 class="title">
				<span class="text"><strong>Formulario </strong> de
					Autenticación</span>
			</h4>
			<form action="/presentacion/registro/auth" method="post">
				<input type="hidden" name="next" value="/">
				<fieldset>
					<div class="control-group">
						<label class="control-label">Correo Electrónico</label>
						<div class="controls">
							<input type="text" placeholder="Ingresa tu email"
								id="username" name="correo" class="input-xlarge" required="required">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Contraseña</label>
						<div class="controls">
							<input type="password" placeholder="Ingresa tu contraseña"
								id="password" name="password" class="input-xlarge" required="required">
						</div>
					</div>
					<div class="control-group">
						<input tabindex="3" class="btn btn-inverse large" type="submit"
							value="Ingresar">
						<hr>
						<p class="reset">
							Recuperar tu <a tabindex="4" href="#"
								title="Recuperar el usuario o contraseña">Nombre de usuario
								o contraseña</a>
						</p>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="span7">
			<h4 class="title">
				<span class="text"><strong>Formulario</strong> de Registro</span>
			</h4>
			
			<form:form action="#" method="post" class="form-stacked" commandName="usuario">

				<fieldset>
					<div class="control-group">
						<label class="control-label">* Usuario</label>
						<div class="controls">
							<form:input type="text" path="nombre"
								placeholder="Ingresa tu nombre" class="input-xlarge" required="required"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">* Apellidos</label>
						<div class="controls">
							<form:input type="text" path="apellido" class="input-xlarge"
								placeholder="Ingresa tus apellidos" required="required"/>
						</div>
						<div class="control-group">
							<label class="control-label">* Email:</label>
							<div class="controls">
								<form:input type="text" path="email" placeholder="Ingresa tu email"
									class="input-xlarge" required="required"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">* Contraseña:</label>
							<div class="controls">
								<form:input type="password" path="password"
									placeholder="Ingresa tu contraseña" class="input-xlarge" required="required"/>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">* Dirección</label>
						<div class="controls">
							<form:input type="text" path="direccion" class="input-medium"
								placeholder="Ingresa tu direccion" required="required"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">* Teléfono</label>
						<div class="controls">
							<form:input type="text" path="telefono" class="input-medium"
								placeholder="Ingresa tu número telefónico" required="required"/>
						</div>
					</div>
					<hr>
					<div class="actions">
						<input tabindex="9" class="btn btn-inverse large" type="submit"
							value="Crear tu cuenta">
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</section>

<c:if test="${response != null}">
	<script type="text/javascript">
	    $(window).load(function(){
	        $('#myModal').modal('show');
	    });
	</script>
</c:if>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					onclick="$('.modal').hide()">&times;</button>
				<h4 class="modal-title">Información de la compra</h4>
			</div>
			<div class="modal-body">
				<h5><strong style="color:#eb4800;">${response}</h5>
			</div>
			<div class="modal-footer">
				<a href="/presentacion/registro"><button class="btn btn-inverse" type="button">Aceptar</button></a>
			</div>
		</div>

	</div>
</div>
<%@include file="footer.jsp"%>