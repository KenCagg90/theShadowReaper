package kayncode.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static kayncode.KaynMod.makeID;

public class TacticalSelection extends AbstractEasyCard {
    public final static String ID = makeID(TacticalSelection.class.getSimpleName());

    public TacticalSelection() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FetchAction(p.drawPile, card -> true));
    }

    public void upp() {
        this.exhaust = false;
    }
}
