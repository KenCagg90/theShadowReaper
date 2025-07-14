package kayncode.actions;

import kayncode.powers.ReapPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TripleReapAction extends AbstractGameAction {
    private AbstractCreature target;

    public TripleReapAction(AbstractCreature target) {
        this.target = target;
    }

    @Override
    public void update() {
        AbstractPower reapPower = target.getPower(ReapPower.POWER_ID);
        if (reapPower != null) {
            int newAmount = reapPower.amount * 2;
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, AbstractDungeon.player, new ReapPower((AbstractMonster) target, newAmount), newAmount));
        }
        this.isDone = true;
    }
}