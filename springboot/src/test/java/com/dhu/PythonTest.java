package com.dhu;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/29 3:02
 */
public class PythonTest {
    @Test
    public void TestLuogu(){
        Process proc;
        try{
//            这里python解释器位置也要写好
//            proc = Runtime.getRuntime().exec("C:\\Users\\13280\\PycharmProjects\\pythonProject\\venv\\Scripts\\python.exe \"C:\\Users\\13280\\Desktop\\ACM Intelligent management platform\\springboot\\src\\main\\java\\com\\dhu\\python\\luogu_user.py\"");
            String exe = "C:\\Users\\13280\\PycharmProjects\\pythonProject\\venv\\Scripts\\python.exe";
            String command = "C:\\Users\\13280\\Desktop\\ACM Intelligent management platform\\springboot\\src\\main\\java\\com\\dhu\\python\\luogu_user.py";
            String[] cmdArr =  new String[] {exe, command, "4"};
            proc = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            List<String> stringList = new ArrayList<>();
            while((line = in.readLine()) != null){
                stringList.add(line);
            }
            in.close();
            if(stringList.size() == 2 && stringList.get(1).equals("None")){
                System.out.println("用户不存在");
            }else{
                for(int i=0;i<stringList.size();i++){
                    System.out.println(stringList.get(i));
                }
            }
            int res = proc.waitFor();
//            0表示正常否则不正常
//            System.out.println(res);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestNormal(){
        Process proc;
        try{
//            proc = Runtime.getRuntime().exec("C:\\Users\\13280\\PycharmProjects\\pythonProject\\venv\\Scripts\\python.exe \"C:\\Users\\13280\\Desktop\\ACM Intelligent management platform\\springboot\\src\\main\\java\\com\\dhu\\python\\test.py\"");
            String exe = "python";
            String command = "C:\\Users\\13280\\Desktop\\ACM Intelligent management platform\\springboot\\src\\main\\java\\com\\dhu\\python\\test.py";
            String[] cmdArr =  new String[] {exe, command, "hello", "world"};
            proc = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while((line = in.readLine()) != null){
                System.out.println(line);
            }
            in.close();
            int res = proc.waitFor();
//            0表示正常否则不正常
            System.out.println(res);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
