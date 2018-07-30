package com.meitu.qihangni.feedtimelinewiththirdpartproject.util.okhttpInteceptors;

import java.io.IOException;
import java.util.List;

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

    public void setHeaders(List<String> headers) {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers != null && headers.size() > 0) {
            for (int i = 0; i < headers.size(); i += 2) {
                headerBuilder.add(headers.get(i), headers.get(i + 1));
            }
        }
        mHeaders = headerBuilder.build();
    }
}
