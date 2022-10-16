import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
public class CustomHashSolver {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        final byte[] HASH_TO_CRACK = "254a841f790e8efba6e23c190c36a750ce33ba44acc711a845d7aff12dabec507675763fa53005ee5616eeb5b0b72be92176ee0d19f0d098fef8b2fe45a8d0ab".getBytes("UTF-8");
        System.out.println("Getting key space...");
        List<String> strings = getPlainTexts("./allLower5");
        System.out.println("Starting Cracking...");
        for(String string : strings) {
            byte[] hash = getHash(string);
            if(MessageDigest.isEqual(HASH_TO_CRACK, hash)) {
                System.out.println("CRACKED: " + string);
            }
        }

    }
    // runs the plain text through md5 hash 100 times
    public static byte[] getMD5Hash(byte[] plain) throws NoSuchAlgorithmException{
        MessageDigest mdMD5 = MessageDigest.getInstance("MD5");
        mdMD5.update(plain);
        for(int i = 0; i < 99; i++) {
            mdMD5.update(mdMD5.digest());
        }
        return mdMD5.digest();
    }

    public static byte[] getSHA256Hash(byte[] hash1) throws NoSuchAlgorithmException{
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        sha256.update(hash1);
        for(int i = 0; i < 99; i++) {   
            sha256.update(sha256.digest());
        }
        return sha256.digest();
    }

    public static byte[] getSHA512Hash(byte[] hash2) throws NoSuchAlgorithmException {
        MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
        sha512.update(hash2);
        for(int i = 0; i < 99; i++) {
            sha512.update(sha512.digest());
        }
        return sha512.digest();
    }

    public static byte[] getHash(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] s1 = getMD5Hash(s.getBytes("UTF-8"));
        byte[] s2 = getSHA256Hash(s1);
        byte[] s3 = getSHA512Hash(s2);
        return s3;
    }

    public static List<String> getPlainTexts(String pathFile) {
        List<String> plainTexts = new ArrayList<String>();
        try{
            final BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("./allLower5.txt"), "UTF-8"));

            String currLine;
            while((currLine = reader.readLine()) != null) {
                plainTexts.add(currLine);
            }
            reader.close();
        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return plainTexts;
    }
}
