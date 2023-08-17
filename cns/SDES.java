import java.util.*;
class SDES{
    public static String initialPermutation10(char[] txt){
        //Rearrange this according to your wish
        int[] arr={2,4,1,6,3,9,0,8,7,5};
        String text = "";
        for(int i=0;i<txt.length;i++){
            text += txt[arr[i]];
        }
        return text;
    }
    public static String ePermutation10(char[] txt){
        //Rearrange this according to your wish
        int[] arr = {5,2,6,3,7,4,9,8};
        String text = "";
        for(int i=0;i<arr.length;i++){
            text += txt[arr[i]];
        }
        return text;
    }
    public static String initialPermutation8(char[] txt){
        //Rearrange this according to your wish
        int[] arr={1,5,2,0,3,7,4,6};
        String text = "";
        for(int i=0;i<arr.length;i++){
            text += txt[arr[i]];
        }
        return text;
    }
    public static String inverseInitialPermutation8(char[] txt){
        //Rearrange this according to your wish
        int[] arr={3,0,2,4,6,1,7,5};
        String text = "";
        for(int i=0;i<arr.length;i++){
            text += txt[arr[i]];
        }
        return text;
    }
    public static String ePermutation8(char[] txt){
        //Rearrange this according to your wish
        int[] arr = {7,4,5,6,5,6,7,4};
        String text = "";
        for(int i=0;i<arr.length;i++){
            text += txt[arr[i]];
        }
        return text;
    }
    public static String ePermutation4(char[] txt){
        //Rearrange this according to your wish
        int[] arr = {1,3,2,0};
        String text = "";
        for(int i=0;i<arr.length;i++){
            text += txt[arr[i]];
        }
        return text;
    }
    public static String xor(char[] txt, char[] key){
        String text="";
        for(int i=0;i<txt.length;i++){
            if(txt[i] == key[i]){
                text+='0';
            }else{
                text+='1';
            }
        }
        return text;
    } 
    public static String shift(char[] txt, int x) {
        while (x > 0) {
            x--;
            String text = "";
            for (int i = 1; i < txt.length / 2; i++) {
                text += txt[i];
            }
            text += txt[0];
            for (int i = (txt.length / 2)+1; i < txt.length; i++) {
                text += txt[i];
            }
            text += txt[txt.length / 2];
            txt = text.toCharArray();
        }
        return new String(txt);
    }
    public static int dec(char x, char y){
        return Integer.parseInt(""+x)*2 + Integer.parseInt(""+y);
    }
    public static String bin(int x){
        String[] val = {"00", "01", "10", "11"};
        return val[x];
    }
    public static String combine(char[] txt) {
        int[][] s0 = {
            { 1, 0, 3, 2 },
            { 3, 2, 1, 0 },
            { 0, 2, 1, 3 },
            { 3, 1, 3, 2 }
        };
        int[][] s1 = {
            { 0, 1, 2, 3 },
            { 2, 0, 1, 3 },
            { 3, 0, 1, 0 },
            { 2, 1, 0, 3 }
        };
        int p = dec(txt[0], txt[3]);
        int q = dec(txt[1], txt[2]);
        String text = bin(s0[p][q]);
        int r = dec(txt[4], txt[7]);
        int s = dec(txt[5], txt[6]);
        text += bin(s1[r][s]);
        return text;
    }
    
    public static String encrypt(char[] text, char[] key){
        String key1 = ePermutation10(shift(initialPermutation10(key).toCharArray(),1).toCharArray());
        String key2 = ePermutation10(shift(initialPermutation10(key).toCharArray(),3).toCharArray());

        String ip = initialPermutation8(text); 
        String ep = ePermutation8(ip.toCharArray());
        String aftxor = xor(ep.toCharArray(), key1.toCharArray());
        String p4 = combine(aftxor.toCharArray());
        String ip4 = ePermutation4(p4.toCharArray());
        String aftxor1 = xor((ip.substring(0, 4)).toCharArray(),ip4.toCharArray());
        String comrhip =  (ip.substring(4, 8)) + aftxor1;

        String ep1 = ePermutation8(comrhip.toCharArray());
        String aftxor2 = xor(ep1.toCharArray(), key2.toCharArray());
        String p4_1 = combine(aftxor2.toCharArray());
        String ip4_1 = ePermutation4(p4_1.toCharArray());
        String aftxor3 = xor((comrhip.substring(0, 4)).toCharArray(),ip4_1.toCharArray());
        String comrhip1 = aftxor3 + (comrhip.substring(4, 8));

        return inverseInitialPermutation8(comrhip1.toCharArray());
    }

    public static String decrypt(char[] text, char[] key){
        String key1 = ePermutation10(shift(initialPermutation10(key).toCharArray(),1).toCharArray());
        String key2 = ePermutation10(shift(initialPermutation10(key).toCharArray(),3).toCharArray());

        String ip = initialPermutation8(text);
        String ep = ePermutation8(ip.toCharArray());
        String aftxor = xor(ep.toCharArray(), key2.toCharArray());
        String p4 = combine(aftxor.toCharArray());
        String ep0 = ePermutation4(p4.toCharArray());
        String aftxor1 = xor((ip.substring(0, 4)).toCharArray(),ep0.toCharArray());
        String comrhip = (ip.substring(4, 8)) + aftxor1 ;

        
        String ep1 = ePermutation8(comrhip.toCharArray());
        String aftxor2 = xor(ep1.toCharArray(), key1.toCharArray());
        String p4_1 = combine(aftxor2.toCharArray());
        String ep2 = ePermutation4(p4_1.toCharArray());
        String aftxor3 = xor((comrhip.substring(0, 4)).toCharArray(),ep2.toCharArray());
        String comrhip1 = aftxor3 + (comrhip.substring(4, 8));
        
        return inverseInitialPermutation8(comrhip1.toCharArray());
    }

    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a choice [1] - ENCRYPT / [2] - DECRYPT: ");
        int ch = s.nextInt();
        s.nextLine();
        System.out.print("Enter a text : ");
        String text = s.nextLine();
        System.out.print("Enter a key : ");
        String key = s.nextLine();
        if (ch == 1) {
            System.out.println("Cipher Text : " + encrypt(text.toCharArray(), key.toCharArray()));
        }else {
            System.out.println("Plain Text : " + decrypt(text.toCharArray(), key.toCharArray()));
        }
        s.close();
    }
}


// = (1+(2 * pi))/e;
//         double decrypt = (Math.pow(cipher,d)%n);
//         System.out.println("Plain Text : "+decrypt);