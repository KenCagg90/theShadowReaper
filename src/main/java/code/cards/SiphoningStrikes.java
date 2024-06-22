package code.cards;

import code.powers.SiphoningStrikesPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class SiphoningStrikes extends AbstractEasyCard {
    public final static String ID = makeID(SiphoningStrikes.class.getSimpleName());

    public SiphoningStrikes() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 3;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SiphoningStrikesPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1); // Upgrades the card to cost 0 energy
    }
}
