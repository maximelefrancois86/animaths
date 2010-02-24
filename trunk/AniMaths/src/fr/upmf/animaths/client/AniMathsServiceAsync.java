package fr.upmf.animaths.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AniMathsServiceAsync {

	public void loadEquation(String id, AsyncCallback<String> callback);

	public void loadPaths(String path, AsyncCallback<List<String>> callback);

}
