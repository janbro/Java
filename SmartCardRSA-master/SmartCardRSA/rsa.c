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
 * Known values of N, e and cipher text. Given in the problem statement.
 */
#define VALN "75489763393055314128289985760068186621492280562287920094260074153209517236970341572518559797153344946557614545420102689128360149448826277159732843552885090712289384951797592094776916869951359415795387573058732394347634041226477604151022089442263032171551224119059291246759481118626066831025730728959763973939"

#define VALE "46933839936513203806814534785430440399121060041961563534238921289223215886833155748923329942768184793127030357335234488752951998951952300752961654791044921714986192071286036662233822586116573773834895955581767885885461402578281187712069245154703440996167817002215606658260574029353837097272922247982958120199"

#define VALC "17303115588385783231855542914594436033706210611592142994148195847161380369694148898294321316619889161465646741342622951747792020391919396377166600390042691916460332038663202841616005426744660399537915742224300757610350068952082197457369985659556824400134147883343181884291377763582387957067404145280221333702"

/* 
 * Value of d found using SAGE maths software
 */
#define VALD "29235388203225151855982606543162114421159514708452342137796951594760129369360385688163677618785920218861333171008867258831702704727994505487617056692109655236000618879853697057856377631633282283619642504529182679717421031071673698983860124599297173183006640601250924067577646694560764290193982315564505517239"

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
	printf("\nMessage in plain text : %s\n\n", pm);

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
 * This function generates value of plain text message in decimal using normal RSA
 * m = c^d mod N
 */
void get_plain_text_message(BIGD m, BIGD c, BIGD d, BIGD N)
{
	bdModExp(m, c, d, N);
        BDPRINTDECIMAL("plain text message in decimal format = ", m, "\n");	
	get_plain_text_message_in_string(m);
	
}

int main(void)
{
	BIGD m, e, N, c, d;
	
	BDMALLOC(m);
	BDMALLOC(e);
	BDMALLOC(d);
	BDMALLOC(c);
	BDMALLOC(N);
	
	/* Read in modulus values for three 512-bit public keys */

	bdConvFromDecimal(N, VALN);
	bdPrintDecimal("\nN = ", N, "\n");

	bdConvFromDecimal(e, VALE);	
	bdPrintDecimal("\ne = ", e, "\n");
        
	bdConvFromDecimal(c, VALC);
	bdPrintDecimal("\ncipher text = ", c, "\n");

	bdConvFromDecimal(d, VALD);
	bdPrintDecimal("\nd = ", d, "\n");

	get_plain_text_message(m, c, d, N);
	
	BDFREE(m);
	BDFREE(e);
	BDFREE(N);
	BDFREE(c);
	BDFREE(d);
	
	return 0;
}

