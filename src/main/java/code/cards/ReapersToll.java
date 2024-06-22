package code.cards;

import code.actions.ReapersTollAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class ReapersToll extends AbstractEasyCard {
    public final static String ID = makeID(ReapersToll.class.getSimpleName());

    public ReapersToll() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ReapersTollAction(m));
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
    }
}
