//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package code.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static code.ModFile.makeID;


public class DarkinRagePower extends AbstractEasyPower {
        public static String ID = makeID(DarkinRagePower.class.getSimpleName());

        private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
        private AbstractCreature source;

        public DarkinRagePower(AbstractCreature owner, int amount) {
            super(ID, powerStrings.NAME, PowerType.BUFF, false, owner, amount);
        }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.costForTurn >= 2) {
            this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, this.amount), this.amount));
        }
    }

        @Override
        public void updateDescription() {
            description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
        }
    }

