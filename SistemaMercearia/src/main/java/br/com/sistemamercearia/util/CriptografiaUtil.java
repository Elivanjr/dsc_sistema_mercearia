/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.util;

/**
 *
 * @author gabriel
 */
import org.mindrot.jbcrypt.BCrypt;

public class CriptografiaUtil {
    public static String gerarHash(String senha){
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }
    
    public static boolean senhaCorreta(String senha, String hash){
        return BCrypt.checkpw(senha, hash);
    }
}
