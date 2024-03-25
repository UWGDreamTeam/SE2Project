package edu.westga.cs3212.inventory_manager.model.server_impl;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Supports making requests to the server.
 * 
 * @author Jason Nunez
 * @version Spring 2024
 */
public class Server {

	/** The Constant CONNECTION_STRING. */
	private static final String CONNECTION_STRING = "tcp://127.0.0.1:5555";

	/**
	 * Sends a request to the server and returns the server's response as a String.
	 *
	 * @param request the request string to send to the server. The request should be in a format specified in the system's README documentation.
	 * 
	 * @precondition 	request != null && request is valid as per the format defined in the README. The request 
	 * 					format includes specific types of operations or queries the server can handle.
	 * 
	 * @postcondition 	A non-null string response is returned from the server. If the server identifies the request 
	 * 					as having a "bad format", an IllegalArgumentException is thrown with a message 
	 * 					to provide a valid request type.
	 * 
	 * @return 	the response string from the server, which could be the result of the request or an error message 
	 * 			indicating "bad format" if the request is not in a valid format.
	 * 
	 * @throws 	IllegalArgumentException if the request is null or if the server returns a response 
	 * 			indicating "bad format". The exception message provides guidance on ensuring the request matches the expected format.
	 */
	public static String sendRequest(String request) {
		if (request == null) {
			throw new IllegalArgumentException("request must not be null");
		}
		
		Context context = ZMQ.context(1);
		Socket socket = context.socket(ZMQ.REQ);
		
		socket.connect(Server.CONNECTION_STRING);
		socket.send(request.getBytes(ZMQ.CHARSET), 0);
		
		byte[] reply = socket.recv(0);
		String response = new String(reply, ZMQ.CHARSET);
		
		if (response.equals("bad format")) {
			throw new IllegalArgumentException("Must provide a valid request type (see README)");
		}
		
		return response;
	}

	
	/**
	 * Sends a request with specified type and data to the server and parses the server's response into a Map<String, Object>.
	 *
	 * @param requestType the type of the request, which categorizes the request for the server. Examples include "query", "update", etc.
	 * @param requestData a map containing the data for the request. This map must include a String key "data" with the request's payload Object.
	 * 
	 * @precondition 	requestType is a valid type understood by the server, and requestData contains a valid "data" key with 
	 * 					data structured according to the operation specified by requestType.
	 * 
	 * @postcondition 	The method returns a Map<String, Object> with at least a "status" key. If "status" is "error", 
	 * 					an IllegalArgumentException is thrown with the error message provided by the server. Otherwise, 
	 * 					the "data" key in the returned map contains the result of the request.
	 * 
	 * @return 	a Map<String, Object> parsed from the JSON response from the server. The map includes a "status" key that indicates 
	 * 			the success or failure of the operation, and a "data" key with the operation's result if successful.
	 * 
	 * @throws 	IllegalArgumentException if the requestType is not recognized by the server, if the response from the server 
	 * 			does not contain a "status" key, or if the "status" is "error" and potentially includes a "message" key with details about the error.
	 * 
	 * 
	 * The method utilizes the Gson library to serialize the requestData into JSON format for the request and to deserialize the server's response. 
	 * An IllegalArgumentException is thrown for any issues with the server's response, including missing "status" key or error status 
	 * with an optional error message.
	 */
	public static Map<String, Object> sendRequestAndGetResponse(String requestType, Map<String, Object> requestData)
			throws IllegalArgumentException {
		Map<String, Object> fullRequestData = new HashMap<>();
		fullRequestData.put("type", requestType);
		fullRequestData.put("data", requestData.get("data"));

		Gson gson = new Gson();
		String requestJson = gson.toJson(fullRequestData);
		String response = Server.sendRequest(requestJson);
		
		Type responseType = new TypeToken<Map<String, Object>>() {
		}.getType();
		
		Map<String, Object> responseMap = gson.fromJson(response, responseType);

		if (!responseMap.containsKey("status")) {
			throw new IllegalArgumentException("Response from server is missing the status key.");
		}

		String status = (String) responseMap.get("status");
		
		if ("error".equals(status)) {
			String errorMessage = responseMap.containsKey("message") ? (String) responseMap.get("message")
					: "Unknown error occurred";
			throw new IllegalArgumentException(errorMessage);
		}

		return safelyCastToMap(responseMap.get("data"));
	}

	/**
	 * Safely cast to map.
	 *
	 * @param object the object
	 * @return the map
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, Object> safelyCastToMap(Object object) {
		return (Map<String, Object>) object;
	}

}
