package code.actions;

import code.powers.ReapPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class ReapersTollAction extends AbstractGameAction {
    private AbstractCreature target;
    private AbstractPlayer player;

    public ReapersTollAction(AbstractCreature target) {
        this.target = target;
        this.player = AbstractDungeon.player;
        this.actionType = ActionType.DAMAGE;
    }

    @Override
    public void update() {
        if (target.hasPower(ReapPower.ID)) {
            int reapAmount = target.getPower(ReapPower.ID).amount;
            int damage = reapAmount * 2;
            addToBot(new DamageAction(target, new DamageInfo(player, damage, DamageInfo.DamageType.THORNS), AttackEffect.SLASH_DIAGONAL));
            addToBot(new RemoveSpecificPowerAction(target, player, ReapPower.ID));
        }
        this.isDone = true;
    }
}
