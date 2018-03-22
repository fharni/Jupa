package de.falkharnisch.web.jupa.beans;


import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.ConfigurationService;
import de.falkharnisch.web.jupa.services.UserService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@ManagedBean
@SessionScoped
public class UserBean {

    private static final int IMAGE_HEIGHT = 120;
    private static final int IMAGE_WIDTH = 90;

    @Inject
    private UserService userService;

    @Inject
    private ConfigurationService configurationService;

    @Inject
    private ServletContext context;

    private User user;

    @PostConstruct
    private void initUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();
        String username = externalContext.getUserPrincipal().getName();
        user = userService.getUserByUsername(username);
    }

    public String getName() {
        return user.getForename() + " " + user.getSurname();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getClubName() {
        return user.getClub().getName();
    }

    public String getDistrictName() {
        return user.getClub().getDistrict().getName();
    }

    public String getFederationName() {
        return user.getClub().getDistrict().getFederation().getName();
    }

    public static int getImageWidth() {
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
                configurationService.getConfigurationValue("profile_pic.path") + getUsername() + ".jpg");
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
        return false;
    }

    public boolean isCourses() {
        return false;
    }

    public boolean isLicenses() {
        return false;
    }

}
