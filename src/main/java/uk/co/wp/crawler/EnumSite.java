package uk.co.wp.crawler;

/**
 * Enumeration representing external sites.
 * 
 * @author katharem
 *
 */
public enum EnumSite {
	FACEBOOK("facebook", false), TWITTER("twitter", false), AMAZON("amazon", false), EBAY("ebay",
			false), LINKEDIN("linkedin", false), GOOGLE("google", false), OTHERS("External link Pages.", false);

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

	/**
	 * Method to identify particular external sites.
	 * 
	 * @param site
	 * @return
	 */
	public static boolean isNotExternal(String site) {
		boolean returnValue = true;
		for (EnumSite enumSite : EnumSite.values()) {
			if (site.contains(enumSite.getSite())) {
				returnValue = enumSite.isInternal();
			}
		}
		return returnValue;
	}

	/**
	 * Method to get sites.
	 * 
	 * @return
	 */
	public String getSite() {
		return site;
	}

	/**
	 * Method to identify if internal site.
	 * 
	 * @return
	 */
	public boolean isInternal() {
		return isInternal;
	}

}
