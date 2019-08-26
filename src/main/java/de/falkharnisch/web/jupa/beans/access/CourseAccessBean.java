package de.falkharnisch.web.jupa.beans.access;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import java.io.Serializable;

@Named
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
