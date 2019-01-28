package br.com.unimeduberaba.util.slack;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.unimeduberaba.util.Json;
import br.com.unimeduberaba.util.slack.domain.Envio;

public class Slack {
	public static final String link = "https://hooks.slack.com/services/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";

	public static void enviar(Envio envio) {

		try {
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(link);

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.post(Entity.entity(Json.toJson(envio), MediaType.APPLICATION_JSON));

			if (response.getStatus() != 200) {
				System.err.println("Erro ao enviar para o Slack");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
