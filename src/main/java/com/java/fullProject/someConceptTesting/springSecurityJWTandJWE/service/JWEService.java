package com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.service;

import com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.dto.JWEData;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.*;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

/*Here we are getting the data in JWEData DTO and use the values to create the JWT token for us,
* We use JWE because in JWT our payload is not encrypted, anyone can see our payload, but using JWE, we can
* prevent that*/
@Service
public class JWEService {

    SecretKey key=null; //If we want we can hardcode the key here, but we are generating a random key below

/*Here we are generating the secret key everytime the server starts and the bean is created.*/
    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        KeyGenerator keygen= KeyGenerator.getInstance("AES");//we are defining a KeyGenerator which will help us to generate a key using AES algorithm.
        keygen.init(128);
        key = keygen.generateKey();//using Keygen we are generating a random encrypted key.
        System.out.println("My KEY is-->>" + key.getEncoded());
}
        public String generateToken(JWEData jweData) {
            try {
                JWTClaimsSet payload = new JWTClaimsSet.Builder()
                .expirationTime(Date.from(ZonedDateTime.now().plusMinutes(30).toInstant()))
                .issuer(jweData.getIssuer())
                .audience(jweData.getAudience())
                .subject(jweData.getSubject())
                .claim("roles", jweData.getRolesString())
                .build();//This is my payload

                JWEHeader jweHeader = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128GCM);//Encrypting the header

                EncryptedJWT jwe = new EncryptedJWT(jweHeader, payload);
                DirectEncrypter encrypter =null;

                encrypter = new DirectEncrypter(key);//Here we are creating the encryptor using the key generated above
                jwe.encrypt(encrypter);//Here we are encrypting the jwe using the encrypter we have created just above
                return jwe.serialize();//After the encryption is done I am returning the JWE token in serialized form because only JWE is not a string, and we are returning a string
            } catch (JOSEException e) {
            }
            return null;
    }
    
    //Writing our logic to validate the token
    //public Optional<JWTClaimsSet> validateToken(String token){//Use this
    public JWTClaimsSet validateToken(String token){//Use this
        try {
            EncryptedJWT jwe = EncryptedJWT.parse(token);//Here we are parsing the token that we got as an argument
            DirectDecrypter directDecrypter = new DirectDecrypter(key);//Here we are creating the decrypter using the key generated above
            jwe.decrypt(directDecrypter);//Here we are decrypting the jwe using the decrypter we have created just above
            JWTClaimsSet jwtClaimsSet = jwe.getJWTClaimsSet();//Here we are getting the claimSet.
           // return Optional.of(jwtClaimsSet);//use this
            return jwtClaimsSet;
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
        }
       // return Optional.empty();//return this
        return null;
    }

}
