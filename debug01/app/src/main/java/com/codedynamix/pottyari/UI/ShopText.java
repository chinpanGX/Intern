package com.codedynamix.pottyari.UI;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.System.Texture;
import com.codedynamix.pottyari.System.Vector2;

public class ShopText extends Object
{
    private static Texture texture;

    private int texNum;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public ShopText(int num)
    {
        super();
        setLayer(Object.Layer.LAYER_BUTTON);
        switch (num)
        {
            case 0:
                //pt
                setSize(new Vector2(100.0f,100.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x / 1.5f,GameActivity.getBaseHei() / 2- size.y/0.185f));
                texStartPoint = new Vector2();
                texSize = new Vector2(0.125f,0.25f);
                break;
            case 1:
                //ptが足りません
                setSize(new Vector2(600.0f,100.0f));
                setPosition(new Vector2());
                texStartPoint = new Vector2(0.0f,0.25f);
                texSize = new Vector2(1.0f,0.25f);
                break;
            case 2:
                //所持数
                setSize(new Vector2(360.0f,120.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x/2,-GameActivity.getBaseHei() / 2+ size.y/0.6f));
                texStartPoint = new Vector2(0.0f,0.5f);
                texSize = new Vector2(0.375f,0.25f);
                break;
        }

    }

    @Override
    public void draw()
    {
        if(texture == null) return;
        texture.draw(pos,size,rotate,reverse,texStartPoint,texSize,color);
    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.shoptext2);
    }
}
