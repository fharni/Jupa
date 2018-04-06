package de.falkharnisch.web.jupa.page;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebDriver;

@Location("index.xhtml")
public class HomePage {

    @Drone
    private WebDriver driver;




}
