package com.insitejyl.kr;

import org.apache.tomcat.util.codec.binary.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Calendar;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

public class jwt {

    final String securityKey = "jaeyoonlee";
    final String customKey = "5f7cc1338860b4dc667ce5df77d3c19dedc9f9519eb167197cffe328ba944605";
    final int expireTime = 30;
    final String msg = "This video is not permitted to you.";
    final boolean playing = true;

    public String jwt_encode(String payload, String secretKey) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
        String header = "{\"typ\": \"JWT\", \"alg\": \"HS256\"}";
        Charset charset = Charset.forName("UTF-8");
        String h = Base64.encodeBase64URLSafeString(header.getBytes(charset));
        String p = Base64.encodeBase64URLSafeString(payload.getBytes(charset));
        String content = String.format("%s.%s", h, p);
        final Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secretKey.getBytes(charset), "HmacSHA256"));
        byte[] signatureBytes = mac.doFinal(content.getBytes(charset));
        String signature = Base64.encodeBase64URLSafeString(signatureBytes);
        return String.format("%s.%s", content, signature);
    }


}
