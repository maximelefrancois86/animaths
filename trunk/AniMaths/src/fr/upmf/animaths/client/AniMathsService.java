package fr.upmf.animaths.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *  The RPC api available to the client. The asynchronous version that is used
 * directly by the client is {@link AniMathsServiceAsync}.
 * @author Édouard Lopez & Maxime Lefrancois
 *
 */
@RemoteServiceRelativePath("service")
public interface AniMathsService extends RemoteService {
	
	  String loadEquation(String id);

}
