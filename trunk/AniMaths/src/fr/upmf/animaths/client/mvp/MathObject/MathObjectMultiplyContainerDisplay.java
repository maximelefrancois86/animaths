package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathML.MathMLFrac;
import fr.upmf.animaths.client.mvp.MathML.MathMLOperator;
import fr.upmf.animaths.client.mvp.MathML.MathMLRow;

public class MathObjectMultiplyContainerDisplay implements MathObjectMultiplyContainerPresenter.Display {

	private MathMLOperator lFence;
	private MathMLOperator rFence;
	private MathMLFrac frac;
	private MathMLRow numeratorRow;
	private MathMLRow denominatorRow;
	
	public MathObjectMultiplyContainerDisplay() { }

	@Override
	public MathMLOperator getLFence() {
		return lFence;
	}

	@Override
	public MathMLOperator getRFence() {
		return rFence;
	}

	@Override
	public MathMLFrac getFrac() {
		return frac;
	}

	@Override
	public MathMLRow getNumeratorRow() {
		return numeratorRow;
	}

	@Override
	public MathMLRow getDenominatorRow() {
		return denominatorRow;
	}

	@Override
	public void setLFence(MathMLOperator lFence) {
		this.lFence = lFence;
	}

	@Override
	public void setRFence(MathMLOperator rFence) {
		this.rFence = rFence;
	}

	@Override
	public void setFrac(MathMLFrac frac) {
		this.frac = frac;
	}

	@Override
	public void setNumeratorRow(MathMLRow numeratorRow) {
		this.numeratorRow = numeratorRow;
	}

	@Override
	public void setDenominatorRow(MathMLRow denominatorRow) {
		this.denominatorRow = denominatorRow;
	}

	@Override
	public void startProcessing() {
	}

	@Override
	public void stopProcessing() {
	}

}	

