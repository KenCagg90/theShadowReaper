package kayncode.actions;

import kayncode.powers.ReapPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SoulHarvestAction extends AbstractGameAction {
    private AbstractPlayer player;

    public SoulHarvestAction(AbstractPlayer player) {
        this.player = player;
    }

    @Override
    public void update() {
        int totalReap = 0;

        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (monster.hasPower(ReapPower.ID)) {
                totalReap += monster.getPower(ReapPower.ID).amount;
            }
        }

        if (totalReap > 0) {
            this.addToBot(new GainBlockAction(player, totalReap / 2));
        }

        this.isDone = true;
    }
}
