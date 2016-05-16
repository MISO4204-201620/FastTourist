package reportes.rest;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import fabricas.entidades.Busquedas;
import fabricas.entidades.Transacciones;

public interface IReportes {

	public ResponseEntity<ArrayList<Busquedas>> getBusquedas(@PathVariable int idProveedor);

	
}
