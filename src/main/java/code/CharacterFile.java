package code;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import basemod.animations.SpriterAnimation;
import code.cards.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import code.relics.TheDarkinScythe;

import java.util.ArrayList;

import static code.CharacterFile.Enums.TODO_COLOR;
import static code.ModFile.*;

public class CharacterFile extends CustomPlayer {

    private static final Float SIZE_SCALE = 0.8F;
    static final String ID = makeID("ModdedCharacter");
    static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    static final String[] NAMES = characterStrings.NAMES;
    static final String[] TEXT = characterStrings.TEXT;

    // Path to your Spine animation files
    private static final String SKELETON_ATLAS_PATH = makeCharacterPath("mainChar/Kayn_spine.atlas");
    private static final String SKELETON_JSON_PATH = makeCharacterPath("mainChar/Kayn_ske.json");

    public CharacterFile(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, makeCharacterPath("mainChar/orb/vfx.png"), null),
                new SpineAnimation(SKELETON_JSON_PATH, SKELETON_ATLAS_PATH, SIZE_SCALE));
        initializeClass(null,
                SHOULDER1,
                SHOULDER2,
                CORPSE,
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
        retVal.add(TheDarkinScythe.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("KAYN_SELECT", MathUtils.random(-0.0F, 0.0F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

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
        return TODO_COLOR;
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
        System.out.println("YOU NEED TO SET getStartCardForEvent() in your " + getClass().getSimpleName() + " file!");
        return null;
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new CharacterFile(name, chosenClass);
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
        //TODO: Change these.
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_TODO;
        @SpireEnum(name = "TODO_COLOR")
        public static AbstractCard.CardColor TODO_COLOR;
        @SpireEnum(name = "TODO_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }
}
