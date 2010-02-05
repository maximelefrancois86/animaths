//package fr.upmf.animaths.client.mvp.interaction.process.core;
//
//import net.customware.gwt.presenter.client.EventBus;
//import fr.upmf.animaths.client.mvp.AniMathsPresenter;
//import fr.upmf.animaths.client.mvp.interaction.events.process.DragOverEvent;
//import fr.upmf.animaths.client.mvp.interaction.events.process.DropOverEvent;
//import fr.upmf.animaths.client.mvp.interaction.process.Process;
//
//public class AddCommutation extends Process {
//
//	private EventBus eventBus = AniMathsPresenter.eventBus;
//
//	public AddCommutation() {};
//
//	@Override
//	public void setEnabled(boolean enabled) {
//		eventBus.addHandler(DragOverEvent.getType(), this);
//	}
//	
//	@Override
//	public void onDragOver(DragOverEvent event) {
//		if(event.getUnderElement().isInRightZone(event.getClientX(),event.getClientY())) {
//			
//		}
//	}
//
//	@Override
//	public void onDropOver(DropOverEvent event) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	
//}
