package kayncode.cards;

import kayncode.actions.ReapersTollAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class ReapersToll extends AbstractEasyCard {
    public final static String ID = makeID(ReapersToll.class.getSimpleName());


    public ReapersToll() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ReapersTollAction(m));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}
