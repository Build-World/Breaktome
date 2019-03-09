package com.breaktome.engine.interfaces;

public interface IDebug {

    String __debug__();

    boolean isDebug();

    default void printDebug() {
        if(isDebug())
        {
            System.out.println(__debug__());
        }
    }

}
