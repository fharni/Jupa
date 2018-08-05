package de.falkharnisch.web.jupa.beans;


import de.falkharnisch.web.jupa.database.Course;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.database.UserGrading;
import de.falkharnisch.web.jupa.services.ConfigurationService;
import de.falkharnisch.web.jupa.services.CourseService;
import de.falkharnisch.web.jupa.services.GradingService;
import de.falkharnisch.web.jupa.services.UserService;
import de.falkharnisch.web.jupa.util.Util;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@ManagedBean
@SessionScoped
public class UserBean {

    private static final int IMAGE_HEIGHT = 120;
    private static final int IMAGE_WIDTH = 90;

    @Inject
    private UserService userService;

    @Inject
    private GradingService gradingService;

    @Inject
    private CourseService courseService;

    @Inject
    private ConfigurationService configurationService;

    @Inject
    private ServletContext context;

    private User user;
    private List<UserGrading> gradings;
    private List<Course> courses;

    @PostConstruct
    private void initUser() {
        String username = Util.getUserName();
        user = userService.getUserByUsername(username);
        gradings = gradingService.getGradingsByUser(user);
        gradings.sort((o1, o2) -> {
            if (o1.getGrading().getDiscipline().equals(o2.getGrading().getDiscipline())) {
                return o2.getGrading().getSortOrder().compareTo(o1.getGrading().getSortOrder());
            }
            return o1.getGrading().getDiscipline().getId().compareTo(o2.getGrading().getDiscipline().getId());
        });

        courses = courseService.getCoursesForUser(user);
    }

    public String getName() {
        return user.getForename() + " " + user.getSurname();
    }

    public String getUsername() {
        return user.getUsername();
    }

    private boolean hasClub() {
        return user.getClub() != null;
    }

    public String getClubName() {
        if (hasClub()) {
            return user.getClub().getName();
        } else {
            return "";
        }
    }

    public String getDistrictName() {
        if (hasClub()) {
            return user.getClub().getDistrict().getName();
        } else {
            return "";
        }
    }

    public String getFederationName() {
        if (hasClub()) {
            return user.getClub().getDistrict().getFederation().getName();
        } else {
            return "";
        }
    }

    public int getImageWidth() {
        return IMAGE_WIDTH;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    public StreamedContent getImage() throws IOException {
        BufferedImage originalImage;
        File file = new File(
                configurationService.getConfigurationValue(ConfigurationService.PROFILE_PIC_PATH) + getUsername());
        if (file.exists()) {
            originalImage = ImageIO.read(file);
        } else {
            originalImage = ImageIO.read(context.getResourceAsStream("/WEB-INF/images/dummy.png"));
        }
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        BufferedImage bufferedImg = resizeImage(originalImage, type);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImg, "png", os);
        return new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png");
    }

    public int getImageHeight() {
        return IMAGE_HEIGHT;
    }

    public boolean isGrading() {
        return !gradings.isEmpty();
    }

    public List<UserGrading> getGradings() {
        return gradings;
    }

    public boolean isCourse() {
        return !courses.isEmpty();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public boolean isLicenses() {
        return false;
    }
}
