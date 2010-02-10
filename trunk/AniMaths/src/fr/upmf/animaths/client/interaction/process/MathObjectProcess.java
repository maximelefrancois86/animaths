package fr.upmf.animaths.client.interaction.process;

import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent.Type;

import fr.upmf.animaths.client.interaction.SelectionElement;
import fr.upmf.animaths.client.interaction.process.event.DragSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.DragSelectedHandler;
import fr.upmf.animaths.client.interaction.process.event.DropSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.DropSelectedHandler;
import fr.upmf.animaths.client.interaction.process.event.GrabSelectedEvent;
import fr.upmf.animaths.client.interaction.process.event.GrabSelectedHandler;
import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.MathObjectDynamicPresenter;
import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;

public abstract class MathObjectProcess implements GrabSelectedHandler, DragSelectedHandler, DropSelectedHandler {
	
	private final EventBus eventBus = AniMathsPresenter.eventBus;

	protected static final void setEnabled(MathObjectProcess process) {
		process.setHandler(GrabSelectedEvent.getType());
	}

	private Map<Type<?>,HandlerRegistration> hr = new HashMap<Type<?>,HandlerRegistration>();
	
	protected SelectionElement selectionElement;
	protected MathObjectDynamicPresenter presenter;
	protected MathObjectElementPresenter<?> selectedElement;

	protected MathObjectElementPresenter<?> whereElement;
	protected short zoneH;
	protected short zoneV;

	private MathObjectElementPresenter<?> choosenWhereElement;
	protected short choosenZoneH;
	protected short choosenZoneV;
	
	private int priorityOfProcess = 0;

	@Override
	public final void onGrabSelected(GrabSelectedEvent event) {
		selectionElement = event.getSelectionElement();
		presenter = selectionElement.getPresenter();
		selectedElement = selectionElement.getSelectedElement();
		if(isProcessInvolved()) {
			setHandler(DragSelectedEvent.getType());
			selectionElement.setProcessFound();
		}
	}
	
	@Override
	public final void onDragSelected(DragSelectedEvent event) {
		if(event.isFirstLevel()) {
			removeHandler(DropSelectedEvent.getType());
			priorityOfProcess = 0;
		}
		zoneH = event.getZoneH();
		zoneV = event.getZoneV();
		whereElement = event.getWhereElement();
		if(whereElement==selectedElement)
			return;
		int priorityOfProcess = getPriorityOfProcess();
		if(priorityOfProcess>this.priorityOfProcess) {
			this.priorityOfProcess = priorityOfProcess;
			choosenWhereElement = whereElement;
			choosenZoneH = zoneH;
			choosenZoneV = zoneV;
			setHandler(DropSelectedEvent.getType());
			selectionElement.setPriorityOfProcess(priorityOfProcess);
		}
	}
	
	@Override
	public final void onDropSelected(DropSelectedEvent event) {
		removeHandler(DragSelectedEvent.getType());
		removeHandler(DropSelectedEvent.getType());
		if(selectionElement.isProcessDone() || selectionElement.getGreatestPriorityFound()!=priorityOfProcess)
			return;
		zoneH = choosenZoneH;
		zoneV = choosenZoneV;
		whereElement = choosenWhereElement;
		executeProcess();
		presenter.setElement(presenter.getElement());
		selectionElement.setProcessDone();
	}
	
	protected abstract boolean isProcessInvolved();
	protected abstract int getPriorityOfProcess();
	protected abstract void executeProcess();

	@SuppressWarnings("unchecked")
	protected final <T extends EventHandler> void setHandler(Type<T> type) {
		if(hr.get(type)==null)
			hr.put(type, eventBus.addHandler(type,(T) this));
	}
	
	protected final <T extends EventHandler> void removeHandler(Type<T> type) {
		if(hr.get(type)!=null) {
			hr.get(type).removeHandler();
			hr.put(type,null);
		}
	}
	
}
