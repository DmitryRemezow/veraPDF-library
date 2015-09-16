package org.verapdf.model.impl.pb.pd;

import org.apache.pdfbox.pdmodel.interactive.action.PDActionGoTo;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionNamed;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionRemoteGoTo;
import org.verapdf.model.baselayer.Object;
import org.verapdf.model.pdlayer.PDAction;

import java.util.ArrayList;
import java.util.List;

/**
 * PDF action object
 *
 * @author Evgeniy Muravitskiy
 */
public class PBoxPDAction extends PBoxPDObject implements PDAction {

	public static final String ACTION_TYPE = "PDAction";

	public static final String NEXT = "Next";

    public PBoxPDAction(
            org.apache.pdfbox.pdmodel.interactive.action.PDAction simplePDObject) {
        this(simplePDObject, ACTION_TYPE);
    }

	public PBoxPDAction(
			org.apache.pdfbox.pdmodel.interactive.action.PDAction simplePDObject,
			final String type) {
		super(simplePDObject, type);
	}

    @Override
    public String getS() {
        return ((org.apache.pdfbox.pdmodel.interactive.action.PDAction) simplePDObject)
                .getSubType();
    }

    @Override
    public List<? extends Object> getLinkedObjects(String link) {
        if (NEXT.equals(link)) {
            return this.getNext();
        }
        return super.getLinkedObjects(link);
    }

    private List<PDAction> getNext() {
        List<PDAction> actions = new ArrayList<>();
        List<org.apache.pdfbox.pdmodel.interactive.action.PDAction> next =
				((org.apache.pdfbox.pdmodel.interactive.action.PDAction) this.simplePDObject)
                .getNext();
        if (next != null) {
            for (org.apache.pdfbox.pdmodel.interactive.action.PDAction action : next) {
				PDAction result = getAction(action);
				if (result != null) {
					actions.add(result);
				}
			}
        }
        return actions;
    }

	public static PDAction getAction(org.apache.pdfbox.pdmodel.interactive.action.PDAction action) {
		if (action == null) {
			return null;
		}

		switch (action.getSubType()) {
			case "Named":
				return new PBoxPDNamedAction((PDActionNamed) action);
			case "GoTo":
				return new PBoxPDGoToAction((PDActionGoTo) action);
			case "GoToR":
				return new PBoxPDGoToRemoteAction((PDActionRemoteGoTo) action);
			default:
				return new PBoxPDAction(action);
		}
	}
}
