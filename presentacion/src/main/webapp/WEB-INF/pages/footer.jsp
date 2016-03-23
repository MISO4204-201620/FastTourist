	<section id="footer-bar">
		<div class="row">
			<div class="span3">
				<h4>Navegación</h4>
				<ul class="nav">
					<li><a href="/presentacion/">Inicio</a></li>  
					<li><a href="/presentacion/nosotros">Sobre Nosotros</a></li>
					<li><a href="/presentacion/contactenos">Contactenos</a></li>
					<li><a href="/presentacion/carrito">Mi Carrito</a></li>
					<li><a href="/presentacion/registro">Ingresar</a></li>							
				</ul>					
			</div>
			<div class="span4">
				<h4>Mi Cuenta</h4>
				<ul class="nav">
					<li><a href="#">Mi Cuenta</a></li>
					<li><a href="#">Mis Transacciones</a></li>

				</ul>
			</div>
			<div class="span5">
				<p class="logo"><img src="${pageContext.request.contextPath}/resources/themes/images/logo.png" class="site_logo" alt=""></p>
				<p>FastTourist es un producto de FastFactory s.a</p>
				<br/>
				<span class="social_icons">
					<a class="facebook" href="#">Facebook</a>
					<a class="twitter" href="#">Twitter</a>
					<a class="skype" href="#">Skype</a>
					<a class="vimeo" href="#">Vimeo</a>
				</span>
			</div>					
		</div>	
	</section>
	<section id="copyright">
		<span>Copyright 2016 FastFactory All right reserved.</span>
	</section>
	
	<script src="${pageContext.request.contextPath}/resources/themes/js/common.js"></script>
	<script>
		$(function () {
			$('#myTab a:first').tab('show');
			$('#myTab a').click(function (e) {
				e.preventDefault();
				$(this).tab('show');
			})
		})
		$(document).ready(function() {
			$('.thumbnail').fancybox({
				openEffect  : 'none',
				closeEffect : 'none'
			});
			
			$('#myCarousel-2').carousel({
	                  interval: 2500
	              });								
		});
	</script>
</body>
</html>