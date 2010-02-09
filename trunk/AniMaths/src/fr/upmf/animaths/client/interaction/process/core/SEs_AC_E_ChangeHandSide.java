//package fr.upmf.animaths.client.interaction.process.core;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import net.customware.gwt.presenter.client.EventBus;
//
//import com.google.gwt.event.shared.EventHandler;
//import com.google.gwt.event.shared.HandlerRegistration;
//import com.google.gwt.event.shared.GwtEvent.Type;
//
//import fr.upmf.animaths.client.interaction.SelectionElement;
//import fr.upmf.animaths.client.interaction.process.MathObjectProcess;
//import fr.upmf.animaths.client.interaction.process.event.DragSelectedEvent;
//import fr.upmf.animaths.client.interaction.process.event.DropSelectedEvent;
//import fr.upmf.animaths.client.interaction.process.event.GrabSelectedEvent;
//import fr.upmf.animaths.client.mvp.AniMathsPresenter;
//import fr.upmf.animaths.client.mvp.MathObjectDynamicPresenter;
//import fr.upmf.animaths.client.mvp.MathObjectStaticPresenter;
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectAddContainerPresenter;
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectElementPresenter;
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectEquationPresenter;
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectMultiplyElementPresenter;
//import fr.upmf.animaths.client.mvp.MathObject.MathObjectSignedElementPresenter;
//
//public class SEs_AC_E_ChangeHandSide implements MathObjectProcess{
//
//	private static SEs_AC_E_ChangeHandSide instance = new SEs_AC_E_ChangeHandSide();
//	private EventBus eventBus = AniMathsPresenter.eventBus;
//	
//	private SelectionElement selectionElement;
//	private MathObjectDynamicPresenter presenter;
//	private MathObjectSignedElementPresenter selected;
//	private MathObjectElementPresenter<?> parentOfSelected;
//	private MathObjectElementPresenter<?> greatParentOfSelected;
//	private boolean atLeft;
//
//	private MathObjectElementPresenter<?> where;	
//	private short zone = -1;
//	private short zoneOld = -1;
//	
//	// 1 : MOSE->MOAC->MOE
//	// 2 : MOSE->MOAC->autre
//	// 3 : MOSE->MOSE
//	private short cas = -1;
//
//	private Map<Type<?>,HandlerRegistration> hr = new HashMap<Type<?>,HandlerRegistration>();
//
//	private SEs_AC_E_ChangeHandSide() {}
//
//	public static void setEnabled() {
//		instance.setHandler(GrabSelectedEvent.getType());
//	}
//
//	/**
//	 * On gère ici les fonctionnalités de base des sélections de type MathObjectSignedElementPresenter :
//	 */
//	@Override
//	public void onGrabSelected(GrabSelectedEvent event) {
//		cas = -1;
//		selectionElement = event.getSelectionElement();
//		if(selectionElement.getSelectedElement() instanceof MathObjectSignedElementPresenter) {
//			presenter = selectionElement.getPresenter();
//			selected = (MathObjectSignedElementPresenter) selectionElement.getSelectedElement();
//			parentOfSelected = selected.getMathObjectParent();
//			greatParentOfSelected = parentOfSelected.getMathObjectParent();
//			
//			if(parentOfSelected instanceof MathObjectAddContainerPresenter)
//				if(greatParentOfSelected instanceof MathObjectEquationPresenter) {
//					cas = 1;
//					atLeft = ((MathObjectEquationPresenter) greatParentOfSelected).getLeftHandSide()==parentOfSelected;
//				}
//				else
//					cas = 2;
//			else  if(parentOfSelected instanceof MathObjectSignedElementPresenter)
//				cas = 3;
//
//			if(cas!=-1) {
//				selectionElement.setProcessFound(true);
//				setHandler(DragSelectedEvent.getType());
//			}
//		}
//	}
//
//	@Override
//	public void onDragSelected(DragSelectedEvent event) {
//		zoneOld = zone;
//		where = event.getWhereElement();
//		zone = event.getZone();
//		switch(cas) {
//		case 1:
//			onDragSelectedCase1();
//			break;
//		case 2:
//			onDragSelectedCase2();
//			break;
//		case 3:
//			onDragSelectedCase3();
//			break;
//		case 4:
//			onDragSelectedCase4();
//			break;
//		}
//	}
//
//	private void onDragSelectedCase1() {
//
//		//CAS : on est sur l'équation, ou n'importe où ailleurs que sur le div dynamique
//		if(where==greatParentOfSelected) {
//			// On change de côté par rapport au égal...
//			// alors si on VIENT de changer de côté, on change le signe de l'élement signé.
//			if((aZone==MathObjectElementPresenter.ZONE_EQ_LEFT_IN||aZone==MathObjectElementPresenter.ZONE_EQ_LEFT_OUT)
//					&& (zone==MathObjectElementPresenter.ZONE_EQ_RIGHT_IN||zone==MathObjectElementPresenter.ZONE_EQ_RIGHT_OUT) 
//				|| (aZone==MathObjectElementPresenter.ZONE_EQ_RIGHT_IN||aZone==MathObjectElementPresenter.ZONE_EQ_RIGHT_OUT)
//					&&(zone==MathObjectElementPresenter.ZONE_EQ_LEFT_IN||zone==MathObjectElementPresenter.ZONE_EQ_LEFT_OUT))
//					inverseSign();
//			// Si on est à EQ_O ou EQ_E du égal et à IN_O ou IN_E, on continue le test... sur le membre de gauche ou de droite
//			if(zone==MathObjectElementPresenter.ZONE_EQ_LEFT_IN) {
//				element = greatParentOfSelected.getRightHandSide();
//				zone = MathObjectElementPresenter.ZONE_IN_E;
//			}
//			else if(zone==MathObjectElementPresenter.ZONE_EQ_RIGHT_IN) {
//				element = greatParentOfSelected.getLeftHandSide();
//				zone = MathObjectElementPresenter.ZONE_IN_O;
//			}
//		}
//
//		//CAS 1: on est sur un MOSE->mêmeMOAC, du même côté
//		if(parentOfSelected == element.getMathObjectParent()) {
//			switch(zone) {
//			case MathObjectElementPresenter.ZONE_IN_NO:
//			case MathObjectElementPresenter.ZONE_IN_O:
//			case MathObjectElementPresenter.ZONE_IN_SO:
//			case MathObjectElementPresenter.ZONE_IN_NE:
//			case MathObjectElementPresenter.ZONE_IN_E:
//			case MathObjectElementPresenter.ZONE_IN_SE:
//				if(!selectionElement.getProcessFound()) {
//					setHandler(DropSelectedEvent.getType());
//					selectionElement.setProcessFound(true);
//				}
//				whereElement = (MathObjectSignedElementPresenter) element;
//				break;
//			}
//		}
//		//CAS 2: on est sur un MOSE->MOAC->MOE, de l'autre côté.
//		else if(element instanceof MathObjectSignedElementPresenter 
//				&& element.getMathObjectParent() instanceof MathObjectAddContainerPresenter
//				&& element.getMathObjectParent()!=parentOfSelected
//				&& element.getMathObjectParent().getMathObjectParent()==greatParentOfSelected) {
//			switch(zone) {
//			case MathObjectElementPresenter.ZONE_IN_NO:
//			case MathObjectElementPresenter.ZONE_IN_O:
//			case MathObjectElementPresenter.ZONE_IN_SO:
//			case MathObjectElementPresenter.ZONE_IN_NE:
//			case MathObjectElementPresenter.ZONE_IN_E:
//			case MathObjectElementPresenter.ZONE_IN_SE:
//				cas = 2;
//				break;
//			}
//		}
//		//CAS 3: on est sur un MOSE->MOE, de l'autre côté.
//		else if(element instanceof MathObjectSignedElementPresenter 
//				&& element.getMathObjectParent() ==greatParentOfSelected) {
//			switch(zone) {
//			case MathObjectElementPresenter.ZONE_IN_NO:
//			case MathObjectElementPresenter.ZONE_IN_O:
//			case MathObjectElementPresenter.ZONE_IN_SO:
//			case MathObjectElementPresenter.ZONE_IN_NE:
//			case MathObjectElementPresenter.ZONE_IN_E:
//			case MathObjectElementPresenter.ZONE_IN_SE:
//				cas = 3;
//				break;
//			}
//		}
//		//CAS 4: on est à dr. ou à g. d'un fil du MOE, de l'aute côté.
//		else if(element.getMathObjectParent()==greatParentOfSelected
//				&& element!=parentOfSelected) {
//			switch(zone) {
//			case MathObjectElementPresenter.ZONE_IN_NO:
//			case MathObjectElementPresenter.ZONE_IN_O:
//			case MathObjectElementPresenter.ZONE_IN_SO:
//			case MathObjectElementPresenter.ZONE_IN_NE:
//			case MathObjectElementPresenter.ZONE_IN_E:
//			case MathObjectElementPresenter.ZONE_IN_SE:
//				cas = 4;
//				break;
//			}
//		}
//		if(cas!=-1 && !selectionElement.getProcessFound()) {
//			setHandler(DropSelectedEvent.getType());
//			selectionElement.setProcessFound(true);
//			whereElement = element;
//		}
//	}
//
//	@Override
//	public void onDropSelected(DropSelectedEvent event) {
//		removeHandler(DragSelectedEvent.getType());
//		switch(cas) {
//		case 1:
//			MathObjectEquationPresenter moe = (MathObjectEquationPresenter) whereElement;
//			
//			break;
//		case 2:
//			MathObjectSignedElementPresenter mose = (MathObjectSignedElementPresenter) whereElement;
//			
//			break;
//			
//		case 3:
//			MathObjectSignedElementPresenter mose = (MathObjectSignedElementPresenter) whereElement;
//			
//			break;
//		case 4:
//			MathObjectAddContainerPresenter moac = (MathObjectEquationPresenter) whereElement;
//			
//			break;
//		}
//		List<MathObjectSignedElementPresenter> children = parentOfSelected.getChildren();
//		children.remove(children.indexOf(selectedElement));
//		int indexOfWhere = children.indexOf(whereElement);
//		switch(zone) {
//		case MathObjectElementPresenter.ZONE_IN_NE:
//		case MathObjectElementPresenter.ZONE_IN_E:
//		case MathObjectElementPresenter.ZONE_IN_SE:
//			indexOfWhere++;
//		case MathObjectElementPresenter.ZONE_IN_NO:
//		case MathObjectElementPresenter.ZONE_IN_O:
//		case MathObjectElementPresenter.ZONE_IN_SO:
//			children.add(indexOfWhere,selectedElement);
//			presenter.setElement(presenter.getElement());
//			break;
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	private <T extends EventHandler> void setHandler(Type<T> type) {
//		if(hr.get(type)==null)
//			hr.put(type, eventBus.addHandler(type,(T) this));
//	}
//	
//	@SuppressWarnings("unused")
//	private <T extends EventHandler> void removeHandler(Type<T> type) {
//		if(hr.get(type)!=null) {
//			hr.get(type).removeHandler();
//			hr.put(type,null);
//		}
//	}
//	
//
//	private void inverseSign() {
//		MathObjectElementPresenter<?> element = selectionElement.getCopiedPresenter().getElement();
//		MathObjectSignedElementPresenter seElement;
//		if(element instanceof MathObjectSignedElementPresenter) {
//			seElement = ((MathObjectSignedElementPresenter) element);
//			seElement.setMinus(!seElement.isMinus());
//		}
//		else
//			seElement = new MathObjectSignedElementPresenter(element,true);
//		selectionElement.getCopiedPresenter().setElement(seElement);
//		seElement.getDisplay().getSign().getElement().setAttribute("id","focus");
//	}
//}
