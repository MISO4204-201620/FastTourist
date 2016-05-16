package reportes.rest;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import fabricas.entidades.Busquedas;
import fabricas.entidades.Transacciones;

public class ReportesBasica implements IReportes{

	@Override
	public ResponseEntity<ArrayList<Busquedas>> getBusquedas(int idProveedor) {
		// TODO Auto-generated method stub
		return null;
	}

}
