package com.nao20010128nao.FuckinCalculator

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.util.Log

import java.util.regex.Pattern

public class Utils {
    public static Pattern PATTERN_NUMBER_ANY_DIGIT=Pattern.compile("^[0-9]*\$")
    /**
     * Calculates nPr
     * */
    public static BigInteger nPr(BigInteger n,BigInteger r){
        if(n==r)return factorial(n)//nPn=n!
        if(r==BigInteger.ONE)return n//nP1=n
        if(r==BigInteger.ZERO)return BigInteger.ONE//nP0=1
        BigInteger now=BigInteger.ONE
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
     * Calculates coefficients of x^a*y^b for multiplying (x+y)^n style formula
     * */
    public static List<BigInteger[]> multiplyCoefficients(BigInteger factor,BigInteger xFactor=1,BigInteger yFactor=1){
        def pow={BigInteger a,BigInteger b->
            if(a==BigInteger.ONE)return BigInteger.ONE//1^n=1
            if(b==BigInteger.ZERO)return BigInteger.ONE//n^0=1
            if(b==BigInteger.ONE)return a//n^1=n
            BigInteger now=BigInteger.ONE
            b.times{
                now*=a
            }
            return now
        }
        List<BigInteger[]> values=[]
        BigInteger.ZERO.upto(factor) {
            BigInteger xMultiply=factor-it
            BigInteger yMultiply=it
            BigInteger ncr=nCr(factor,it as BigInteger)
            BigInteger multiplied=ncr*pow(xFactor,xMultiply)*pow(yFactor,yMultiply)
            // r, coefficient on x, coefficient on y, result of nCr, multiplied value
            values.add([it.toBigInteger(),xMultiply,yMultiply,ncr,multiplied])
        }
        return values
    }
    public static Spanned applySpannable(CharSequence cs,Object what){
        SpannableStringBuilder ssb=new SpannableStringBuilder()
        ssb.append(cs)
        ssb.setSpan(what,0,cs.length(),SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ssb
    }
}