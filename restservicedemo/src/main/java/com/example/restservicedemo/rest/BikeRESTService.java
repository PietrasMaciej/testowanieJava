package com.example.restservicedemo.rest;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Bike;
import com.example.restservicedemo.service.BikeManager;

@Path("bike")
public class BikeRESTService {
	
	private BikeManager bm = new BikeManager();
	
	@GET
	@Path("/{bikeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Bike getBike(@PathParam("bikeId") Long id) {
		Bike b = bm.getBike(id);
		return b;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBike(Bike bike) {
		bm.addBike(bike);
		return Response.status(201).entity("Car").build();
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bike> getAllBikes() {
		List<Bike> cars = bm.getAllBikes();
		return cars;
}
	
	@GET
	@Path("/testBike")
	@Produces(MediaType.TEXT_HTML)
	public String testBike() {
		return "REST API /car is running";
	}
	
	@DELETE
	public Response clearBikes() {
		bm.clearBikes();
		return Response.status(200).build();
	}

}