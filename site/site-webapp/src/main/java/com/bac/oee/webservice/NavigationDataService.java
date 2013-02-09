package com.bac.oee.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;

import com.bac.oee.content.ContentManager;
import com.bac.oee.content.Navigation;
import com.bac.oee.factory.NavigationFactory;
import com.bac.oee.model.navigation.NavigationData;
import com.bac.oee.service.TaxonomyService;

// TODO: Auto-generated Javadoc
/**
 * The Class NavigationDataService.
 */
@Path("/navigation-services/")
@Produces("application/json")
public class NavigationDataService {

	/** The content manager. */
	private ContentManager contentManager;

	/** The taxonomy service. */
	@Autowired
	protected TaxonomyService taxonomyService;

	/** The navigation factory. */
	@Autowired
	protected NavigationFactory navigationFactory;

	/**
	 * Gets the navigation data.
	 * 
	 * @param locale
	 *            the locale
	 * @return the navigation data
	 */
	@GET
	@Path("/data")
	@Produces("application/json")
	public NavigationData getNavigationData(@QueryParam("locale") String locale) {
		Navigation navigation = contentManager.retrieveNavigation(locale);

		NavigationData navigationData = new NavigationData(navigation);
		navigationData.setLocale(locale);
		navigationFactory.setLocale(locale);
		navigationFactory.populateNavigationData(navigationData, navigation);
		return navigationData;
	}

	/**
	 * Sets the content manager.
	 * 
	 * @param contentManager
	 *            the new content manager
	 */
	public void setContentManager(ContentManager contentManager) {
		this.contentManager = contentManager;
	}
}
