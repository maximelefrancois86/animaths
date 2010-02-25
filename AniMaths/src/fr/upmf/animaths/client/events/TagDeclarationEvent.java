package fr.upmf.animaths.client.events;

import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.interaction.AniMathsAbstractProcess;

public class TagDeclarationEvent extends GwtEvent<TagDeclarationHandler> {

	private static final Type<TagDeclarationHandler> TYPE = new Type<TagDeclarationHandler>();
	
    public static Type<TagDeclarationHandler> getType() {
        return TYPE;
    }

    private short tag;
    private AniMathsAbstractProcess process;
    
    public TagDeclarationEvent(short tag, AniMathsAbstractProcess process) {
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
	
	public AniMathsAbstractProcess getProcess() {
		return process;
	}
}
