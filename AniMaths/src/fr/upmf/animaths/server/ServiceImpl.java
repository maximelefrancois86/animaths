package fr.upmf.animaths.server;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.upmf.animaths.client.Service;
import fr.upmf.animaths.client.mvp.AniMathsPresenter.Display;
import fr.upmf.animaths.client.mvp.MathObject.Equation;
import fr.upmf.animaths.client.mvp.MathObject.MOEquation;

/**
 * @description server-side load and save current exercice 
 */
public class ServiceImpl extends RemoteServiceServlet implements
		Service {

	private static final long serialVersionUID = 8772776810359697699L;

	@Override
	public Equation loadExercice(String id) {
//		MOIdentifier x = new MOIdentifier("x");
		Equation eq = new Equation();
		MOEquation moeq = eq.generateEquation();
		
		Window.alert("Server side");
		return eq;
	}

	@Override
	public Boolean saveExercice() {
		// TODO Auto-generated method stub
		return null;
	}

}
