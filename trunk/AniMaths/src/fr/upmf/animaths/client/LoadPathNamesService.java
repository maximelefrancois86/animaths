package fr.upmf.animaths.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *  The RPC api available to the client. The asynchronous version that is used
 * directly by the client is {@link LoadPathNamesServiceAsync}.
 * @author Maxime Lefrançois & Édouard Lopez
 *0
 */
@RemoteServiceRelativePath("loadPathNamesService")
public interface LoadPathNamesService extends RemoteService {
	
	  List<String> loadPathNames(String path);

}
