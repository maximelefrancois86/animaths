package fr.upmf.animaths.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoadPathNamesServiceAsync{

	void loadPathNames(String path, AsyncCallback<List<String>> callback);

}
