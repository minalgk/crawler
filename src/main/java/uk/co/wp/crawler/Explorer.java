package uk.co.wp.crawler;
/**
 * Explorer class to explore web
 * @author katharem
 *
 */
public class Explorer {
	
	public static void main(String[] args) {
		Crawler crawler = new Crawler("WebUrl.txt");
		crawler.startCrawling();
	}
}
