package com.dhu.utils;

/**
 * @Author: ComingLiu
 * @Date: 2022/6/3 2:20
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B>{
    public final C third;
    public ThreeTuple(A a, B b, C c){
        super(a, b);
        this.third = c;
    }
}
