/**
 * 
 */
package se.vgregion.portal.core.domain.patterns.entity;

import java.io.Serializable;

/**
 * An entity, as explained in the DDD book.
 * 
 * @author Anders Asplund - Logica
 */
public interface Entity<T, ID> extends Serializable {
    /**
     * Entities have an identity.
     * 
     * @return The identity of this entity.
     */
    ID getId();

}
