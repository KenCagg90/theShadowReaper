package kayncode.relics;

import kayncode.TheShadowReaper;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;

import static kayncode.KaynMod.makeID;

public class BaseForm extends AbstractEasyRelic {
    public static final String ID = makeID("BaseForm");
    private static final int EXTRA_CARDS = 1;
    private int turnsDrawn = 0;

    public BaseForm() {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void atBattleStart() {
        turnsDrawn = 0; // Reset the turn counter at the start of each combat
        this.counter = 2;
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
