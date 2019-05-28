package de.falkharnisch.web.jupa.beans;

import de.falkharnisch.web.jupa.database.Discipline;
import de.falkharnisch.web.jupa.database.Grading;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.ConfigurationService;
import de.falkharnisch.web.jupa.services.GradingService;
import de.falkharnisch.web.jupa.services.MembershipService;
import de.falkharnisch.web.jupa.services.UserService;
import de.falkharnisch.web.jupa.util.RandomString;
import de.falkharnisch.web.jupa.util.Util;

import org.mindrot.jbcrypt.BCrypt;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Managed bean for manipulating other user data.
 */
@Named
@SessionScoped
public class UserEditBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int CLUB_PREFIX_END_INDEX = 7;
    private static final int USERID_WITHOUT_CHECKSUM_LENGTH = 12;
    private RandomString randomString = new RandomString();

    @Resource(mappedName = "java:jboss/mail/Default")
    private Session smtpSession;

    @Inject
    private Logger logger;

    @Inject
    private UserService userService;

    @Inject
    private MembershipService membershipService;

    @Inject
    private GradingService gradingService;

    @Inject
    private ConfigurationService configurationService;

    private User selectedUser;
    private LocalDate selectedBeginDate;
    private Discipline selectedDiscipline;
    private Grading selectedGrading;
    private LocalDate selectedGradingDate;
    private UploadedFile profilePic;

    private User user;

    @PostConstruct
    private void initUser() {
        String username = Util.getUserName();
        user = userService.getUserByUsername(username);
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public LocalDate getSelectedBeginDate() {
        return selectedBeginDate;
    }

    public void setSelectedBeginDate(LocalDate selectedBeginDate) {
        this.selectedBeginDate = selectedBeginDate;
    }

    public Discipline getSelectedDiscipline() {
        return selectedDiscipline;
    }

    public void setSelectedDiscipline(Discipline selectedDiscipline) {
        this.selectedDiscipline = selectedDiscipline;
    }

    public Grading getSelectedGrading() {
        return selectedGrading;
    }

    public void setSelectedGrading(Grading selectedGrading) {
        this.selectedGrading = selectedGrading;
    }

    public LocalDate getSelectedGradingDate() {
        return selectedGradingDate;
    }

    public void setSelectedGradingDate(LocalDate selectedGradingDate) {
        this.selectedGradingDate = selectedGradingDate;
    }

    public UploadedFile getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(UploadedFile profilePic) {
        this.profilePic = profilePic;
    }

    public boolean isShowCreateMember() {
        return selectedUser != null;
    }

    public void newMember() {
        this.selectedUser = new User();
    }

    public void abort() {
        selectedUser = null;
    }

    public void saveMember() {
        if (selectedUser.getId() == null) {
            String userId = getNextUserId();
            selectedUser.setUsername(userId);

            String password = randomString.nextString();
            selectedUser.setPassword(generateHashedPassword(password));

            userService.persist(selectedUser);
            mailPasswordToUser(password);

            membershipService.beginMembership(selectedUser, user.getClub(), selectedBeginDate);

            if (selectedGrading != null) {
                gradingService.addGradingForUser(selectedUser, selectedGrading, selectedGradingDate);
            }

            uploadProfilePic();
        } else {
            userService.merge(selectedUser);
        }
        abort();
    }

    private void uploadProfilePic() {
        String profilePicPath = configurationService.getConfigurationValue(ConfigurationService.PROFILE_PIC_PATH);
        try (InputStream is = profilePic.getInputstream()) {
            Files.copy(is, new File(profilePicPath, selectedUser.getUsername()).toPath());
        } catch (IOException e) {
            logger.error("Fehler beim schreiben des Profilbildes");
        }
    }

    private String getNextUserId() {
        String maxId = userService.getMaxIdForClub(user.getClub());
        // cut last check number
        String prefix = maxId.substring(0, CLUB_PREFIX_END_INDEX);
        Integer nextId = Integer.parseInt(maxId.substring(CLUB_PREFIX_END_INDEX, USERID_WITHOUT_CHECKSUM_LENGTH)) + 1;

        DecimalFormat idFormat = new DecimalFormat("00000");
        String idWithoutCheckNumber = prefix + idFormat.format(nextId);

        return Util.calculateEan13(idWithoutCheckNumber);
    }

    private String generateHashedPassword(String password) {
        logger.info("New Password for user {} is now {}", selectedUser.getUsername(), password);
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private void mailPasswordToUser(String password) {
        if (configurationService.getConfigurationBooleanValue("sendmail.enabled")) {
            try {

                String recipient = selectedUser.getEmail();
                String subject = configurationService.getConfigurationValue("sendmail.newuser.subject");
                String body = String.format(configurationService.getConfigurationValue("sendmail.newuser.body"),
                        selectedUser.getForename(),
                        selectedUser.getUsername(),
                        password);

                MimeMessage msg = new MimeMessage(smtpSession);
                //set message headers
                msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                msg.addHeader("format", "flowed");
                msg.addHeader("Content-Transfer-Encoding", "8bit");

                msg.setFrom(new InternetAddress("no_reply@djjv.de"));
                msg.setReplyTo(InternetAddress.parse("no_reply@djjv.de", false));

                msg.setSubject(subject, "UTF-8");
                msg.setText(body, "UTF-8");

                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, false));
                Transport.send(msg);
            } catch (MessagingException e) {
                logger.error("Error while sending a mail.");
            }
        } else {
            logger.warn("Mailing is disabled");
        }
    }

    public Set<Discipline> getDisciplines() {
        return gradingService.getDisciplines();
    }

    public List<Grading> getGradings() {
        return gradingService.getGradingsByDiscipline(selectedDiscipline);
    }
}
