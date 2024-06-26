package kayncode.cards;

import kayncode.powers.BloodragePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class Bloodrage extends AbstractEasyCard {
    public final static String ID = makeID(Bloodrage.class.getSimpleName());

    public Bloodrage() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new BloodragePower(p, 1), 1));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0); // Upgrades the card to cost 0 energy
    }
}
