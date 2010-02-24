package fr.upmf.animaths.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *  The RPC api available to the client. The asynchronous version that is used
 * directly by the client is {@link LoadEquationServiceAsync}.
 * @author Édouard Lopez & Maxime Lefrancois
 *0
 */
@RemoteServiceRelativePath("loadEquationService")
public interface LoadEquationService extends RemoteService {
	
	  String loadEquation(String id);

}
