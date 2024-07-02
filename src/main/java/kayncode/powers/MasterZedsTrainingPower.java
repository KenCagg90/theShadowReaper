package kayncode.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static kayncode.KaynMod.makeID;

public class MasterZedsTrainingPower extends AbstractEasyPower {
    public static String ID = makeID(MasterZedsTrainingPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public MasterZedsTrainingPower(AbstractCreature owner, int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (EnergyPanel.totalCount > 0) {
            int remainingEnergy = EnergyPanel.totalCount;
            if (remainingEnergy > 0) {
                this.flash();
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new DrawCardNextTurnPower(this.owner, this.amount),  this.amount));
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}