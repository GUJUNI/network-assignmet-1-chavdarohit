import java.io.IOException;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class FileMessageDigest {
    public static void main(String[] args) {
        String filePath = "F:\\os 2\\p1.txt";  // Replace with the actual file path

        try {
            // Create an instance of the SHA-256 digest algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Create a FileInputStream for the file
            FileInputStream fileInputStream = new FileInputStream(filePath);
            
            // Wrap the FileInputStream with a DigestInputStream
            DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, digest);

            // Read the file contents and update the digest
            byte[] buffer = new byte[8192];
            while (digestInputStream.read(buffer) != -1) {
                // Reading the file automatically updates the digest
            }

            // Get the computed message digest
            byte[] fileDigest = digest.digest();

            // Convert the byte array to a hexadecimal string representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : fileDigest) {
                hexString.append(String.format("%02x", b));
            }

            // Print the message digest
            System.out.println("Message Digest (SHA-256): " + hexString.toString());

            // Close the streams
            digestInputStream.close();
            fileInputStream.close();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
