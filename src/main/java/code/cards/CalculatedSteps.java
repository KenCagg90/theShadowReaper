package code.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static code.ModFile.makeID;

public class CalculatedSteps extends AbstractEasyCard {
    public final static String ID = makeID(CalculatedSteps.class.getSimpleName());

    public CalculatedSteps() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 6;
        this.selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
    }

    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
        if (this.cost > 0) {
            this.modifyCostForCombat(-1);
        }
    }

    @Override
    public void resetAttributes() {
        super.resetAttributes();
        this.costForTurn = this.cost; // Ensure cost is properly reset for turn
    }

    @Override
    public void upp() {
        this.upgradeBlock(4); // Upgrade to gain 10 Block instead of 6
    }
}