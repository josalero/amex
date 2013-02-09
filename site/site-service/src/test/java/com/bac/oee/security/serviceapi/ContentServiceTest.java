package com.bac.oee.security.serviceapi;

import org.fornax.cartridges.sculptor.framework.test.AbstractDbUnitJpaTests;
import static org.junit.Assert.fail;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import com.amex.srt.security.serviceapi.ContentService;
import com.amex.srt.security.serviceapi.ContentServiceTestBase;

/**
 * Spring based transactional test with DbUnit support.
 */
public class ContentServiceTest extends AbstractDbUnitJpaTests
    implements ContentServiceTestBase {
    @Autowired
    protected ContentService contentService;

    @Test
    public void testGetPrimaryNavigation() throws Exception {
        // TODO Auto-generated method stub
        fail("testGetPrimaryNavigation not implemented");
    }
}
