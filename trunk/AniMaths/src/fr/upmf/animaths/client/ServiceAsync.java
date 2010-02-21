package fr.upmf.animaths.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.upmf.animaths.client.mvp.MathObject.Equation;

public interface ServiceAsync {

	public void saveExercice(AsyncCallback<Boolean> callback);

	public void loadExercice(String id, AsyncCallback<Equation> callback);

}
