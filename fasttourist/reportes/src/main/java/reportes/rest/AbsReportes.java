package reportes.rest;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import fabricas.entidades.Busquedas;
import fabricas.entidades.Transacciones;

public abstract class AbsReportes implements IReportes{

	protected IReportes iRep;
	
	

	
	
	
	public ResponseEntity<ArrayList<Busquedas>> getBusquedas(int idProveedor) 
	{
		return iRep.getBusquedas(idProveedor);
	}
	
	
}
