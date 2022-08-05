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
    public void apiTest(){

        RequestSpecification spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();

        //Set the base url and path params
        spec.pathParams("first","booking","second",3);


        //Send the Get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        response.prettyPrint();

        Map<String,Object> actualdata = response.as(HashMap.class);

        Map<String,Object> bookingdates = (Map)actualdata.get("bookingdates");

        assertEquals("Jim",actualdata.get("firstname"));
        assertEquals("Jackson",actualdata.get("lastname"));
        assertEquals(982,actualdata.get("totalprice"));
     //   assertEquals(false,actualdata.get("depositpait"));

        assertEquals("2018-12-06",bookingdates.get("checkin"));
        assertEquals("2019-09-05",bookingdates.get("checkout"));

        response.then().body("Jim",equalTo("firstname"));

    }

}
