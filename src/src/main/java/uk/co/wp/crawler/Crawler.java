package uk.co.wp.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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

	private String fileName;
	private String baseUrl;
	private Set<String> pagesVisited = new HashSet<String>();
	private List<LinkDetails> listListDetails = new ArrayList<LinkDetails>();
	String solutionFileName = "CrawlerSolution.txt";

	/**
	 * Constructor to read file.
	 * 
	 * @param fileInput
	 */
	Crawler(String fileInput) {
		this.fileName = fileInput;
	}

	/**
	 * Utility method to identify links on the web page.
	 */
	public void startCrawling() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			Scanner webUrl = new Scanner(file);
			while (webUrl.hasNextLine()) {
				this.baseUrl = webUrl.nextLine();
				System.out.println("URL crawling= " + baseUrl);
				System.out.println("Processing..... ");
			}
			getParentUrlLinks();
			getChildUrlLinks();
			writeToFile();

			if (webUrl != null) {
				webUrl.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Utility method to write solution to file.
	 * 
	 * @throws IOException
	 */
	private void writeToFile() throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(solutionFileName));
		for (LinkDetails itm : listListDetails) {
			writer.write(itm.toString());
		}
		System.out.println("Writing solution to file: " + solutionFileName);
	}

	/**
	 * Method to identify links of page.
	 * 
	 * @throws MalformedURLException
	 */
	private void getParentUrlLinks() throws MalformedURLException {
		LinkDetails linkDetail = new LinkDetails(baseUrl);

		try {
			// one of the validations for url format
			if (baseUrl.contains(".")) {
				URL url = new URL(baseUrl);
				if (!pagesVisited.contains(baseUrl) && isInternalLink(url.getHost())) {

					URLConnection urlConnection = null;
					urlConnection = url.openConnection();

					try (InputStream input = urlConnection.getInputStream()) {

						Document doc = Jsoup.parse(input, "UTF-8", "");
						Elements elements = doc.select("a");
						Elements img = doc.select("img");

						for (Element element : elements) {
							String linkUrl = element.attr("href");
							if (EnumSite.isNotExternal(linkUrl)) {
								pagesVisited.add(linkUrl);
							} else {
								linkDetail.getExternalpagesVisited().add(linkUrl);
							}
						}
						addImageLink(linkDetail, img);
					}
				} else {
					linkDetail.getExternalpagesVisited().add(baseUrl);
				}
				linkDetail.getInternalpagesVisited().addAll(pagesVisited);
			} else {
				// Invalid URL
				linkDetail.getInvalidUrl().add(baseUrl);
			}
			listListDetails.add(linkDetail);
		} catch (IOException e) {
			System.err.println("For '" + baseUrl + "': " + e.getMessage());
		}
	}

	/**
	 * Method to add image links on particular url.
	 * 
	 * @param linkDetail
	 * @param img
	 */
	private void addImageLink(LinkDetails linkDetail, Elements img) {
		for (Element el : img) {
			String imgSrc = el.attr("src");
			linkDetail.getImgScr().add(imgSrc);
		}
	}

	/**
	 * Utility method to identify links.
	 * 
	 * @param baseUrl
	 * @throws MalformedURLException
	 */
	private void getDetailedPageLinks(String baseUrl) throws MalformedURLException {
		LinkDetails linkDetail = new LinkDetails(baseUrl);

		try {
			// one of the validations for url format
			if (baseUrl.contains(".")) {
				URL url = new URL(baseUrl);
				if (isInternalLink(url.getHost())) {

					URLConnection urlConnection = null;
					urlConnection = url.openConnection();

					try (InputStream input = urlConnection.getInputStream()) {

						Document doc = Jsoup.parse(input, "UTF-8", "");
						Elements elements = doc.select("a");
						Elements img = doc.select("img");

						for (Element element : elements) {
							String linkUrl = element.attr("href");
							if (EnumSite.isNotExternal(linkUrl)) {
								linkDetail.getInternalpagesVisited().add(linkUrl);
							} else {
								linkDetail.getExternalpagesVisited().add(linkUrl);
							}
						}
						addImageLink(linkDetail, img);
					}
				}
			} else {
				// Invalid URL
				linkDetail.getInvalidUrl().add(baseUrl);
			}
			listListDetails.add(linkDetail);

		} catch (IOException e) {
			System.err.println("For '" + baseUrl + "': " + e.getMessage());
		}
	}

	/**
	 * Utility method to identify sub links of page.
	 * 
	 * @throws MalformedURLException
	 */
	private void getChildUrlLinks() throws MalformedURLException {
		pagesVisited.forEach(item -> {
			try {
				getDetailedPageLinks(item);
			} catch (Exception e) {
				System.err.println("For '" + item + "': " + e.getMessage());
			}
		});
	}

	/**
	 * Method to identify iternal links.
	 * 
	 * @param linkHost
	 * @return
	 * @throws MalformedURLException
	 */
	public boolean isInternalLink(String linkHost) throws MalformedURLException {

		boolean returnValue = false;
		if (linkHost.contains(getBaseUr().getHost())) {
			returnValue = true;
		}
		return returnValue;
	}

	/**
	 * Utility method to get base url.
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public URL getBaseUr() throws MalformedURLException {
		URL url = new URL(baseUrl);
		return url;
	}

	/**
	 * Method to set base url for testing purpose.	
	 * 
	 * @param string
	 */
	public void setBaseUrl(String string) {
		this.baseUrl = string;
	}
}
