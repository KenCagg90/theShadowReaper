package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.localization.CardStrings;

import static kayncode.KaynMod.makeID;

public class AdaptiveStrategy extends AbstractEasyCard {
    public final static String ID = makeID(AdaptiveStrategy.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public AdaptiveStrategy() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 10;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard lastCard = null;
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() > 0) {
            lastCard = AbstractDungeon.actionManager.cardsPlayedThisTurn.get(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 2);
        }

        if (lastCard != null) {
            switch (lastCard.type) {
                case SKILL:
                    this.addToBot(new DrawCardAction(p, this.magicNumber));
                    break;
                case ATTACK:
                    this.addToBot(new GainEnergyAction(this.magicNumber));
                    break;
                case POWER:
                    this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                    break;
            }
        } else {
            return;
        }


    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() < 1) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return super.canUse(p, m);
    }

    @Override
    public void upp() {
        this.upgradeDamage(2);
        this.upgradeMagicNumber(1);
        this.initializeDescription();
    }
}
