all:
	gcc smartcard_rsa.c bigd.c bigd.h bigdigits.c bigdigits.h -Wall -o smartcardRsa
	gcc rsa.c bigd.c bigd.h bigdigits.c bigdigits.h -Wall -o rsa
	gcc brute_force_rsa.c bigd.c bigd.h bigdigits.c bigdigits.h -Wall -o bruteForceRsa
clean:
	rm -f smartcardRsa rsa bruteForceRsa
