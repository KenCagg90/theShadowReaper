package code.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import static code.ModFile.makeID;

public class Stalk extends AbstractEasyCard {
    public final static String ID = makeID(Stalk.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , ,

    public Stalk() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.magicNumber), this.magicNumber));
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}