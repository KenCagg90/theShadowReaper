package code.cards;

import code.powers.AssassinsTrainingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class AssassinsTraining extends AbstractEasyCard {
    public final static String ID = makeID(AssassinsTraining.class.getSimpleName());

    public AssassinsTraining() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new AssassinsTrainingPower(p, 1), 1));
    }

    @Override
    public void upp() {
        upgradeBaseCost(1); // Upgrades the card to cost 0 energy
    }
}
