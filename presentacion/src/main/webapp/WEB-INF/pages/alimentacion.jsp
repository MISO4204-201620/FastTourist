<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<div class="block" style="text-align:left !important">					
	<h4 class="title"><span class="text"><strong>Alimentación</strong></span></h4>
	<form action="#" method="post" class="form-stacked">					
		<div class="control-group">
			<label class="control-label">¿Qué tanto quieres comer?</label>
			<label class="radio">
				<input type="radio" name="optionsRadios" id="optionsRadios1" value="unaComida" checked="">
				Una sóla comida
			</label>
			<label class="radio">
				<input type="radio" name="optionsRadios" id="optionsRadios2" value="mediaPension" checked="">
				Media Pensión
			</label>
	        <label class="radio">
				<input type="radio" name="optionsRadios" id="optionsRadios3" value="pensionCompleta" checked="">
				Pensión completa
			</label>
	        <label class="radio">
				<input type="radio" name="optionsRadios" id="optionsRadios4" value="todoIncluido" checked="">
				Todo incluido
			</label>
			<hr>
	        <div class="actions"><input tabindex="9" class="btn btn-inverse large" type="submit" value="Buscar"></div>	        
		</div>					
	</form>					
</div>	
</body>
</html>