package com.bac.oee.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import com.bac.oee.util.EncryptionUtils;

/**
 * EncryptedStringUserType will automatically hash string values, thereby
 * encapsulating the process.
 * 
 * @author christopher.tai@starcomworldwide.com
 */
@SuppressWarnings("serial")
public class HashedStringUserType implements UserType, Serializable, Cloneable {
	private static final int[] TYPES = new int[] { Types.VARCHAR };

	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

	public Object deepCopy(Object value) throws HibernateException {
		if (value == null) {
			return null;
		} else {
			return new String((String) value);
		}
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		}
		if (x == null) {
			return false;
		}
		return x.equals(y);
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public boolean isMutable() {
		return false;
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		String hashedValue = (String) Hibernate.STRING
				.nullSafeGet(rs, names[0]);
		return hashedValue;
	}

	public void nullSafeSet(PreparedStatement ps, Object value, int index)
			throws HibernateException, SQLException {
		if (value != null) {
			String hashedValue = EncryptionUtils.createHash((String) value);
			Hibernate.STRING.nullSafeSet(ps, hashedValue, index);
		} else {
			Hibernate.STRING.nullSafeSet(ps, (String)value, index);
		}
	}

	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

	public Class<String> returnedClass() {
		return String.class;
	}

	public int[] sqlTypes() {
		return TYPES;
	}
}
