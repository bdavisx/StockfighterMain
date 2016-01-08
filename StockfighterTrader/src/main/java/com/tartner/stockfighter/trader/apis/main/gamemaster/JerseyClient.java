package com.tartner.stockfighter.trader.apis.main.gamemaster;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class JerseyClient {

    public static void main(String[] args) {
        try {
            ClientConfig clientConfig = new DefaultClientConfig();

            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

            Client client = Client.create(clientConfig);

            final URI uri = UriBuilder.fromUri("https://www.stockfighter.io/gm/levels/{levelName}")
                .resolveTemplate("levelName", "first_steps")
                .build();
            WebResource webResource = client.resource(uri.toString());

            ClientResponse response = webResource.accept("application/json").type("application/json")
                .post(ClientResponse.class);

            if(response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            String output = response.getEntity(String.class);

            System.out.println("Server response .... \n");
            System.out.println(output);

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

}