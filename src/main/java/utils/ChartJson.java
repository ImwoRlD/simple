package utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ChartJson {
    JSONObject data;
    JSONArray labels;
    JSONArray datasets;
    public ChartJson(){
        this.data=new JSONObject();
        this.labels=new JSONArray();
        this.datasets=new JSONArray();
    }

    public ChartJson addLabel(String label) {
        labels.add(label);
        return this;
    }
    public ChartJson addDatasetLabel(String label){
        JSONObject json=new JSONObject();
        json.put("label",label);
        datasets.add(json);
        return this;
    }
    public ChartJson addDatasetData(String label,Long i){
        for(int num=0;num<datasets.size();num++){
            if (((JSONObject)datasets.get(num)).get("label").equals(label)){
                if (((JSONObject)datasets.get(num)).get("data")!=null){
                    ((JSONArray)((JSONObject)datasets.get(num)).get("data")).add(i);
                }else {
                    JSONArray temp=new JSONArray();
                    temp.add(i);
                    ((JSONObject)datasets.get(num)).put("data",temp);
                }
            }
        }
        return this;
    }
    public ChartJson addDatasetIntData(String label,int i){
        for(int num=0;num<datasets.size();num++){
            if (((JSONObject)datasets.get(num)).get("label").equals(label)){
                if (((JSONObject)datasets.get(num)).get("data")!=null){
                    ((JSONArray)((JSONObject)datasets.get(num)).get("data")).add(i);
                }else {
                    JSONArray temp=new JSONArray();
                    temp.add(i);
                    ((JSONObject)datasets.get(num)).put("data",temp);
                }
            }
        }
        return this;
    }
    public ChartJson addDatasetFloatData(String label,float i){
        for(int num=0;num<datasets.size();num++){
            if (((JSONObject)datasets.get(num)).get("label").equals(label)){
                if (((JSONObject)datasets.get(num)).get("data")!=null){
                    ((JSONArray)((JSONObject)datasets.get(num)).get("data")).add(i);
                }else {
                    JSONArray temp=new JSONArray();
                    temp.add(i);
                    ((JSONObject)datasets.get(num)).put("data",temp);
                }
            }
        }
        return this;
    }
    public ChartJson addDatasetData(Long i){
        if (datasets.size()<1){
            JSONObject data=new JSONObject();
            JSONArray arr=new JSONArray();
            arr.add(i);
            data.put("data",arr);
            datasets.add(data);
        }else {
            ((JSONObject)datasets.get(0)).getJSONArray("data").add(i);
        }
        return this;
    }
    public ChartJson addDatasetBackgroudColor(){
        String[] colors={"red","orange","yellow","green","blue","purple","black"};
        JSONArray arr=new JSONArray();
        for (int i=0;i<this.datasets.getJSONObject(0).getJSONArray("data").size();i++){
            arr.add(CastUtil.parseColor(colors[i%colors.length]));
        }
        this.datasets.getJSONObject(0).put("backgroundColor",arr);
        return this;
    }
    public ChartJson addDatasetBackgroudColor(String label,String color){
        String co=CastUtil.parseColor(color);
        for(int num=0;num<datasets.size();num++){
            if (((JSONObject)datasets.get(num)).get("label").equals(label)){
                if (((JSONObject)datasets.get(num)).get("backgroundColor")!=null){
                    ((JSONArray)((JSONObject)datasets.get(num)).get("backgroundColor")).add(co);
                }else {
                    JSONArray temp=new JSONArray();
                    temp.add(co);
                    ((JSONObject)datasets.get(num)).put("backgroundColor",temp);
                }
            }
        }
        return this;
    }
    public JSONObject build() {
        data.put("labels",labels);
        data.put("datasets",datasets);
        return data;
    }
}
