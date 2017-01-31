package uk.co.wp.crawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Maze test class
 * 
 * @author katharem
 *
 */
public class CrawlerTest {

	Crawler crawler = new Crawler("WebUrl.txt");
	
	@Test
	public void createMazeTest() {
		crawler.startCrawling();

		
	}

	@Test
	public void isInternalLinkTest() {
		Crawler crawler = new Crawler("WebUrl.txt");
		//crawler.startCrawling();
		crawler.setBaseUrl("http://wiprodigital.com");
		assertFalse(crawler.isInternalLink("www.facebook.com"));
		assertTrue(crawler.isInternalLink("http://wiprodigital.com/privacy-policy"));
	}
}
