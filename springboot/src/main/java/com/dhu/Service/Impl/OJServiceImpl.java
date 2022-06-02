package com.dhu.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhu.Service.OJService;
import com.dhu.mapper.OJMapper;
import com.dhu.pojo.OJLuogu;
import com.dhu.pojo.User;
import com.dhu.utils.ThreeTuple;
import com.dhu.utils.TwoTuple;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/29 21:17
 */
@Service
public class OJServiceImpl extends ServiceImpl<OJMapper, OJLuogu> implements OJService {
    public List<String> getAllQuestions(String id) {
        Process proc;
        List<String> list = new ArrayList<>();
        try{
//            String exe = "C:\\Users\\13280\\PycharmProjects\\pythonProject\\venv\\Scripts\\python.exe";
//            String command = "C:\\Users\\13280\\Desktop\\ACM Intelligent management platform\\springboot\\src\\main\\java\\com\\dhu\\python\\luogu_user.py";
            String exe = "/usr/bin/python3";
            String command = "/root/luogu_user.py";
            String[] cmdArr =  new String[] {exe, command, id};
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
                return null;
            }else{
                String luoguName = stringList.get(0);
                list.add(luoguName);
                for(int i=2;i<stringList.size();i++){
//                    System.out.println(stringList.get(i));
                    list.add(stringList.get(i));
                }
            }
            int res = proc.waitFor();
//            0表示正常否则不正常
            if(res != 0) {
                System.out.println("爬取洛谷数据时, 执行python程序出现错误");
//                注意千万不要 return null 要不就坏事了...
                return list;
            }
//            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public ThreeTuple<HashMap<String, Integer>, Integer, Boolean> getLuoguInformation(HttpServletRequest request, String id) {
        List<String> list = getAllQuestions(id);
//        之后处理
        HashMap<String, Integer> hashMap = new HashMap<>();
        int rating = 0;
        if(list == null) return new ThreeTuple<>(hashMap, rating, false);
        if(list.isEmpty()) return new ThreeTuple<>(hashMap, rating, true);
        List<OJLuogu> luoguList = baseMapper.selectBatchIds(list);
        for(OJLuogu ojLuogu : luoguList){
            String[] data = ojLuogu.getAlgorithm().split(" ");
            for(String s : data){
                if(hashMap.containsKey(s)){
                    hashMap.replace(s, hashMap.get(s) + 1);
                }else{
                    hashMap.put(s, 1);
                }
            }
//            先随便搞一个, 这个算法很不合理...
            switch (ojLuogu.getDifficulty()) {
                case "入门":
                    rating += 1;
                    break;
                case "普及-":
                    rating += 2;
                    break;
                case "普及/提高-":
                    rating += 3;
                    break;
                case "普及+/提高":
                    rating += 4;
                    break;
                case "提高+/省选-":
                    rating += 6;
                    break;
                case "省选/NOI-":
                    rating += 7;
                    break;
                case "NOI/NOI+/CTSC":
                    rating += 10;
                    break;
                default:
                    break;
            }
        }
        return new ThreeTuple<>(hashMap, rating, true);
    }
}
