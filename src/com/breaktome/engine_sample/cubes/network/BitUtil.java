/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.breaktome.engine_sample.cubes.network;

/**
 *
 * @author Carl
 */
public class BitUtil{
    
    public static int getNeededBitsCount(int value){
        return (32 - Integer.numberOfLeadingZeros(value));
    }
}
