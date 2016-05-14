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
						<c:if test="${not empty usuarioAutenticado and moduloBusquedas}">
							<li><a href="/presentacion/busquedas/">
									<span class="icon-zoom-in"></span> Mis busquedas
							</a></li>
						</c:if>																			
					</ul>
				</nav>
			</div>
			</section>		
			<section class="google_map">
				<iframe width="100%" height="300" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" 
				src="https://www.google.com/maps/embed/v1/place?q=Bogotá,+Colombia&key=AIzaSyAN0om9mFmy1QN6Wf54tXAowK4eT0ZUPrU">
				</iframe>
			</section>
			
			<section class="header_text sub">
				<h4><span>Contactenos</span></h4>
			</section>
			<section class="main-content">				
				<div class="row">				
					<div class="span3">
						<div>
							<h5>INFORMACION ADICIONAL</h5>
							<p><strong>Telefono:</strong>&nbsp;(123) 456-7890<br>
							<strong>Fax:</strong>&nbsp;+04 (123) 456-7890<br>
							<strong>Email:</strong>&nbsp;<a href="#">fast_tourist@gmail.com.co</a>								
							</p>
							<br/>
						</div>
					</div>
					<div class="span8">
						<p>Queremos que la experiencia de nuestros clientes en FastTourist desde el momento de la compra hasta el momento de uso del servicio, sea una experiencia excepcional. </p>
						<form method="post" action="#">
							<fieldset>
								<div class="clearfix">
									<label for="name"><span>Nombre:</span></label>
									<div class="input">
										<input tabindex="1" size="18" id="name" name="name" type="text" value="" class="input-xlarge" placeholder="Nombre">
									</div>
								</div>
								
								<div class="clearfix">
									<label for="email"><span>Email:</span></label>
									<div class="input">
										<input tabindex="2" size="25" id="email" name="email" type="text" value="" class="input-xlarge" placeholder="Email">
									</div>
								</div>
								
								<div class="clearfix">
									<label for="message"><span>Mensaje:</span></label>
									<div class="input">
										<textarea tabindex="3" class="input-xlarge" id="message" name="body" rows="7" placeholder="Mensaje"></textarea>
									</div>
								</div>
								
								<div class="actions">
									<button tabindex="3" type="submit" class="btn btn-inverse">Enviar Mensaje</button>
								</div>
							</fieldset>
						</form>
					</div>				
				</div>
			</section>			
<%@include file="footer.jsp"%>