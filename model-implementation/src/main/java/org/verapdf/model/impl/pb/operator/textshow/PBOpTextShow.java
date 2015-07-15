package org.verapdf.model.impl.pb.operator.textshow;

import org.apache.pdfbox.cos.COSBase;
import org.verapdf.model.baselayer.Object;
import org.verapdf.model.impl.pb.operator.base.PBOperator;
import org.verapdf.model.operator.OpTextShow;
import org.verapdf.model.pdlayer.PDFont;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Timur Kamalov
 */
public abstract class PBOpTextShow extends PBOperator implements OpTextShow {

	public static final String FONT = "font";

	protected PBOpTextShow(List<COSBase> arguments) {
		super(arguments);
	}

	@Override
	public List<? extends org.verapdf.model.baselayer.Object> getLinkedObjects(String link) {
		List<? extends Object> list;

		switch (link) {
			case FONT:
				list = this.getFont();
				break;
			default:
				list = super.getLinkedObjects(link);
				break;
		}

		return list;
	}

	private List<PDFont> getFont() {
		List<PDFont> result = new ArrayList<>();
		return result;
	}

}
