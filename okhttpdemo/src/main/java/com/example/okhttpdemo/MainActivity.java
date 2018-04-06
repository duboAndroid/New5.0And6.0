package com.example.okhttpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {
    public static final String TAG = "MainActivity";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String json = "{\n" +
            "    \"name\": \"hello.android6.0\"\n" +
            "}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1,开启一个子线程,做联网操作
        new Thread(){
            @Override
            public void run() {
//                get();
//                postJson();
//                postParams();
            }
        }.start();

//        uploadFile();
        uploadMultiFiles();
    }

    private void postParams() {
        //1,创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2,构建请求体
        RequestBody requestBody = new FormEncodingBuilder()
                .add("platform", "android")
                .add("version", "23")
                .add("SDK", "24")
                .build();
        //3,构建一个请求对象
        Request request = new Request.Builder()
                .url("http://10.0.3.2:8080/TestProject1/ParamServlet")
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            //4,判断此请求是否成功
            if(response.isSuccessful()){
                //5,在post请求发送成功后,服务端返回的内容,做一个打印
                Log.i(TAG,response.body().string()+"xxxxxxxxxxxxxxxxxx");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void postJson() {
        //1,申明给服务端传递一个json串
        //2,创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //3,创建一个RequestBody(参数1:数据类型 参数2:出阿尼的json串)
        RequestBody requestBody = RequestBody.create(JSON, json);
        //4,创建一个请求对象
        Request request = new Request.Builder()
                .url("http://10.0.3.2:8080/TestProject1/JsonServlet")
                .post(requestBody)
                .build();
        //5,发送请求获取响应对象
        try {
            Response response = okHttpClient.newCall(request).execute();
            //6,判断此请求是否成功
            if(response.isSuccessful()){
                //7,在post请求发送成功后,服务端返回的内容,做一个打印
                Log.i(TAG,response.body().string()+"00000000000000");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传单个文件
     */
    private void uploadFile(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://10.0.3.2:8080/TestProject1/JsonServlet")
                .post(RequestBody.create(MediaType.parse("application/octet-stream"),
                        new File(Environment.getExternalStorageDirectory(), "/download1/hh.apk")))
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() { //子线程中执行
            @Override
            public void onFailure(Request request, IOException e) {
                //注意该回调是在子线程
            }

            @Override
            public void onResponse(Response response) throws IOException {
                //注意该回调是在子线程
                Log.i(TAG,response.body().string()+"00000000000000");
            }
        });
    }

    /**
     * 上传多个文件
     */
    private void uploadMultiFiles(){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBuilder()
                .type(MediaType.parse("multipart/form-data"))
                .addFormDataPart("file1", "hhh.apk1", RequestBody.create(MediaType.parse("application/octet-stream")
                        , new File(Environment.getExternalStorageDirectory() + "/download1/hh.apk")))
                .addFormDataPart("file2", "hhh.apk2", RequestBody.create(MediaType.parse("application/octet-stream")
                        , new File(Environment.getExternalStorageDirectory() + "/download1/hh.apk")))
                .build();
        Request request = new Request.Builder()
                .url("http://10.0.3.2:8080/TestProject1/JsonServlet")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {  //子线程中执行
            @Override
            public void onFailure(Request request, IOException e) {
                //注意该回调是在子线程
            }

            @Override
            public void onResponse(Response response) throws IOException {
                //注意该回调是在子线程
                Log.i(TAG,response.body().string()+"00000000000000");
            }
        });
    }


    private void get() {
        OkHttpClient okHttpClient = new OkHttpClient();
        //2,构建一个请求对象
        Request request = new Request.Builder()
                .url("http://wthrcdn.etouch.cn/weather_mini?citykey=101010100")
                .build();
        //3,发送请求
        try {
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            //4,打印服务端返回的json串
            Log.i(TAG,response.body().string()+"==========");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
