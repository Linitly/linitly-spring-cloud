import com.linitly.service.provider.util.encrypt.AES;
import com.linitly.service.provider.util.encrypt.EncryptionUtil;
import com.linitly.service.provider.util.encrypt.RSA;
import com.linitly.service.provider.util.encrypt.RSAUtil;
import org.junit.Test;

import java.util.Map;

/**
 * @author: linxiunan
 * @date: 2019/10/31 17:41
 * @descrption
 */

public class EncryptTest {

    /**
     * @description rsa测试
     */
    @Test
    public void testRSA() throws Exception {
        Map<String, String> keyMap = RSA.generateKeyPair();
        System.out.println(keyMap.get("publicKey"));
        System.out.println(keyMap.get("privateKey"));
        System.out.println(keyMap.get("modulus"));
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArj/3wlLk4qnO4cBiqiO9lh309zgR8qzyDS9cAc595oPP+W2AzGuteNo11EAUQ/pdCMfGYmBoFn4UxfIKXF5fMRus7yhMDQDvJDT9t0q1jAbiXwPQHlXqG5WHt8TeNEUIJewUlF/9MGQexMEpzCygafHnk6WQ1b5FhFN57ib3FBX/3cRhdICJqFXZvt17GUSJPCQI6sy6Fb0738YrG4zVq/KvsQWUledFVRMthnuLDUWHJz2Ou2/dtBE/9/qX+jlDhVpo6BxNtnxqQ6KiXcDU4p5BRk6YRKr4eLZo58ja+kcwPR067NUw1Kx3Sr+IQoCtyl5N3cOMZLmCmUq/d6kZOwIDAQAB";
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCuP/fCUuTiqc7hwGKqI72WHfT3OBHyrPINL1wBzn3mg8/5bYDMa6142jXUQBRD+l0Ix8ZiYGgWfhTF8gpcXl8xG6zvKEwNAO8kNP23SrWMBuJfA9AeVeoblYe3xN40RQgl7BSUX/0wZB7EwSnMLKBp8eeTpZDVvkWEU3nuJvcUFf/dxGF0gImoVdm+3XsZRIk8JAjqzLoVvTvfxisbjNWr8q+xBZSV50VVEy2Ge4sNRYcnPY67b920ET/3+pf6OUOFWmjoHE22fGpDoqJdwNTinkFGTphEqvh4tmjnyNr6RzA9HTrs1TDUrHdKv4hCgK3KXk3dw4xkuYKZSr93qRk7AgMBAAECggEAdz6dXaV+r0LHqiQPISHeTwnnJvt/BNSnvLApRa8653JS8/2lA7+4chjad4r2k2KVIjocoBwbfzD/JEs0HewTNrcazHuGN+vou51r93Av90iKx70DQo9G62OUAgW+joSjQ9hgaP2bde8+QyVxZiEwEfCSqq7GBA/24MyDh5+wvFQA/jVEPTOB7z9BYFUkF/KOsM98o+gyukIiedTg2+fB0C9efSyffNTLx4xiFTBPgsTXzFOj3M8DXno53EP6qEL0Z+xfNomv2n2H8XwLVKLGoG2uX5EYQxWVF8AghkkxIKp948PsG5EOza8bpxYM+gyZ8yyxK7SQ/7JsE+HQ1hE18QKBgQD38GPfFDTf2oovL20LWdPyFtMFG1QJKwd2PzFJEZaEsF4rWrERQ6bCTWqibmohMpjcO+eMSLcNZAvWU3f+z1sG/mqpS8EJ3zrumsfcQaXxMASbnD4fChvmXfIbrtagJQyz33HpvHu9MyJiMB3NkfXblG4zB+8LhK2rMeR/ctgrkwKBgQCz6kJIW2BtqlSWU+DnxTOCvj1Xp1PktvepU7WXgSjir8JZQuBhCrqGAe6pvLs8NN72tDjqIpCJaQHvPgR316/gqxNNl9eOLm66bNWnoXMz6V9wCSy/GBsjrX8Z+p8ohy3JttASa6HYPJrFVUFKoPYzagr/yFfuyx+2YBxg0WF0uQKBgFRv4qNDL2qY83x0S0+VTO/iRAU2NF1W8RidFrzKp5cStU9QeILnpi//y3P75Frf71z5bbbBwM1np/xaur/lgBuWo156dQhWpKxDvBLPrKLB9p/ECwfpGm1OyzgQR/mMBJJxY0r1rLJXartDpbKvpaodF5Z7ejZS/g4SVL3ur53PAoGAMUemNimJRSCVoMOC5BkY3eP762lyyj/voFrEb6Y6K7VWt5pg2VPV+WTIGYgPhRM6SzrcKA2lp22y0PQcxzK2AwT4+ZTwvwoq3Dd5YjwP0RVIRejNb95LeL9KBDSoqbI1MX44igMfRpp7Djgm9SkXpseSEbOvmkHbAtpai45uckkCgYEA3DsqQtDptIZHYG+jU5Z8eaXESeUq3Lm9bVICB5DaCuwbcz7uXt+gwXKfuydFwLJojeRCzEGEpizyMOYvQiM2+4pJ73JmPO/D2yhI/ZUuoHB39JaaiErw6XbefD0RhaJb8Zay3KWbxNQWbDno5sLXXB8rRdX6yuzotQe6IYiHZRQ=";
        String content = "士第哦啊手机吊饰趸交时加大司机大数据的";
        String encryptContent = RSAUtil.publicEncrypt(content, publicKey);
        System.out.println("加密后的数据为；" + encryptContent);
        System.out.println("解密后的数据为：" + RSAUtil.privateDecrypt(encryptContent, privateKey));
    }

    /**
     * @description AES加密解密测试:AES/CBC/PKCS5Padding,iv=key,结果输出为base64,128位。由于将key当iv，所以限制key必须为16位，否则iv会报错
     */
    @Test
    public void testAES() {
//        String key = SecureRandomUtil.getRandom(16);
        String key = "06f0MQd42U9wcI11";
        System.out.println("key:" + key);
        String data = "按时交付怕是击破敌军哦怕京东瓶塞第哦啊";
        String encryptContent = AES.encryptToBase64(data, key);
        System.out.println("加密后的数据:" + encryptContent);
        System.out.println("解密后的数据：" + AES.decryptFromBase64(encryptContent, key));
    }

    @Test
    public void testMD5() {
        String salt = "asdkladksa;ldkas;dkasldlko2k3opk";
        System.out.println("加密数据：" + EncryptionUtil.md5("测试一下", salt));
    }

    @Test
    public void testSha256() {
        String content = "asdkladksa;ldkas;dkasldlko2k3opk";
        System.out.println("加密数据：" + EncryptionUtil.sha256(content));
    }

    @Test
    public void testSha1() {
        String content = "asdkladksa;ldkas;dkasldlko2k3opk";
        System.out.println("加密数据：" + EncryptionUtil.sha1(content));
    }
}
