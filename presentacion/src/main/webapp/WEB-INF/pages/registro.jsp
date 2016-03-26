<%@include file="header.jsp"%>	
			<section class="navbar main-menu">
				<div class="navbar-inner main-menu">				
					<nav id="menu" class="pull-left">
						<ul>
							<li><a href="/presentacion/">Alojamiento</a></li>
							<li><a href="./products.html">Transporte</a>
							<li><a href="./products.html">Alimentacion</a></li>	
							<li><a href="./products.html">Paseos Turisticos</a></li>
							<li><a href="./products.html">Paquetes</a></li>																					
						</ul>
					</nav>
				</div>
			</section>
			<section class="header_text sub">
				<img class="pageBanner" src="${pageContext.request.contextPath}/resources/themes/images/pageBanner.png" alt="New products" >
				<h4><span>Registrarse</span></h4>
			</section>			
			<section class="main-content">				
				<div class="row">
					<div class="span5">					
						<h4 class="title"><span class="text"><strong>Formulario </strong> de Autenticación</span></h4>
						<form action="#" method="post">
							<input type="hidden" name="next" value="/">
							<fieldset>
								<div class="control-group">
									<label class="control-label">Usuario</label>
									<div class="controls">
										<input type="text" placeholder="Ingresa tu nombre de usuario" id="username" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Contraseña</label>
									<div class="controls">
										<input type="password" placeholder="Ingresa tu contraseña" id="password" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<input tabindex="3" class="btn btn-inverse large" type="submit" value="Ingresar">
									<hr>
									<p class="reset">Recuperar tu <a tabindex="4" href="#" title="Recuperar el usuario o contraseña">Nombre de usuario o contraseña</a></p>
								</div>
							</fieldset>
						</form>				
					</div>
					<div class="span7">					
						<h4 class="title"><span class="text"><strong>Formulario</strong> de Registro</span></h4>
						<form action="#" method="post" class="form-stacked">
							<fieldset>
								<div class="control-group">
									<label class="control-label">Usuario</label>
									<div class="controls">
										<input type="text" name="nombre" placeholder="Ingresa tu nombre de usuario" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Apellidos</label>
									<div class="controls">
										<input type="text" name="apellido" class="input-medium">
									</div>
								<div class="control-group">
									<label class="control-label">Email:</label>
									<div class="controls">
										<input type="text" name="email" placeholder="Ingresa tu email" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Contraseña:</label>
									<div class="controls">
										<input type="password" name="password" placeholder="Ingresa tu contraseña" class="input-xlarge">
									</div>
								</div>
								</div>
								<div class="control-group">
									<label class="control-label">Dirección</label>
									<div class="controls">
										<input type="text" name="direccion" class="input-medium">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Teléfono</label>
									<div class="controls">
										<input type="text" name="telefono" class="input-medium">
									</div>
								</div>

								<div class="control-group">
									<table>
										<tr>
											<td width="100px"><label class="control-label">Tipo Usuario</label></td>
										</tr>
										<tr>
											<td><select class="input-mini" name="tipoUsuario">
													<option value="1">Proveedor</option>
													<option value="2">Cliente</option>
											</select></td>
										</tr>
									</table>
								</div>

					<hr>
						<div class="actions">
							<input tabindex="9" class="btn btn-inverse large" type="submit"
								value="Crear tu cuenta">
						</div>
				</fieldset>
						</form>					
					</div>				
				</div>
			</section>			
<%@include file="footer.jsp"%>