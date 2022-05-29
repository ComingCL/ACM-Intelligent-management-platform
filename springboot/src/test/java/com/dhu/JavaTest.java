package com.dhu;

import com.dhu.Service.OJService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/22 15:44
 */

public class JavaTest {
    @Test
    public void testContains(){
//        Scanner sc = new Scanner(System.in);
//        String s = sc.next();
        String s = "1.2.3.4..5";

        System.out.println(s.lastIndexOf('.'));
    }
}
