package com.wonderfulshirt.glue;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.ValidatableResponse;

public class StepDefs {

  private Response response;
  private ValidatableResponse json;
  private RequestSpecification request;

  // This should be the service endpoint but for demo purposes we're calling wiremock directly
  private String ENDPOINT_WIREMOCK = "http://localhost:8080/test";

  @Given("a request has a header (.*) with the value (.*)")
  public void request_with_header(String header, String value){
    request = given().header(header, value);
  }

  @When("the service is called")
  public void service_called(){
    response = request.when().get(ENDPOINT_WIREMOCK);
  }

  @Then("the status code is (\\d+)")
  public void verify_status_code(int statusCode){
    json = response.then().statusCode(statusCode);
  }

  /**
   * asserts on json fields with single values
   */
  @Then("the response includes the following$")
  public void response_includes(Map<String,String> responseFields){
    for (Map.Entry<String, String> field : responseFields.entrySet()) {
      if(StringUtils.isNumeric(field.getValue())){
        json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
      }
      else{
        json.body(field.getKey(), equalTo(field.getValue()));
      }
    }
  }


  /**
   * asserts on json arrays
   */
  @Then("the response includes the following in any order")
  public void response_contains_in_any_order(Map<String,String> responseFields){
    for (Map.Entry<String, String> field : responseFields.entrySet()) {
      if(StringUtils.isNumeric(field.getValue())){
        json.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
      }
      else{
        json.body(field.getKey(), containsInAnyOrder(field.getValue()));
      }
    }
  }


}
