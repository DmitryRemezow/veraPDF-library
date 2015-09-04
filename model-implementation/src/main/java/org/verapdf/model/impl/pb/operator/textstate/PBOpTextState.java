package org.verapdf.model.impl.pb.operator.textstate;

import org.apache.pdfbox.cos.COSBase;
import org.verapdf.model.impl.pb.operator.base.PBOperator;
import org.verapdf.model.operator.OpTextState;

import java.util.List;

/**
 * Base class for all text state operators
 *
 * @author Timur Kamalov
 */
public class PBOpTextState extends PBOperator implements OpTextState {

    public static final String OP_TEXT_STATE_TYPE = "OpTextState";

    public PBOpTextState(List<COSBase> arguments) {
        this(arguments, OP_TEXT_STATE_TYPE);
    }

    public PBOpTextState(List<COSBase> arguments, final String opType) {
        super(arguments, opType);
    }

}
