package uk.co.wp.crawler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.junit.Test;

/**
 * Crawler test class
 * 
 * @author katharem
 *
 */
public class CrawlerTest {

	Crawler crawler = new Crawler("WebUrl.txt");
	String solutionFileName = "CrawlerSolution.txt";

	@Test
	public void crawlerTest() throws IOException {
		crawler.startCrawling();
		// TODO: Improve on test (copied the file from\target to \test\resources for testing purpose)
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(solutionFileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

		StringBuffer sbfr = new StringBuffer();
		while (br.ready()) {
			sbfr.append(br.readLine());
		}
		assertTrue(sbfr.toString().contains("http://wiprodigital.com "));

	}

	@Test
	public void isInternalLinkTest() throws MalformedURLException {
		Crawler crawler = new Crawler("WebUrl.txt");
		crawler.setBaseUrl("http://wiprodigital.com");
		assertFalse(crawler.isInternalLink("www.facebook.com"));
		assertTrue(crawler.isInternalLink("http://wiprodigital.com/privacy-policy"));
	}
}
