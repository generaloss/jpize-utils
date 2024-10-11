package jpize.util.security;

import jpize.util.res.ExternalResource;
import jpize.util.res.Resource;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class PrivateRSA {

    private final PrivateKey key;
    private final Cipher cipher;

    public PrivateRSA(PrivateKey key) {
        this.key = key;
        this.cipher = getDecryptCipher(key);
    }

    public PrivateRSA(byte[] bytes) {
        this(generatePrivateKey(bytes));
    }

    public PrivateRSA(Resource res) {
        this(res.readBytes());
    }

    public PrivateKey getKey() {
        return key;
    }


    public byte[] decrypt(byte[] data) {
        try{
            return cipher.doFinal(data);
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


    private static PrivateKey generatePrivateKey(byte[] bytes) {
        try{
            return KeyFactory
                .getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(bytes));
        }catch(InvalidKeySpecException e){
            throw new RuntimeException(e);
        }catch(NoSuchAlgorithmException ignored){
            return null;
        }
    }

    private static Cipher getDecryptCipher(PrivateKey privateKey) {
        try{
            final Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher;
        }catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e){
            throw new RuntimeException(e);
        }
    }

}
