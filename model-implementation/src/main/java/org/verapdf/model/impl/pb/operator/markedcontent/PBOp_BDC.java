package org.verapdf.model.impl.pb.operator.markedcontent;

import org.apache.pdfbox.cos.COSBase;
import org.verapdf.model.operator.Op_BDC;

import java.util.List;

/**
 * @author Timur Kamalov
 */
public class PBOp_BDC extends PBOpMarkedContent implements Op_BDC {

    public static final String OP_BDC_TYPE = "Op_BDC";

    public PBOp_BDC(List<COSBase> arguments) {
        super(arguments, OP_BDC_TYPE);
    }

    @Override
    public List<? extends org.verapdf.model.baselayer.Object> getLinkedObjects(
            String link) {
        switch (link) {
        case TAG:
            return this.getTag();
        case PROPERTIES:
            return this.getPropertiesDict();
        default:
            return super.getLinkedObjects(link);
        }
    }

}
