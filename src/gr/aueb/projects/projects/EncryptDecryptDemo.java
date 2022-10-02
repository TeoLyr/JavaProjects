package gr.aueb.projects.projects;

import java.util.Arrays;

public class EncryptDecryptDemo {
    public static void main(String[] args) {
        char[] message = {'A', '1', 'i', 'c', 'e', '#'};

        int[] cipher = encrypt(message);
        for (int i :cipher){
            System.out.print(i + " ");
        }

        System.out.println();

        char[] decipher = decrypt(cipher);

        assert decipher != null;

        for (char i: decipher){
            System.out.print(i + " ");
        }
    }

    public static int[] encrypt(char[] message){
        if (message ==  null) return null;

        final int KEY = 300, CIPHER_END = -1;
        int[] cipher = new int[100];
        int pivot = 0;
        int ch = message[pivot];
        if (ch == '#'){
            return null;
        }
        int charCipher = ch;
        cipher[pivot++] = charCipher;
        int previous = charCipher;

        while ((ch =  message[pivot]) != '#'){
            charCipher = (ch +previous) % KEY;
            cipher[pivot++] = charCipher;
            previous = charCipher;
        }

        cipher[pivot++] = CIPHER_END;
        return Arrays.copyOfRange(cipher, 0, pivot);
    }

    public static char[] decrypt(int[] cipher){
        char[] decipher = new char[100];
        int pivot = 0;
        char charDecipher;
        final int KEY = 300;

        int current = cipher[pivot];
        if (current == -1){
            return null;
        }

        decipher[pivot++] = (char) current;
        int previousCode = current;

        while ((current = cipher[pivot]) != -1){
            charDecipher = (char) (KEY + current - previousCode);
            while (charDecipher > KEY) charDecipher -= KEY;
            decipher[pivot++] = charDecipher;
            previousCode = current;
        }

        return Arrays.copyOfRange(decipher, 0, pivot);
    }
}
