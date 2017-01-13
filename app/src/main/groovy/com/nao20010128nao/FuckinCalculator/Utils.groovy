package com.nao20010128nao.FuckinCalculator

public class Utils {
    /**
     * Calculates nPr
     * */
    public static BigInteger nPr(BigInteger n,BigInteger r){
        if(n==r)return factorial(n)//nPn=n!
        if(r==BigInteger.ONE)return n//nP1=n
        if(r==BigInteger.ZERO)return BigInteger.ZERO//nP0=0
        BigInteger now=BigDecimal.ONE
        r.times {
            now*=n
            n--
        }
        return now
    }
    /**
     * Calculates n!
     * */
    public static BigInteger factorial(BigInteger n){
        if(n==BigInteger.ONE)return BigInteger.ONE//1!=1
        BigInteger now=BigInteger.ONE
        n.times {
            now*=(it+1)
        }
        return now
    }
    /**
     * Calculates nCr<br>
     * Equals to nPr(n,r)/factorial(r)
     * */
    public static BigInteger nCr(BigInteger n,BigInteger r){
        return nPr(n,r)/factorial(r)
    }
    /**
     * Calculates coefficients of x and y for multiplying (x+y)^n style formula
     * */
    public static BigInteger[][] multiplyCoefficients(BigInteger factor,BigInteger xFactor=1,BigInteger yFactor=1){
        def pow={BigInteger a,BigInteger b->
            if(a==BigInteger.ONE)return BigInteger.ONE//1^n=1
            if(b==BigInteger.ZERO)return BigInteger.ONE//n^0=1
            if(b==BigInteger.ONE)return a//n^1=a
            BigInteger now=BigInteger.ONE
            b.times{
                now*=a
            }
            return now
        }
        BigInteger[][] values=[]
        BigInteger.ZERO.upto(factor) {
            BigInteger xMultiply=factor-it
            BigInteger yMultiply=it
            BigInteger multiplied=nCr(factor,it as BigInteger)*pow(xFactor,xMultiply)*pow(yFactor,yMultiply)
            values+=[/*r*/it,multiplied]
        }
        return values
    }
}