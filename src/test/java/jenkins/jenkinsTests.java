package jenkins;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

public class jenkinsTests {



    @Test
    public void apiTest() {

        RequestSpecification spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();

        //Set the base url and path params
        spec.pathParams("first", "booking", "second", 331);


        //Send the Get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        response.prettyPrint();

        Map<String,Object> actualdata = response.as(HashMap.class);

        Map<String,Object> bookingdates = (Map)actualdata.get("bookingdates");

        assertEquals("Dane",actualdata.get("firstname"));
        assertEquals("Vera",actualdata.get("lastname"));
        assertEquals(111,actualdata.get("totalprice"));
        assertEquals(true,actualdata.get("depositpaid"));

        assertEquals("2018-01-01",bookingdates.get("checkin"));
        assertEquals("2019-01-01",bookingdates.get("checkout"));

     //   response.then().body("Dane",equalTo("firstname"));

    }

}
