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
    public ChartJson addDatasetData(String label,Integer i){
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
    public JSONObject build() {
        data.put("lables",labels);
        data.put("datasets",datasets);
        return data;
    }
}
