package jpize.util.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyRsa {

    private final PublicRsa publicKey;
    private final PrivateRsa privateKey;

    public KeyRsa(int size) {
        final KeyPair pair = generateKeyPair(size);
        if(pair == null)
            throw new RuntimeException("Unable to generate RSA key pair.");

        this.publicKey = new PublicRsa(pair.getPublic());
        this.privateKey = new PrivateRsa(pair.getPrivate());
    }

    public PublicRsa getPublic() {
        return publicKey;
    }

    public PrivateRsa getPrivate() {
        return privateKey;
    }


    public byte[] encrypt(byte[] bytes) {
        return publicKey.encrypt(bytes);
    }

    public byte[] decrypt(byte[] bytes) {
        return privateKey.decrypt(bytes);
    }


    private static KeyPair generateKeyPair(int size) {
        try{
            final KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance("RSA");
            pairGenerator.initialize(size);
            return pairGenerator.generateKeyPair();
        }catch(NoSuchAlgorithmException ignored){
            return null;
        }
    }

}
