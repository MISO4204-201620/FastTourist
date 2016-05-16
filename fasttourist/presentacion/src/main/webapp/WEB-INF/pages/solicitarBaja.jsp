<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">
		<nav id="menu" class="pull-left">
			<ul>
				<li><a href="/presentacion/adminProveedor/"><span
				 		class="icon-home"></span> Servicios</a></li>
				<li><a href="/presentacion/moduloRespuestas/"><span
						class="question-sign"></span> Respuestas</a>
				<li><a href="/presentacion/transacciones/historicos"><span
						class="icon-file"></span> Históricos</a></li>
				<li><a href="/presentacion/transacciones/"><span
						class="icon-refresh"></span> Transacciones</a></li>
				<li><a href="/presentacion/adminProveedor/solicitarBaja/"
						class="paginaActiva"><span
						class="icon-ban-circle"></span> Solicitar Baja</a></li>
				<li><a href="/presentacion/adminProveedor/editar-info/"><span
						class="icon-pencil"></span> Editar Info</a></li>
				<c:if test="${not empty usuarioAutenticado and moduloMensajeria}">	
					<li><a href="/presentacion/mensajeria/">
						<span class="icon-envelope"></span> Mensajería</a></li>
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
			<h4 style="text-align: center;color:#eb4800;">SOLICITAR BAJA</h4>
			<form action="#" method="post" class="form-inline">	
				<table>
					<tr>						
						<td><span style="color: #eb4800">
						Dando click en botón "Solicitar baja" ud estará solicitando la inactivación de su usuario en el sistema.
						</span></td>
					</tr>							
					<tr>		
					<hr>				
						<td><button class="btn btn-inverse" type="submit">Solicitar baja</button></td>
					</tr>
				</table>					
			</form>			
		</div>
	</div>
</section>
<%@include file="footer.jsp"%>