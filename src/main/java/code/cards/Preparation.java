package code.cards;

import code.actions.ApplyRetainToDrawPileAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class Preparation extends AbstractEasyCard {
    public final static String ID = makeID(Preparation.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public Preparation() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, 1));
        this.addToBot(new ApplyRetainToDrawPileAction());
    }

    @Override
    public void upp() {
        this.upgradeBaseCost(0);
    }

    @Override
    public AbstractEasyCard makeCopy() {
        return new Preparation();
    }
}
