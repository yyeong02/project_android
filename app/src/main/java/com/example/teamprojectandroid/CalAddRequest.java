package com.example.teamprojectandroid;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CalAddRequest extends StringRequest {

    final static private String URL = "http://211.62.65.201/addcalendar.php";
    private Map<String, String> parameters;

    public CalAddRequest(String id, String medicine, String startdate, String finishdate, int detail1, int detail2, int detail3, int detail4, int detail5, String memo, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("medicine", medicine);
        parameters.put("startdate", startdate);
        parameters.put("finishdate", finishdate);
        parameters.put("detail1", String.valueOf(detail1));
        parameters.put("detail2", String.valueOf(detail2));
        parameters.put("detail3", String.valueOf(detail3));
        parameters.put("detail4", String.valueOf(detail4));
        parameters.put("detail5", String.valueOf(detail5));
        parameters.put("memo", memo);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}
