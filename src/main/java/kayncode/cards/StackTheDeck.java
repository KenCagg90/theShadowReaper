package kayncode.cards;

import kayncode.actions.StackTheDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static kayncode.KaynMod.makeID;

public class StackTheDeck extends AbstractEasyCard {
    public final static String ID = makeID(StackTheDeck.class.getSimpleName());

    public StackTheDeck() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new StackTheDeckAction(2));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0); // Upgrade to cost 0 energy
    }
}
