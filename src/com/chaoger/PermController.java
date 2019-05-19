package com.chaoger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermController {

    private void upsertPerm(NewPermAO newPermAO){



        //2.落地权限数据

        //3.落地到操作历史表


    }


    //获取perm权限数据
    private List<PermDateVO> getBatchPermFromSci(){
        return new ArrayList<>();
    }


    //1.获取修改前的权限
    private NewPermAO convertPermDate2NewPermAO(List<PermDateVO> permDateVOS){
        return new NewPermAO();
    }

    //1.获取修改前的权限
    private List<PermDateVO> convertNewPermAO2PermDateVO(NewPermAO permAO){
        List<PermDateVO> permDateVOList = new ArrayList<>();
        Map<String, Map<String,Object>> model2Columns  = new HashMap<>();
        for (PermFieldAO permFieldAO : permAO.getPermFieldAOList()) {
            String permValue = permFieldAO.getFieldName();
            PermMapEnum permMapEnum = PermMapEnum.genPermMapEnumByPermVaules(permValue);


            String modelName = permMapEnum.getModelName();
            if(model2Columns.get(modelName)==null){
                model2Columns.put(modelName,new HashMap<>());
            }
            for (String column : permMapEnum.getColumns()) {
                model2Columns.get(modelName).put(column,permFieldAO.getValue());
            }
        }
        for (PermKeyAO permKeyAO : permAO.getPermKeyAOList()) {
           for (String modelName : model2Columns.keySet()) {
                PermDateVO permDateVO = new PermDateVO();
                permDateVO.setUserId(permKeyAO.getSupplierId());
                permDateVO.setModelName(modelName);
                permDateVO.setDemensionKey(String.valueOf(permKeyAO.getItemId()));
                permDateVO.setColumnFields(model2Columns.get(modelName).toString());

               permDateVOList.add(permDateVO);

            }
        }
        return permDateVOList;
    }


    //done
    private List<String> getOpertionContent(NewPermAO prePerm,NewPermAO curPerm){
        //增删改
        return new ArrayList<>();
    }

    public static void main(String[] args) {

        String express = "statDate>=nowTime&&statDate-nowTime<=2592000000";
        Map<String,Long> map = new HashMap<>();
        map.put("statDate",System.currentTimeMillis());
        map.put("nowTime",System.currentTimeMillis());



        for (String key : map.keySet()) {
             express = express.replace(key, String.valueOf(map.get(key)));
        }




        System.out.println(express);

        Long dayOfMs = 24*3600*1000L;
        System.out.println(getExpressParam(express,"<=",dayOfMs));

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        Object result = null;
        try {
             result= engine.eval(express);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    private  static Boolean getExpressResult(String express,Long statDate,Long nowTime){

        return true;
    }

    private  static long getExpressParam(String express,String op,Long factor){

        String[] split = express.split(op);
        return Long.valueOf(split[1])/factor;


    }


}
