package com.bac.oee.security.repositoryimpl;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.amex.srt.security.domain.OEESiteUser;

/**
 * Repository implementation for OEESiteUser
 */
@Repository("oEESiteUserRepository")
public class OEESiteUserRepositoryImpl extends OEESiteUserRepositoryBase {
    public OEESiteUserRepositoryImpl() {
    }

    public List<OEESiteUser> findByZipCode(String zipCode) {

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("findByZipCode not implemented");

    }
}
