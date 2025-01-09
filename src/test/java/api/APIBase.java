package api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class APIBase {
	public Response response;

	public Response getResponse() {
		RestAssured.baseURI = "https://api.coindesk.com/v1/bpi/currentprice.json"; 
		RequestSpecification httpRequest = RestAssured.given(); 
		response = httpRequest.get();
		System.out.println(response.asPrettyString());
		return response;

	}
	public boolean validateBPIs(Response response, List<String> lst) throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(response.getBody().asPrettyString());
		Iterator<Entry<String,JsonNode>> KeyValueEntries= jsonNode.fields();
		boolean bpsflag = false;
		

		while(KeyValueEntries.hasNext()) {
			Entry<String,JsonNode> node = KeyValueEntries.next();
			if(node.getKey().equalsIgnoreCase("bpi")) {
				System.out.println(node.getKey()+ ":"+node.getValue());
				List<String> keys = new ArrayList<>();
				ObjectMapper mapper2 = new ObjectMapper();
				JsonNode jsonNode2 = mapper2.readTree(node.getValue().toPrettyString());
				Iterator<String> iterator2 = jsonNode2.fieldNames();
				iterator2.forEachRemaining(e -> keys.add(e));
				bpsflag = lst.equals(keys);
			}

		}
		return bpsflag;

	}

	public String validateGBPDes(Response response) throws JsonMappingException, JsonProcessingException {
		JsonPath path = response.jsonPath();
		return path.get("bpi.GBP.description").toString();


	}




}
