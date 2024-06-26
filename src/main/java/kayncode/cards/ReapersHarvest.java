package kayncode.cards;

import kayncode.actions.DoubleReapAction;
import kayncode.actions.TripleReapAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class ReapersHarvest extends AbstractEasyCard {
    public final static String ID = makeID(ReapersHarvest.class.getSimpleName());

    public ReapersHarvest() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.exhaust = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded) {
            this.addToBot(new DoubleReapAction(m));
        }
        else {
            this.addToBot(new TripleReapAction(m));
        }
    }

    @Override
    public void upp() {
    }


}