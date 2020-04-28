package com.xmvp.xcynice.http.gson;

import com.google.gson.TypeAdapter;
import com.xmvp.xcynice.base.XBaseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author yechao
 * @date 2019/11/18/018
 * Describe : 重写ResponseBodyConverter对json预处理
 */
public class BaseResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    /**
     * 登陆失效
     */
    private static final int LOG_OUT_TIME = -1001;

    BaseResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String jsonString = value.string();
        try {
            JSONObject object = new JSONObject(jsonString);
            int code = object.getInt("errorCode");
            if (0 != code) {
                String data;
                //错误信息
                if (code == LOG_OUT_TIME) {
                    data = "登录失效，请重新登录";
                } else {
                    data = object.getString("errorMsg");
                }
                //异常处理
                throw new XBaseException(code, data);
            }
            //正确返回整个json
            return adapter.fromJson(jsonString);

        } catch (JSONException e) {
            e.printStackTrace();
            //数据解析异常即json格式有变动
            throw new XBaseException(XBaseException.PARSE_ERROR_MSG);
        } finally {
            value.close();
        }
    }
}