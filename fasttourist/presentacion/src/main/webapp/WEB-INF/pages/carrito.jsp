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
<section class="main-content">
	<h4 style="text-align: center">
		<span>CARRITO DE COMPRAS</span>
	</h4>
	<div class="row">
		<div class="span12">
			<h4 class="title">
				<span class="text"><strong>Tu</strong> Carrito</span>
			</h4>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Remover</th>
						<th>Imagen</th>
						<th>Nombre del Servicio</th>
						<th>Cantidad</th>
						<th>Precio Unitario</th>
						<th>Total</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${carrito}" var="carrito">
					<tr>
						<td><input type="checkbox" value="${carrito.idCarrito}" name="checks"></td>
						<td width="150px"><img alt="${carrito.servicio.nombre}" src="${carrito.servicio.rutaGaleria}" width="150px" style="with:150px !important"></td>
						<td>${carrito.servicio.nombre}</td>
						<td>1</td>
						<td>${carrito.servicio.precio}</td>
						<td>${carrito.servicio.precio}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>

			<hr>
			<p class="cart-total right">
				<strong>Sub-Total</strong>: $${valor}<br> 
				<strong>Impuestos</strong>: $${impuestos}<br>
				<strong>Total</strong>: <span style="color:#eb4800;">$${total}</span><br>
			</p>
			<hr />
			<p class="buttons center">
				<c:if test="${not empty usuarioAutenticado}">
					<button class="btn" type="button" onclick="assign();">Remover</button>
					<a href="/presentacion/pagar/checkout"><button
							class="btn btn-inverse" type="submit" id="checkout">Comprar</button></a>
				</c:if>
				<c:if test="${empty usuarioAutenticado}">
					<button class="btn" type="button" onclick="assign();" disabled="disabled">Remover</button>
					<a href="/presentacion/pagar/checkout"><button
							class="btn btn-inverse" type="submit" id="checkout" disabled="disabled">Comprar</button></a>
				</c:if>	
			</p>
		</div>
	</div>
</section>

	<script type="text/javascript">
	function assign()
	   { 
		var values = [];
		var cbs = document.getElementsByName("checks");
		for(var i=0,cbLen=cbs.length;i<cbLen;i++){
		  if(cbs[i].checked){
		    values.push(cbs[i].value);
		  }
		}
		window.location = 'http://localhost:8080/presentacion/pagar/carrito/' + values;
		
	}
	</script>


<%@include file="footer.jsp"%>