package HSCTF;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/*
Enter your desired private key
***REDACTED***
Here is your public key: 9688413780250260554169572591781852128738032502213227267925946853725871884460706729937087547164200656773254197013784255205945933930652283528710732368043399
Here is your public modulus: 10088154142733354288123217318727770466063748636251592905837230742938574451978881838242647376668649071022125772619439767278139514280626548410654953587202089
Enter your desired message
***REDACTED***
Here is your cryptotext: 4175026061673890692556640107606427647836576705798274365139647325625539738907501658190699138730320824379544184447726828986903070171381565091800450524704863
*/

public class customRSAsuite {
	
	public static BigInteger[] ModGen()
	{
		// This generates two very large primes, and multiplies them.
		Random r = new Random();
		BigInteger p = BigInteger.probablePrime(256, r); 
		BigInteger q = BigInteger.probablePrime(256, r); 
		BigInteger[] ModGen = {p,q, p.multiply(q)}; 
		return ModGen;
	}
	
	public static BigInteger PublicKey(BigInteger PrivateKey, BigInteger[] ModGen)
	{
		// This calculates the PublicKey
		BigInteger p= ModGen[0];
		BigInteger q= ModGen[1];
		BigInteger mod = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		return PrivateKey.modInverse(mod);
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		//get user input for private key
		System.out.println("Enter your desired private key"); 
		long d = reader.nextLong(); 
		BigInteger PrivateKey = BigInteger.valueOf(d);
		
		//generate parameters
		BigInteger[] ModGen = ModGen();
		BigInteger PublicKey = PublicKey(PrivateKey, ModGen);
		System.out.println("Here is your public key: " + PublicKey.toString());
		System.out.println("Here is your public modulus: "+ ModGen[2].toString());
		
		//get user input for message
		System.out.println("Enter your desired message");
		String message = reader.next();
		String ascii = "";
		
		//convert message to ascii to BigInteger
		for(int i = 0; i<message.length(); i++)
		{
			char a = message.charAt(i);
			ascii = ascii + Integer.toString((int) a);
		}
		BigInteger m = new BigInteger(ascii);
		
		
		//encryption step
		BigInteger c = m.modPow(PublicKey, ModGen[2]);
		System.out.println("Here is your cryptotext: " + c.toString());
		
		reader.close();
		
	}

}
