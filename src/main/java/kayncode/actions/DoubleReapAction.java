package kayncode.actions;

import kayncode.powers.ReapPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DoubleReapAction extends AbstractGameAction {
    private AbstractCreature target;

    public DoubleReapAction(AbstractCreature target) {
        this.target = target;
    }

    @Override
    public void update() {
        AbstractPower reapPower = target.getPower(ReapPower.ID);
        if (reapPower != null) {
            int newAmount = reapPower.amount ;
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, AbstractDungeon.player, new ReapPower((AbstractMonster) target, newAmount), newAmount));
        }
        this.isDone = true;
    }
}