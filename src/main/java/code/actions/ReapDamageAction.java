package code.actions;

import code.powers.ReapPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ReapDamageAction extends AbstractGameAction {
    private AbstractPlayer player;
    private AbstractMonster target;

    public ReapDamageAction(AbstractPlayer player, AbstractMonster target) {
        this.player = player;
        this.target = target;
        this.actionType = ActionType.DAMAGE;
    }

    @Override
    public void update() {
        if (target.hasPower(ReapPower.ID)) {
            int reapAmount = target.getPower(ReapPower.ID).amount;
            int damage = reapAmount;
            addToBot(new SFXAction("REAP"));
            this.addToBot(new DamageAction(target, new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL), AttackEffect.NONE));
        }
        this.isDone = true;
    }
}