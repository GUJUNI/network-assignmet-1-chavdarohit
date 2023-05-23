import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class EncryptionProgram {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a message: ");
        String message = scanner.nextLine();
        
        System.out.print("Enter a key (shift value): ");
        int key = scanner.nextInt();
        
        String encryptedMessage = encrypt(message, key);
        System.out.println("Encrypted message: " + encryptedMessage);
        
        String decryptedMessage = decrypt(encryptedMessage, key);
        System.out.println("Decrypted message: " + decryptedMessage);
        
        scanner.close();
    }
    
    public static String encrypt(String message, int key) {
        message = message.toLowerCase();
        StringBuilder encrypted = new StringBuilder();
        Map<Character, Character> charMap = generateCharMap(key);
        
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char encryptedChar = charMap.getOrDefault(c, c);
                encrypted.append(encryptedChar);
            } else {
                encrypted.append(c);
            }
        }
        
        return encrypted.toString();
    }
    
    public static String decrypt(String encryptedMessage, int key) {
        StringBuilder decrypted = new StringBuilder();
        Map<Character, Character> charMap = generateCharMap(key);
        
        for (char c : encryptedMessage.toCharArray()) {
            if (Character.isLetter(c)) {
                char decryptedChar = charMap.entrySet().stream()
                        .filter(entry -> entry.getValue() == c)
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(c);
                decrypted.append(decryptedChar);
            } else {
                decrypted.append(c);
            }
        }
        
        return decrypted.toString();
    }
    
    private static Map<Character, Character> generateCharMap(int key) {
        Map<Character, Character> charMap = new HashMap<>();
        
        for (int i = 0; i < ALPHABET.length(); i++) {
            char originalChar = ALPHABET.charAt(i);
            int shiftedIndex = (i + key) % ALPHABET.length();
            char shiftedChar = ALPHABET.charAt(shiftedIndex);
            charMap.put(originalChar, shiftedChar);
        }
        
        return charMap;
    }
}
