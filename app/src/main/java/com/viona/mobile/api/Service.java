package com.viona.mobile.api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.http.body.Part;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.koushikdutta.ion.builder.Builders;
import com.viona.mobile.helper.GeneralHelper;

import org.json.JSONObject;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Service {
    private final Activity activity;
    private final GeneralHelper functionHelper;
    private final ProgressDialog pDialog;
//    private String baseUrl = "https://api.gencarlca.id/public/";
    private final String baseUrl = "http://172.20.10.2/api-lca/public/";
    public String login = baseUrl + "auth/login";
    public String register = baseUrl + "auth/register";
    public String saveSchedule = baseUrl + "transaction/save";
    public String listSchedule = baseUrl + "transaction";
    public String flagSchedule = baseUrl + "transaction/flagTransaction";
    public String deleteSchedule = baseUrl + "transaction/delete";
    public String listTeacher = baseUrl + "transaction/listTeacher";
    public String presence = baseUrl + "transaction/presence";
    public String feedback = baseUrl + "transaction/feedback";
    public String updateProfile = baseUrl + "account/updateProfile";
    public String changePassword = baseUrl + "account/changePassword";
    public String statisticDashboard = baseUrl + "welcome/statisticDashboard";
    public String getScheduleSurvey = baseUrl + "sensus";
    public String getVillage = baseUrl + "sensus/getVillage";
    public String getDetailVillage = baseUrl + "sensus/getDetailVillage";
    public String getStatisticSurvey = baseUrl + "sensus/statistic";
    public String about = baseUrl + "welcome/about";
    public String sync = baseUrl + "sensus/sync";
    public String resultsSurvey = baseUrl + "sensus/result";
    public String saveSurvey = baseUrl + "sensus/saveSurvey";
    public String saveStatisticSurvey = baseUrl + "sensus/saveStatistic";
    public String monitoringSurvey = baseUrl + "sensus/monitoring";

    public Service(Activity activity, ProgressDialog pDialog) {
        this.activity   = activity;
        this.pDialog    = pDialog;
        functionHelper  = new GeneralHelper(activity);
        functionHelper.setupProgressDialog(pDialog, "Please wait ...");

        Ion.getDefault(activity).getConscryptMiddleware().enable(false);
        Ion.getDefault(activity).configure().setLogging("LOG API", Log.DEBUG);

        configApiService();
    }

    //    Trust all certificate website
    private void configApiService() {
        Ion.getDefault(activity).getConscryptMiddleware().enable(false);
        Ion.getDefault(activity).configure().setLogging("LOG GET API", Log.DEBUG);
        Ion.getDefault(activity).getHttpClient().getSSLSocketMiddleware().setTrustManagers(new TrustManager[] {new X509TrustManager() {
            @Override
            public void checkClientTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {}

            @Override
            public void checkServerTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {}

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }});
    }

    // Fungsi ini digunakan untuk mengambil data hashmap dari reponse API
    public interface hashMapListener {
        String getHashMap(Map<String, String> hashMap);
    }

    public void apiService(String url, Map<String, String> paramList, List<Part> files, boolean showLoading,
                           hashMapListener listener) {
        try {
            if (showLoading)
                functionHelper.showProgressDialog(pDialog, true);
            Builders.Any.B config = Ion.with(activity).load(url)
                    .setTimeout(AsyncHttpRequest.DEFAULT_TIMEOUT)
                    .setLogging("ApiService", Log.DEBUG)
                    .setHeader("Authorization", functionHelper.getSession("key"))
                    .noCache();

            if (files != null) {
                if (paramList != null) {
                    for (Map.Entry<String, String> data : paramList.entrySet()) {
                        String key = data.getKey();
                        String value = data.getValue();

                        config.setMultipartParameter(key, value);
                    }
                }

                try {
                    files.removeAll(Collections.singleton(null));
                    config.addMultipartParts(files);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (paramList != null) {
                    for (Map.Entry<String, String> data : paramList.entrySet()) {
                        String key      = data.getKey();
                        String value    = data.getValue();

                        config.setBodyParameter(key, value);
                    }
                }
            }

            config.asString()
                    .withResponse()
                    .setCallback(new FutureCallback<Response<String>>() {
                        @Override
                        public void onCompleted(Exception e, Response<String> result) {
                            if (showLoading)
                                functionHelper.showProgressDialog(pDialog, false);
                            try {
                                Map<String, String> hash = new HashMap<String, String>();
                                hash.clear();
                                if (e == null) {
                                    Log.e("result", result.getResult());
                                    if (result.getHeaders().code() == 200) {
                                        JSONObject results  = new JSONObject(result.getResult());
                                        String status       = results.getString("status");
                                        String message      = results.getString("message");

                                        hash.put("status", status);
                                        hash.put("message", message);

                                        if (status.equals("true") && results.has("data")) {
                                            String data     = results.getString("data");
                                            hash.put("data", data);
                                        }
                                        listener.getHashMap(hash);
                                    } else {
                                        hash.put("status", "false");
                                        hash.put("message", result.getHeaders().code() + " : " + result.getHeaders().message());
                                        listener.getHashMap(hash);
                                    }
                                } else {
                                    hash.put("status", "false");
                                    hash.put("message", e.getLocalizedMessage());
                                    listener.getHashMap(hash);
                                }
                            } catch (Exception ex) {
                                functionHelper.showToast(ex.getLocalizedMessage(), 0);
                                Log.e("Exception Api Service", "exception", ex);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
