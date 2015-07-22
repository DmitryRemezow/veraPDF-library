package org.verapdf.model.impl.pb.pd;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.verapdf.model.baselayer.Object;
import org.verapdf.model.impl.BaseTest;
import org.verapdf.model.impl.pb.operator.base.PBOperator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Evgeniy Muravitskiy
 */
public class PBoxPDContentStreamTest extends BaseTest {

	public static final String FILE_RELATIVE_PATH = "cos/veraPDF test suite 6-1-2-t02-fail-a.pdf";

	@BeforeClass
	public static void setUp() throws URISyntaxException, IOException {
		expectedType = "PDContentStream";
		expectedID = null;

		setUp(FILE_RELATIVE_PATH);
		actual = new PBoxPDContentStream(document.getPage(0));
	}

	@Test
	public void testOperatorsLink() {
		List<? extends Object> operators = actual.getLinkedObjects(PBoxPDContentStream.OPERATORS);
		Assert.assertTrue(operators.size() > 0);
		for (Object object : operators) {
			Assert.assertTrue(object instanceof PBOperator);
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
