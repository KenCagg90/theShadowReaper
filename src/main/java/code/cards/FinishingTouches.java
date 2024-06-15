package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class FinishingTouches extends AbstractEasyCard {
    public final static String ID = makeID(FinishingTouches.class.getSimpleName());

    public FinishingTouches() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 6;
        this.baseMagicNumber = this.magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Deal 6(8) damage twice
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));

        // Calculate the number of Attack cards played this turn
        long attackCardsPlayedThisTurn = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream()
                .filter(card -> card.type == CardType.ATTACK)
                .count();

        // Gain 1 Energy for each Attack played this turn, up to a maximum of 2(3)
        int energyToGain = (int) Math.min(attackCardsPlayedThisTurn, this.upgraded ? 3 : 2);
        for (int i = 0; i < energyToGain; i++) {
            this.addToBot(new GainEnergyAction(1));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().anyMatch(card -> card.type == CardType.ATTACK)
                ? AbstractCard.GOLD_BORDER_GLOW_COLOR
                : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void upp() {
        upgradeDamage(2); // Upgrade to deal 8 damage instead of 6
        upgradeMagicNumber(1);
    }

}