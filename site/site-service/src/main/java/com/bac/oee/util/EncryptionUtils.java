package com.bac.oee.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class EncryptionUtils {
	private static Log log = LogFactory.getLog(EncryptionUtils.class);

	public static String createHash(String plaintext) {
		String digest = null;
		if (plaintext != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				md.update(plaintext.getBytes("UTF-8"));
				byte raw[] = md.digest();
				digest = (new BASE64Encoder()).encode(raw);
			} catch (NoSuchAlgorithmException e) {
				log.error("Error encrypting plaintext: ", e.fillInStackTrace());
			} catch (UnsupportedEncodingException e) {
				log.error("Error encrypting plaintext: ", e.fillInStackTrace());
			}
		}
		return digest;
	}

	public static String encrypt(String plaintext) {
		String cyphertext = null;
		if (plaintext != null) {
			// get the secret key
			SecretKey secretKey = KeyStoreUtils
					.getSecretKey(KeyStoreUtils.BOA_KEY_192);
			// Generate the secret key specs.
			byte[] raw = secretKey.getEncoded();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			// Instantiate the cipher
			try {
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
				byte[] encrypted = cipher.doFinal(plaintext.getBytes("UTF-8"));
				cyphertext = new String(Base64.encode(encrypted), "UTF-8");
			} catch (NoSuchAlgorithmException e) {
				log.error("Error encrypting plaintext: ", e.fillInStackTrace());
			} catch (NoSuchPaddingException e) {
				log.error("Error encrypting plaintext: ", e.fillInStackTrace());
			} catch (InvalidKeyException e) {
				log.error("Error encrypting plaintext: ", e.fillInStackTrace());
			} catch (IllegalBlockSizeException e) {
				log.error("Error encrypting plaintext: ", e.fillInStackTrace());
			} catch (BadPaddingException e) {
				log.error("Error encrypting plaintext: ", e.fillInStackTrace());
			} catch (UnsupportedEncodingException e) {
				log.error("Error encrypting plaintext: ", e.fillInStackTrace());
			}
		}
		return cyphertext;
	}

	public static String decrypt(String cyphertext) {
		String plaintext = null;
		if (cyphertext != null) {
			// get the secret key
			SecretKey secretKey = KeyStoreUtils
					.getSecretKey(KeyStoreUtils.BOA_KEY_192);
			// Generate the secret key specs.
			byte[] raw = secretKey.getEncoded();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			// Instantiate the cipher
			try {
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.DECRYPT_MODE, skeySpec);
				byte[] decrypted = cipher.doFinal(Base64.decode(cyphertext
						.getBytes("UTF-8")));
				plaintext = new String(decrypted);
			} catch (NoSuchAlgorithmException e) {
				log.error("Error decrypting cyphertext: ", e);
			} catch (NoSuchPaddingException e) {
				log.error("Error decrypting cyphertext: ", e);
			} catch (InvalidKeyException e) {
				log.error("Error decrypting cyphertext: ", e);
			} catch (IllegalBlockSizeException e) {
				log.error("Error decrypting cyphertext: ", e);
			} catch (BadPaddingException e) {
				log.error("Error decrypting cyphertext: ", e);
			} catch (UnsupportedEncodingException e) {
				log.error("Error decrypting cyphertext: ", e);
			} catch (IllegalArgumentException e) {
				log.error("Error decrypting cyphertext: ", e);
			}
		}
		return plaintext;
	}

	public static void main(String[] args) throws Exception {
		File input = null;
		if (args.length > 0) {
			input = new File(args[0]);
		}
		if (input != null && input.isFile()) {
			BufferedReader br = new BufferedReader(new FileReader(input));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(EncryptionUtils.encrypt(line));
			}
		} else {
			System.out.println(EncryptionUtils.encrypt(args[0]));
		}
	}
}
