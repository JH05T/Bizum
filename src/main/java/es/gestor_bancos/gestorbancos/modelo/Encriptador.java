package es.gestor_bancos.gestorbancos.modelo;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptador {

    // Devuelve encriptado el String que se le pasa por parámetro
    public String encriptar(String string){

        double d = 1;

        byte[] b = string.getBytes();

        char[] c = new char[b.length];

        for (int i = 0; i < b.length; i++){

            for (int j = 1; j < b[i]; j++){

                d += j * d;

            }

            d = d * (i + 1) * b.length;

            if (i == 0 && !(b.length <= 2)) {

                d = d * b[i + 1] + d * b[i + 2];

            } else if (i == b.length - 1  && !(b.length <= 2)) {

                d = d * b[i - 1] + d * b[i - 2];

            } else if (!(b.length <= 2)) {

                d = d * b[i + 1] + d * b[i - 1];

            }

            for (int j = 1; j < b[i]; j++){

                while (d > 255){

                    d = d / 255;
    
                }

                for (int k = 1; k < b[i]; k++){

                    d += k * d;
    
                }

            }      
            
            d = d * 2147483647;

            while (d > 255){

                d = d / 255;

            }


            c[i] = (char) Math.round(d);

        }

        string = new String(c);

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(string.getBytes(StandardCharsets.UTF_8));

            BigInteger number = new BigInteger(1, hash);

            StringBuilder hexString = new StringBuilder(number.toString(16));

            while (hexString.length() < 32) {

                hexString.insert(0, '0');
                
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException(e);

        }

    }

}