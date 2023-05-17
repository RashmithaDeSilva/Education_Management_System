package com.developersstack.educationmanagementsystem.util.tools;

import java.util.Random;

public class VerificationCodeGenerator {

    private final String NUMBERS = "0123456789";

    public int getCode(int codeLength) {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<codeLength;i++) {
            char selectedNumber = NUMBERS.charAt(new Random().nextInt(10));
            if (i==0 && selectedNumber=='0') {
                i--;
                continue;
            }
            sb.append(selectedNumber); // 0-9
        }
        return Integer.parseInt(sb.toString());
    }
}
