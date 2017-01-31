package uk.co.wp.crawler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Crawler class
 * 
 * @author katharem
 *
 */
public class Crawler {

	public static final String URL_REGEX = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$";


	
	private String fileName;
	private List<String> links;
	private String baseUrl;
	private Set<String> pagesVisited = new HashSet<String>();
	// private List<String> pagesToVisit = new LinkedList<String>();
	Queue<String> pagesToVisit = new ConcurrentLinkedQueue<String>();

	/**
	 * Constructor to read file.
	 * 
	 * @param fileInput
	 */
	Crawler(String fileInput) {
		this.fileName = fileInput;
		this.links = new ArrayList<String>();
	}

	/**
	 * Utility method to identify links on the web page.
	 */
	public void startCrawling() {
		try {
			System.out.println("Inside startCrawling");
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			Scanner webUrl = new Scanner(file);
			while (webUrl.hasNextLine()) {
				baseUrl = webUrl.nextLine();
				System.out.println("URL crawling= " + baseUrl);
			}

			getPageLinks(baseUrl);
			System.out.println("MMMMMMMMM" + links.size());
			if (webUrl != null) {
				webUrl.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPageLinks(String url) {
		// 4. Check if you have already crawled the URLs
		// (we are intentionally not checking for duplicate content in this
		// example)
		// !links.contains(url) &&
		if (isInternalLink(url)) {
			try {
				// 4. (i) If not add it to the index
				if (links.add(url)) {
					System.out.println(url);
				}

				// 2. Fetch the HTML code
				Document document = Jsoup.connect(url).get();
				// 3. Parse the HTML to extract links to other URLs
				Elements linksOnPage = document.select("a[href]");

				// 5. For each extracted URL... go back to Step 4.
				for (Element page : linksOnPage) {
					System.out.println("*************************page.attr href****" + page.attr("href"));

					getPageLinks(page.attr("href"));
				}

			} catch (IOException e) {
				System.err.println("For '" + url + "': " + e.getMessage());
			}

		}
	}

	private void readPage(String url) {
		// get useful information
		Document doc;
		try {
			doc = Jsoup.connect(url).get();

			pagesVisited.add(url);

			// get all links and recursively call the processPage method
			Elements questions = doc.select("a[href]");
			for (Element link : questions) {
				// part of internal site.
				// url.contains(link.attr("href")) EnumSite.isInternalSite(url)
				// &&
				// if ( EnumSite.isInternalSite(url)) {
				pagesToVisit.add(link.text());

				// }

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isInternalLink(String link) {
		
		System.out.println("baseUrl:"+baseUrl);
		System.out.println("link:"+link);

		boolean returnValue = false;
		if (link.contains("wiprodigital.com")) {
			returnValue = true;
		}
		return returnValue;
	}

	String pageVisitedToString() {
		StringBuilder result = new StringBuilder();
		for (String item : pagesToVisit) {
			result.append(item);
			result.append("\n"); // optional
		}
		return result.toString();
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}
