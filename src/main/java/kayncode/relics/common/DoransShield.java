package kayncode.relics.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import kayncode.TheShadowReaper;
import kayncode.relics.AbstractEasyRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static kayncode.KaynMod.makeID;

public class DoransShield extends AbstractEasyRelic {
    public static final String ID = makeID("DoransShield");
    private static final int BLOCK_AMOUNT = 3;

    public DoransShield() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void atBattleStart() {
        this.counter = 3;
        this.grayscale = false;

    }
    public void atTurnStart() {
        if (counter > 0) {
            this.flash();
            this.counter--;
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, BLOCK_AMOUNT));
        }
        if (counter == 0) {
            this.grayscale = true;
            this.counter = -1;

        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BLOCK_AMOUNT + DESCRIPTIONS[1];
    }

    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new DoransShield();
    }
}
