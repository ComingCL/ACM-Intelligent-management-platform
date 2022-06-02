package com.dhu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhu.Service.OJService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    @Test
    public void testEmpty(){
        List<String> list = null;
        if(list == null) System.out.println(1);
    }
    @Test
    public void testTime() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
        if(format.parse("2022-06-01 23:27:00").before(date)) System.out.println(1);
    }
}
