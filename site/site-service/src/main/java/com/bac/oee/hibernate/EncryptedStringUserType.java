package com.bac.oee.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

import com.bac.oee.util.EncryptionUtils;

/**
 * EncryptedStringUserType will automatically encrypt and decrypt string values,
 * thereby encapsulating the process.
 * 
 * @author christopher.tai@starcomworldwide.com
 */
@SuppressWarnings("serial")
public class EncryptedStringUserType implements UserType, Serializable,
		Cloneable {
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
			
		String encryptedValue = (String) StringType.INSTANCE.nullSafeGet(rs,
				names[0]);
		if (encryptedValue != null) {
			return EncryptionUtils.decrypt(encryptedValue);
		} else {
			return null;
		}
	}

	public void nullSafeSet(PreparedStatement ps, Object value, int index)
			throws HibernateException, SQLException {
		if (value != null) {
			String encryptedValue = EncryptionUtils.encrypt((String) value);
			StringType.INSTANCE.nullSafeSet(ps, encryptedValue, index);
		} else {
			StringType.INSTANCE.nullSafeSet(ps, (String)value, index);
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
