package com.example.teamprojectandroid;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import androidx.fragment.app.Fragment;


public class PharFragment extends Fragment {

    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_phar, container, false);

        webView = v.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setGeolocationEnabled(true);

        webView.loadUrl("http://211.62.65.201:8080/pharmacy/");




//
//        MapView mapView = new MapView(getActivity());
//        ViewGroup mapViewContainer = (ViewGroup) v.findViewById(R.id.map_view);
//        mapViewContainer.addView(mapView);
//
//        // 중심점 변경 - 예제 좌표는 영남대학교 경산캠퍼스
//        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.8318190753964, 128.75853345073014), true);
//
//        // 줌 레벨 변경
//        mapView.setZoomLevel(4,true);
//
//        //마커 찍기
//        MapPoint MARKER_POINT = MapPoint.mapPointWithCONGCoord(35.8318190753964, 128.75853345073014);
//        MapPOIItem marker = new MapPOIItem();
//        marker.setItemName("Default Marker");
//        marker.setTag(0);
//        marker.setMapPoint(MARKER_POINT);
//        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); //기본으로 제공하는 BluePin 마커 모양.
//        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); //마커를 클릭했을 때, 기본적으로 제공하는 RedPin 마커 모양.
//
//        mapView.addPOIItem(marker);

        return v;
    }
}
