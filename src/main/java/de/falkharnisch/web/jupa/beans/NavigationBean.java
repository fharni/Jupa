package de.falkharnisch.web.jupa.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String page = "start.xhtml";

    public String getPage() {
        return page;
    }

    public void setPage(String currentPage) {
        this.page = currentPage;
    }
}
