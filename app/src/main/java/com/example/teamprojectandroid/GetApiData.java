package com.example.teamprojectandroid;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class GetApiData {
    Map<String, String> urls;

    public ArrayList<ApiData> getData(String state){

        setUrls();

        ArrayList<ApiData> dataArray = new ArrayList<>();

        Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    String fullUrl = urls.get(state)+"&returnType=XML";
                    URL url = null;
                    url = new URL(fullUrl);
                    InputStream is = url.openStream();

                    XmlPullParserFactory xmlFactory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = xmlFactory.newPullParser();
                    parser.setInput(is,"utf-8");

                    boolean bName = false;
                    boolean bAddress = false;
                    boolean bPhone = false;
                    String name = "";
                    String address = "";
                    String phone = "";

                    while(parser.getEventType() != XmlPullParser.END_DOCUMENT){
                        int type = parser.getEventType();
                        ApiData data = new ApiData();

                        if(type == XmlPullParser.START_TAG){
                            if(parser.getName().equals("col")){
                                if((parser.getAttributeValue(0).equals("기관명칭"))||(parser.getAttributeValue(0).equals("관할기관"))||(parser.getAttributeValue(0).equals("상호"))||(parser.getAttributeValue(0).equals("기관명")))
                                    bName = true;
                                else if((parser.getAttributeValue(0).equals("도로명주소"))||(parser.getAttributeValue(0).equals("주소"))||(parser.getAttributeValue(0).equals("소재지")))
                                    bAddress = true;
                                else if((parser.getAttributeValue(0).equals("전화번호"))||(parser.getAttributeValue(0).equals("연락처")))
                                    bPhone = true;
                            }
                        }
                        else if(type == XmlPullParser.TEXT){
                            if(bName){
                                name = parser.getText();
                                bName = false;
                            }else if(bAddress){
                                address = parser.getText();
                                bAddress = false;
                            }else if(bPhone){
                                phone = parser.getText();
                                bPhone = false;
                            }
                        }

                        else if(type == XmlPullParser.END_TAG && parser.getName().equals("item")){
                            data.setName(name);
                            data.setAddress(address);
                            data.setPhone(phone);
                            dataArray.add(data);
                        }

                        type = parser.next();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
        };

        try{
            thread.start();
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return dataArray;
    }

    public void setUrls(){
        urls.put("서구","https://api.odcloud.kr/api/15112956/v1/uddi:9392b675-e632-47e5-809a-e69a926b1d17?page=1&perPage=1000&serviceKey=R23Qsfh7ikGmK%2Bjusy8V%2BdtfQnsIyk4eesAelagVJ8H0sQL0N01s54uRQWpFAJZkGWWVhEcs38GXMXq3dA4p6w%3D%3D");
        urls.put("달성군", "https://api.odcloud.kr/api/15113143/v1/uddi:c0d2cd21-f081-4d2c-81bd-d8ab18e74883?page=1&perPage=1000&serviceKey=R23Qsfh7ikGmK%2Bjusy8V%2BdtfQnsIyk4eesAelagVJ8H0sQL0N01s54uRQWpFAJZkGWWVhEcs38GXMXq3dA4p6w%3D%3D");
        urls.put("북구", "https://api.odcloud.kr/api/15113020/v1/uddi:f6ebd1a8-23ce-46ee-be13-be3650c592d2?page=1&perPage=1000&serviceKey=R23Qsfh7ikGmK%2Bjusy8V%2BdtfQnsIyk4eesAelagVJ8H0sQL0N01s54uRQWpFAJZkGWWVhEcs38GXMXq3dA4p6w%3D%3D");
        urls.put("중구", "https://api.odcloud.kr/api/15113008/v1/uddi:ebad7b3b-0abc-41b9-908c-ef5a5673cb82?page=1&perPage=1000&serviceKey=R23Qsfh7ikGmK%2Bjusy8V%2BdtfQnsIyk4eesAelagVJ8H0sQL0N01s54uRQWpFAJZkGWWVhEcs38GXMXq3dA4p6w%3D%3D");
        urls.put("수성구", "https://api.odcloud.kr/api/15113103/v1/uddi:01272062-5cdb-48c4-ad43-f23360ba3807?page=1&perPage=1000&serviceKey=R23Qsfh7ikGmK%2Bjusy8V%2BdtfQnsIyk4eesAelagVJ8H0sQL0N01s54uRQWpFAJZkGWWVhEcs38GXMXq3dA4p6w%3D%3D");
        urls.put("남구", "https://api.odcloud.kr/api/15113095/v1/uddi:e76b98b5-9602-41b1-a362-ae28d26237ba?page=1&perPage=1000&serviceKey=R23Qsfh7ikGmK%2Bjusy8V%2BdtfQnsIyk4eesAelagVJ8H0sQL0N01s54uRQWpFAJZkGWWVhEcs38GXMXq3dA4p6w%3D%3D");
    }

}
