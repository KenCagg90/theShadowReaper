package code.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static code.ModFile.makeID;

public class ShadowSight extends AbstractEasyCard {
    public final static String ID = makeID(ShadowSight.class.getSimpleName());
    private static int totalPlaysThisTurn = 0;

    public ShadowSight() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        selfRetain = true;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(this.magicNumber));
        totalPlaysThisTurn++;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (totalPlaysThisTurn >= 2) {
            return false;
        }
        return super.canUse(p, m);
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        totalPlaysThisTurn = 0; // Reset the counter at the start of each turn
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
