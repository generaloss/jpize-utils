package jpize.util.security;

import jpize.util.res.ExternalResource;
import jpize.util.res.Resource;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESKey {

    private final SecretKey key;
    private final Cipher decryptCipher, encryptCipher;

    public AESKey(SecretKey key) {
        this.key = key;
        this.decryptCipher = getDecryptCipher(key);
        this.encryptCipher = getEncryptCipher(key);
    }

    public AESKey(byte[] bytes) {
        this(new SecretKeySpec(bytes, "AES"));
    }

    public AESKey(Resource res) {
        this(res.readBytes());
    }

    public AESKey(int size) {
        this(generateSecretKey(size));
    }

    public SecretKey getKey() {
        return key;
    }


    public byte[] encrypt(byte[] bytes) {
        try{
            return encryptCipher.doFinal(bytes);
        }catch(IllegalBlockSizeException | BadPaddingException e){
            throw new RuntimeException(e);
        }
    }

    public byte[] decrypt(byte[] bytes) {
        try{
            return decryptCipher.doFinal(bytes);
        }catch(IllegalBlockSizeException | BadPaddingException e){
            throw new RuntimeException(e);
        }
    }


    public boolean save(ExternalResource res) {
        if(res.mkAll()){
            res.writeBytes(key.getEncoded());
            return true;
        }
        return false;
    }


    private static SecretKey generateSecretKey(int size) {
        try{
            final KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(size);
            return generator.generateKey();
        }catch(NoSuchAlgorithmException ignored){
            return null;
        }
    }

    private static Cipher getDecryptCipher(SecretKey key) {
        try{
            final Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher;

        }catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e){
            throw new RuntimeException(e);
        }
    }

    private static Cipher getEncryptCipher(SecretKey key) {
        try{
            final Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher;

        }catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e){
            throw new RuntimeException(e);
        }
    }

}
