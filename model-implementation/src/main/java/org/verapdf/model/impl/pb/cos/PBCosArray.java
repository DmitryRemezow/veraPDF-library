package org.verapdf.model.impl.pb.cos;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.verapdf.model.baselayer.Object;
import org.verapdf.model.coslayer.CosArray;
import org.verapdf.model.coslayer.CosObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Current class is representation of CosArray interface of abstract model. This
 * class is analogue of COSArray in pdfbox.
 *
 * @author Evgeniy Muravitskiy
 */
public class PBCosArray extends PBCosObject implements CosArray {

    /** Type name for PBCosArray */
    public static final String COS_ARRAY_TYPE = "CosArray";

    public static final String ELEMENTS = "elements";
    private final int size;
    private final List<CosObject> elements;

    public PBCosArray(COSArray array) {
        super(array, COS_ARRAY_TYPE);
        this.size = array.size();
        this.elements = parseElements(array);
    }

    /**
     * Getter for array size.
     *
     * @return size of array
     */
    @Override
    public Long getsize() {
        return Long.valueOf(this.size);
    }

    @Override
    public List<? extends Object> getLinkedObjects(String link) {
        if (link.equals(ELEMENTS)) {
            return this.elements;
        }
        return super.getLinkedObjects(link);
    }

    /**
     * Get all elements of array.
     *
     * @return elements of array
     */
    private List<CosObject> parseElements(COSArray array) {
        List<CosObject> list = new ArrayList<>(this.getsize().intValue());
        for (COSBase base : array) {
            if (base != null) {
                list.add(getFromValue(base));
            }
        }
        return Collections.unmodifiableList(list);
    }
}
