package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class ShadowWaltz extends AbstractEasyCard {
    public final static String ID = makeID(ShadowWaltz.class.getSimpleName());

    public ShadowWaltz() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, 1));
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= 3) {
            this.addToBot(new DrawCardAction(p, this.magicNumber));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
