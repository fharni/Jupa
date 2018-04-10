package de.falkharnisch.web.jupa.test;

import de.falkharnisch.web.jupa.page.LoginPage;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.testng.annotations.Test;

import java.io.File;

@RunAsClient
public class LoginIT extends Arquillian {

    private static final String WEBAPP_SRC = "src/main/webapp";

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml");
        File[] dependencies = pom.importRuntimeDependencies().resolve().withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class, "login.war")
                .addPackages(true, "de.falkharnisch.web.jupa")
                .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                                .importDirectory(WEBAPP_SRC).as(GenericArchive.class),
                        "/", Filters.include(".*\\.(xhtml|css)$"))
                .addAsLibraries(dependencies)
                .setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"))
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/faces-config.xml"));
    }

//    @Page
//    private LoginPage loginPage;

//    @Page
//    private HomePage homePage;

    @Test(dataProvider = Arquillian.ARQUILLIAN_DATA_PROVIDER)
//    @Test()
    public void should_login_successfully(@InitialPage LoginPage loginPage) {
        loginPage.login("0502001000015", "falk");
    }
}
