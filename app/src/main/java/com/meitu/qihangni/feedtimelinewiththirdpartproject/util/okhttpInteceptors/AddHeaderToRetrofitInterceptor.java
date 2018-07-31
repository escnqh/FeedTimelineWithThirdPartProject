package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.okhttpInteceptors;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author nqh 2018/7/26
 */
public class AddHeaderToRetrofitInterceptor implements Interceptor {
    private Headers mHeaders = null;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        if (mHeaders == null) {
            return chain.proceed(original);
        }
        Request request = original.newBuilder()
                .headers(mHeaders)
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }

    public void setHeaders(Map<String, String> headers) {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers != null && headers.size() > 0) {
            for (String key : headers.keySet()) {
                headerBuilder.add(key, headers.get(key));
            }
        }
        mHeaders = headerBuilder.build();
    }
}
