package fr.upmf.animaths.client.mvp.MathObject;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.RootPanel;


public class Equation {
		public ArrayList<MOEquation> equationSystem; 
	
		public Equation()
		{
			equationSystem = new ArrayList<MOEquation>();
		}
		
		/**
		 * 
		 * @return a mockup equation object
		 */
		public MOEquation generateEquation()
		{
			MOIdentifier x = new MOIdentifier("x");
			MOEquation eq = new MOEquation();
			eq.setLeftHandSide(
				new MOAddContainer(
						new MOSignedElement(new MOIdentifier("x"),true),
						new MOSignedElement(new MONumber(10)),
						new MOSignedElement(
								new MOMultiplyContainer(
										new MOMultiplyElement(new MONumber(2)),
										new MOMultiplyElement(
												new MOAddContainer(
														new MOSignedElement(new MOIdentifier("x"),false),
														new MOSignedElement(new MONumber(1),true)
												)
										)
								)
						)
				)
			);
			eq.setRightHandSide(
				new MOMultiplyContainer(
						new MOMultiplyElement(
								new MOSignedElement(
										new MOSignedElement(
												new MOSignedElement(
														new MONumber(3),
												true),
										false),
								true)
						),
						new MOMultiplyElement(
								new MOAddContainer(
										new MOSignedElement(
												new MOMultiplyContainer(
														new MOMultiplyElement(new MONumber(2)),
														new MOMultiplyElement(new MOIdentifier("x"))
												)
										),
										new MOSignedElement(new MONumber(1))
								)
						)
				)
			);
			
			equationSystem.add(eq);
			return eq;
		}
		
		public void newEquationLine()
		{
			MOEquation last = equationSystem.get(equationSystem.size());
			this.equationSystem.add(last);
			last.bind();
		}
}
