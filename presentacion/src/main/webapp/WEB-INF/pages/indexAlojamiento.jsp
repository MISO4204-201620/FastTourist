<%@include file="header.jsp"%>
		<section class="navbar main-menu">
			<div class="navbar-inner main-menu">				
				<nav id="menu" class="pull-left">
					<ul>
						<li><a href="/presentacion/" class="paginaActiva">Alojamiento</a></li>
						<li><a href="./indexTransporte">Transporte</a>
						<li><a href="./products.html">Alimentacion</a></li>	
						<li><a href="./products.html">Paseos Turisticos</a></li>
						<li><a href="./products.html">Paquetes</a></li>																					
					</ul>
				</nav>
			</div>
		</section>
		<section class="header_text sub">
		
		<section class="span4 col">				
				<div class="row">
					<div class="block" style="text-align:left !important">					
						<h4 class="title"><span class="text"><strong>Alojamiento</strong></span></h4>
						<form action="#" method="post" class="form-stacked">
							
								<div class="control-group">
									<label class="control-label">¿Donde quieres ir?</label>
									<div class="controls">
										<input type="text" placeholder="Ingresa una cuidad u hotel" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">¿En qué fecha?</label>
									<div class="controls">
										<div class="input-append date" id="dp1" data-date="01-04-2016" data-date-format="dd-mm-yyyy">
											<input class="input-small" size="16" type="text" placeholder="Entrada" readonly="">
											<span class="add-on"><i class="icon-calendar"></i></span>
										</div>
										&nbsp;
										<div class="input-append date" id="dp2" data-date="01-04-2016" data-date-format="dd-mm-yyyy">
											<input class="input-small" size="16" type="text" placeholder="Salida" readonly="">
											<span class="add-on"><i class="icon-calendar"></i></span>
										</div>
									</div>
								</div>
		
								<div class="control-group">
									<table>
										<tr>
											<td width="100px"><label class="control-label">Habitaciones</label></td>
											<td width="100px"><label class="control-label">Personas</label></td>
										</tr>
										<tr>
											<td>
												<select class="input-mini">
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
												</select>
										</td>
											<td>
												<select class="input-mini">
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
													<option value="4">5</option>
													<option value="4">6</option>
												</select>
											</td>
										</tr>
									</table>
								</div>
								<hr>
								<div class="actions"><input tabindex="9" class="btn btn-inverse large" type="submit" value="Buscar.."></div>
							
						</form>					
					</div>				
				</div>
			</section>			
				
		
		</section>
		<section class="main-content">				
			<div class="row">						
				<div class="span9">
					<div class="row">
						<div class="span4">
							<a href="/presentacion/producto/get/1/">Ver Producto</a> 
							<h3><span>${servicio.nombre}</span></h3>
																
						</div>					
					</div>
				</div>
			</div>
		</section>
<%@include file="footer.jsp"%>			
		