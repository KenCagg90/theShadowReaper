package code.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;

import static code.ModFile.makeID;

public class ComboMastery extends AbstractEasyCard {
    public final static String ID = makeID(ComboMastery.class.getSimpleName());

    public ComboMastery() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true; // Card exhausts after use
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, this.magicNumber));
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    c.setCostForTurn(Math.max(0, c.costForTurn - 1));
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}