package com.pmac.zero;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
    public static AssetManager manager;
    public static final String greencirclebg = "skinSprites/greencirclebg.png";
    public static final String greenhexbg = "skinSprites/greenhexbg.png";
    public static final String greendiamondbg = "skinSprites/greendiamondbg.png";
    public static final String greenheartbg = "skinSprites/greenheartbg.png";
    public static final String redcirclebg = "skinSprites/redcirclebg.png";
    public static final String redhexbg = "skinSprites/redhexbg.png";
    public static final String reddiamondbg = "skinSprites/reddiamondbg.png";
    public static final String redheartbg = "skinSprites/redheartbg.png";
    public static final String pinkcirclebg = "skinSprites/pinkcirclebg.png";
    public static final String pinkhexbg = "skinSprites/pinkhexbg.png";
    public static final String pinkdiamondbg = "skinSprites/pinkdiamondbg.png";
    public static final String pinkheartbg = "skinSprites/pinkheartbg.png";
    public static final String bluecirclebg = "skinSprites/bluecirclebg.png";
    public static final String bluehexbg = "skinSprites/bluehexbg.png";
    public static final String bluediamondbg = "skinSprites/bluediamondbg.png";
    public static final String blueheartbg = "skinSprites/blueheartbg.png";

    public static final String atlas = "explodeSpritesheet/plogo2.atlas";
    public static final String agreenCircle = "longSprites/greenCircle.png";
    public static final String agreenHex = "longSprites/greenHex.png";
    public static final String agreenDiamond = "longSprites/greenDiamond.png";
    public static final String agreenHeart = "longSprites/greenHeart.png";
    public static final String aredCircle = "longSprites/redCircle.png";
    public static final String aredHex = "longSprites/redHex.png";
    public static final String aredDiamond = "longSprites/redDiamond.png";
    public static final String aredHeart = "longSprites/redHeart.png";
    public static final String apinkCircle = "longSprites/pinkCircle.png";
    public static final String apinkHex = "longSprites/pinkHex.png";
    public static final String apinkDiamond = "longSprites/pinkDiamond.png";
    public static final String apinkHeart = "longSprites/pinkHeart.png";
    public static final String ablueCircle = "longSprites/blueCircle.png";
    public static final String ablueHex = "longSprites/blueHex.png";
    public static final String ablueDiamond = "longSprites/blueDiamond.png";
    public static final String ablueHeart = "longSprites/blueHeart.png";
    public static final String asaiyan = "longSprites/saiyan.png";

    public static final String font = "r.fnt";

    public static final String greent = "skinSprites/greent.png";
    public static final String redt = "skinSprites/redt.png";
    public static final String pinkt = "skinSprites/pinkt.png";
    public static final String bluet = "skinSprites/bluet.png";
    public static final String pButton = "skinSprites/pButton.png";

    public static final String bg = "bg.jpg";
    public static final String two = "stage/two.png";
    public static final String three = "stage/three.png";
    public static final String four = "stage/four.png";
    public static final String five = "stage/five.png";
    public static final String six = "stage/six.png";
    public static final String seven = "stage/seven.png";
    public static final String eight = "stage/eight.png";
    public static final String nine = "stage/nine.png";
    public static final String ten = "stage/ten.png";
    public static final String fifty = "stage/fifty.png";
    public static final String onehundred = "stage/onehundred.png";

    public static final String skin = "vv.json";
    public static final String greenCircle = "skinSprites/greencircle.png";
    public static final String greenBarO = "healthBar/greenBarOutline.png";
    public static final String redBarO = "healthBar/redBarOutline.png";
    public static final String pinkBarO = "healthBar/pinkBarOutline.png";
    public static final String blueBarO = "healthBar/blueBarOutline.png";
    public static final String greenBar = "healthBar/greenBarFill.png";
    public static final String redBar = "healthBar/redBarFill.png";
    public static final String pinkBar = "healthBar/pinkBarFill.png";
    public static final String blueBar = "healthBar/blueBarFill.png";
    public static final String laser = "laser.ogg";
    public static final String musicbg = "bg.mp3";
    public static final String sound1 = "collide.ogg";
    public static final String pmacboost = "pmacboost.ogg";

    public static final String gameOverBanner = "gameOver.png";
    public static final String tryAgain = "tryAgain.png";
    public static final String ultSprite = "ult.png";
    public static final String pmacWarrior = "pmacWarrior.png";
    public static final String pmacLord = "pmacLord.png";
    public static final String pmacLegend = "pmacLegend.png";
    public static final String pmacGod = "pmacGod.png";
    public static final String star = "star.png";
    public static final String room = "stage/room.png";
    public static final String available = "stage/available.png";


    public static void load(){
        manager = new AssetManager();
        Zero.select = 1;
        manager.load(skin, Skin.class);
        manager.load(sound1, Sound.class);
        manager.load(laser, Sound.class);
        manager.load(pmacboost, Sound.class);
        manager.load(musicbg, Music.class);
        manager.load(font, BitmapFont.class);
        manager.load(atlas, TextureAtlas.class);

        manager.load(star, Texture.class);
        manager.load(room, Texture.class);
        manager.load(available, Texture.class);
        manager.load(two, Texture.class);
        manager.load(three, Texture.class);
        manager.load(four, Texture.class);
        manager.load(five, Texture.class);
        manager.load(six, Texture.class);
        manager.load(seven, Texture.class);
        manager.load(eight, Texture.class);
        manager.load(nine, Texture.class);
        manager.load(ten, Texture.class);
        manager.load(fifty, Texture.class);
        manager.load(onehundred, Texture.class);
        manager.load(pmacGod, Texture.class);
        manager.load(pmacLegend, Texture.class);
        manager.load(pmacLord, Texture.class);
        manager.load(pmacWarrior, Texture.class);
        manager.load(tryAgain, Texture.class);
        manager.load(ultSprite, Texture.class);
        manager.load(pButton, Texture.class);
        manager.load(gameOverBanner, Texture.class);
        manager.load(blueBar, Texture.class);
        manager.load(pinkBar, Texture.class);
        manager.load(redBar, Texture.class);
        manager.load(greenBar, Texture.class);
        manager.load(blueBarO, Texture.class);
        manager.load(pinkBarO, Texture.class);
        manager.load(redBarO, Texture.class);
        manager.load(greenBarO, Texture.class);
        manager.load(greenCircle, Texture.class);
        manager.load(bg, Texture.class);
        manager.load(greent, Texture.class);
        manager.load(redt, Texture.class);
        manager.load(pinkt, Texture.class);
        manager.load(bluet, Texture.class);
        manager.load(greencirclebg, Texture.class);
        manager.load(greenhexbg, Texture.class);
        manager.load(greenheartbg, Texture.class);
        manager.load(greendiamondbg, Texture.class);
        manager.load(redcirclebg, Texture.class);
        manager.load(redhexbg, Texture.class);
        manager.load(redheartbg, Texture.class);
        manager.load(reddiamondbg, Texture.class);
        manager.load(pinkcirclebg, Texture.class);
        manager.load(pinkhexbg, Texture.class);
        manager.load(pinkheartbg, Texture.class);
        manager.load(pinkdiamondbg, Texture.class);
        manager.load(bluecirclebg, Texture.class);
        manager.load(bluehexbg, Texture.class);
        manager.load(blueheartbg, Texture.class);
        manager.load(bluediamondbg, Texture.class);
        manager.load(agreenCircle, Texture.class);
        manager.load(agreenDiamond, Texture.class);
        manager.load(agreenHeart, Texture.class);
        manager.load(agreenHex, Texture.class);
        manager.load(aredCircle, Texture.class);
        manager.load(aredDiamond, Texture.class);
        manager.load(aredHeart, Texture.class);
        manager.load(aredHex, Texture.class);
        manager.load(apinkCircle, Texture.class);
        manager.load(apinkDiamond, Texture.class);
        manager.load(apinkHeart, Texture.class);
        manager.load(apinkHex, Texture.class);
        manager.load(ablueCircle, Texture.class);
        manager.load(ablueDiamond, Texture.class);
        manager.load(ablueHeart, Texture.class);
        manager.load(ablueHex, Texture.class);
        manager.load(asaiyan, Texture.class);
    }

    public static void dispose(){
        manager.dispose();
    }

}
