package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.gsondeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * 为了容错后台返回的结果
 * 拦截int或者Integer类型后台返回"" 或者 "null"，则设置默认值为 -1
 *
 * @author nqh 2018/7/16
 */
public class IntegerGsonDeserializer implements JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            //默认后台返回"" 或者 "null"，则设置默认值为 -1
            if (json.getAsString().equals("") || json.getAsString().equals("null")) {
                return -1;
            }
        } catch (Exception e) {
        }
        try {
            return json.getAsInt();
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }
}
