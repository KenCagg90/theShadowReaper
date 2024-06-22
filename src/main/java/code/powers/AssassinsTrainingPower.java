package code.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static code.ModFile.makeID;

public class AssassinsTrainingPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("AssassinsTrainingPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public AssassinsTrainingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, powerStrings.NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer && EnergyPanel.totalCount > 0) {
            int remainingEnergy = EnergyPanel.totalCount;
            this.flash();
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, remainingEnergy * this.amount), remainingEnergy * this.amount));
        }
        }


    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0];
    }
}
