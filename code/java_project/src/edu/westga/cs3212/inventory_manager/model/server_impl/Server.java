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
	 * Creates a server object to send requests to the server.
	 *
	 * @param request the request string to send to the server
	 * @return the response string from the server
	 * @precondition request != null && request is valid (see README)
	 * @postondition none
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
	 * Send request and get response.
	 *
	 * @param requestType the request type
	 * @param requestData the request data
	 * @return the map
	 * @throws IllegalArgumentException the illegal argument exception
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
