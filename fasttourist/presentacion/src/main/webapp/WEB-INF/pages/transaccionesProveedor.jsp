<%@include file="header.jsp"%>
<script src="${pageContext.request.contextPath}/resources/amcharts/amcharts.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/amcharts/serial.js" type="text/javascript"></script>
<script type="text/javascript">
	var chartData = ${fechas}
	
	AmCharts.ready(function() {
		var chart = new AmCharts.AmSerialChart();
		chart.dataProvider = chartData;
		chart.categoryField = "fecha";
		
		var graph = new AmCharts.AmGraph();
		graph.valueField = "valor";
		graph.type = "column";
		chart.addGraph(graph);
		
		chart.write('chartdiv');

		var categoryAxis = chart.categoryAxis;
		categoryAxis.autoGridCount  = false;
		categoryAxis.gridCount = chartData.length;
		categoryAxis.gridPosition = "start";
	});
</script>

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
				<li><a href="/presentacion/transacciones/" 
						class="paginaActiva"><span
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

		<div class="span2 col">
			<div class="block" style="text-align: left !important">
				<h4 class="title">
					<span class="text"><strong>Opciones</strong></span>
				</h4>
				<a href="/presentacion/admin/nuevo/"> 
					Agregar Proveedor
				</a>
			</div>
		</div>
		<div class="span9 col">
		
		    <div id="chartdiv" style="width: 640px; height: 400px;"></div>

<br><br>
			<h4 style="text-align: center; color: #eb4800;">Transacciones</h4>
			<table class="table table-striped" id="tablaUsuarios">
				<thead>
					<tr>
						<th width="4%">Id</th>
						<th width="10%">Fecha</th>
						<th width="15%">Cliente</th>
						<th width="20%">Servicio</th>
						<th width="6%">Compra</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${transacciones}" var="transaccion">
						<tr>
							<td><span>${transaccion.idtransacciones}</span></td>
							<td><span>${transaccion.fecha}</span></td>
							<td><span>${transaccion.usuario.nombre} ${transaccion.usuario.apellido}</span></td>
							<td><span>${transaccion.servicio.nombre}</span></td>
							<td><span>${transaccion.estadoTransaccion.nombre}</span></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
	</div>
</section>


<%@include file="footer.jsp"%>