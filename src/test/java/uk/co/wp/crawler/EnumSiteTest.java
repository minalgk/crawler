package uk.co.wp.crawler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for EnumSite
 * 
 * @author katharem
 *
 */

public class EnumSiteTest {

	@Test
	public void testEnumSite() {

		EnumSite internalSite = EnumSite.FACEBOOK;
		assertFalse(internalSite.isInternal());
		assertTrue(EnumSite.isInternalSite("www.wipro.com"));
	}
}
	