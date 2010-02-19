package fr.upmf.animaths.client.mvp.MathObject;

import fr.upmf.animaths.client.mvp.MathML.MMLFrac;
import fr.upmf.animaths.client.mvp.MathML.MMLOperator;
import fr.upmf.animaths.client.mvp.MathML.MMLRow;

public class MOMultiplyContainerDisplay implements MOMultiplyContainer.Display {

	private MMLOperator lFence;
	private MMLOperator rFence;
	private MMLFrac frac;
	private MMLRow numeratorRow;
	private MMLRow denominatorRow;
	
	public MOMultiplyContainerDisplay() { }

	@Override
	public MMLOperator getLFence() {
		return lFence;
	}

	@Override
	public MMLOperator getRFence() {
		return rFence;
	}

	@Override
	public MMLFrac getFrac() {
		return frac;
	}

	@Override
	public MMLRow getNumeratorRow() {
		return numeratorRow;
	}

	@Override
	public MMLRow getDenominatorRow() {
		return denominatorRow;
	}

	@Override
	public void setLFence(MMLOperator lFence) {
		this.lFence = lFence;
	}

	@Override
	public void setRFence(MMLOperator rFence) {
		this.rFence = rFence;
	}

	@Override
	public void setFrac(MMLFrac frac) {
		this.frac = frac;
	}

	@Override
	public void setNumeratorRow(MMLRow numeratorRow) {
		this.numeratorRow = numeratorRow;
	}

	@Override
	public void setDenominatorRow(MMLRow denominatorRow) {
		this.denominatorRow = denominatorRow;
	}

	@Override
	public void startProcessing() {
	}

	@Override
	public void stopProcessing() {
	}

}	

