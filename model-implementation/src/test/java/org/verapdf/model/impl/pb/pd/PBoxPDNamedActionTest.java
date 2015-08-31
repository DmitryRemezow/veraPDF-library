package org.verapdf.model.impl.pb.pd;

import org.apache.pdfbox.pdmodel.interactive.action.PDActionNamed;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.verapdf.model.pdlayer.PDAction;
import org.verapdf.model.pdlayer.PDNamedAction;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Evgeniy Muravitskiy
 */
public class PBoxPDNamedActionTest extends PBoxPDActionTest {

	public static final String IINTERACTIVE_OBJECTS_PATH = "pd/InteractiveObjects.pdf";

	@BeforeClass
	public static void setUp() throws URISyntaxException, IOException {
		expectedType = TYPES.contains(PBoxPDNamedAction.NAMED_ACTION_TYPE) ? PBoxPDNamedAction.NAMED_ACTION_TYPE : null;
		expectedID = null;

		setUp(IINTERACTIVE_OBJECTS_PATH);
		PDOutlineItem node = document.getDocumentCatalog().getDocumentOutline().getFirstChild().getFirstChild();
		actual = new PBoxPDNamedAction((PDActionNamed) node.getAction());
	}

	@Override
	@Test
	public void testSMethod() {
		Assert.assertEquals("Named", ((PDAction) actual).getS());
	}

	@Test
	public void testNMethod() {
		Assert.assertEquals("NextPage", ((PDNamedAction) actual).getN());
	}

}
