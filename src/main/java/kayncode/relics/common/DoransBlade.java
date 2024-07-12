package kayncode.relics.common;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import kayncode.TheShadowReaper;
import kayncode.relics.AbstractEasyRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static kayncode.KaynMod.makeID;

public class DoransBlade extends AbstractEasyRelic {
    public static final String ID = makeID("DoransBlade");
    private static final int VIGOR_AMOUNT = 3;

    public DoransBlade() {
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
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, VIGOR_AMOUNT), VIGOR_AMOUNT));
    }
        if (counter == 0) {
            this.grayscale = true;
            this.counter = -1;

        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + VIGOR_AMOUNT + DESCRIPTIONS[1];
    }

    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new DoransBlade();
    }
}
