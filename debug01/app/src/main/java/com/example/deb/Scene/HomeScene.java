package com.example.deb.Scene;


import com.example.deb.BaseClass.BaseScene;
import com.example.deb.BG.BGTitle;
import com.example.deb.Title.UITitle;
import com.example.deb.UI.HeroUI;

public class HomeScene extends BaseScene
{
    private BGTitle bgTitle;
    private UITitle uiTitle;
    private HeroUI heroTitle;


    public HomeScene()
    {
//インスタンス初期化
        //背景
        bgTitle = new BGTitle();
        list.add(bgTitle);

        //UI
        uiTitle = new UITitle();
        list.add(uiTitle);

        //勇者
        heroTitle = new HeroUI();
        list.add(heroTitle);
    }
}
