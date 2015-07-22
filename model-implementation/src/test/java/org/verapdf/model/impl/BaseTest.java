package org.verapdf.model.impl;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Assert;
import org.junit.Test;
import org.verapdf.model.ModelHelper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Evgeniy Muravitskiy
 */

public abstract class BaseTest {

	private static final String BASE_FOLDER = "/model/impl/pb/";

    protected static org.verapdf.model.baselayer.Object actual;
	protected static PDDocument document;

    protected static String expectedType;
    protected static String expectedID;

    @Test
    public void testTypeAndID() {
        Assert.assertEquals(expectedType, actual.getType());
        Assert.assertEquals(expectedID, actual.getID());
    }

	@Test
	public void testLinksMethod() {
		List<String> expectedLinks = ModelHelper.getListOfLinks(actual.getType());
		for (String link : expectedLinks) {
			Assert.assertNotNull(actual.getLinkedObjects(link));
		}
		expectedLinks.clear();
	}

	@Test(expected = IllegalAccessError.class)
	public void testNonexistentParentLink() {
		actual.getLinkedObjects("Wrong link.");
	}

	protected static void setUp(String path) throws URISyntaxException, IOException {
		String fileAbsolutePath = getSystemIndependentPath(BASE_FOLDER + path);
		File file = new File(fileAbsolutePath);
		document = PDDocument.load(file, Boolean.FALSE, Boolean.TRUE);
	}

	protected static String getSystemIndependentPath(String path) throws URISyntaxException {
		URL resourceUrl = ClassLoader.class.getResource(path);
		Path resourcePath = Paths.get(resourceUrl.toURI());
		return resourcePath.toString();
	}
}
