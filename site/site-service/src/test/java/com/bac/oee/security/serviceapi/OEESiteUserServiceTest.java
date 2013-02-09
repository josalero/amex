package com.bac.oee.security.serviceapi;

import org.fornax.cartridges.sculptor.framework.test.AbstractDbUnitJpaTests;
import static org.junit.Assert.fail;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Spring based transactional test with DbUnit support.
 */
public class OEESiteUserServiceTest extends AbstractDbUnitJpaTests
    implements OEESiteUserServiceTestBase {
    @Autowired
    protected OEESiteUserService oEESiteUserService;

    @Test
    public void testFindByUserName() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindByUserName not implemented");
    }

    @Test
    public void testFindByFirstName() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindByFirstName not implemented");
    }

    @Test
    public void testFindByLastName() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindByLastName not implemented");
    }

    @Test
    public void testFindByZipCode() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindByZipCode not implemented");
    }

    @Test
    public void testFindById() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindById not implemented");
    }

    @Test
    public void testFindAll() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindAll not implemented");
    }

    @Test
    public void testSave() throws Exception {
        // TODO Auto-generated method stub
        fail("testSave not implemented");
    }

    @Test
    public void testDelete() throws Exception {
        // TODO Auto-generated method stub
        fail("testDelete not implemented");
    }
}
