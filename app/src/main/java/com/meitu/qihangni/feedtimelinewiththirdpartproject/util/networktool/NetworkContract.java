package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool.ResponseMethod.JsonResponse;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * NetworkClient工具协议类，定义了一些需要遵守的事情
 *
 * @author nqh 2018/7/26
 */
public class NetworkContract {

    /**
     * 这是网络请求拓展需要遵守的协议
     */
    public interface NetworkMethod {
        void execute(NetworkClient.RequestBuilder requestBuilder, OkHttpClient okHttpClient, ResponseMethod responseMethod);

        void cancel();
    }

    public interface DownloadMethod extends NetworkMethod{
        void pause();
    }

    /**
     * 这是获取处理返回应遵守的协议
     * 示例{@link JsonResponse}
     */
    public interface ResponseMethod {
        void onSucceed(Response response) throws IOException;

        void onFailed(String errorMsg);
    }

    public interface DownloadResponseMethod extends ResponseMethod {
        void onProcess(long total, long process);

        void onComplete(String filename);
    }

    /**
     * 这是用户所关心的Callback处理接口，当用户层面需要处理返回时，可以实现此接口
     */
    public interface NetworkCallback<T> {
        void onSucceed(T t);

        void onFailed(String errorMsg);

    }

    /**
     * 定义了基本的网络请求方法
     */
    public enum HttpMethod {
        GET, POST
    }
}
