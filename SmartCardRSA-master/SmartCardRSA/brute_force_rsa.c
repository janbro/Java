#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "bigd.h"

/*
 * By default DEBUG mode is off. To turn it on set DEBUGMODE to 1
 */
#define DEBUGMODE 0

/*
 * Macro to allocate memory in BigDigit 
 */
#define BDMALLOC(x) {\
	x = bdNew();\
}

/*
 * Macro to free memory in BigDigit
 */
#define BDFREE(x) {\
	bdFree(&x);\
}

/*
 * Macro to print debug data
 */
#define BDPRINTDECIMAL(str1, x, str2) {\
	if(DEBUGMODE) {\
		bdPrintDecimal(str1, x, str2);\
	}\
}

/* 
 * Known plain text message used for brute force attack and it is in string format 'TEST SMARTCARD RSA' 
 */
#define KNOWNMESSAGE "846983843283776582846765826832828365"

/*
 * This function calculates the cipher text for known plain text message using
 * knownc = knownm^e mod N
 */
void get_known_cipher_text(BIGD knownm, BIGD knownc, BIGD e, BIGD N)
{

	bdConvFromDecimal(knownm, KNOWNMESSAGE);
	BDPRINTDECIMAL("\nknown plain text message = ", knownm, "\n");
	
	// knownc = knownm^e mod N
	bdModExp(knownc, knownm, e, N);
	BDPRINTDECIMAL("known cipher text = ", knownc, "\n");

}

/*
 * This function finds out the value of p and dp. 
 * To find p it mounts brute force attack on dp using known plain and cipher text pair
 * dp iterates from 1 to (N/2) 
 * if we still dont find p and dp then returns error
 * dp = e^-1 mod (p-1)
 */
int get_p_dp(BIGD p, BIGD dp, BIGD knownm, BIGD knownc, BIGD N, BIGD e)
{
	
	BIGD d1iterator, n1, n2;
	int d1 = 0;

	BDMALLOC(d1iterator)
	BDMALLOC(n1)
	BDMALLOC(n2)

	//d1iterator = N/2
	bdShortDiv(d1iterator, n1, N, 2);
	BDPRINTDECIMAL("\ndp iterator can go till = ", d1iterator, "\n");
	
	d1 = 2;
	while(bdShortCmp(d1iterator, d1) != 0)
        {
                //n1 = c1^dp
                bdPower(n1, knownc, d1);
                //n2 = n1 - m i.e. c1^dp - m
                bdSubtract(n2, n1, knownm);
                //n3 = n2 % N i.e. ( c1^dp - m ) % N
                bdModulo(n1, n2, N);
                // p=gcd(n3,N)
                bdGcd(p, n1, N);
                //check if p'is prime and not equal to 1
                if((bdShortCmp(p, 1) != 0) && (bdIsPrime(p, 64))) {
			if(bdShortCmp(p, d1) < 0) {
				printf("\nNo proper p found \n");
				BDFREE(d1iterator)
				BDFREE(n1)
				BDFREE(n2)
				return (-1);
			}
			bdPrintDecimal("p' = ", p, "\n");
                        printf("dp' = %d\n", d1);
			break;
                }
                d1++;
        }

	bdPrintDecimal("\np = ", p, "\n");

	// if d1 reached till the end of loop and still p is not found then generate error
	if(bdShortCmp(d1iterator, d1) == 0) {
		bdPrintDecimal("\nP is not found did bruteforce attack till ", d1iterator, "\n");
		BDFREE(d1iterator)
		BDFREE(n1)
		BDFREE(n2)
		return (-1);
	}

	/* calculates dp */
	// n1 = p - 1
	bdShortSub(n1, p, 1);
	// dp = e^-1 mod n1
	bdModInv(dp, e, n1);
	bdPrintDecimal("\ndp = ", dp, "\n");
	
	BDFREE(d1iterator)
	BDFREE(n1)
	BDFREE(n2)

	return 0;
}

/*
 * This function finds out q and dq
 * q = N / p
 * dq = e^-1 mod (q - 1)
 */
void get_q_dq(BIGD q, BIGD dq, BIGD N, BIGD p, BIGD e)
{
	BIGD r;

	BDMALLOC(r)

	// q = N / p
	bdDivide(q, r, N, p);
	bdPrintDecimal("\nq = ", q, "\n");
	
	// r = q - 1
	bdShortSub(r, q, 1);
	// dq = e^-1 mod r
	bdModInv(dq, e, r);
	bdPrintDecimal("\ndq = ", dq, "\n");

	BDFREE(r) 
}

/* 
 * Converts the message in decimal format to ASCII string format
 */
int get_plain_text_message_in_string(BIGD m)
{
	size_t ndigits;
	char *hexm = NULL;
	char *plaintext = NULL;
	char *pm = NULL;
	unsigned int decimalm = 0;
	int index = 0;
	int len = 0;
	
	ndigits = m->ndigits;

	hexm = malloc((int)ndigits * 10);
	if(hexm == NULL) {
		printf("\nError in allocating memory\n");
		return (-1);
	}
	bdConvToHex(m, hexm, (int)ndigits * 10);

	len = (int)strlen(hexm);
	plaintext = malloc(((int)(len/2)) + 1);
	if(plaintext == NULL) {
		printf("\nError in allocating memory\n");
		return (-1);
	}

	pm = plaintext;	
	while(index < len) {
		sscanf(hexm+index, "%2x", &decimalm);
		*plaintext++ = decimalm;
		index = index + 2;
	}
		
	*plaintext = '\0';
	printf("\nPlain text message in string : %s\n", pm);

	if(hexm) {
	  free(hexm);
	  hexm = NULL;
	}
	if(pm) {
	  free(pm);
	  pm = NULL;
	}
	
	return 0;
}

/*
 * This function generates value of plain text message in decimal using Chinese Remainder Theorem
 * m1 = c^dp mod p
 * m2 = c^dq mod q
 * qinv = q^-1 mod p
 * h = qinv * (m1 - m2) mod p
 * m = m2 + (h * q)
 */
void get_plain_text_message(BIGD m, BIGD c, BIGD dp, BIGD p, BIGD q, BIGD dq)
{
	BIGD m1, m2, h, qinv, msub, r;

	BDMALLOC(m1);
	BDMALLOC(m2);
	BDMALLOC(h);
	BDMALLOC(qinv);
	BDMALLOC(msub);
	BDMALLOC(r);

	// m1 = c^dp mod p
	bdModExp(m1, c, dp, p);
	BDPRINTDECIMAL("\nm1 = ", m1, "\n");
 	
	// m2 = c^dq mod q
	bdModExp(m2, c, dq, q);
	BDPRINTDECIMAL("\nm2 = ", m2, "\n");
 
	// qinv = q^-1 mod p
	bdModInv(qinv, q, p);
	BDPRINTDECIMAL("\nqinv = ", qinv, "\n");
 
	// msub = m1 - m2
	bdSubtract(msub, m1, m2);
	BDPRINTDECIMAL("\nmsub = ", msub, "\n");
	
	// h = (qinv * msub) mod p
	bdModMult(h, qinv, msub, p);
	BDPRINTDECIMAL("\nh = ", h, "\n");
	
	// r = h * q
	bdMultiply(r, h, q);
	BDPRINTDECIMAL("\nr = ", r, "\n");
	
	// m = m2 + r
	bdAdd(m, m2, r);
	bdPrintDecimal("\nplaintext message in decimal = ", m, "\n");

	get_plain_text_message_in_string(m);
	
	BDFREE(m1);
	BDFREE(m2);
	BDFREE(h);
	BDFREE(qinv);
	BDFREE(msub);
	BDFREE(r);

}

int main(int argc, char *argv[])
{
	BIGD m, e, N, c, p, q, dp, dq, knownc, knownm;
	int ret = 0;
		
	if(argc != 4) {
		printf("\nUsage: [%s] <Value of N> <Value of e> <value of cipher text>\n", argv[0]);
		return 0;
	}

	BDMALLOC(m)	
	BDMALLOC(e)	
	BDMALLOC(p)	
	BDMALLOC(dp)	
	BDMALLOC(q)	
	BDMALLOC(dq)	
	BDMALLOC(c)	
	BDMALLOC(N)	
	BDMALLOC(knownc)	
	BDMALLOC(knownm)	
	
	/* Read in modulus values for three 512-bit public keys */

	bdConvFromDecimal(N, argv[1]);
	bdPrintDecimal("\nN = ", N, "\n");

	bdConvFromDecimal(e, argv[2]);	
	bdPrintDecimal("\ne = ", e, "\n");
        
	bdConvFromDecimal(c, argv[3]);
	bdPrintDecimal("\nciphertext = ", c, "\n");

	get_known_cipher_text(knownm, knownc, e, N);

	ret = get_p_dp(p, dp, knownm, knownc, N, e);
	if(ret == -1) {
		printf("\nNo plain text message found\n");
		goto clean_up;
	}

	get_q_dq(q, dq, N, p, e);

	get_plain_text_message(m, c, dp, p, q, dq);


clean_up:
	BDFREE(m);
	BDFREE(e);
	BDFREE(N);
	BDFREE(c);
	BDFREE(p);
	BDFREE(q);
	BDFREE(dp);
	BDFREE(dq);
	BDFREE(knownc);
	BDFREE(knownm);
	
	return 0;
}

