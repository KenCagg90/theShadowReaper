package code.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;

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
    public void atStartOfTurn() {
        this.flash();
        this.addToBot(new LoseHPAction(this.owner, this.owner, 3));
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}
