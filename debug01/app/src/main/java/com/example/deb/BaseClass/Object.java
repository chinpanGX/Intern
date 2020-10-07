package com.example.deb.BaseClass;

import android.view.MotionEvent;

import com.example.deb.System.TextureInfo;

import javax.microedition.khronos.opengles.GL10;

/*
* javaの命名規則について(C++との違い)
* メソッド(関数)の頭文字も小文字を扱う
* 例:getPosition
* ここだけ気を付けて良きjavaLifeを!
* */


//ゲームオブジェクト
//継承用
public class Object
{
//protected:
    //javaにはVector2が用意されていないので自分で用意する
    protected class Vector2
    {
        float x;
        float y;

        //初期化式
        public Vector2()
        {
            x = 0.0f;
            y = 0.0f;
        }
        //代入式
        public Vector2(float X,float Y)
        {
            x = X;
            y = Y;
        }
    }

    //カラー型も用意しておく
    protected class Color
    {
        float r;
        float g;
        float b;
        float a;

        //初期化式
        public Color()
        {
            r = 1.0f;
            g = 1.0f;
            b = 1.0f;
            a = 1.0f;
        }

        //代入式
        public Color(float R,float G,float B, float A)
        {
            r = R;
            g = G;
            b = B;
            a = A;
        }
    }

    //レイヤーはオブジェクト側で管理する
    protected enum Layer
    {
        LAYER_BG(0),
        LAYER_CHARE(1),
        LAYER_BUTTON(2),
        LAYER_MESSAGE(3),
        LAYER_MAX(4);

        private final int id;

        private Layer(int num) {this.id = num; }

        public int getId(){return this.id;}
    }

    //draw関数で使う変数群
    protected GL10          gl10;       //GLを司るもの あまりよくわからない
    protected TextureInfo   texInfo;    //テクスチャ情報

    protected Vector2       pos;        //描画座標 左上基準
    protected Vector2       size;       //描画サイズ

    protected float         rotate;     //回転 ゲームによってはあまり使わない
    protected boolean       reverce;    //反転 同上

    protected int           animCnt;    //アニメーションの状態遷移を管理
    protected int           interval;   //アニメーションの休憩時間
    protected int           pattern;    //アニメーションが何パターンあるか

    protected Color         color;      //色 all1.0fで描画される


    protected Layer layer;
    //public:
    //純粋仮想関数もどき 基本継承して自分のしたいことすればいいと思う
    public void init() {}
    public void uninit() {texInfo = null;}

    public void update(float dt)
    {
        animCnt += dt;      //アニメーションを動かす
    }

    public void draw(GL10 gl)
    {
        //例外処理
        if(texInfo == null) return;

        //描画

    }

    //スマホっぽい関数 これも継承して使う
    public void touch(MotionEvent event){}
    public void button(int id, MotionEvent event){}

    //ゲッターとセッター
    public Vector2 getPosition()           {return pos;}
    public void setPosition(Vector2 pos)   {pos = pos;}

    public int getLayer()                 {return layer.id;}
}
