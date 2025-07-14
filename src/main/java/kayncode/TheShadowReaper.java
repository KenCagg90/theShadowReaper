package kayncode;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import kayncode.cards.*;
import kayncode.relics.BaseForm;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import kayncode.relics.TheDarkinScythe;

import java.util.ArrayList;

import static kayncode.TheShadowReaper.Enums.SHADOWREAPER_COLOR;
import static kayncode.KaynMod.*;

public class TheShadowReaper extends CustomPlayer {

    static final String ID = makeID("Kayn");
    static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    static final String[] NAMES = characterStrings.NAMES;
    static final String[] TEXT = characterStrings.TEXT;

    private static final String DEFAULT_ATLAS_PATH = KAYN_ATLAS_PATH;
    private static final String DEFAULT_JSON_PATH = KAYN_JSON_PATH;
    private static final String DEFAULT_ANIMATION_NAME = "KaynIdle";
    private static final String DEFAULT_SHOULDER_1 = SHOULDERKAYN;
    private static final String DEFAULT_SHOULDER_2 = SHOULDERKAYN2;
    private static final String DEFAULT_CORPSE = CORPSEKAYN;

    private static final String[] orbTextures = {
            makeCharacterPath("mainChar/orb/layer1.png"),
            makeCharacterPath("mainChar/orb/layer2.png"),
            makeCharacterPath("mainChar/orb/layer3.png"),
            makeCharacterPath("mainChar/orb/layer4.png"),
            makeCharacterPath("mainChar/orb/layer4.png"),
            makeCharacterPath("mainChar/orb/layer6.png"),
            makeCharacterPath("mainChar/orb/layer1d.png"),
            makeCharacterPath("mainChar/orb/layer2d.png"),
            makeCharacterPath("mainChar/orb/layer3d.png"),
            makeCharacterPath("mainChar/orb/layer4d.png"),
            makeCharacterPath("mainChar/orb/layer5d.png"),
    };

    private Color orbColor = Color.WHITE.cpy(); // Default orb color

    public TheShadowReaper(String name, PlayerClass setClass) {
        super(name, setClass, new KaynEnergyOrb(orbTextures, makeCharacterPath("mainChar/orb/vfx.png"), null),
                new SpineAnimation(KAYN_ATLAS_PATH, KAYN_JSON_PATH, 0.8F));
        AnimationState.TrackEntry e = state.setAnimation(0, "KaynIdle", true);
        initializeClass(null,
                SHOULDERKAYN,
                SHOULDERKAYN2,
                CORPSEKAYN,
                getLoadout(), 20.0F, -10.0F, 200.0F, 250.0F, new EnergyManager(3));

        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                75, 75, 0, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            retVal.add(Strike.ID);
        }
        for (int i = 0; i < 4; i++) {
            retVal.add(Defend.ID);
        }
        for (int i = 0; i < 1; i++) {
            retVal.add(PrepareTheKill.ID);
        }
        for (int i = 0; i < 1; i++) {
            retVal.add(SlaughterTheWeak.ID);
        }
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(BaseForm.ID);
        retVal.add(TheDarkinScythe.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("KAYN_SELECT", MathUtils.random(-0.0F, 0.0F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    private boolean transformedToRhaast = false;
    private boolean transformedToAssassin = false;

    public void transformToAssassin() {
        setVisuals(ASSASSIN_ATLAS_PATH, ASSASSIN_JSON_PATH, 0.7F, "AssassinIdle",
                SHOULDERASSASSIN, SHOULDERASSASSIN2, CORPSEASSASSIN);
        this.transformedToAssassin = true;
        this.transformedToRhaast = false;
    }

    public void transformToRhaast() {
        setVisuals(RHAAST_ATLAS_PATH, RHAAST_JSON_PATH, 0.7F, "RhaastIdle",
                SHOULDERRHAAST, SHOULDERRHAAST2, CORPSERHAAST);
        this.transformedToRhaast = true;
        this.transformedToAssassin = false;
    }


    private void setVisuals(String atlasPath, String jsonPath, float scale, String animationName,
                            String shoulder1Path, String shoulder2Path, String corpsePath) {
        // Update animation
        this.loadAnimation(atlasPath, jsonPath, scale);
        state.setAnimation(0, animationName, true);

        // Update shoulder and corpse images
        this.shoulderImg = ImageMaster.loadImage(shoulder1Path);
        this.shoulder2Img = ImageMaster.loadImage(shoulder2Path);
        this.corpseImg = ImageMaster.loadImage(corpsePath);
    }

    public void resetToDefaultAppearance() {
        setVisuals(DEFAULT_ATLAS_PATH, DEFAULT_JSON_PATH, 0.8F, DEFAULT_ANIMATION_NAME,
                DEFAULT_SHOULDER_1, DEFAULT_SHOULDER_2, DEFAULT_CORPSE);
        this.transformedToRhaast = false;
        this.transformedToAssassin = false;
    }

    public boolean isTransformedToRhaast() {
        return transformedToRhaast;
    }

    public boolean isTransformedToAssassin() {
        return transformedToAssassin;
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "KAYN_SELECT";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 8;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return SHADOWREAPER_COLOR;
    }

    @Override
    public Color getCardTrailColor() {
        return characterColor.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new SlaughterTheWeak();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheShadowReaper(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return characterColor.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return characterColor.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SLASH_VERTICAL,
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_SHADOWREAPER;
        @SpireEnum(name = "SHADOWREAPER_COLOR")
        public static AbstractCard.CardColor SHADOWREAPER_COLOR;
        @SpireEnum(name = "SHADOWREAPER_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }


}
