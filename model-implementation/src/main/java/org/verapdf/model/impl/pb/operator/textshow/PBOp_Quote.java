package org.verapdf.model.impl.pb.operator.textshow;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.verapdf.model.operator.Op_Quote;

import java.util.List;

/**
 * @author Evgeniy Muravitskiy
 */
public class PBOp_Quote extends PBOpStringTextShow implements Op_Quote {

    public static final String OP_QUOTE_TYPE = "Op_Quote";

    public PBOp_Quote(List<COSBase> arguments, PDFont font) {
        super(arguments, font, OP_QUOTE_TYPE);
    }
}
