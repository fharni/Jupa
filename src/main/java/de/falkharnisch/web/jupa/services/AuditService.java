package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.Audit;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class AuditService extends BaseService<Audit> {

}
