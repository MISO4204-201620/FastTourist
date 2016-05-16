<div class="span3 col">

	<div class="block" style="text-align: left !important">
		<ul class="nav nav-pills nav-stacked">
			<li class="dropdown"><a href="/presentacion/adminProveedor/"
				data-toggle="dropdown" class="dropdown-toggle">Servicios
					Individuales <b class="caret"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a href="/presentacion/adminProveedor/">Ver Servicios</a></li>
					<li class="divider"></li>
					<li><a href="/presentacion/adminProveedor/crear/">Crear
							Servicio</a></li>
				</ul></li>
			<li class="dropdown"><a href="/presentacion/adminProveedor/paquetes/"
				data-toggle="dropdown" class="dropdown-toggle">Paquetes de
					Servicios <b class="caret"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a href="/presentacion/adminProveedor/paquetes/">Ver Paquetes</a></li>
					<li class="divider"></li>
					<li><a href="/presentacion/adminProveedor/paquetes/crear/">Armar Paquete</a></li>
				</ul></li>
		</ul>
	</div>

	<div class="block">
		<ul class="nav nav-list">
			<li class="nav-header">Categorias</li>
			<li><a href="#">Alojamiento</a></li>
			<li><a href="#">Transporte</a></li>
			<li><a href="#">Alimentación</a></li>
			<li><a href="#">Paseos Ecologicos</a></li>
			<li><a href="#">Otras Categorias</a></li>			
		</ul>
	</div>
	<div class="block">
		<h4 class="title">
			<span class="pull-left"><span class="text">Servicios</span></span> <span
				class="pull-right"> <a class="left button" href="#myCarousel"
				data-slide="prev"></a><a class="right button" href="#myCarousel"
				data-slide="next"></a>
			</span>
		</h4>
		<div id="myCarousel" class="carousel slide">
			<div class="carousel-inner">
				<c:forEach items="${servicios}" var="servicio" begin="0" end="0">
					<div class="active item">
						<ul class="thumbnails listing-products">
							<li class="span3">
								<div class="product-box">
									<span class="sale_tag"></span> <a href="#"><img alt=""
										src="${servicio.rutaGaleria}"
										style="height: 150px !important;"></a><br /> <a href="#"
										class="title">${servicio.nombre}</a><br /> <a href="#"
										class="category">${servicio.categoria.nombre}</a>
								</div>
							</li>
						</ul>
					</div>
				</c:forEach>
				<c:forEach items="${servicios}" var="servicio" begin="1">
					<div class="item">
						<ul class="thumbnails listing-products">
							<li class="span3">
								<div class="product-box">
									<a href="#"><img alt="" src="${servicio.rutaGaleria}"
										style="height: 150px !important;"></a><br /> <a href="#"
										class="title">${servicio.nombre}</a><br /> <a href="#"
										class="category">${servicio.categoria.nombre}</a>
								</div>
							</li>
						</ul>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

</div>