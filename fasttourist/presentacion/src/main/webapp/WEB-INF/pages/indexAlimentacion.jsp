<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">				
		<nav id="menu" class="pull-left">
			<ul>
				<li><a href="/presentacion/">Alojamiento</a></li>
				<li><a href="/presentacion/transporte/">Transporte</a>
				<li><a href="/presentacion/alimentacion/" class="paginaActiva">Alimentacion</a></li>
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
<section class="header_text sub">		
	<section class="span4 col">
		<div class="row">			
			<div class="block" style="text-align:left !important">					
				<h4 class="title"><span class="text"><strong>Alimentación</strong></span></h4>
				<form action="#" method="post" class="form-stacked">
					<div class="control-group">
						<label class="control-label">¿Proveedor?</label>
						<div class="controls">
							<select class="input-large" name="proveedor">
								<option value="">Seleccione...</option>
								<c:forEach items="${proveedores}" var="proveedor"> 
								<option value="${proveedor.idusuario}">${proveedor.nombre}  ${proveedor.apellido}</option>
								</c:forEach>
							</select>
						</div>
					</div>		
<!-- 					<div class="control-group"> -->
<!-- 						<label class="control-label">¿En qué fecha?</label> -->
<!-- 						<div class="controls"> -->
<!-- 							<div class="input-append date" id="dp1" data-date="2016-04-01" -->
<!-- 								data-date-format="yyyy-mm-dd"> -->
<!-- 								<input class="input-small" size="16" type="text" name="fechaEntrada" -->
<!-- 									placeholder="Entrada" readonly=""> <span class="add-on"><i -->
<!-- 									class="icon-calendar"></i></span> -->
<!-- 							</div> -->
<!-- 							&nbsp; -->
<!-- 							<div class="input-append date" id="dp2" data-date="2016-04-01" -->
<!-- 								data-date-format="yyyy-mm-dd"> -->
<!-- 								<input class="input-small" size="16" type="text" name="fechaSalida" -->
<!-- 									placeholder="Salida" readonly=""> <span class="add-on"><i -->
<!-- 									class="icon-calendar"></i></span> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div>				 -->
					<div class="control-group">
						<label class="control-label">¿Qué tanto quieres comer?</label>
						<select class="input-medium" name=tipo>
							<option value="">Seleccione...</option>
							<c:forEach items="${tipo}" var="tipo">
								<option value="${tipo.idtipoAlimentacion}">${tipo.nombre}</option>
							</c:forEach>
						</select>											
						
						<hr>
				        <div class="actions"><input tabindex="9" class="btn btn-inverse large" type="submit" value="Buscar"></div>	        
					</div>					
				</form>					
			</div>	
		</div>
	</section>	
	
	<section class="span0 col" style="margin-left:60px">
  	</section>
	<section class="span4 col">
	<div class="row">
		<div class="span8">
			<ul class="thumbnails listing-products">
				<c:forEach items="${servicios}" var="servicio" begin="0" end="1"> 
					<li class="span4">
						<div class="product-box">
							<span class="sale_tag"></span> 
							<a href="/presentacion/getAlimentacion/${servicio.idservicios}/">
								<img alt="${servicio.nombre}" src="${servicio.rutaGaleria}" style="height:230px; max-with:342px">
							</a>
							<br /> 
							<a href="/presentacion/alimentacion/getAlimentacion/${servicio.idservicios}/" class="title">${servicio.nombre}</a>
							<p class="name">${servicio.alimentacion.nombre}</p>
							<p class="title">$${servicio.precio}</p>
							<p><b>Descuento $${servicio.descuento}</b></p>											
							
<%-- 							<c:if test="${servicio.alimentacion.tipoalimentacion.idtipoAlimentacion = '4'}"> --%>
<%-- 								<span class="title" title="Wifi"><img src="${pageContext.request.contextPath}/resources/themes/images/food.png" title="Comidas" width="16px"/></span> --%>
<%-- 								<span class="title" title="Wifi"><img src="${pageContext.request.contextPath}/resources/themes/images/drink.png" title="Bebidas" width="16px"/></span> --%>
<%-- 							</c:if>							 --%>
							<p class="category">${servicio.alimentacion.nombre}</p>
							<p class="buttons center">	
								<a href="/presentacion/alimentacion/getAlimentacion/${servicio.idservicios}/">			
									<button class="btn" type="button" >Ver Detalle</button>
								</a>
							</p>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	</section>
	
<!-- 	<section class="span4 col"> -->
<!-- 		<div class="row"> -->
<!-- 			<div class="span8"> -->

<!-- 				<table class="table table-striped" style="width:97% !important;margin-left:-15px"> -->
<!-- 					<thead> -->
<!-- 						<tr> -->
<!-- 							<th>Proveedor</th>							 -->
<!-- 							<th>Tipo</th> -->
<!-- 							<th>Precio</th> -->
<!-- 							<th>Ver Detalles</th> -->
<!-- 						</tr> -->
<!-- 					</thead> -->
<!-- 					<tbody> -->
<%-- 						<c:forEach items="${servicios}" var="servicio"> --%>
<!-- 							<tr> -->
<%-- 								<td><img alt="" src="${servicio.rutaGaleria}" width="80px" --%>
<!-- 									title="Vista 1"></td>								 -->
<%-- 								<td><span>${servicio.alimentacion.tipoalimentacion.nombre}</span></td> --%>
<!-- 								<td><span>$${servicio.precio}</span></td> -->
<!-- 								<td> -->
<!-- 									<p class="buttons center"> -->
<%-- 										<a href="/presentacion/alimentacion/getAlimentacion/${servicio.idservicios}/"> --%>
<!-- 											<button class="btn" type="button">Ver Detalle</button> -->
<!-- 										</a> -->
<!-- 									</p> -->
<!-- 								</td> -->
<!-- 							</tr> -->
<%-- 						</c:forEach> --%>
<!-- 					</tbody> -->
<!-- 				</table> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</section>	 -->
</section>
<section class="main-content">
	<div class="row">
		<div class="span12">
		</div>
	</div>
</section>
<%@include file="footer.jsp"%>