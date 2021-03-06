package com.codedynamix.pottyari.Title;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.MotionEvent;

import androidx.appcompat.app.AlertDialog;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.Object.Figure;
import com.codedynamix.pottyari.Progress.ProgressHero;
import com.codedynamix.pottyari.Scene.ProgressScene;
import com.codedynamix.pottyari.Scene.StatusScene;
import com.codedynamix.pottyari.System.NewEnter;
import com.codedynamix.pottyari.System.StService;
import com.codedynamix.pottyari.System.StepCount;
import com.codedynamix.pottyari.System.Vector2;
import com.codedynamix.pottyari.UI.HeroUI;
import com.codedynamix.pottyari.UI.HomeButton;
import com.codedynamix.pottyari.UI.Information;
import com.codedynamix.pottyari.UI.Waku;

import static androidx.core.content.ContextCompat.startForegroundService;
import static com.codedynamix.pottyari.Activity.GameActivity.getActivity;

public class UITitle extends Object
{
    private HomeButton hint;
    private HomeButton stop;
    private HomeButton adventure;

    private HeroUI heroTitle;

    private ProgressHero progHero;

    private Waku waku;
    private Figure step;

    private Information info;
    private Information hintInfo;

    public UITitle()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        hint = new HomeButton(0);
        stop = new HomeButton(1);
        adventure = new HomeButton(2);

        waku=new Waku();

        heroTitle = new HeroUI();
        heroTitle.setSize(new Vector2(800.0f,800.0f));
        heroTitle.setPosition(new Vector2(0.0f,100.0f));

        //アニメーション勇者
        progHero = new ProgressHero();
        progHero.setSize(new Vector2(100.0f,100.0f));
        progHero.setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + progHero.getSize().x,GameActivity.getBaseHei() / 2 - progHero.getSize().y));

        //歩数 オーバーしないように限界を定める
        step = new Figure(Math.min(StepCount.getAll(),999999999),1);
        step.setSize(new Vector2(100.0f,100.0f));
        step.setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x/0.18f,GameActivity.getBaseHei() / 2 - size.y/1.0f));

        info = new Information();
    }

    @Override
    public void draw()
    {
        heroTitle.draw();
        hint.draw();
        stop.draw();
        adventure.draw();
        waku.draw();
        step.draw();
        progHero.draw();
        info.draw(0);

        if(hintInfo != null) hintInfo.draw();
    }

    @Override
    public void update()
    {
        progHero.update();
        step.setValue(Math.min(StepCount.getAll(),999999999));
        step.update();
    }


    @Override
    public void touch(MotionEvent event)
    {
        Vector2 ratio = new Vector2(GameActivity.getBaseWid() / GameActivity.getSurfaceWid(),
                GameActivity.getBaseHei() / GameActivity.getSurfaceHei());

        Vector2 touchPos = new Vector2(event.getX() * ratio.x - GameActivity.getBaseWid() / 2,(-event.getY() * ratio.y + GameActivity.getBaseHei() / 2));
        if(event.getAction() == MotionEvent.ACTION_DOWN)    //trigger
        {
            //infoが見えてる間はそこを進めることしかできない
            if(NewEnter.getInformScene(0))
            {
                info.addTouch();
            }
            else if(hintInfo != null)
            {
                hintInfo.addTouch();
                if(hintInfo.getTouchCnt() >= 7)
                    hintInfo = null;
            }
            else
            {
                //当たり判定取得(めんどい)
                if(touchPos.x < adventure.getPosition().x + adventure.getSize().x / 2 && touchPos.x > adventure.getPosition().x - adventure.getSize().x / 2 &&
                        touchPos.y < adventure.getPosition().y + adventure.getSize().y / 2 && touchPos.y > adventure.getPosition().y - adventure.getSize().y / 2)
                {
                    //冒険する を押したらゲーム画面へ
                    BaseScene.setnextScene(new ProgressScene());
                }

                if(touchPos.x < heroTitle.getPosition().x + heroTitle.getSize().x / 4 && touchPos.x > heroTitle.getPosition().x - heroTitle.getSize().x / 4 &&
                        touchPos.y < heroTitle.getPosition().y + heroTitle.getSize().y / 4 && touchPos.y > heroTitle.getPosition().y - heroTitle.getSize().y / 3)
                {
                    //プレイヤーを押したらステータス確認画面へ
                    BaseScene.setnextScene(new StatusScene());
                }

                if(touchPos.x < stop.getPosition().x + stop.getSize().x / 2 && touchPos.x > stop.getPosition().x - stop.getSize().x / 2 &&
                        touchPos.y < stop.getPosition().y + stop.getSize().y / 2 && touchPos.y > stop.getPosition().y - stop.getSize().y / 2)
                {
                    //サービスを終了
                    if(GameActivity.getIntent() != null)
                    {
                        new AlertDialog.Builder(getActivity())
                                .setTitle("注意")
                                .setMessage("アプリの常駐状態を解除します。\n歩数の取得が不安定になることが予想されますがよろしいですか？")
                                .setPositiveButton("はい", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        getActivity().stopService(GameActivity.getIntent());
                                        GameActivity.setIntent(null);
                                        new AlertDialog.Builder(getActivity())
                                                .setMessage("次回アプリ起動の際には常駐状態に戻ります。")
                                                .setPositiveButton("わかった", null)
                                                .show();
                                    }
                                })
                                .setNegativeButton("いいえ", null)
                                .show();
                    }
                }

                if(touchPos.x < hint.getPosition().x + hint.getSize().x / 2 && touchPos.x > hint.getPosition().x - hint.getSize().x / 2 &&
                        touchPos.y < hint.getPosition().y + hint.getSize().y / 2 && touchPos.y > hint.getPosition().y - hint.getSize().y / 2)
                {
                    //?を押したら説明が読めるぞ！
                    hintInfo = new Information();
                }
            }
        }
    }
}
