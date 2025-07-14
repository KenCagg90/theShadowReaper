package kayncode.actions;

import kayncode.powers.ReapPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

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
        if (target.hasPower(ReapPower.POWER_ID)) {
            ReapPower reapPower = (ReapPower) target.getPower(ReapPower.POWER_ID);

            // Trigger double Reap damage
            reapPower.triggerReap(2.0f);

            // Remove the Reap power
            addToBot(new RemoveSpecificPowerAction(target, player, ReapPower.POWER_ID));
        }
        this.isDone = true;
    }
}