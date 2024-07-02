package kayncode.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.Settings;

public class LoseEnergyNextTurnAction extends AbstractGameAction {
    private final int energyLoss;

    public LoseEnergyNextTurnAction(int energyLoss) {
        this.energyLoss = energyLoss;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.actionManager.addToBottom(new WaitAction(Settings.ACTION_DUR_FAST));
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractDungeon.player.loseEnergy(energyLoss);
                    this.isDone = true;
                }
            });
            this.isDone = true;
        }
        this.tickDuration();
    }
}
