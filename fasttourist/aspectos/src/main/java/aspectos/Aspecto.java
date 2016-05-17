package aspectos;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.client.RestTemplate;


@Aspect
public class Aspecto {

	@Pointcut("execution(* fabricas.presentacion.controladores.AlimentacionControlador.getAlimentacion(..))")
	public void alimentacion() {
	}

	@Pointcut("execution(* fabricas.presentacion.controladores.AlojamientoControlador.getAlojamiento(..))")
	public void alojamiento() {
	}

	@Pointcut("execution(* fabricas.presentacion.controladores.TransporteControlador.getTransporte(..))")
	public void transporte() {
	}

	@Pointcut("execution(* fabricas.presentacion.controladores.PaseosController.darPaseoEcologico(..))")
	public void paseos() {
	}


	@Before("alimentacion() || alojamiento() || transporte() || paseos()")
	public void antesPunto(JoinPoint joinPoint) {

		System.out.println("Advice antes de foo" + joinPoint.getThis());
	}

	@After("alimentacion() || alojamiento() || transporte() || paseos()")
	public void despuesPunto(JoinPoint joinPoint) {
		System.out.println("Advice después de foo");
	}

	@AfterReturning(pointcut="alimentacion() || alojamiento() || transporte() || paseos()", returning= "result")
	public void handlingReturnCollection(JoinPoint joinPoint, Object result) {
		String respuesta = result.toString();
		if(!respuesta.contains("model is null")){
			Object[] argumentos = joinPoint.getArgs();
			respuesta=respuesta.substring(respuesta.indexOf("idusuarioAutenticado="));
			respuesta=respuesta.substring(respuesta.indexOf("=")+1, respuesta.indexOf(","));

			RestTemplate restTemplate = new RestTemplate();
			Map<String, Integer> map = new HashMap<String, Integer>();

			if(respuesta != null && !respuesta.isEmpty() && !respuesta.equals("null")){
				map.put("idService", new Integer(argumentos[0].toString()));
				map.put("idUser", new Integer(respuesta));
				System.out.println(map);
				String uri = "http://localhost:8080/busqueda/";
				String retorno = (String) restTemplate.postForObject(uri,map,String.class);
			}
		}
	}

	@AfterThrowing(pointcut="alimentacion() || alojamiento() || transporte() || paseos()",  throwing="ex")
	public void doRecoveryActions( Exception ex) {
		System.out.println(ex);
	}
}