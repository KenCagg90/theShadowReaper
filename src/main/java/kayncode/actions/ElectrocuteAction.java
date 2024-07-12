package kayncode.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.vfx.RedLightningEffect;

import java.util.List;
import java.util.stream.Collectors;

public class ElectrocuteAction extends AbstractGameAction {
    private final int damageAmount;

    public ElectrocuteAction(AbstractCreature target, int damageAmount) {
        this.target = target;
        this.damageAmount = damageAmount;
        this.actionType = ActionType.DAMAGE;
    }

    @Override
    public void update() {
        AbstractCreature targetCreature = getValidTarget(target);
        if (targetCreature != null) {
            this.addToBot(new DamageAction(targetCreature, new DamageInfo(AbstractDungeon.player, damageAmount, DamageInfo.DamageType.THORNS), AttackEffect.NONE));
            this.addToBot(new VFXAction(new RedLightningEffect(targetCreature.drawX, targetCreature.drawY), 0.1F));
            this.addToBot(new SFXAction("ELECTROCUTE_PROC"));
        }
        isDone = true;
    }

    private AbstractCreature getValidTarget(AbstractCreature target) {
        if (target != null && !target.isDeadOrEscaped()) {
            return target;
        } else {
            List<AbstractMonster> aliveMonsters = AbstractDungeon.getCurrRoom().monsters.monsters.stream()
                    .filter(monster -> !monster.isDeadOrEscaped())
                    .collect(Collectors.toList());
            if (!aliveMonsters.isEmpty()) {
                return aliveMonsters.get(AbstractDungeon.cardRandomRng.random(aliveMonsters.size() - 1));
            }
        }
        return null;
    }
}
