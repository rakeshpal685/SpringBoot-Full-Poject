package com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.service;

import com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.dto.JWTData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.*;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;

/*Here we are getting the data in JWTData DTO and use the values to create the JWT token for us*/
@Service
public class JWTService {
    private PublicKey publicKey;
    private PrivateKey privateKey;

/*Here we are generating the key pair to sign the token when using RSA singing technique
@PostConstruct//When the bean of this service class is created, this method will get called, it happens only once
Therefore, the @PostConstruct method is called only once per bean instance, just after the initialization of bean properties.*/
    public void init() throws NoSuchAlgorithmException {

/* This is a java inbuilt class to generate the keypair,we have to pass which algorithm we need to follow and
       what should be the keysize of it
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2018);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        privateKey=keyPair.getPrivate();
        publicKey=keyPair.getPublic();*/

//But we have the io.jsonwebtoken dependency that will help us to generate the keypair, internally it uses the above thing only
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
        publicKey= keyPair.getPublic();
        privateKey= keyPair.getPrivate();
    }
    public String generateToken(JWTData jwtData){
    String token = Jwts.builder()
            .setIssuer(jwtData.getIssuer())//Issuer name
            .setSubject(jwtData.getSubject())//coming from above
            .setAudience(jwtData.getAudience())//To whom this token belongs
            .setExpiration(Date.from(ZonedDateTime.now().plusHours(1).toInstant()))//expires in 1 hour
//            .setIssuedAt(new Date(System.currentTimeMillis()))
//            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .setId(jwtData.getUserId())
            .claim("email",jwtData.getEmail())//this is my custom claim
            .claim("roles",jwtData.getRolesString())//Here I am getting all the roles for the user above and adding it
//          .addClaims(claims)//Here I can specify multiple claims together, that we declared above
            .signWith(privateKey)//the signed private key is formed above
            .compact();

    printPublicKey(publicKey);
    System.out.println(token);
    return token;
}

private void printPublicKey(PublicKey pubKey){

        String publicKeyEncodedVersion= Base64.getEncoder().encodeToString(pubKey.getEncoded());
    String publicKey = "\n-----BEGIN PUBLIC KEY-----\n" +
                        publicKeyEncodedVersion +
                        "\n-----END PUBLIC KEY-----\n";
    System.out.println(publicKey);
}

public Claims validateToken(String token){
        try{
           Claims claim= Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
           return claim;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
}
}
