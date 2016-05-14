<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">
		<nav id="menu" class="pull-left">
			<ul>
				<li><a href="/presentacion/">Alojamiento</a></li>
				<li><a href="/presentacion/transporte/">Transporte</a>
				<li><a href="/presentacion/alimentacion/">Alimentación</a></li>
				<li><a href="/presentacion/paseos/paseos" class="paginaActiva">Paseos Turísticos</a></li>
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

	<a href="/presentacion/paseo/getPaseo/13/">Ver detalle de paseo</a>
</section>

<%@include file="footer.jsp"%>
