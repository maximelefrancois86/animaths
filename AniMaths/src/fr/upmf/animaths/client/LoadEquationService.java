package fr.upmf.animaths.client;

import java.io.IOException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *  The RPC api available to the client. The asynchronous version that is used
 * directly by the client is {@link LoadEquationServiceAsync}.
 * @author Maxime Lefrançois & Édouard Lopez
 *0
 */
@RemoteServiceRelativePath("loadEquationService")
public interface LoadEquationService extends RemoteService {
	
	  String loadEquation(String id) throws IOException;

}
