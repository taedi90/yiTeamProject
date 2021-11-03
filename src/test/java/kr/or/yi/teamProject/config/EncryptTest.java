package kr.or.yi.teamProject.config;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

import java.util.Scanner;

@Slf4j
public class EncryptTest {

    public static StringEncryptor getEncryptor(){

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(System.getenv("TAEDI_ENC_KEY"));
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("10");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        return encryptor;
    }

    public static void main(String[] args) {

        log.info("암호화");
        StringEncryptor encryptor = getEncryptor();


        Scanner sc = new Scanner(System.in);

        String str = null;

        while (true) {
            System.out.print("암호화 할 키를 입력하세요 -> ");
            str = sc.next();
            if(str.equals("exit")){
                break;
            }
            System.out.println(encryptor.encrypt(str));
        }

        sc.close();
    }
}