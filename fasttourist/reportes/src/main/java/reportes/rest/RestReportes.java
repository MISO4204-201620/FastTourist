package reportes.rest;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fabricas.entidades.Busquedas;
import fabricas.entidades.Transacciones;
import utilidades.Constantes;

@RestController
@RequestMapping("/")
public class RestReportes {

	
	@RequestMapping(value = "/buscarPorUsuario/{idProveedor}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Busquedas>> getBusquedas(@PathVariable int idProveedor) {
		
		if(Constantes.MODULO_REPORTES.equals("true")){
			Reportes rep = new Reportes();
			return rep.getBusquedas(idProveedor);			
		}
		else{
			ReportesBasica rBas = new ReportesBasica();
			return rBas.getBusquedas(idProveedor);			
		}		
	}
	
	
	@RequestMapping(value = "/getFromProveedor/{idProveedor}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Transacciones>> getTransacciones(@PathVariable int idProveedor) {
		
		if(Constantes.MODULO_REPORTES.equals("true")){
			Reportes rep = new Reportes();
			return rep.getTransacciones(idProveedor);			
		}
		else{
			return null;
		}
		
	}
	
	
}
