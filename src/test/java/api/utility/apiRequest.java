package api.utility;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class apiRequest {
	
	/**
	 * 
	 * @param endPoint
	 * @return the statusCode of end point
	 */
	
	public static int  getStatusCode(String endPoint) {
		Response res = RestAssured.get(endPoint);
		return res.getStatusCode();
	}

}
