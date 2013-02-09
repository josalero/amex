package com.bac.oee.struts.action;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bac.oee.AppConstants;
import com.bac.oee.content.ContentManager;
import com.bac.oee.content.Navigation;
import com.bac.oee.factory.NavigationFactory;
import com.bac.oee.model.navigation.NavigationData;
import com.bac.oee.service.TaxonomyService;
import com.bac.oee.struts.ViewConstants;
import com.bac.oee.web.model.CityUIModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;

// TODO: Auto-generated Javadoc
/**
 * AbstractBaseAction encapsulates core functionality that executes on every
 * page.
 * 
 * @author christopher.tai@starcomworldwide.com
 */
@SuppressWarnings("serial")
@Conversion()
public abstract class AbstractBaseAction extends ActionSupport {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(AbstractBaseAction.class);

	/** The action context. */
	protected ActionContext actionContext;

	/** The page not found action. */
	protected final String PAGE_NOT_FOUND_ACTION = "pageNotFoundAction";

	/** The server error action. */
	protected final String SERVER_ERROR_ACTION = "serverErrorAction";

	/** The content manager. */
	@Autowired
	protected ContentManager contentManager;

	/** The city data. */
	protected List<CityUIModel> cityData;

	/** The json object mapper. */
	protected ObjectMapper jsonObjectMapper;

	/** The navigation factory. */
	@Autowired
	protected NavigationFactory navigationFactory;

	/** The taxonomy service. */
	@Autowired
	protected TaxonomyService taxonomyService;

	/** The google analytics site id. */
	@Autowired
	@Qualifier("google.analytics.site.id")
	protected String googleAnalyticsSiteID;
	
	/**
	 * Instantiates a new abstract base action.
	 */
	public AbstractBaseAction() {
		super();
		jsonObjectMapper = new ObjectMapper();
		jsonObjectMapper.setDateFormat(new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss"));
		SerializationConfig config = jsonObjectMapper.getSerializationConfig();
		config = config.withSerializationInclusion(Inclusion.NON_NULL);
		// config.with(Feature.WRITE_ENUMS_USING_TO_STRING);
		jsonObjectMapper.setSerializationConfig(config);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {

		HttpSession httpSession = getServletRequest().getSession();

		log.debug(">>>> SESSION ID: " + httpSession.getId());

		actionContext = ActionContext.getContext();

		log.debug(">>>> Action Context: " + actionContext);

		Navigation navigation = getNavigationData();

		actionContext.put(ViewConstants.NAVIGATION_DATA, navigation);

		populateNavigationData(navigation);

		return SUCCESS;
	}

	/**
	 * Gets the locale string.
	 * 
	 * @param servletRequest
	 *            the servlet request
	 * @return the locale string
	 */
	protected String getLocaleString(ServletRequest servletRequest) {

		HttpSession httpSession = getServletRequest().getSession();

		Locale locale = (Locale) httpSession
				.getAttribute(AppConstants.USER_LOCALE);
		if (locale == null) {
			locale = Locale.US;
		}
		String language = locale.getLanguage();
		String country = locale.getCountry();

		if (language.equals("en") && StringUtils.isEmpty(country)) {
			country = "US";
		}

		return language + "_" + country;
	}

	/**
	 * Gets the navigation data.
	 * 
	 * @return the navigation data
	 */
	protected Navigation getNavigationData() {
		Navigation navigation = null;
		HttpSession httpSession = getServletRequest().getSession();
		String locale;

		if (httpSession.getAttribute(AppConstants.USER_LOCALE) != null) {
			locale = httpSession.getAttribute(AppConstants.USER_LOCALE)
					.toString();
		} else {
			locale = null;
		}

		try {
			navigation = contentManager.retrieveNavigation(locale);
		} catch (Exception e) {
			log.error("Unable to get the navigation data!", e);
		}

		return navigation;
	}

	/**
	 * Gets the servlet request.
	 * 
	 * @return the servlet request
	 */
	protected HttpServletRequest getServletRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * Gets the servlet response.
	 * 
	 * @return the servlet response
	 */
	protected HttpServletResponse getServletResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * Gets the city data.
	 * 
	 * @return the city data
	 */
	public List<CityUIModel> getCityData() {
		return this.cityData;
	}

	/**
	 * Sets the city data.
	 * 
	 * @param cityData
	 *            the new city data
	 */
	public void setCityData(List<CityUIModel> cityData) {
		this.cityData = cityData;
	}

	/**
	 * Gets the page locale prefix.
	 * 
	 * @return the page locale prefix
	 */
	protected String getPageLocalePrefix() {
		Locale locale = (Locale) getServletRequest().getSession().getAttribute(
				AppConstants.USER_LOCALE);

		if (locale != null) {
			return (locale.getLanguage() + '-' + locale.getCountry())
					.toLowerCase();
		}

		return "";
	}

	/**
	 * Populate navigation data.
	 * 
	 * @param navigation
	 *            the navigation
	 */
	public void populateNavigationData(Navigation navigation) {
		NavigationData navData = (NavigationData) actionContext
				.get("siteNavigationData");
		// Trying to populate just one time ...
		if (navData == null || navData.getGeoTaxonomy() == null) {
			navData = new NavigationData(navigation);
			Locale locale = (Locale) getServletRequest().getSession()
					.getAttribute(AppConstants.USER_LOCALE);
			navData.setLocale(getPageLocalePrefix());
			navigationFactory.setLocale(locale.getLanguage() + '_'
					+ locale.getCountry());
			navigationFactory.populateNavigationData(navData, navigation);
			actionContext.put(ViewConstants.SITE_NAVIGATION_DATA, navData);
		}
	}
}
