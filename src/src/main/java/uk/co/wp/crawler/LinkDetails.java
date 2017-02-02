package uk.co.wp.crawler;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing visited links.
 * @author katharem
 *
 */
public class LinkDetails {

	private String baseUrl;
	private Set<String> internalpagesVisited = new HashSet<String>();
	private Set<String> externalpagesVisited = new HashSet<String>();
	private Set<String> invalidUrl = new HashSet<String>();
	private Set<String> imgScr = new HashSet<String>();

	public Set<String> getInternalpagesVisited() {
		return internalpagesVisited;
	}

	public Set<String> getExternalpagesVisited() {
		return externalpagesVisited;
	}

	public LinkDetails(String baseUrl) {
		super();
		this.baseUrl = baseUrl;
	}

	public Set<String> getInvalidUrl() {
		return invalidUrl;
	}

	public Set<String> getImgScr() {
		return imgScr;
	}

	private String getDetails(Set<String> toFormat) {
		StringBuilder formatBuiler = new StringBuilder();
		toFormat.forEach(action -> formatBuiler.append("\t\t").append(action).append("\n"));
		return formatBuiler.toString().isEmpty() ? "None" : formatBuiler.toString();
	}

	@Override
	public String toString() {
		return "\nUrl=" + baseUrl + "\nInternal Pages Visited=\n" + getDetails(internalpagesVisited)
				+ "\nExternal Pages Visited=\n" + getDetails(externalpagesVisited)+ "\nStatic Content Image Links=\n" + getDetails(imgScr);
	}

}
