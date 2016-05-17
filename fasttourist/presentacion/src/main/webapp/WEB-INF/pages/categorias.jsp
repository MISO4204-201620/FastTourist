<%@include file="header.jsp"%>
<section class="navbar main-menu">
	<div class="navbar-inner main-menu">
		<nav id="menu" class="pull-left">
			<ul>
				<c:choose>
					<c:when test="${not empty mensaje}">
						<li><a href="/presentacion/admin/"><span
								class="icon-home"></span> Proveedores</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/presentacion/admin/" ]><span
								class="icon-home"></span> Proveedores</a></li>
					</c:otherwise>
				</c:choose>
				
				<li><a href="/presentacion/admin/transacciones"><span
						class="icon-refresh"></span> Transacciones</a></li>

				<c:choose>
					<c:when test="${not empty mensaje}">
						<li><a href="/presentacion/admin/solicitudes-baja" class="paginaActiva"><span
								class="icon-ban-circle"></span> Solicitudes de Baja</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/presentacion/admin/solicitudes-baja"><span
								class="icon-ban-circle"></span> Solicitudes de Baja</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="/presentacion/admin/categorias/" class="paginaActiva"><span
						class="icon-plus"></span> Categorias Adicionales</a></li>
				<c:if test="${not empty usuarioAutenticado and moduloMensajeria}">	
					<li><a href="/presentacion/mensajeria/">
						<span class="icon-envelope"></span> Mensajeria</a></li>
				</c:if>		
			</ul>
		</nav>
	</div>
</section>
<section class="header_text sub"></section>

<section class="main-content" style="margin-top: -30px">
	<div class="row">

		<div class="span2 col">
			<div class="block" style="text-align: left !important">
				<h4 class="title">
					<span class="text"><strong>Opciones</strong></span>
				</h4>
				<a href="/presentacion/admin/nuevo/"> 
					Agregar Proveedor
				</a>
<!-- 				<a href="/presentacion/reportes/">  -->
<!-- 					Generar Reportes -->
<!-- 				</a> -->
				
			</div>
		</div>
		<div class="span9 col">

			<h4 style="text-align: center; color: #eb4800;">Crear una nueva
				categoria</h4>

			<form action="#" method="post" class="form-stacked">

				<table align="center" class="authors-list">
					<tr>
						<td>Nombre de la categoria</td>
					</tr>
					<tr>
						<td><input type="text" name="nombre" /></td>
					</tr>
					<tr>
						<td>Atributo(s)</td>
					</tr>
					<tr>
						<td><input type="text" name="atributo" /></td>
					</tr>
				</table>
				<div style="text-align: center">
					<a style="text-align: center" href="#" title="" class="add-author">AÒadir
						atributo</a><br>
					<br> <input class="btn btn-success" type="submit"
						value="Guardar"> <a class="btn btn-danger"
						href="<c:url value='#'/>">Cancelar</a>
				</div>
			</form>


			<script>
			jQuery(function(){
			    var counter = 1;
			    jQuery('a.add-author').click(function(event){
			        event.preventDefault();
			        counter++;
			        var newRow = jQuery('<tr><td><input type="text" name="atributo"' +
			            counter + '"/></td></tr>');

			        jQuery('table.authors-list').append(newRow);
					});
				});
			</script>

		</div>
	</div>
</section>


<c:if test="${response != null}">
	<script type="text/javascript">
	    $(window).load(function(){
	        $('#myModal').modal('show');
	    });
	</script>
</c:if>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					onclick="$('.modal').hide()">&times;</button>
				<h4 class="modal-title">Informaci€n de reporte</h4>
			</div>
			<div class="modal-body">
				<h5><strong style="color:#eb4800">${response}</strong></h5>
			</div>
			<div class="modal-footer">
				<a href="/presentacion/admin/"><button class="btn btn-inverse" type="button">Aceptar</button></a>
			</div>
		</div>
	</div>
</div>


<%@include file="footer.jsp"%>