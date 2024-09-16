package jpize.util.security;

import jpize.util.res.ExternalResource;
import jpize.util.res.Resource;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class PublicRsa {

    private final PublicKey key;
    private final Cipher cipher;

    public PublicRsa(PublicKey key) {
        this.key = key;
        this.cipher = getEncryptCipher(key);
    }

    public PublicRsa(byte[] bytes) {
        this(generatePublicKey(bytes));
    }

    public PublicRsa(Resource res) {
        this(res.readBytes());
    }

    public PublicKey getKey() {
        return key;
    }


    public byte[] encrypt(byte[] data) {
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


    private static PublicKey generatePublicKey(byte[] bytes) {
        try{
            return KeyFactory
                .getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(bytes));
        }catch(InvalidKeySpecException e){
            throw new RuntimeException(e);
        }catch(NoSuchAlgorithmException ignored){
            return null;
        }
    }

    private static Cipher getEncryptCipher(PublicKey publicKey) {
        try{
            final Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher;
        }catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e){
            throw new RuntimeException(e);
        }
    }

}
