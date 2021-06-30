package com.flipkart.restController;

import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {

	register(StudentRESTApi.class);
	register(UserRESTApi.class);
	register(ProfessorRESTApi.class);
	register(AdminRESTApi.class);
	}

}