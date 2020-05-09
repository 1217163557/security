package com.example.hibernateone.util;

public class TokenProvider {

//    private final String secretKey;
//    private final int tokenValidity;
//
//    public TokenProvider(String secretKey, int tokenValidity) {
//        this.secretKey = secretKey;
//        this.tokenValidity = tokenValidity;
//    }
//    // 生成token
//    public Token createToken(UserDetails userDetails) {
//        long expires = System.currentTimeMillis() + 1000L * tokenValidity;
//        String token =  computeSignature(userDetails, expires);
//        return new Token(token, expires);
//    }
//    // 验证token
//    public boolean validateToken(String authToken, UserDetails userDetails) {
//        check token
//        return true or false;
//    }
//    // 从token中识别用户
//    public String getUserNameFromToken(String authToken) {
//        // ……
//        return login;
//    }
//    public String computeSignature(UserDetails userDetails, long expires) {
//        // 一些特有的信息组装 ,并结合某种加密活摘要算法
//        return 例如 something+"|"+something2+MD5(s);
//    }
}
