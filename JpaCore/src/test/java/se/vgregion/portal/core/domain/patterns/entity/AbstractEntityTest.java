package se.vgregion.portal.core.domain.patterns.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This action do that and that, if it has something special it is.
 *
 * @author <a href="mailto:david.rosell@redpill-linpro.com">David Rosell</a>
 */
public class AbstractEntityTest {
    AbstractEntity<TestEntity, Long> entity1;
    AbstractEntity<TestEntity, Long> entity2;
    AbstractEntity<TestEntity, Long> entitySame1;
    AbstractEntity<TestEntity, Long> entityNull1;
    AbstractEntity<TestEntity, Long> entityNull2;

    @Before
    public void setUp() {
        entity1 = new TestEntity(1L);
        entity2 = new TestEntity(2L);
        entitySame1 = new TestEntity(1L);
        entityNull1 = new TestEntity(null);
        entityNull2 = new TestEntity(null);
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(1, entity1.hashCode());
        assertEquals(entity1.hashCode(), entitySame1.hashCode());
        assertNotSame(entity1.hashCode(), entity2.hashCode());
        assertNotSame(entityNull1.hashCode(), entityNull2.hashCode());
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue(entity1.equals(entitySame1));
        assertFalse(entity1.equals(entity2));
        assertFalse(entityNull1.equals(entityNull2));
        assertTrue(entityNull1.equals(entityNull1));
        assertFalse(entity1.equals(entityNull1));
        assertFalse(entity1.equals(new Object()));
        assertFalse(entity1.equals(null));
    }

    @Test
    public void testToString() throws Exception {
        assertTrue(entity1.toString().contains("id=1"));
    }

    private class TestEntity extends AbstractEntity<TestEntity, Long> {

        private Long id;

        @Override
        public Long getId() {
            return id;
        }

        public TestEntity(Long id) {
            this.id = id;
        }
    }
}
