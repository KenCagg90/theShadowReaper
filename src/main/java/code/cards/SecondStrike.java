package code.cards;

import code.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class SecondStrike extends AbstractEasyCard {
    public final static String ID = makeID(SecondStrike.class.getSimpleName());

    public SecondStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 11;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Deal 11(15) damage
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        // Check if a card costing more than 1 energy has been played this turn
        boolean costReductionConditionMet = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream()
                .anyMatch(card -> card.costForTurn > 0);
        // Adjust the cost of the card based on the condition
        if (costReductionConditionMet) {
            this.setCostForTurn(0);
        } else {
            this.setCostForTurn(1);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().anyMatch(card -> card.costForTurn > 1)
                ? AbstractCard.GOLD_BORDER_GLOW_COLOR
                : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void upp() {
        upgradeDamage(4); // Upgrade to deal 15 damage instead of 11
    }

}