#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "bigd.h"

/*
 * By default DEBUG mode is off. To turn it on set DEBUGMODE to 1 
 */
#define DEBUGMODE 1

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
 * Known values of N, e and cipher text. Given in the problem statement.
 */
#define VALN "9688413780250260554169572591781852128738032502213227267925946853725871884460706729937087547164200656773254197013784255205945933930652283528710732368043399"

#define VALE "10088154142733354288123217318727770466063748636251592905837230742938574451978881838242647376668649071022125772619439767278139514280626548410654953587202089"

#define VALC "4175026061673890692556640107606427647836576705798274365139647325625539738907501658190699138730320824379544184447726828986903070171381565091800450524704863"

/* 
 * Value of P found by mounting brute force attck using SAGE maths software
 */
#define VALP "7018218586682780617408992942049094507398545988573755376711814005672197148814320105215550815776617482233629755364714330648127911440775205101042685290382227"

/* 
 * This function based on value of p finds dp
 * dp = e^-1 mod (p - 1)
 */
void get_dp(BIGD dp, BIGD p, BIGD e)
{
	BIGD r;
	
	BDMALLOC(r);

	// r = p - 1 
	bdShortSub(r, p, 1);
	// dp = e^-1 mod r
	bdModInv(dp, e, r);
	bdPrintDecimal("\ndp = ", dp, "\n");

	BDFREE(r);
}

/*
 * This function finds out q and dq.
 * q = N / p
 * dq = e^-1 mod (q-1)
 */
void get_q_dq(BIGD q, BIGD dq, BIGD N, BIGD p, BIGD e)
{
	BIGD r;
	
	BDMALLOC(r);
	
	// q = N / p
	bdDivide(q, r, N, p);
	bdPrintDecimal("\nq = ", q, "\n");

	// r = q - 1
	bdShortSub(r, q, 1);
	// dq = e^-1 mod r
	bdModInv(dq, e, r);
	bdPrintDecimal("\ndq = ", dq, "\n");
	
	BDFREE(r);
}

/* 
 * Converts the message in decimal format to ASCII string format
 */
int get_plain_text_message_in_string(BIGD m)
{
	size_t ndigit;
	char *hexm = NULL;
	char *plaintext = NULL;
	char *pm = NULL;
	unsigned int decimalm = 0;
	int index = 0;
	int len = 0;
	

	ndigit = m->ndigits;

	hexm = malloc((int)ndigit * 10);
	if(hexm == NULL) {
		printf("\nError in allocating memory\n");
		return (-1);
	}
	bdConvToHex(m, hexm, (int)ndigit * 10);

	len = (int)strlen(hexm);
	plaintext = malloc(((int)(len/2)) + 1);
	if(plaintext == NULL) {
		printf("\nError in allocating memory\n");
		return(-1);
	}

	pm = plaintext;	
	while(index < len) {
		sscanf(hexm + index, "%2x", &decimalm);
		*plaintext++ = decimalm;
		index = index + 2;
	}
		
	*plaintext = '\0';
	printf("\nPlain text message in ASCII string format : %s\n\n", pm);

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
void get_plain_text_message(BIGD m, BIGD c, BIGD dp, BIGD p, BIGD dq, BIGD q)
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
	bdPrintDecimal("\nPlaintext message in decimal format = ", m, "\n");
	
	get_plain_text_message_in_string(m);
	
	BDFREE(m1);
	BDFREE(m2);
	BDFREE(h);
	BDFREE(qinv);
	BDFREE(msub);
	BDFREE(r);
}

int main(void)
{
	BIGD m, e, N, c, p, q, dp, dq;
	
	BDMALLOC(m);
	BDMALLOC(e);
	BDMALLOC(p);
	BDMALLOC(dp);
	BDMALLOC(dq);
	BDMALLOC(q);
	BDMALLOC(c);
	BDMALLOC(N);
	
	/* Read in modulus values for three 512-bit public keys */

	bdConvFromDecimal(N, VALN);
	bdPrintDecimal("\nN = ", N, "\n");

	bdConvFromDecimal(e, VALE);	
	bdPrintDecimal("\ne = ", e, "\n");
        
	bdConvFromDecimal(c, VALC);
	bdPrintDecimal("\ncipher text = ", c, "\n");

	bdConvFromDecimal(p, VALP);
	bdPrintDecimal("\np = ", p, "\n");

	get_dp(dp, p, e);
	
	get_q_dq(q, dq, N, p, e);

	get_plain_text_message(m, c, dp, p, dq, q);
	
	BDFREE(m);
	BDFREE(e);
	BDFREE(N);
	BDFREE(c);
	BDFREE(p);
	BDFREE(q);
	BDFREE(dp);
	BDFREE(dq);
	
	return 0;
}

