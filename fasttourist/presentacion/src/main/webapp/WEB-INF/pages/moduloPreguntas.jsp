
<div class="row">
	<div class="span9">
		<ul class="nav nav-tabs" id="myTab">
			<li class="active"><a href="#preguntas">Preguntas</a></li>
			<c:if test="${moduloCalificaciones == 'true'}">
				<li class=""><a href="#calificaciones">Calificación</a></li>
			</c:if>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="preguntas">

				<form action="#" method="post" class="form-inline">
					<p class="buttons left">
						<label><strong>¿Tienes una pregunta?:</strong></label> <input
							type="text" class="input-xxlarge"
							placeholder="Ingresa tu pregunta" name="inputPregunta">
						<button class="btn btn-inverse" type="submit">Enviar</button>
					</p>
				</form>
				<hr>
				<c:forEach items="${servicio.preguntas}" var="preguntas">
					<table>
						<tr>
							<td rowspan="3" style="padding: 20px; text-align: center"><img
								src="${pageContext.request.contextPath}/resources/themes/images/avatar-reviews.png"
								title="${preguntas.usuario.nombre}" /></td>
							<td><fmt:formatDate type="date"
									value="${preguntas.fechaCreacion}" /></td>
						</tr>
						<tr>
							<td width="400px"><span style="color: #eb4800">Pregunta:</span>
								<strong>${preguntas.pregunta}</strong></td>
						</tr>
						<tr>
							<td><span style="color: #eb4800">Respuesta:</span>
								${preguntas.respuesta}</td>
						</tr>
						<tr>
							<td style="text-align: center">${preguntas.usuario.nombre}
								${preguntas.usuario.apellido}</td>
						</tr>
						<tr>
							<td style="text-align: center">${preguntas.usuario.email}</td>
						</tr>
					</table>
					<hr>
				</c:forEach>
			</div>
			<c:if test="${moduloCalificaciones == 'true'}">
				<div class="tab-pane" id="calificaciones">
					<%@include file="moduloCalificaciones.jsp"%>
				</div>
			</c:if>
		</div>
	</div>

</div>