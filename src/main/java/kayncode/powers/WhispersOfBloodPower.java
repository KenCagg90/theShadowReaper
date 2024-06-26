//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kayncode.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static kayncode.KaynMod.makeID;


public class WhispersOfBloodPower extends AbstractEasyPower {
        public static String ID = makeID(WhispersOfBloodPower.class.getSimpleName());

        private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
        private AbstractCreature source;

        public WhispersOfBloodPower(AbstractCreature owner, int amount) {
            super(ID, powerStrings.NAME, PowerType.BUFF, false, owner, amount);
        }

    @Override
    public void atStartOfTurn() {
        flash();
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (!mo.isDeadOrEscaped()) {
                this.addToBot(new ApplyPowerAction(mo, owner, new ReapPower(mo, this.amount), this.amount));
            }
        }
    }

        @Override
        public void updateDescription() {
            description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
        }
    }



