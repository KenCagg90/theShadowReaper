package code.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import static code.ModFile.makeID;
import static code.util.Wiz.atb;
import static code.util.Wiz.makeInHand;

public class Shadowstep extends AbstractEasyCard {
    public final static String ID = makeID(Shadowstep.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , ,

    public Shadowstep() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 3;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}