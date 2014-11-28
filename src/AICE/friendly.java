package AICE;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class friendly {


        public static void main(String[] args){
                Scanner sc = new Scanner(System.in);
                PrintWriter pw = new PrintWriter(System.out);
                String input;
                while(sc.hasNextLine()){
                        input = sc.nextLine();
                        String time = input.split(" ")[0];
                        String[] times = time.split(":");
                        int key[] = { (Integer.parseInt(times[0])*9), ((Integer.parseInt(times[1])*3+Integer.parseInt(times[2])))};
                        pw.print(time);
                        String encrypted = xor_encrypt(input.substring(input.indexOf(" ")+1,input.length()),key);
                        for(char a:encrypted.toCharArray()){
                                pw.printf(" 0x%02X",a&0xFF);
                        }pw.print(" ");
                        pw.println();
                        pw.flush();
                }
        }

        public static String xor_encrypt(String message, int[] key){
            try {
              if (message==null || key==null ) return null;

              char[] mesg=message.toCharArray();
             // BASE64Encoder encoder = new BASE64Encoder();

              int ml=mesg.length;
              int kl=key.length;
              char[] newmsg=new char[ml];

              for (int i=0; i<ml; i++){
                newmsg[i]=(char)(mesg[i]^key[i%2]);
              }
              mesg=null;
              key=null;
              String temp = new String(newmsg);
              return temp;
            }
            catch ( Exception e ) {
              return null;
            }
          }

        
}





