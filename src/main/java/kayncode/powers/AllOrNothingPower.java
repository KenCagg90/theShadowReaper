package kayncode.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static kayncode.KaynMod.makeID;

public class AllOrNothingPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("AllOrNothingPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public AllOrNothingPower(AbstractCreature owner, int strengthAmount) {
        super(POWER_ID, powerStrings.NAME, PowerType.BUFF, false, owner, strengthAmount);
        this.owner = owner;
        this.amount = strengthAmount;
        updateDescription();
        this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, this.amount), this.amount));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.flash();
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, StrengthPower.POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0];
    }
}
