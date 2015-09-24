package org.verapdf.model.impl.pb.pd.images;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.verapdf.model.baselayer.Object;
import org.verapdf.model.coslayer.CosDict;
import org.verapdf.model.coslayer.CosStream;
import org.verapdf.model.impl.pb.cos.PBCosStream;
import org.verapdf.model.impl.pb.pd.PBoxPDContentStream;
import org.verapdf.model.impl.pb.pd.PBoxPDGroup;
import org.verapdf.model.pdlayer.PDContentStream;
import org.verapdf.model.pdlayer.PDGroup;
import org.verapdf.model.pdlayer.PDXForm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Evgeniy Muravitskiy
 */
public class PBoxPDXForm extends PBoxPDXObject implements PDXForm {

    public static final String X_FORM_TYPE = "PDXForm";

    public static final String GROUP = "Group";
    public static final String PS = "PS";
    public static final String REF = "Ref";
    public static final String CONTENT_STREAM = "contentStream";

    public PBoxPDXForm(PDFormXObject simplePDObject) {
        super(simplePDObject, X_FORM_TYPE);
    }

    @Override
    public String getSubtype2() {
        final COSStream subtype2 = ((PDFormXObject) this.simplePDObject)
                .getCOSStream();
        return getSubtypeString(subtype2
                .getDictionaryObject(COSName.getPDFName("Subtype2")));
    }

	@Override
	public List<? extends Object> getLinkedObjects(String link) {
		switch (link) {
			case GROUP:
				return this.getGroup();
			case PS:
				return this.getPS();
			case REF:
				return this.getREF();
			case CONTENT_STREAM:
				return this.getContentStream();
			default:
				return super.getLinkedObjects(link);
		}
	}

    private List<PDGroup> getGroup() {
        org.apache.pdfbox.pdmodel.graphics.form.PDGroup group = ((PDFormXObject) this.simplePDObject)
                .getGroup();
        if (group != null) {
			List<PDGroup> groups = new ArrayList<>(MAX_NUMBER_OF_ELEMENTS);
			groups.add(new PBoxPDGroup(group));
			return Collections.unmodifiableList(groups);
        }
        return Collections.emptyList();
    }

    private List<CosStream> getPS() {
        final COSStream cosStream = ((PDFormXObject) this.simplePDObject)
                .getCOSStream();
        COSStream ps = (COSStream) cosStream.getDictionaryObject(COSName.PS);
        if (ps != null) {
			List<CosStream> postScript = new ArrayList<>(MAX_NUMBER_OF_ELEMENTS);
			postScript.add(new PBCosStream(ps));
			return Collections.unmodifiableList(postScript);
        }
        return Collections.emptyList();
    }

    private List<CosDict> getREF() {
        return getLinkToDictionary(REF);
    }

    private List<PDContentStream> getContentStream() {
        List<PDContentStream> streams = new ArrayList<>(MAX_NUMBER_OF_ELEMENTS);
        streams.add(new PBoxPDContentStream(
				(PDFormXObject) simplePDObject));
        return streams;
    }
}
