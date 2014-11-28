import java.math.BigInteger;
import java.util.ArrayList;


public class haha {
	
	public static void main(String[] args){
		
		ArrayList<BigInteger> a = new ArrayList<BigInteger>();
		for (int i = 0; i < 40; i++) {
			a.add(BigInteger.valueOf(i));
		}
		System.out.println(createSavedPoleInformation(a));
	}
	
	public static BigInteger createSavedPoleInformation(ArrayList<BigInteger> disk) {
		ArrayList<BigInteger> primes = getPrimes(40);
		BigInteger poleValue = BigInteger.ONE; // Start with one
		for (BigInteger i : disk) {
			// If a disk is size n, multiply by the nth prime number
			poleValue = poleValue.multiply(primes.get(i.intValue()));
		}	
		return poleValue;
	}

	
	public static ArrayList<BigInteger> getPrimes(int n) {
		ArrayList<BigInteger> primes = new ArrayList<BigInteger>();
		BigInteger currentNumber = BigInteger.ONE.add(BigInteger.ONE);		
		while (primes.size() < n) {
			if (currentNumber.isProbablePrime(1000000)) {
				primes.add(currentNumber);
			}
			currentNumber = currentNumber.add(BigInteger.ONE);
		}
		return primes;
	}
}
