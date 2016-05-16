<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">
		<nav id="menu" class="pull-left">
			<ul>
				<li><a href="/presentacion/adminProveedor/"><span
				 		class="icon-home"></span> Servicios</a></li>
				<li><a href="/presentacion/moduloRespuestas/"><span
						class="question-sign"></span> Respuestas</a>
				<li><a href="/presentacion/transacciones/historicos"
						class="paginaActiva"><span
						class="icon-file"></span> Históricos</a></li>
				<li><a href="/presentacion/transacciones/"><span
						class="icon-refresh"></span> Transacciones</a></li>
				<li><a href="/presentacion/adminProveedor/solicitarBaja/"><span
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
			<h4 style="text-align: center;color:#eb4800;">HISTÓRICO</h4>
			<c:choose>
				<c:when test="${empty transacciones}">
					<strong>Aún no tiene transacciones asociadas.</strong>
				</c:when>
				<c:otherwise>															
					<table class="table table-striped" id="tablaTransacciones">
						<thead>
							<tr>
								<th width="60%">Servicio</th>
								<th width="30%">Cantidad</th>
								<th width="10%">Detalle</th>									
							</tr>
						</thead>
						<c:forEach varStatus="ts" var="transaccion" items="${transacciones}">								
							<tr>
								<td><span style="color: #eb4800">${transaccion.key}</span>												
								<td><span style="color: #eb4800">${transaccion.value}</span>
								<td>
									<p class="buttons center">
										<a href="/presentacion/admin/editar/${proveedor.idusuario}/"
											class="icon-zoom-in"></a>
									</p>
								</td>
							</tr>	
						</c:forEach>
					</table>
					<hr>										
				</c:otherwise>
			</c:choose>						
		</div>
	</div>
</section>
<%@include file="footer.jsp"%>