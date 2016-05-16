package fabricas.presentacion.controladores;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fabricas.presentacion.VOs.BusquedasVO;
import fabricas.presentacion.VOs.TransaccionesVO;
import fabricas.presentacion.VOs.UsuarioVO;
import utilidades.Constantes;
import utilidades.utilidades;

@Controller
@RequestMapping("/reportes")
public class ReportesController{

	private static final String REPORTE = "reportes";
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView darReporteProveedor(
			@PathVariable("id")int id)
	{
		
		String mensaje = "";
		//Busquedas, transacciones, consultas

		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		//Buscar proveedores
		UsuarioVO prov = new UsuarioVO();
		List<UsuarioVO> proveedores = new ArrayList<>();
		List<TransaccionesVO> transacciones = new ArrayList<>();
		List<BusquedasVO> busquedas = new ArrayList<>();

		//Get proveedor solicitado
		String result = restTemplate.getForObject("http://localhost:8080/logica/admin/getProveedor/"+id, String.class);
		try {
			prov = mapper.readValue(result, new TypeReference<UsuarioVO>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Get todos proveedores
		result = restTemplate.getForObject("http://localhost:8080/logica/admin/", String.class);
		try {
			proveedores = mapper.readValue(result, new TypeReference<List<UsuarioVO>>(){});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//En caso que se acceda desde la URI, si es módulo es false entonces no deja hacer consultas ni Excel
		if(Constantes.MODULO_REPORTES.equals("false"))
		{
			mensaje = "No se ha creado el archivo de excel. Módulo inhabilitado";
			
			ModelAndView modelAndView = new ModelAndView("admin");
			modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
			modelAndView.addObject("proveedores", proveedores);
			modelAndView.addObject("response", mensaje);
			return modelAndView;	
		}
		
		
		//Transacciones
		result = restTemplate.getForObject("http://localhost:8080/reportes/getFromProveedor/"+prov.getIdusuario(), String.class);
		try {
			transacciones = mapper.readValue(result, new TypeReference<List<TransaccionesVO>>(){});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//Busquedas
		{
			result = restTemplate.getForObject("http://localhost:8080/reportes/buscarPorUsuario/"+prov.getIdusuario(), String.class);
			try {
				busquedas = mapper.readValue(result, new TypeReference<List<BusquedasVO>>(){});
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
			
		//Consultas
//		result = restTemplate.getForObject("http://localhost:8080/logica/reporte/getConsultasProv/"+prov.getIdusuario(), String.class);
//		try {
//			consultas = mapper.readValue(result, new TypeReference<List<ConsultasVO>>(){});
//
//		} catch (Exception e) {
//			mensaje = "Se ha presentado un error";
//			System.out.println(e.getMessage());
//		}


		int fila = 0;
		try {
			String filename = "ReporteProveedor"+prov.getIdusuario()+".xls" ;
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("FirstSheet");  
			HSSFRow rowhead = sheet.createRow((short)fila);
			fila++;
			rowhead.createCell(0).setCellValue("Transacciones");
			rowhead = sheet.createRow((short)fila);
			fila++;
			rowhead.createCell(0).setCellValue("Usuario");
			rowhead.createCell(1).setCellValue("Servicio");
			rowhead.createCell(2).setCellValue("Fecha");

			int i = 0;
			while(i<transacciones.size())
			{
				TransaccionesVO tr = transacciones.get(i);
				rowhead = sheet.createRow((short)fila);
				rowhead.createCell(0).setCellValue(tr.getUsuario().getNombre()+" "+tr.getUsuario().getApellido());
				rowhead.createCell(1).setCellValue(tr.getServicio().getNombre());
				rowhead.createCell(2).setCellValue(tr.getFecha().toString());
				i++;
				fila++;
			}
			
			if(transacciones.size() == 0){
				rowhead = sheet.createRow((short)fila);
				rowhead.createCell(0).setCellValue("No se han registrado trnasacciones");
				fila++;
			}

			fila++;
			
			HSSFRow row = sheet.createRow((short)fila);
			fila++;
			row.createCell(0).setCellValue("Búsquedas");
			row = sheet.createRow((short)fila);
			fila++;
			row.createCell(0).setCellValue("Usuario");
			row.createCell(1).setCellValue("Servicio");
			row.createCell(2).setCellValue("Fecha");
			
			String serv = "";
			int idServ = -1;
			int cont = 0;
			
			i = 0;
			while(i<busquedas.size())
			{
				BusquedasVO tr = busquedas.get(i);
				rowhead = sheet.createRow((short)fila);
				rowhead.createCell(0).setCellValue(tr.getUsuario().getNombre()+" "+tr.getUsuario().getApellido());
				rowhead.createCell(1).setCellValue(tr.getServicio().getNombre());
				rowhead.createCell(2).setCellValue(tr.getFecha().toString());
				
				if(idServ == tr.getServicio().getIdservicios() || idServ == -1)
					{
						cont++;
						if(idServ==-1)
							idServ = tr.getServicio().getIdservicios();
					}
				if(idServ != tr.getServicio().getIdservicios() || i+1==busquedas.size()){
					fila++;
					row = sheet.createRow((short)fila);

					row.createCell(0).setCellValue("El servicio "+serv+" has sido consultado "+ cont +" veces");
					idServ = tr.getServicio().getIdservicios();
					serv = tr.getServicio().getNombre();
					cont = 0;
					fila++;
				}
				
				i++;
				fila++;
			}
			
			if(busquedas.size() == 0){
				rowhead = sheet.createRow((short)fila);
				rowhead.createCell(0).setCellValue("No se han registrado búsquedas");
				fila++;
			}

			
			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated!");

		} catch ( Exception ex ) {
			mensaje = "Se ha presentado un error";
			System.out.println(ex);
		}

		mensaje = "Se ha generdo el excel adecuadamente";
		
		ModelAndView modelAndView = new ModelAndView("admin");
		modelAndView.addObject("usuarioAutenticado",utilidades.getSessionUser());
		modelAndView.addObject("proveedores", proveedores);
		modelAndView.addObject("response", mensaje);
		return modelAndView;	
	}
	
	
}
