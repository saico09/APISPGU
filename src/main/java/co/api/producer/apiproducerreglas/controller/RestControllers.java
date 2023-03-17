package co.api.producer.apiproducerreglas.controller;

import java.io.IOException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import co.api.producer.apiproducerreglas.jms.ProducerGestionDelBeneficio;
import co.api.producer.apiproducerreglas.vo.EnLineaUsuarios;
import co.api.producer.apiproducerreglas.vo.UsuariosMasivos;
import org.springframework.core.env.Environment;
import co.api.producer.apiproducerreglas.dao.GestionDelBeneficioDAO;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.json.JSONException;

@RestController
@RequestMapping(path = "/api")
public class RestControllers {

	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	private static final String CONTEXT = "context";
	private static final String APPLICATION_JSON = "application/json";
	private static final String REGLAS = "reglas";
	
	@Autowired
	private Environment env;

	@Autowired
	ProducerGestionDelBeneficio producer;

	@Autowired
	GestionDelBeneficioDAO gestionDelBeneficioDAO;

	// Procesamiento masivo
	@PostMapping("/producer/reglas/gestionDelBeneficio/total")
	public ResponseEntity<?> procesarTotalRegistros(@RequestParam(required = false) String title,
			@RequestBody UsuariosMasivos ejecutaReglas) {

		final HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl("no-cache, no-store, must-revalidate");
		headers.setPragma("no-cache");
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		headers.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON);

		logger.info("Ingreso en procesamiento total registros");

		int ejecutaReglasLength = ejecutaReglas.getEjecutaReglas().length;

		ResponseEntity<?> data = ResponseEntity.ok().headers(headers)
				.body(gestionDelBeneficioDAO.listDataGestionDelBeneficio());
		JSONObject arr = new JSONObject((data));

		JSONObject listReglasAEjecutar = new JSONObject(env.getProperty("LISTAREGLAS"));

		for (int i = 0; i < arr.optJSONArray("body").length(); i++) {
			try {
				JSONObject dataGestionDelBeneficio = new JSONObject(arr.optJSONArray("body").get(i).toString());
				
				for (int e = 0; e < listReglasAEjecutar.optJSONArray(REGLAS).length(); e++) {
					dataGestionDelBeneficio.put(listReglasAEjecutar.optJSONArray(REGLAS).get(e).toString(), "0");
				}

				if (ejecutaReglasLength == 0) {
					for (int e = 0; e < listReglasAEjecutar.optJSONArray(REGLAS).length(); e++) {
						String valueReglaAEjecutar = listReglasAEjecutar.optJSONArray(REGLAS).get(e).toString();
						dataGestionDelBeneficio.put(valueReglaAEjecutar, valueReglaAEjecutar);
					}
				} else {
					for (int e = 0; e < listReglasAEjecutar.optJSONArray(REGLAS).length(); e++) {
						String valorDeEjecutarRegla = listReglasAEjecutar.optJSONArray(REGLAS).get(e).toString();
						for (int j = 0; j < ejecutaReglasLength; j++) {
							String valorDeSolicitud = String.valueOf(ejecutaReglas.getEjecutaReglas()[j]);
							if (valorDeSolicitud.equals(valorDeEjecutarRegla)) {
								dataGestionDelBeneficio.put(valorDeSolicitud, valorDeSolicitud);
								break;
							}
						}
					}
				}
				this.enviarTotalRegistros(dataGestionDelBeneficio.toString());
			} catch (JSONException ex) {
				logger.error(CONTEXT, ex);
			}
		}

		return new ResponseEntity<>("{ \"status\" : \"proceso completo\" }", headers, HttpStatus.OK);
	}

	// Procesamiento en linea
	@PostMapping("/producer/reglas/gestionDelBeneficio/enlinea")
	@ResponseBody()
	public ResponseEntity<?> procesarRegistrosEnLinea(@RequestParam(required = false) String title,
			@RequestBody EnLineaUsuarios enLineaUsuarios) throws IOException {

		logger.info("Ingreso en procesamiento en linea");

		final HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl("no-cache, no-store, must-revalidate");
		headers.setPragma("no-cache");
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		headers.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON);

		int bodyLength = enLineaUsuarios.getPosNumDocumento().length;
		ArrayList<String> resultadoUsuariosEnLinea = new ArrayList<>();
		int ejecutaReglasLength = enLineaUsuarios.getEjecutaReglas().length;

		JSONObject listReglasAEjecutar = new JSONObject(env.getProperty("LISTAREGLAS"));

		for (int i = 0; i < bodyLength; i++) {
			ResponseEntity<?> data = ResponseEntity.ok().headers(headers).body(gestionDelBeneficioDAO
					.listDataGestionDelBeneficioEnLinea(enLineaUsuarios.getPosNumDocumento()[i]));
			JSONObject arr = new JSONObject((data));

			try {
				JSONObject dataGestionDelBeneficioEnLinea = new JSONObject(arr.optJSONArray("body").get(0).toString());
				
				for (int e = 0; e < listReglasAEjecutar.optJSONArray(REGLAS).length(); e++) {
					dataGestionDelBeneficioEnLinea.put(listReglasAEjecutar.optJSONArray(REGLAS).get(e).toString(),
							"0");
				}

				if (ejecutaReglasLength == 0) {
					for (int e = 0; e < listReglasAEjecutar.optJSONArray(REGLAS).length(); e++) {
						String valueReglaAEjecutar = listReglasAEjecutar.optJSONArray(REGLAS).get(e).toString();
						dataGestionDelBeneficioEnLinea.put(valueReglaAEjecutar, valueReglaAEjecutar);
					}
				} else {
					for (int e = 0; e < listReglasAEjecutar.optJSONArray(REGLAS).length(); e++) {
						String valorDeEjecutarRegla = listReglasAEjecutar.optJSONArray(REGLAS).get(e).toString();
						for (int j = 0; j < ejecutaReglasLength; j++) {
							String valorDeSolicitud = String.valueOf(enLineaUsuarios.getEjecutaReglas()[j]);
							if (valorDeSolicitud.equals(valorDeEjecutarRegla)) {
								dataGestionDelBeneficioEnLinea.put(valorDeSolicitud, valorDeSolicitud);
								break;
							}
						}
					}
				}

				logger.info("bodyToProxy {}", dataGestionDelBeneficioEnLinea.toString());
				resultadoUsuariosEnLinea.add(this.enviarRegistroEnLinea(dataGestionDelBeneficioEnLinea.toString()));
			} catch (JSONException ex) {
				logger.error(CONTEXT, ex);
			}
		}
		return new ResponseEntity<>(resultadoUsuariosEnLinea.toString(), headers, HttpStatus.OK);
	}

	private boolean enviarTotalRegistros(String dataTotal) {
		try {
			producer.send("reglas-vivienda-gestion-subsidio", dataTotal);
		} catch (DataAccessException ex) {
			logger.error(CONTEXT, ex);
			return false;
		}
		return true;
	}

	private String enviarRegistroEnLinea(String bodyVerificacion) throws IOException {

		HttpPost postD = new HttpPost(
				"http://proxy-consumer-servicios-regla-negocios.apps.ocp.minvivienda.gov.co/proxy/consume/reglas/gestionDelBeneficio");
		postD.addHeader("content-type", APPLICATION_JSON);
		postD.addHeader("Authorization", "Basic YWRtaW5Vc2VyOlJlZEhhdA==");

		StringBuilder jsonD = new StringBuilder();
		jsonD.append(bodyVerificacion);

		postD.setEntity(new StringEntity(jsonD.toString()));

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(postD);

		String responseString = new BasicResponseHandler().handleResponse(response);

		return responseString;
	}

}