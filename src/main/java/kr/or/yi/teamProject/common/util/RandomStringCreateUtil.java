package kr.or.yi.teamProject.common.util;

import lombok.Builder;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 랜덤 문자 생성해주는 유틸입니다.
 *
 * 문자열 길이나 charSet 을 바꾸려면 setter 혹은 builder 를 이용하시기 바랍니다.
 *
 * @author taedi
 */


public class RandomStringCreateUtil {


    public RandomStringCreateUtil() {
    }

    private int size = 20;
    private char[] charSet = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public void setSize(int size) {
        this.size = size;
    }

    public void setCharSet(char[] charSet) {
        this.charSet = charSet;
    }

    public String getSecureRand() {

        StringBuffer sb = new StringBuffer();

        SecureRandom rnd = null;
        try {
            rnd = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        int idx = 0;
        int len = charSet.length;

        for (int i = 0; i < size; i++) {
            idx = rnd.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom 을 사용한다.
            sb.append(charSet[idx]);
        }

        return sb.toString();

    }
}
