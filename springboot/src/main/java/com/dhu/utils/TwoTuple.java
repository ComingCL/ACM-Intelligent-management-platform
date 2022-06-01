package com.dhu.utils;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/31 16:37
 */
// 此法可做到返回两个值, 返回多个值同理
public class TwoTuple<A, B> {
    public final A first;
    public final B second;

    public TwoTuple(A a, B b){
        first = a;
        second = b;
    }
}
