package se.vgregion.portal.core.domain.patterns.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author Anders Asplund - Logica
 * 
 */
@SuppressWarnings({"unchecked", "serial"})
public abstract class AbstractEntity<T extends Entity, ID extends Serializable> implements Entity<T, ID> {

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        if (getId() == null) {
            return super.hashCode();
        }
        return getId().hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }

        if (getClass() != other.getClass()) {
            return false;
        }

        T otherType = (T) other;

        if (getId() == null || otherType.getId() == null) {
            return false;
        }

        return new EqualsBuilder().append(otherType.getId(), getId()).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
