package LoginProccess;

import lombok.*;

import java.util.Arrays;
import java.util.List;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
        private List<String> accounts = Arrays.asList("5559 0000 0000 0001", "5559 0000 0000 0002");

        public static AuthInfo getAuthInfo() {
            return new AuthInfo("vasya", "qwerty123");
        }
    }

    @Value
    public static class VerificationCode {
        private String code;

        public static VerificationCode getVerificationCodeFore (AuthInfo authInfo) {
            return new VerificationCode("12345");
        }
    }
}