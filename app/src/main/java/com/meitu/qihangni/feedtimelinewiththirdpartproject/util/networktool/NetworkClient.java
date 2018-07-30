package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.networktool;

import android.support.annotation.NonNull;

import com.meitu.qihangni.feedtimelinewiththirdpartproject.util.okhttpInteceptors.AddHeaderToRetrofitInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * NetworkClient工具类
 *
 * @author nqh 2018/7/25
 */
public class NetworkClient {
    private static String TAG = NetworkClient.class.getName();
    private static final int DEFAULT_TIME_OUT = 5;                                      //超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;                                //超时时间 10s
    private static NetworkContract.NetworkMethod mNetworkMethod;
    private static RequestBuilder mRequest;
    private static OkHttpClient mOkHttpClient;


    private NetworkClient() {
        AddHeaderToRetrofitInterceptor mAddHeaderToRetrofitInterceptor = new AddHeaderToRetrofitInterceptor();
        mAddHeaderToRetrofitInterceptor.setHeaders(mRequest.getHeaders());
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(mAddHeaderToRetrofitInterceptor)
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    public void execute(NetworkContract.ResponseMethod responseMethod) {
        mNetworkMethod.execute(mRequest, mOkHttpClient, responseMethod);
    }

    public void cancel() {
        mNetworkMethod.cancel();
    }

    private static class InstanceHolder {
        private static final NetworkClient INSTANCE = new NetworkClient();
    }

    /**
     * {@link InstanceHolder}保证NetworkClient实例单一，利用类加载机制进行懒加载
     *
     * @return NetworkClient实例
     */
    public static NetworkClient getInstance(@NonNull RequestBuilder requestBuilder, @NonNull NetworkContract.NetworkMethod networkMethod) {
        mNetworkMethod = networkMethod;
        mRequest = requestBuilder;
        return InstanceHolder.INSTANCE;
    }

    public static class RequestBuilder {
        private List<String> headers = new ArrayList<>();
        private String url;
        private String string;
        private List<String> params = new ArrayList<>();
        private NetworkContract.HttpMethod httpMethod = NetworkContract.HttpMethod.GET;
        private RequestBody requestBody;

        public RequestBuilder addParam(String param) {
            params.add(param);
            return this;
        }

        public RequestBuilder httpMethod(NetworkContract.HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        public RequestBuilder requestBody(RequestBody requestBody) {
            this.requestBody = requestBody;
            return this;
        }

        public RequestBuilder string(String string) {
            this.string = string;
            return this;
        }

        public RequestBuilder addHeader(String key, String value) {
            headers.add(key);
            headers.add(value);
            return this;
        }

        public RequestBuilder url(String url) {
            this.url = url;
            return this;
        }

        public NetworkClient build(NetworkContract.NetworkMethod networkMethod) {
            return getInstance(this, networkMethod);
        }

        public List<String> getHeaders() {
            if (headers.size() == 0) {
                return null;
            }
            return headers;
        }

        public NetworkContract.HttpMethod getHttpMethod() {
            return httpMethod;
        }

        public List<String> getParams() {
            if (params.size() == 0) {
                return null;
            }
            return params;
        }

        public String getUrl() {
            return url;
        }

        public String getString() {
            return string;
        }

        public RequestBody getRequestBody() {
            return requestBody;
        }
    }

}
