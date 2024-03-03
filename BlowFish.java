import java.io.*;
import java.io.FileInputStream;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64; // Correct import for Base64

public class BlowFish {
    public static void main(String[] args) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
        SecretKey secretKey = keyGenerator.generateKey(); // Generate secret key
        Cipher cipherOut = Cipher.getInstance("Blowfish/CFB/NoPadding"); // Changed mode from CFD to CFB
        cipherOut.init(Cipher.ENCRYPT_MODE, secretKey);
        Base64.Encoder encoder = Base64.getEncoder(); // Corrected import and usage
        byte iv[] = cipherOut.getIV();
        if (iv != null) {
            System.out.println("Initialization Vector of the Cipher : " + encoder.encodeToString(iv)); // Corrected typo in print statement and usage
            FileInputStream fin = new FileInputStream("inputFile.txt");
            FileOutputStream fout = new FileOutputStream("outputFile.txt");
            CipherOutputStream cout = new CipherOutputStream(fout, cipherOut);
            int input = 0;
            while ((input = fin.read()) != -1) {
                cout.write(input);
            }
            fin.close(); // Moved these closing statements outside of the loop
            cout.close(); // Moved these closing statements outside of the loop
        }
    }
}

