package se.vgregion.portal.core.infrastructure.persistence.jpa;

import se.vgregion.portal.core.domain.patterns.entity.MockEntity;
import se.vgregion.portal.core.domain.patterns.entity.MockEntityRepository;

/**
 * This action do that and that, if it has something special it is.
 * 
 * @author <a href="mailto:david.rosell@redpill-linpro.com">David Rosell</a>
 */
public class JpaMockEntityRepository extends JpaRepository<MockEntity, Long> implements MockEntityRepository {
}
