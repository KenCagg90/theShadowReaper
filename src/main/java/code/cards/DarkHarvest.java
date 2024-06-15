package code.cards;

import code.actions.DoubleReapAction;
import code.actions.TripleReapAction;
import code.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.unique.DoublePoisonAction;
import com.megacrit.cardcrawl.actions.unique.TriplePoisonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class DarkHarvest extends AbstractEasyCard {
    public final static String ID = makeID(DarkHarvest.class.getSimpleName());

    public DarkHarvest() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
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