package fr.upmf.animaths.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AniMathsServiceAsync {

	public void loadEquation(String id, AsyncCallback<String> callback);

}
