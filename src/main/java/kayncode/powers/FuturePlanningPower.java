package kayncode.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import kayncode.util.Wiz;

import static kayncode.KaynMod.makeID;

public class FuturePlanningPower extends AbstractEasyPower {
    public static String ID = makeID(FuturePlanningPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public FuturePlanningPower(AbstractCreature owner, int amount) {
        super(ID, powerStrings.NAME, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            int cardsInHand = AbstractDungeon.player.hand.size();
            this.addToBot(new ApplyPowerAction(owner, owner, new DrawCardNextTurnPower(owner, cardsInHand), cardsInHand));
            // This power is intended to work only once, so we remove it at the end of the turn.
            this.addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
        }
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0];
    }
}
