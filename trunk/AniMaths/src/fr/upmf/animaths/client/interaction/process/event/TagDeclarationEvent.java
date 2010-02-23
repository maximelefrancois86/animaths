package fr.upmf.animaths.client.interaction.process.event;

import com.google.gwt.event.shared.GwtEvent;

import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;

public class TagDeclarationEvent extends GwtEvent<TagDeclarationHandler> {

	private static final Type<TagDeclarationHandler> TYPE = new Type<TagDeclarationHandler>();
	
    public static Type<TagDeclarationHandler> getType() {
        return TYPE;
    }

    private short tag;
    private MOAbstractProcess process;
    
    public TagDeclarationEvent(short tag, MOAbstractProcess process) {
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
	
	public MOAbstractProcess getProcess() {
		return process;
	}
}
