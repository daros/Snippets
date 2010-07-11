package se.vgregion.portal.core.infrastructure.persistence.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.vgregion.portal.core.domain.patterns.entity.Entity;
import se.vgregion.portal.core.domain.patterns.repository.Repository;

/**
 * @author Anders Asplund - Logica
 * 
 */
public abstract class JpaRepository<T extends Entity<T, ID>, ID extends Serializable> implements Repository<T, ID> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JpaRepository.class);

    /**
     * Entity manager ref.
     */
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Entity class type.
     */
    protected Class<T> type;

    public void setType(Class<T> type) {
        this.type = type;
    }

    /**
     * Default constructor.
     */
    @SuppressWarnings("unchecked")
    public JpaRepository() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Parameterized constructor.
     * 
     * @param type
     *            Entity class type
     */
    public JpaRepository(Class<T> type) {
        this.type = type;
    }

    /**
     * Does entity manager contain entity?
     * 
     * @param entity
     *            Entity to check for
     * @return true if found
     */
    public boolean contains(T entity) {
        return entityManager.contains(entity);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Query query = entityManager.createQuery("select o from " + type.getSimpleName() + " o");
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findByNamedQuery(String queryName, Map<String, ? extends Object> args) {
        Query namedQuery = entityManager.createNamedQuery(queryName);
        for (Map.Entry<String, ? extends Object> parameter : args.entrySet()) {
            namedQuery.setParameter(parameter.getKey(), parameter.getValue());
        }
        return namedQuery.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findByNamedQuery(String queryName, Object[] args) {
        Query namedQuery = entityManager.createNamedQuery(queryName);
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                namedQuery.setParameter(i + 1, args[i]);
            }
        }
        return namedQuery.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    public T findInstanceByNamedQuery(String queryName, Object[] args) {
        Query namedQuery = entityManager.createNamedQuery(queryName);
        if (args != null) {
            int position = 1;
            for (Object value : args) {
                // will throw IllegalArgumentException if position is incorrect
                // or value is incorrect type
                namedQuery.setParameter(position++, value);
            }
        }

        try {
            @SuppressWarnings("unchecked")
            T result = (T) namedQuery.getSingleResult();
            return result;
        } catch (NoResultException nre) {
            LOGGER.warn("No entiry found for query: {}", namedQuery);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public T findInstanceByNamedQuery(String queryName, Map<String, ? extends Object> args) {
        Query namedQuery = entityManager.createNamedQuery(queryName);
        if (args != null) {
            for (Map.Entry<String, ? extends Object> entry : args.entrySet()) {
                // will throw IllegalArgumentException if parameter name not
                // found or value is incorrect type
                namedQuery.setParameter(entry.getKey(), entry.getValue());
            }
        }

        try {
            @SuppressWarnings("unchecked")
            T result = (T) namedQuery.getSingleResult();
            return result;
        } catch (NoResultException nre) {
            LOGGER.warn("No entity found for query: {}", namedQuery);
            return null;
        }

    }

    /**
     * {@inheritDoc}
     */
    public T findByPk(ID pk) {
        return entityManager.find(type, pk);
    }

    /**
     * {@inheritDoc}
     */
    public void flush() {
        entityManager.flush();
    }

    /**
     * {@inheritDoc}
     */
    public T persist(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        entityManager.clear();
    }

    /**
     * {@inheritDoc}
     */
    public void removeEntity(T entity) {
        entityManager.remove(entity);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteByPk(ID taskId) {
        T entity = entityManager.find(type, taskId);
        entityManager.remove(entity);
    }

    /**
     * {@inheritDoc}
     */
    public T merge(T entity) {
        return entityManager.merge(entity);
    }

    /**
     * {@inheritDoc}
     */
    public T store(T entity) {
        if (entity.getId() == null || entityManager.find(type, entity.getId()) == null) {
            persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void refresh(T entity) {
        entityManager.refresh(entity);
    }
}
