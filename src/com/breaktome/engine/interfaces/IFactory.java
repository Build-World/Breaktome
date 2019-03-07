package com.breaktome.engine.interfaces;

public interface IFactory<T extends IFactory<T>> {
    T make();
}
