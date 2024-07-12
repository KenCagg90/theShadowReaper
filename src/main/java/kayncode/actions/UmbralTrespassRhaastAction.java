package kayncode.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class UmbralTrespassRhaastAction extends AbstractGameAction {

    public UmbralTrespassRhaastAction(AbstractCreature target, int amount) {
        this.target = target;
        this.amount = amount;

        this.duration = 0.1F;
    }

    @Override
    public void update() {
        if (duration == 0.1F && target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, AttackEffect.SLASH_VERTICAL));
            target.damage(new DamageInfo(AbstractDungeon.player, amount, DamageInfo.DamageType.THORNS));
            if ((target.isDying || target.currentHealth <= 0) && !target.halfDead && !target.hasPower("Minion")) {
                    AbstractDungeon.player.increaseMaxHp(4, false);
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        tickDuration();
    }
}
