<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">
		<nav id="menu" class="pull-left">
			<ul>
				<li><a href="/presentacion/adminProveedor/"><span
				 		class="icon-home"></span> Servicios</a></li>
				<li><a href="/presentacion/moduloRespuestas/"
						class="paginaActiva"><span
						class="question-sign"></span> Respuestas</a>
				<li><a href="/presentacion/transacciones/historicos"><span
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
			<h4 style="text-align: center;color:#eb4800;">PREGUNTAS</h4>
			<c:choose>
				<c:when test="${empty preguntasWrapper.preguntas}">
					<strong>No tiene preguntas pendientes para responder.</strong>
				</c:when>
				<c:otherwise>
					<form:form action="#" method="post" class="form-inline" modelAttribute="preguntasWrapper">										
						<table>
							<c:forEach varStatus="ps" var="pregunta" items="${preguntasWrapper.preguntas}">
								
								<tr>						
									<td><fmt:formatDate type="date"
											value="${pregunta.fechaCreacion}" /></td>
								</tr>					
								<tr>
									<td width="400px"><span style="color: #eb4800">Pregunta:</span>
										<strong>${pregunta.pregunta}</strong></td>
								</tr>
								<tr>
									<td><span style="color: #eb4800">Respuesta:</span>
									<form:input path="preguntas[${ps.index}].respuesta" 
									class="input-xxlarge" placeholder="Ingresa tu respuesta" />						
								</tr>
								<tr><form:input type="hidden" path="preguntas[${ps.index}].idPreguntas"/>
								</tr>											
											
							</c:forEach>
						</table>
						<hr>	
						<button class="btn btn-inverse" type="submit"> Responder </button>
					</form:form>
				</c:otherwise>
			</c:choose>						
		</div>
	</div>
</section>
<%@include file="footer.jsp"%>