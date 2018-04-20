package de.falkharnisch.web.jupa.page;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("passport.xhtml")
public class PassportPage {

	@FindBy(id="licensesTable")
	private WebElement licenses;

	@FindBy(id="courseTable")
	private WebElement courses;

	@FindBy(id="gradingTable")
	private WebElement gradings;

	@FindBy(id="name")
	private WebElement name;

	@FindBy(id="username")
	private WebElement username;

	@FindBy(id="clubName")
	private WebElement clubName;

	@FindBy(id="districtName")
	private WebElement districtName;

	@FindBy(id="federationName")
	private WebElement federationName;

	public String getName() {
		return name.getText();
	}

	public String getUsername() {
		return username.getText();
	}

	public String getClubName() {
		return clubName.getText();
	}

	public String getDistrictName() {
		return districtName.getText();
	}

	public String getFederationName() {
		return federationName.getText();
	}

}
