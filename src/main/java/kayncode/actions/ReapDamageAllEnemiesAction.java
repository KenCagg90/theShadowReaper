package kayncode.actions;

import kayncode.powers.ReapPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ReapDamageAllEnemiesAction extends AbstractGameAction {
    private AbstractPlayer player;

    public ReapDamageAllEnemiesAction(AbstractPlayer player) {
        this.player = player;
        this.actionType = ActionType.DAMAGE;
    }

    @Override
    public void update() {
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (monster.hasPower(ReapPower.ID)) {
                int reapAmount = monster.getPower(ReapPower.ID).amount;
                addToBot(new SFXAction("REAP"));
                this.addToBot(new DamageAction(monster, new DamageInfo(player, reapAmount, DamageInfo.DamageType.HP_LOSS), AttackEffect.NONE));
            }
        }
        this.isDone = true;
    }
}
