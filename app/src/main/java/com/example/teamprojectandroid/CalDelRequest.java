package com.example.teamprojectandroid;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CalDelRequest extends StringRequest {
    final static private String URL = "http://211.62.65.201/delcalendar.php";
    private Map<String, String> parameters;

    public CalDelRequest(String id, String startdate, String finishdate, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("startdate", startdate);
        parameters.put("finishdate", finishdate);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}
