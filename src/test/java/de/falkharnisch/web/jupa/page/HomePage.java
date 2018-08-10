package de.falkharnisch.web.jupa.page;

import org.jboss.arquillian.graphene.page.Location;
import org.jboss.arquillian.graphene.page.Page;

@Location("index.xhtml")
public class HomePage {

    @Page
    private MenuHeader menuHeader;

    public MenuHeader getMenuHeader() {
        return menuHeader;
    }
}
