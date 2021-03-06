package ocsp;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.io.DigestOutputStream;
import org.bouncycastle.operator.DigestCalculator;

import java.io.OutputStream;

public class Digester implements DigestCalculator {

    private final DigestOutputStream dos;

    private final AlgorithmIdentifier algId;

    private Digester(Digest digest, AlgorithmIdentifier algId) {
        this.dos = new DigestOutputStream(digest);
        this.algId = algId;
    }

    public static DigestCalculator sha1() {
        Digest digest = new SHA1Digest();
        AlgorithmIdentifier algId = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
        return new Digester(digest, algId);
    }

    public static DigestCalculator sha256() {
        Digest digest = new SHA256Digest();
        ASN1ObjectIdentifier oid = new ASN1ObjectIdentifier("2.16.840.1.101.3.4.2.1").intern();
        AlgorithmIdentifier algId = new AlgorithmIdentifier(oid);
        return new Digester(digest, algId);
    }

    @Override
    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return algId;
    }

    @Override
    public OutputStream getOutputStream() {
        return dos;
    }

    @Override
    public byte[] getDigest() {
        return dos.getDigest();
    }
}
