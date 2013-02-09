package com.bac.oee.security.serviceimpl;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.springframework.stereotype.Service;

import com.amex.srt.content.Navigation;

/**
 * Implementation of ContentService.
 */
@Service("contentService")
public class ContentServiceImpl extends ContentServiceImplBase {
    public ContentServiceImpl() {
    }

    public Navigation getPrimaryNavigation(ServiceContext ctx) {

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "getPrimaryNavigation not implemented");

    }
}
