package org.verapdf.model.impl.pb.operator.inlineimage;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.verapdf.model.coslayer.CosDict;
import org.verapdf.model.impl.pb.cos.PBCosDict;
import org.verapdf.model.impl.pb.operator.base.PBOperator;
import org.verapdf.model.operator.OpInlineImage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Timur Kamalov
 */
public class PBOpInlineImage extends PBOperator implements OpInlineImage {

    public static final String OP_INLINE_IMAGE = "OpInlineImage";

    public static final String INLINE_IMAGE_DICTIONARY = "inlineImageDictionary";

    public PBOpInlineImage(List<COSBase> arguments) {
        super(arguments, OP_INLINE_IMAGE);
    }

    @Override
    public List<? extends org.verapdf.model.baselayer.Object> getLinkedObjects(
            String link) {
        if (INLINE_IMAGE_DICTIONARY.equals(link)) {
            return this.getInlineImageDictionary();
        }
        return super.getLinkedObjects(link);
    }

    private List<CosDict> getInlineImageDictionary() {
        List<CosDict> list = new ArrayList<>();
        if (!this.arguments.isEmpty()
                && this.arguments.get(0) instanceof COSDictionary) {
            list.add(new PBCosDict((COSDictionary) this.arguments.get(0)));
        }

        return list;
    }

}
