package code.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;

public class SiphoningStrikesPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("SiphoningStrikesPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public SiphoningStrikesPower(AbstractCreature owner, int amount) {
        super(POWER_ID, powerStrings.NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(ReapPower.ID) && target != this.owner) {
            this.flash();
            this.addToBot(new GainBlockAction(this.owner, this.amount));
        }
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}
