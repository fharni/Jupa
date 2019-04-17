package de.falkharnisch.web.jupa.beans.access;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class CourseAccessBean extends AccessBean implements Serializable {

	private static final long serialVersionUID = 3161945165302916315L;

	public boolean isShowCourseListForDistrict() {
        return isUserInRole("course.list.district");
    }

    public boolean isShowCourseListForFederation() {
        return isUserInRole("course.list.federation");
    }
}
