package com.qa.mystepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.cucumber.java.en.Then;
import java.util.List;
import org.testng.Assert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import api.APIBase;

public class apiBPIsValidationDef {
	private APIBase apiBase ;
	public Response response;

	@When("User send Get request")
	public void user_send_get_request() {
		apiBase = new APIBase();
		response = apiBase.getResponse();	}

	@Then("Verify the list of BPIs")
	public void verify_the_list_of_bp_is(DataTable dataTable) throws JsonMappingException, JsonProcessingException {
		List<String> bpiList = dataTable.asList(String.class);
		Assert.assertTrue(apiBase.validateBPIs(response,bpiList ));
	}
	
	@Then("Verify GBP Description as {string}")
	public void verify_gbp_description_as(String des) throws JsonMappingException, JsonProcessingException {
		String gbpdes = apiBase.validateGBPDes(response);
		Assert.assertEquals(gbpdes,des);

	}
	
}

