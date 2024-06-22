package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ConservePower;

public class ConserveEnergyAction extends AbstractGameAction {
    private int amount;

    public ConserveEnergyAction(AbstractCreature target, int amount) {
        this.target = target;
        this.amount = amount;
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        if (this.target != null && this.target.isPlayer) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.target, new ConservePower(this.target, this.amount), this.amount));
        }
        this.isDone = true;
    }
}
