package org.verapdf.model.impl.pb.pd;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.graphics.form.PDGroup;
import org.junit.*;
import org.verapdf.model.baselayer.Object;
import org.verapdf.model.impl.BaseTest;
import org.verapdf.model.pdlayer.PDColorSpace;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Evgeniy Muravitskiy
 */
public class PBoxPDGroupTest extends BaseTest {

	public static final String FILE_RELATIVE_PATH = "pd/InteractiveObjects.pdf";

	@BeforeClass
	public static void setUp() throws URISyntaxException, IOException {
		expectedType = PBoxPDGroup.GROUP_TYPE;
		expectedID = null;

		setUp(FILE_RELATIVE_PATH);
		COSBase groupDictionary = document.getPage(0).getCOSObject().getDictionaryObject(COSName.GROUP);
		actual = new PBoxPDGroup(new PDGroup((COSDictionary) groupDictionary));
	}

	@Test
	public void testSubtypeMethod() {
		Assert.assertEquals("Transparency", ((org.verapdf.model.pdlayer.PDGroup) actual).getS());
	}

	@Ignore
	@Test
	public void testColorSpaceLink() {
		List<? extends Object> colorSpace = actual.getLinkedObjects(PBoxPDGroup.COLOR_SPACE);
		Assert.assertEquals(1, colorSpace.size());
		for (Object object : colorSpace) {
			Assert.assertTrue(object instanceof PDColorSpace);
		}
	}

	@AfterClass
	 public static void tearDown() throws IOException {
		expectedType = null;
		expectedID = null;
		actual = null;

		document.close();
	}
}
