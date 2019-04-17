package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.Configuration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class ConfigurationService extends BaseService {

	private static final long serialVersionUID = -2208261515736601456L;

	public static final String PROFILE_PIC_PATH = "profile_pic.path";
    private Map<String, String> configurationMap;

    @PostConstruct
    private void init() {
        List<Configuration> configurations = getConfiguration();
        configurationMap = configurations.stream().collect(
                Collectors.toMap(Configuration::getKey, Configuration::getValue));
    }

    private List<Configuration> getConfiguration() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Configuration> query = builder.createQuery(Configuration.class);
        query.from(Configuration.class);
        return em.createQuery(query).getResultList();
    }

    public String getConfigurationValue(String key) {
        return configurationMap.get(key);
    }

    public int getConfigurationIntValue(String key) {
        return Integer.parseInt(configurationMap.get(key));
    }

    public boolean getConfigurationBooleanValue(String key) {
        return Boolean.valueOf(configurationMap.get(key));
    }
}
