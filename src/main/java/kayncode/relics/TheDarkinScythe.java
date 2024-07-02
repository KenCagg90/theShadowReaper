package kayncode.relics;

import kayncode.TheShadowReaper;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

import static kayncode.KaynMod.makeID;

public class TheDarkinScythe extends AbstractEasyRelic implements CustomSavable<int[]> {
    public static final String ID = makeID("TheDarkinScythe");

    public TheDarkinScythe() {
        super(ID, RelicTier.SPECIAL, LandingSound.FLAT, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    public static int redOrbs = 0;
    public static int blueOrbs = 0;

    private boolean redSoundPlayed = false;
    private boolean blueSoundPlayed = false;

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        redOrbs = 0;
        blueOrbs = 0;
    }

    @Override
    public void onVictory() {
        this.flash();
        this.description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        initializeTips();
    }

    public void updateDescription() {
        this.description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        initializeTips();
    }

    public void checkOrbCounts() {
        if (redOrbs >= 8 && !redSoundPlayed) {
            CardCrawlGame.sound.play("RED_TRANSFORM_READY");
            redSoundPlayed = true;
        } else if (blueOrbs >= 8 && !blueSoundPlayed) {
            CardCrawlGame.sound.play("BLUE_TRANSFORM_READY");
            blueSoundPlayed = true;
        }
    }

    private void transformToRelic(AbstractRelic newRelic) {
        AbstractDungeon.player.loseRelic(BaseForm.ID);
        AbstractDungeon.player.loseRelic(this.relicId);
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.hb.cX, this.hb.cY, newRelic);
    }

    public void flashRed() {
        this.flash();
        AbstractDungeon.effectList.add(new BorderFlashEffect(Color.RED, true));
    }

    public void flashBlue() {
        this.flash();
        AbstractDungeon.effectList.add(new BorderFlashEffect(Color.BLUE, true));
    }

    @Override
    public void renderCounter(SpriteBatch sb, boolean inTopPanel) {
        int redOrbs = TheDarkinScythe.redOrbs;
        int blueOrbs = TheDarkinScythe.blueOrbs;
        renderDoubleCounter(sb, inTopPanel, redOrbs, blueOrbs);
    }

    @Override
    public int[] onSave() {
        return new int[]{redOrbs, blueOrbs};
    }

    @Override
    public void onLoad(int[] data) {
        if (data != null && data.length >= 2) {
            redOrbs = data[0];
            blueOrbs = data[1];
        }
    }
}
