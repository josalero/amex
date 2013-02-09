package com.bac.oee.poll.serviceapi;

import org.fornax.cartridges.sculptor.framework.test.AbstractDbUnitJpaTests;
import static org.junit.Assert.fail;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import com.amex.srt.poll.serviceapi.PollResultService;
import com.amex.srt.poll.serviceapi.PollResultServiceTestBase;

/**
 * Spring based transactional test with DbUnit support.
 */
public class PollResultServiceTest extends AbstractDbUnitJpaTests
    implements PollResultServiceTestBase {
    @Autowired
    protected PollResultService pollResultService;

    @Test
    public void testFindByCmsPollId() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindByCmsPollId not implemented");
    }

    @Test
    public void testFindByCmsPollIdAndAnswerKey() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindByCmsPollIdAndAnswerKey not implemented");
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
