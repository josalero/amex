package com.bac.oee.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class KeyStoreUtils {
	private static Log log = LogFactory.getLog(KeyStoreUtils.class);

	public static final String BOA_KEY_128 = "BOA-128";
	public static final String BOA_KEY_192 = "BOA-192";
	private static final String KEYSTORE_FILENAME = "BOA-keystore.ks";
	private static final String KEYSTORE_PASSWORD = "cres#p@=u3EK";

	private static KeyStore ks = null;

	public static synchronized KeyStore getKeyStore() {
		if (ks == null) {
			ks = loadKeyStore();
		}
		return ks;
	}

	public static KeyStore loadKeyStore() {
		KeyStore ks = null;
		try {
			ks = KeyStore.getInstance("JCEKS");
			InputStream is = KeyStoreUtils.class
					.getResourceAsStream(KEYSTORE_FILENAME);
			ks.load(is, KEYSTORE_PASSWORD.toCharArray());
		} catch (KeyStoreException e) {
			log.error("Error loading key store: ", e.fillInStackTrace());
		} catch (NoSuchAlgorithmException e) {
			log.error("Error loading key store: ", e.fillInStackTrace());
		} catch (CertificateException e) {
			log.error("Error loading key store: ", e.fillInStackTrace());
		} catch (IOException e) {
			log.error("Error loading key store: ", e.fillInStackTrace());
		}
		return ks;
	}

	public static synchronized void storeKeyStore() throws Exception {
		File keyStoreFile = new File(KeyStoreUtils.class.getResource(
				KEYSTORE_FILENAME).toURI());
		// File keyStoreFile = new File(KEYSTORE_FILENAME);
		if (!keyStoreFile.isFile()) {
			// make sure the parent directories exist
			keyStoreFile.getParentFile().mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(keyStoreFile);
		KeyStore ks = getKeyStore();
		ks.store(fos, KEYSTORE_PASSWORD.toCharArray());
	}

	public static SecretKey getSecretKey(String alias) {
		// get the KeyStore to find the key
		KeyStore ks = getKeyStore();
		try {
			return (SecretKey) ks
					.getKey(alias, KEYSTORE_PASSWORD.toCharArray());
		} catch (KeyStoreException e) {
			log.error("Error getting secret key: ", e.fillInStackTrace());
		} catch (NoSuchAlgorithmException e) {
			log.error("Error getting secret key: ", e.fillInStackTrace());
		} catch (UnrecoverableKeyException e) {
			log.error("Error getting secret key: ", e.fillInStackTrace());
		}
		return null;
	}

	public static synchronized void setSecretKey(String alias, SecretKey key)
			throws Exception {
		KeyStore ks = getKeyStore();
		ks.setKeyEntry(alias, key, KEYSTORE_PASSWORD.toCharArray(), null);
		storeKeyStore();
	}

	public static void main(String[] args) throws Exception {
		KeyStore ks = KeyStore.getInstance("JCEKS");

		// To create a new keystore & key
		// Note: Before a keystore can be accessed, it must be loaded
		// Passing null creates an empty keystore
		ks.load(null, KEYSTORE_PASSWORD.toCharArray());

		// generate key
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(192);
		SecretKey newSecretKey = kgen.generateKey();
		ks.setKeyEntry(BOA_KEY_192, newSecretKey,
				KEYSTORE_PASSWORD.toCharArray(), null);

		// store away the keystore
		FileOutputStream fos = new FileOutputStream(
				KEYSTORE_FILENAME);
		ks.store(fos, KEYSTORE_PASSWORD.toCharArray());
		fos.close();

		// check that we have a key
		FileInputStream fis = new FileInputStream(KEYSTORE_FILENAME);
		ks.load(fis, KEYSTORE_PASSWORD.toCharArray());
		fis.close();

		SecretKey secretKey = (SecretKey) ks.getKey(BOA_KEY_192,
				KEYSTORE_PASSWORD.toCharArray());
		System.out.println("Secret key found: " + secretKey);
	}
}
