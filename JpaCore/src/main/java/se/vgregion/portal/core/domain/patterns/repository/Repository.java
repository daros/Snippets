package se.vgregion.portal.core.domain.patterns.repository;

import org.springframework.transaction.annotation.Transactional;
import se.vgregion.portal.core.domain.patterns.entity.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Anders Asplund - Logica
 * @param <ID>
 * 
 */
@Transactional
public interface Repository<T extends Entity<T, ID>, ID extends Serializable> {
    /**
     * Store <code>object</code> in the database.
     * 
     * @param object
     *            the instance to save in the database
     * @return the object stored in the database
     */
    T persist(T object);

    /**
     * Taken from the EntityManager documentation: Synchronize the persistence context to the underlying database.
     */
    void flush();

    /**
     * Remove <code>object</code> from the database.
     * 
     * @param object
     *            the object to be removed from the database
     */
    void removeEntity(T object);

    /**
     * Delete by primary key.
     * 
     * @param pk
     *            primary key
     */
    void deleteByPk(ID pk);

    /**
     * Find all instances of <code>T</code> in the database.
     * 
     * @return a list <code>T</code> objects
     */
    List<T> findAll();

    /**
     * Find instances of <code>T</code> that match the criteria defined by query <code>queryName</code>.
     * <code>args</code> provide the values for any named parameters in the query identified by
     * <code>queryName</code>.
     * 
     * @param queryName
     *            the named query to execute
     * @param args
     *            the values used by the query
     * @return a list of <code>T</code> objects
     */
    List<T> findByNamedQuery(String queryName, Map<String, ? extends Object> args);

    /**
     * Find instances of <code>T</code> that match the criteria defined by query <code>queryName</code>.
     * <code>args</code> provide values for positional arguments in the query identified by <code>queryName</code>.
     * 
     * @param queryName
     *            the named query to execute
     * @param args
     *            the positional values used in the query
     * @return a list of <code>T</code> objects
     */
    List<T> findByNamedQuery(String queryName, Object[] args);

    /**
     * Find a single instance of <code>T</code> using the query named <code>queryName</code> and the arguments
     * identified by <code>args</code>.
     * 
     * @param queryName
     *            the name of the query to use
     * @param args
     *            the arguments for the named query
     * @return T or null if no objects match the criteria if more than one instance is returned.
     */
    T findInstanceByNamedQuery(String queryName, Object[] args);

    /**
     * Find a single instance of <code>T</code> using the query named <code>queryName</code> and the arguments
     * identified by <code>args</code>.
     * 
     * @param queryName
     *            the name of the query to use
     * @param args
     *            a Map holding the named parameters of the query
     * @return T or null if no objects match the criteria if more than one instance is returned.
     */
    T findInstanceByNamedQuery(String queryName, Map<String, ? extends Object> args);

    /**
     * Finds the instance of <code>T</code> identified by <code>pk</code>.
     * 
     * @param pk
     *            The primary key
     * @return an object of <code>T</code>
     */
    T findByPk(ID pk);

    /**
     * Taken from the EntityManager documentation: Clear the persistence context, causing all managed entities to
     * become detached. Changes made to entities that have not been flushed to the database will not be persisted.
     */
    void clear();

    /**
     * Update existing <code>object</code>.
     * 
     * @param object
     *            the object to update in the database
     * @return an object of <code>T</code>
     */
    T merge(T object);

    /**
     * Taken from the EntityManager documentation: Refresh the state of the instance from the database, overwriting
     * changes made to the entity, if any.
     * 
     * @param object
     *            the object to refresh
     */
    void refresh(T object);

    /**
     * Check if the entity is available in the EntityManager.
     *
     * @param entity the entity object
     * @return true if present
     */
    boolean contains(T entity);

    /**
     * Convenience method that lets you persist or merge an entity transparently depending on its state.
     *
     * @param entity the entity
     * @return the stored entity
     */
    T store(T entity);
}
