
package br.com.carv.drugstore.resource;

import java.util.logging.Logger;


import br.com.carv.drugstore.payload.response.AddressResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/zip")
public class AddressResource {

private final Logger logger = Logger.getLogger(AddressResource.class.getName());
	
	@GET
	@Path("/{cep}")
	@Produces(MediaType.APPLICATION_JSON)
	public AddressResponse getAddressByCep(@PathParam("cep") String cep) {
		logger.info("Getting Address by Cep: " + cep);
		Client client = ClientBuilder.newClient(); 
		Response response = client.target(String.format("https://viacep.com.br/ws/%s/json", cep))
				.request(MediaType.APPLICATION_JSON).get();
		
		int status = response.getStatus();
		logger.info("Http Status Code: " + status);
		AddressResponse addressResponse = response.readEntity(AddressResponse.class);
		return addressResponse;
	}
}
