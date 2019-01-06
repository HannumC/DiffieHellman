//***********************************************
// Diffie-Hellman Key Exchange
// Author: Corey Hannum
//***********************************************

import java.io.*;
import java.lang.Math;
import java.util.Random;
import java.math.BigInteger;

public class DiffieHellman{
   public static void main(String args[]){
      int input = Integer.parseInt(args[0]);
      BigInteger binary = BigInteger.valueOf(2);
      BigInteger maxP;
      maxP = binary.pow(input);
      
      BigInteger minP;
      minP = binary.pow(input - 1);
      
      BigInteger p;
      Random rnd = new Random();
      
      // Generate value of p
      p = findPrime(minP, input);
      
      // Generate value of g
      BigInteger g = new BigInteger(input, rnd);
      BigInteger n = BigInteger.valueOf(2);
      
      g = (p.subtract(BigInteger.ONE)).divide(n);
      
      while(!isPrime(g)){
         n = n.add(BigInteger.ONE);
         g = (p.subtract(BigInteger.ONE)).divide(n);
      }
      
      // Generate private keys
      BigInteger a = new BigInteger(input, rnd);
      BigInteger b = new BigInteger(input, rnd);
      
      while(a.compareTo(b) == 0){
         a = new BigInteger(input, rnd);
         b = new BigInteger(input, rnd);
      }
      
      //Generate public keys
      BigInteger A = g.modPow(a, p);
      BigInteger B = g.modPow(b, p);
      
      //Generate x
      BigInteger aX = B.modPow(a, p);
      BigInteger bX = A.modPow(b, p);     
      
      //print all values
      System.out.println("The value of p: " + p); 
      System.out.println("The value of g: " + g);
      System.out.println("The value of a: " + a);
      System.out.println("The value of b: " + b);
      System.out.println("The value of A: " + A);
      System.out.println("The value of B: " + B);
      System.out.println("The value of A^b mod p: " + aX);
      System.out.println("The value of B^a mod p: " + bX);
   }
   
   //Generates prime BigInteger p
   private static BigInteger findPrime(BigInteger minP, int input){
      Random rnd = new Random();
      BigInteger p;
      do{
         p = new BigInteger(input, rnd);
         
         if(p.compareTo(minP) >= 0){
            BigInteger bi = BigInteger.valueOf(2);
            
            while(bi.compareTo((p.divide(BigInteger.valueOf(2)))) <= 0){
            
               if((p.mod(bi)).compareTo(BigInteger.ZERO) == 0){
                  p = BigInteger.valueOf(0);
               }
               bi = bi.add(BigInteger.ONE);
            }
         }
      }while(p.compareTo(minP) == -1);
      System.out.println("  ");
      return p;
   }
   
   //Finds prime g such that g = p-1/n
   public static boolean isPrime(BigInteger g){
      BigInteger bi = BigInteger.valueOf(2);
      while(bi.compareTo((g.divide(BigInteger.valueOf(2)))) <= 0){
               
         if((g.mod(bi)).compareTo(BigInteger.ZERO) == 0){
            return false;
         }
         bi = bi.add(BigInteger.ONE);
      }
      return true;
   }
   
}