package de.falkharnisch.web.jupa.producer;

import de.falkharnisch.web.jupa.producer.qualifier.ApplicationManaged;
import de.falkharnisch.web.jupa.producer.qualifier.ContainerManaged;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@ApplicationScoped
public class EntityManagerProducer {

    // Container Managed EntityManager

    @Produces
    @Default
    @ContainerManaged
    @PersistenceContext
    private EntityManager em;

    // Application-managed EntityManager

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Produces
    @ApplicationManaged
    @RequestScoped
    public EntityManager erzeugeEntityManager() {
        return emf.createEntityManager();
    }


    /**
     * Diese Methode wird fuer ApplicationManaged EntityManager vor Beendigung
     * ihres Scopes gerufen. Damit kann der EntityManager geschlossen und
     * damit gehaltene DB-Ressourcen freigegeben werden.
     *
     * @param em der zu schliessende EntityManager
     */
    public void closeEntityManager(@Disposes @ApplicationManaged EntityManager em) {
        em.close();
    }
}
