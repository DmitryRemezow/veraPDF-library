package org.verapdf.model.impl.pb.pd;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.verapdf.model.baselayer.Object;
import org.verapdf.model.factory.operator.OperatorFactory;
import org.verapdf.model.operator.Operator;
import org.verapdf.model.pdlayer.PDContentStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Evgeniy Muravitskiy
 */
public class PBoxPDContentStream extends PBoxPDObject implements
        PDContentStream {

    private static final Logger LOGGER = Logger
            .getLogger(PBoxPDContentStream.class);

	public static final String CONTENT_STREAM_TYPE = "PDContentStream";

	public static final String OPERATORS = "operators";

    public PBoxPDContentStream(
            org.apache.pdfbox.contentstream.PDContentStream contentStream) {
        super(contentStream, CONTENT_STREAM_TYPE);
    }

    @Override
    public List<? extends Object> getLinkedObjects(String link) {
        if (OPERATORS.equals(link)) {
            return this.getOperators();
        }
        return super.getLinkedObjects(link);
    }

    private List<Operator> getOperators() {
        try {
            PDFStreamParser streamParser = new PDFStreamParser(
                    this.contentStream.getContentStream(), true);
            streamParser.parse();
			List<Operator> result = OperatorFactory.operatorsFromTokens(streamParser.getTokens(),
                    this.contentStream.getResources());
			return Collections.unmodifiableList(result);
        } catch (IOException e) {
            LOGGER.error(
                    "Error while parsing content stream. " + e.getMessage(), e);
        }
        return Collections.emptyList();
    }
}
