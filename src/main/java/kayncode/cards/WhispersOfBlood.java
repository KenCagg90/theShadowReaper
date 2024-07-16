package kayncode.cards;

import kayncode.powers.WhispersOfBloodPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class WhispersOfBlood extends AbstractEasyCard {
    public final static String ID = makeID(WhispersOfBlood.class.getSimpleName());

    public WhispersOfBlood() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 4;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new WhispersOfBloodPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}