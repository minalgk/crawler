package uk.co.wp.crawler;

/**
 * Enumeration representing external sites.
 * 
 * @author katharem
 *
 */
public enum EnumSite {
	FACEBOOK("facebook", false), TWITTER("twitter", false), AMAZON("amazon", false), EBAY("ebay", false), LINKEDIN(
			"linkedin", false), GOOGLE("google", false), APPLE("apple", false), IAC("iac", false), VIMEO("vimeo",
					false), CHEMISTRY("chemistry", false), DESIGNIT("designit", false), INSTAGRAM("instagram",
							false), JCBINDIA("jcbindia", false), JCB("jcb", false), FASTCOEXIST("fastcoexist",
									false), FASTCOMPANY("fastcompany", false), FASTCODESIGN("fastcodesign",
											false), FASTCOCREATE("fastcocreate", false), FUTUREOFTRANSIT(
													"futureoftransit",
													false), ETOUCHES("etouches", false), PROMONEXT("promonext2016",
															false), EISEEVERYWHERE("eiseverywhere", false), CHIEFMARKER(
																	"chiefmarketer", false), HBR("hbr",
																			false), BUSYSUB("buysub", false), HARVARD(
																					"harvardbusinesspublishing", false);

	private final String site;
	private final boolean isInternal;

	/**
	 * Parameterised Constructor
	 * 
	 * @param isInternal
	 */
	EnumSite(String siteDetails, boolean isInternal) {
		this.site = siteDetails;
		this.isInternal = isInternal;
	}

	public static boolean isInternalSite(String site) {
		boolean returnValue = true;
		for (EnumSite enumSite : EnumSite.values()) {
			if (site.contains(enumSite.getSite())) {
				returnValue = enumSite.isInternal();
			}
		}
		return returnValue;
	}

	public String getSite() {
		return site;
	}

	public boolean isInternal() {
		return isInternal;
	}

}
