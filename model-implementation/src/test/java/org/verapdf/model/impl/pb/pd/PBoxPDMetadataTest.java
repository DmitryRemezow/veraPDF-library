package org.verapdf.model.impl.pb.pd;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.verapdf.model.baselayer.Object;
import org.verapdf.model.impl.BaseTest;
import org.verapdf.model.pdlayer.PDMetadata;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Evgeniy Muravitskiy
 */
public class PBoxPDMetadataTest extends BaseTest {

	public static final String FILE_RELATIVE_PATH = "cos/veraPDF test suite 6-1-2-t02-fail-a.pdf";

	@BeforeClass
	public static void setUp() throws URISyntaxException, IOException {
		expectedType = "PDMetadata";
		expectedID = null;

		setUp(FILE_RELATIVE_PATH);
		actual = new PBoxPDMetadata(document.getDocumentCatalog().getMetadata(), true);
	}

	@Test
	public void testFiltersMethod() {
		Assert.assertNull(((PDMetadata) actual).getFilter());
	}

	@Test
	public void testXMPPackageLink() {
		List<? extends org.verapdf.model.baselayer.Object> packages =
				actual.getLinkedObjects(PBoxPDMetadata.XMP_PACKAGE);
		Assert.assertEquals(1, packages.size());
		for (Object object : packages) {
			Assert.assertEquals("XMPMainPackage", object.getType());
		}
	}

	@Test
	public void testStreamLink() {
		List<? extends org.verapdf.model.baselayer.Object> packages =
				actual.getLinkedObjects(PBoxPDMetadata.STREAM);
		Assert.assertEquals(1, packages.size());
		for (Object object : packages) {
			Assert.assertEquals("CosStream", object.getType());
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
