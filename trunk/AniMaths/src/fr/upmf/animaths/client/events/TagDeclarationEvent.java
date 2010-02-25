package fr.upmf.animaths.client.events;

import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.interaction.AniMathAbstractProcess;

public class TagDeclarationEvent extends GwtEvent<TagDeclarationHandler> {

	private static final Type<TagDeclarationHandler> TYPE = new Type<TagDeclarationHandler>();
	
    public static Type<TagDeclarationHandler> getType() {
        return TYPE;
    }

    private short tag;
    private AniMathAbstractProcess process;
    
    public TagDeclarationEvent(short tag, AniMathAbstractProcess process) {
    	this.tag = tag;
    	this.process = process;
    }
    
	@Override
	protected void dispatch(TagDeclarationHandler handler) {
		handler.onTagDeclaration(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<TagDeclarationHandler> getAssociatedType() {
		return getType();
	}
	
	public short getTag() {
		return tag;
	}
	
	public AniMathAbstractProcess getProcess() {
		return process;
	}
}
