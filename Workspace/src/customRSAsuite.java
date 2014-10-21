import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

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