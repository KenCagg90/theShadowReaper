package kayncode.relics;

import kayncode.TheShadowReaper;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;

import static kayncode.KaynMod.makeID;

public class BaseForm extends AbstractEasyRelic {
    public static final String ID = makeID("BaseForm");
    private static final int EXTRA_CARDS = 1;
    private static final int TEMP_HP = 3;
    private int turnsDrawn = 0;

    public BaseForm() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void atBattleStart() {
        turnsDrawn = 0; // Reset the turn counter at the start of each combat
        this.counter = 2;
        this.addToBot(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, TEMP_HP)); // Give 5 Temp HP
    }

    @Override
    public void atTurnStart() {
        if (turnsDrawn < 2) {
            this.flash();
            this.addToBot(new DrawCardAction(AbstractDungeon.player, EXTRA_CARDS));
            turnsDrawn++;
            this.counter -= 1;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }
}
