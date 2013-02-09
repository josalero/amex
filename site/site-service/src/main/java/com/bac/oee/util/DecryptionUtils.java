package com.bac.oee.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DecryptionUtils {
	public static void main(String[] args) throws Exception {
		File input = null;
		if (args.length > 0) {
			input = new File(args[0]);
		}
		if (input != null && input.isFile()) {
			BufferedReader br = new BufferedReader(new FileReader(input));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] columns = line.split("\t");
				for (int i = 0; i < columns.length; i++) {
					String plaintext = EncryptionUtils.decrypt(columns[i]);
					if (plaintext == null) {
						plaintext = columns[i];
					}
					if (i != 0) {
						System.out.print("\t");
					}
					System.out.print(plaintext);
				}
				System.out.println();
			}
		} else {
			System.out.println(EncryptionUtils.decrypt(args[0]));
		}
	}
}
