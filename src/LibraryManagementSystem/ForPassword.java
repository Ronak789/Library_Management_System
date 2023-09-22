package LibraryManagementSystem;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;
import java.security.MessageDigest;


public class ForPassword {
    final private static int ITERATIONS = 10000; // Number of iterations (should match the original registration)
    final private static int KEYLENGTH = 256; // Key length in bits
    
    public static String[] encryptPassword(char[] plainPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        PBEKeySpec spec = new PBEKeySpec(plainPassword, salt, ITERATIONS, KEYLENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hashedPassword = factory.generateSecret(spec).getEncoded();

        // Encode the salt and hashed password to Base64
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String encodedHashedPassword = Base64.getEncoder().encodeToString(hashedPassword);
        
        String[] data = {encodedHashedPassword, encodedSalt};
        return data;
    }
    
    public static boolean checkPassword(String hashedPasswordStr, String saltStr, char[] enteredPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = Base64.getDecoder().decode(saltStr);
        byte[] hashedPassword = Base64.getDecoder().decode(hashedPasswordStr);
        
        // Key length in bits (should match the original registration)
        PBEKeySpec spec = new PBEKeySpec(enteredPassword, salt, ITERATIONS, KEYLENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] enteredPasswordHash = factory.generateSecret(spec).getEncoded();

        // Compare the entered password hash with the stored hashed password
        if (MessageDigest.isEqual(enteredPasswordHash, hashedPassword)) {
            return true;
        } else {
            return false;
        }
    }
}
