<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<div class="block" style="text-align:left !important">					
	<h4 class="title"><span class="text"><strong>Alimentación</strong></span></h4>
	<form action="#" method="post" class="form-stacked">
	<div class="control-group">
			<label class="control-label">¿Proveedor?</label>
			<div class="controls">
				<select class="input-large" name="proveedor">
					<option value="">Seleccione...</option>
					<c:forEach items="${proveedores}" var="proveedor"> 
					<option value="${proveedor.idusuario}">${proveedor.nombre}  ${proveedor.apellido}</option>
					</c:forEach>
				</select>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">¿En qué fecha?</label>
			<div class="controls">
				<div class="input-append date" id="dp1" data-date="2016-04-01"
					data-date-format="yyyy-mm-dd">
					<input class="input-small" size="16" type="text" name="fechaEntrada"
						placeholder="Entrada" readonly=""> <span class="add-on"><i
						class="icon-calendar"></i></span>
				</div>
				&nbsp;
				<div class="input-append date" id="dp2" data-date="2016-04-01"
					data-date-format="yyyy-mm-dd">
					<input class="input-small" size="16" type="text" name="fechaSalida"
						placeholder="Salida" readonly=""> <span class="add-on"><i
						class="icon-calendar"></i></span>
				</div>
			</div>
		</div>				
		<div class="control-group">
			
				<select class="input-medium" name=tipo>
					<option value="">Seleccione...</option>
					<c:forEach items="${tipos}" var="tipos">
						<option value="${tipos.idtipoalimentacion}">${tipos.nombre}</option>
					</c:forEach>
				</select>
			
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