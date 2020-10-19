package com.example.deb.UI;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class MessageWindow extends Object
{
    private static Texture texture;

    private int texNum;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public MessageWindow(int num)
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        texNum = num;
        switch (texNum)
        {
            case 0:
                //ptを消費して武器の強化を行いますか？ ショップ想定
                setSize(new Vector2(200.0f,200.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x,0.0f));
                texStartPoint = new Vector2();
                texSize = new Vector2(0.5f,0.3333f);
                return;
            case 1:
                //ptを消費してlevelをあげますか？ ショップ想定
                setSize(new Vector2(200.0f,200.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x,0.0f));
                texStartPoint = new Vector2(0.0f,0.3334f);
                texSize = new Vector2(0.5f,0.3333f);
                return;
            case 2:
                //ptを消費してアイテムを購入しますか？ ショップ想定
                setSize(new Vector2(120.0f,120.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x,GameActivity.getBaseHei() / 2 - size.y));
                texStartPoint = new Vector2(0.0f,0.6667f);
                texSize = new Vector2(0.5f,1.0f);
                return;
            case 3:
                //縦長無地mw 未定
                setSize(new Vector2(120.0f,120.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x,GameActivity.getBaseHei() / 2 - size.y));
                texStartPoint = new Vector2(0.5f,0.0f);
                texSize = new Vector2(0.25f,0.6666f);
                return;
            case 4:
                //横長無地 進行画面想定
                setSize(new Vector2(GameActivity.getBaseWid() - 10.0f,400.0f));
                setPosition(new Vector2(0.0f, -GameActivity.getBaseHei() / 2 + size.y / 1.8f));
                texStartPoint = new Vector2(0.5f,0.6667f);
                texSize = new Vector2(0.5f,0.3333f);
                return;
        }

    }

    @Override
    public void draw()
    {
        texture.draw(pos,size,rotate,reverse,texStartPoint,texSize,color);
    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.window);
    }

}
