package com.example.deb.UI;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class BattleText extends Object
{
    private static Texture texture;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public BattleText(int num)
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        switch (num)
        {
            case 0:
                //敵が現れた
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                setPosition(new Vector2(GameActivity.getBaseWid() / 6,-GameActivity.getBaseHei() / 4 - size.y));
                texStartPoint = new Vector2();
                break;
            case 1:
                //どうする？
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                setPosition(new Vector2(GameActivity.getBaseWid() / 6,-GameActivity.getBaseHei() / 4 - size.y));
                texStartPoint = new Vector2(0.0f,0.1f);
                break;
            case 2:
                //勇者の攻撃
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                setPosition(new Vector2(GameActivity.getBaseWid() / 6,-GameActivity.getBaseHei() / 4 - size.y));
                texStartPoint = new Vector2(0.0f,0.2f);
                break;
            case 3:
                //ダメージ与えた
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                setPosition(new Vector2(GameActivity.getBaseWid() / 6,-GameActivity.getBaseHei() / 4 - size.y));
                texStartPoint = new Vector2(0.0f,0.3f);
                break;
            case 4:
                //相手の攻撃
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                setPosition(new Vector2(GameActivity.getBaseWid() / 6,-GameActivity.getBaseHei() / 4 - size.y));
                texStartPoint = new Vector2(0.0f,0.4f);
                break;
            case 5:
                //ダメージを受けた
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                setPosition(new Vector2(GameActivity.getBaseWid() / 6,-GameActivity.getBaseHei() / 4 - size.y));
                texStartPoint = new Vector2(0.0f,0.5f);
                break;
            case 6:
                //野菜ジュースを飲んだ
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                setPosition(new Vector2(GameActivity.getBaseWid() / 6,-GameActivity.getBaseHei() / 4 - size.y));
                texStartPoint = new Vector2(0.0f,0.6f);
                break;
            case 7:
                //回復した
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                setPosition(new Vector2(GameActivity.getBaseWid() / 6,-GameActivity.getBaseHei() / 4 - size.y));
                texStartPoint = new Vector2(0.0f,0.7f);
                break;
            case 8:
                //生き返った
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                setPosition(new Vector2(GameActivity.getBaseWid() / 6,-GameActivity.getBaseHei() / 4 - size.y));
                texStartPoint = new Vector2(0.0f,0.8f);
                break;
            case 9:
                //死んでしまった
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                setPosition(new Vector2(GameActivity.getBaseWid() / 6,-GameActivity.getBaseHei() / 4 - size.y));
                texStartPoint = new Vector2(0.0f,0.9f);
                break;

        }
        texSize = new Vector2(1.0f,0.1f);

    }

    @Override
    public void draw()
    {
        texture.draw(pos,size,rotate,reverse,texStartPoint,texSize,color);
    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.text);
    }

}
