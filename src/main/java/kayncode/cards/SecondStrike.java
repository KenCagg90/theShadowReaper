package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static kayncode.KaynMod.makeID;

public class SecondStrike extends AbstractEasyCard {
    public final static String ID = makeID(SecondStrike.class.getSimpleName());

    public SecondStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 11;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        boolean costReductionConditionMet = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream()
                .anyMatch(card -> (card.costForTurn > 1) || (card.costForTurn == -1 && card.energyOnUse > 1));

        if (costReductionConditionMet) {
            this.setCostForTurn(0);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream()
                .anyMatch(card -> (card.costForTurn > 1) || (card.costForTurn == -1 && card.energyOnUse > 1))
                ? AbstractCard.GOLD_BORDER_GLOW_COLOR
                : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void upp() {
        upgradeDamage(4); // Upgrade to deal 15 damage instead of 11
    }
}
