package kayncode.cards;

import kayncode.actions.ShadowCloneAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;


public class ShadowClone extends AbstractEasyCard {
    public final static String ID = makeID(ShadowClone.class.getSimpleName());

    public ShadowClone() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ShadowCloneAction(this.upgraded));
    }

    @Override
    public void upp() {
        this.upgradeBaseCost(0); // Upgrades the card to cost 0 energy
    }

    @Override
    public AbstractEasyCard makeCopy() {
        return new ShadowClone();
    }
}
