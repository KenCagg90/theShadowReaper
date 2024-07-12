package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class DarkPact extends AbstractEasyCard {
    public final static String ID = makeID(DarkPact.class.getSimpleName());

    public DarkPact() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 3;
        baseSecondMagic = secondMagic = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new LoseHPAction(p, p, this.magicNumber));
        for (int i = 0; i < secondMagic; i++) {
            AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
            this.addToBot(new MakeTempCardInHandAction(card, 1));
        }
    }

    public void upp() {
        upgradeMagicNumber(-1); // Upgrade to lose 2 HP instead of 3
    }
}
