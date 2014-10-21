package rsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Key Cracker
 * @author machao & Ziwei Song
 * @version 1.0
 */
public class KeyCracker {

	/**
	 * Main function for KeyCracker
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Please enter the public key value");
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		/*try {
			input = inFromUser.readLine();
		} catch (IOException ex) {
			System.out.println("Read in error!");
		}*/
		BigInteger e = new BigInteger("9688413780250260554169572591781852128738032502213227267925946853725871884460706729937087547164200656773254197013784255205945933930652283528710732368043399");
		System.out.println("Please enter the c value");
		inFromUser = new BufferedReader(new InputStreamReader(System.in));
		/*try {
			input = inFromUser.readLine();
		} catch (IOException ex) {
			System.out.println("Read in error!");
		}*/
		BigInteger c = new BigInteger("4175026061673890692556640107606427647836576705798274365139647325625539738907501658190699138730320824379544184447726828986903070171381565091800450524704863");
		crack(e, c);
	}

	/**
	 * Crack a key
	 * @param e public key value
	 * @param c base value
	 */
	public static void crack(BigInteger e, BigInteger c) {

		int phi = RSAKeyGenerator.totient(c.intValue()); 
		BigInteger d = new BigInteger("0");

		for(int i = 1; i < phi; i++) {
			String in = ""+i * phi + 1;
			BigInteger b = new BigInteger(in);
			if(b.mod(e).equals(0)) {
				d = b.divide(e);
				break;
			}
		}
		System.out.println("D was found to be " + d.toString());
		Cipher cipher = new Cipher();
		Key priKey = new Key(d, c);
		String input = "";
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		while(!input.equals("quit")) {
			System.out.println("Please enter a code to decrypt(quit to quit)");
			try {
				input = inFromUser.readLine();
			} catch (IOException ex) {
				System.out.println("Read in error!");
			}
			System.out.println(cipher.decrypt(input, priKey));
		}
	}
}