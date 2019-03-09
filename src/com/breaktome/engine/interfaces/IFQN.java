package com.breaktome.engine.interfaces;

/**
 * Fully Qualified Name
 */
public interface IFQN extends IKey, INamespace {

    default String getFQN()
    {
        return getNamespace() + ":" + getKey();
    }

}
