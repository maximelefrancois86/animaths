//package fr.upmf.animaths.client.mvp.modele;
//
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectAddContainerPresenter;
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectElement;
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectMultiplyContainerPresenter;
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectMultiplyElementPresenter;
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectNumberPresenter;
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectSignedElementPresenter;
//
//
//public class ExpressionGenerator {
//
//	private int complexity=2;
//	private int nAdd=2;
//	private int nMult=2;
//	
//	public ExpressionGenerator(){}
//
//	public ExpressionGenerator(int complexity, int nAdd, int nMult){
//		this.complexity = complexity;
//		this.nAdd = nAdd;
//		this.nMult = nMult;
//	}
//
//	public MathObjectElement generateExpression(MathObjectElement mathObjectParent) {
//		return generateMathObjectElement(mathObjectParent, 0);
//	}
//	
//	public MathObjectElement generateMathObjectElement(MathObjectElement mathObjectParent,int i) {
//		MathObjectElement object;
////		double seuil = 1.001-Math.exp(-i/complexity);
////		if(Math.random()>seuil) {
//		if(i<complexity) {
////			double r = Math.random();
////			if(r<0.2) {
////				object = generateRandomSignedElement(mathObjectParent,i+1);
////				return object;
////			}
////			else if(0.2<r && r<0.6) {
////				object = generateRandomMultiplyContainer(mathObjectParent,i+1);
////				return object;
////			}
////			else if(0.6<r && r<1.0){
////				object = generateRandomAddContainer(mathObjectParent,i+1);
////				return object;
////			}
//		object = generateRandomMultiplyContainer(mathObjectParent,i+1);
//		return object;
//		}
//		else {
//			object = new MathObjectNumberPresenter((int)(Math.random()*10));
//			object.setMathObjectParent(mathObjectParent);
//			return object;
//		}
////		return null;
//	}
//	
//	public MathObjectSignedElementPresenter generateRandomSignedElement(MathObjectElement mathObjectParent,int i) {
//		MathObjectSignedElementPresenter object = new MathObjectSignedElementPresenter();
//		object.setMathObjectParent(mathObjectParent);
//		if(Math.random()<0.5)
//			object.setMinus(true);
//		else
//			object.setMinus(false);
//		object.setChild(generateMathObjectElement(object,i));
//		return object;	
//	}
//	
//	public MathObjectAddContainerPresenter generateRandomAddContainer(MathObjectElement mathObjectParent,int i) {
//		MathObjectAddContainerPresenter object = new MathObjectAddContainerPresenter();
//		object.setMathObjectParent(mathObjectParent);
//		int n = (int) (Math.random()*nAdd+2);
//		for(int j=0;j<n;j++) {
//			object.addChild(generateRandomSignedElement(object,i));
//		}
//		return object;	
//	}
//	
//	public MathObjectMultiplyContainerPresenter generateRandomMultiplyContainer(MathObjectElement mathObjectParent,int i) {
//		MathObjectMultiplyContainerPresenter object = new MathObjectMultiplyContainerPresenter();
//		object.setMathObjectParent(mathObjectParent);
//		int n = (int) (Math.random()*nMult+2);
//		for(int j=0;j<n;j++) {
//			object.addChild(generateRandomMultiplyElement(object,i));
//		}
//		return object;	
//	}
//	
//	public MathObjectMultiplyElementPresenter generateRandomMultiplyElement(MathObjectElement mathObjectParent,int i) {
//		MathObjectMultiplyElementPresenter object = new MathObjectMultiplyElementPresenter();
//		object.setMathObjectParent(mathObjectParent);
//		if(Math.random()<0.5)
//			object.setDivided(true);
//		else
//			object.setDivided(false);
//		object.setChild(generateMathObjectElement(object,i));
//		return object;
//	}
//
//	public int getComplexity() {
//		return complexity;
//	}
//
//	public void setComplexity(int complexity) {
//		this.complexity = complexity;
//	}
//
//}
