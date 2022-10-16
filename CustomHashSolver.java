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
    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        final String HASH_TO_CRACK = "254a841f790e8efba6e23c190c36a750ce33ba44acc711a845d7aff12dabec507675763fa53005ee5616eeb5b0b72be92176ee0d19f0d098fef8b2fe45a8d0ab";
        System.out.println("Getting key space...");
        List<String> strings = getPlainTexts("./allLower5");
        System.out.println("Starting Cracking...");
        // System.out.println(toHexString(getHash("csc474")));
        for(String string : strings) { 
            String hash = toHexString(getHash(string));
            // System.out.println(string);
            // System.out.println(hash);
            if(HASH_TO_CRACK.equals(hash)) {
                System.out.println("--=---==>CRACKED: " + string);
                return;
            }
        }

    }
    // runs the plain text through md5 hash 100 times
    public static byte[] getMD5Hash(byte[] plain) throws NoSuchAlgorithmException{
        MessageDigest mdMD5 = MessageDigest.getInstance("MD5");
        // mdMD5.update(plain);
        String out = null;
        for(int i = 0; i < 100; i++) {
            if(i == 0) {
                mdMD5.update(plain);
                out = toHexString(mdMD5.digest());
            } else {
                mdMD5.update(out.getBytes());
                if(i != 99) {
                    out = toHexString(mdMD5.digest());
                }
            }
        }
        return mdMD5.digest();
    }

    public static byte[] getSHA256Hash(byte[] hash1) throws NoSuchAlgorithmException{
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        String out = toHexString(hash1);
        for(int i = 0; i < 100; i++) {
            sha256.update(out.getBytes());
            if(i != 99) {
                out = toHexString(sha256.digest());
            }
        }
        return sha256.digest();
    }

    public static byte[] getSHA512Hash(byte[] hash2) throws NoSuchAlgorithmException {
        MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
        String out = toHexString(hash2);
        for(int i = 0; i < 100; i++) {
            sha512.update(out.getBytes());
            if(i != 99) {
                out = toHexString(sha512.digest());
            }
        }
        return sha512.digest();
    }

    public static byte[] getHash(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] s1 = getMD5Hash(s.getBytes());
        byte[] s2 = getSHA256Hash(s1);
        byte[] s3 = getSHA512Hash(s2);
        return s3;
    }
    public static String toHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
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
