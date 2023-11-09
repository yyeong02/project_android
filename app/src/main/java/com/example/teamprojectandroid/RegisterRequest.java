package com.example.teamprojectandroid;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://211.62.65.201/register.php";
    private Map<String, String> map;

    public RegisterRequest(String id, String pw, String name, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("id",id);
        map.put("pw",pw);
        map.put("name",name);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError{
        return map;
    }
}
