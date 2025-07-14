package kayncode.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class UnendingSlaughter extends AbstractEasyCard {
    public final static String ID = makeID(UnendingSlaughter.class.getSimpleName());

    public UnendingSlaughter() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 10;
        this.baseMagicNumber = this.magicNumber = 3;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Deal 6(8) damage twice
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        // Calculate the number of Attack cards played this turn before this card
        long attackCardsPlayedThisTurn = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream()
                .filter(card -> card.type == CardType.ATTACK && card != this)
                .count();

        // Gain 1 Energy for each Attack played this turn, up to a maximum of 2(3)
        int energyToGain = (int) Math.min(attackCardsPlayedThisTurn, this.magicNumber);
        for (int i = 0; i < energyToGain; i++) {
            this.addToBot(new GainEnergyAction(1));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        long attackCardsPlayedThisTurn = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream()
                .filter(card -> card.type == CardType.ATTACK && card != this)
                .count();

        this.glowColor = attackCardsPlayedThisTurn >= 3
                ? AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy()
                : AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    @Override
    public void applyPowers() {
        long attackCardsPlayedThisTurn = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream()
                .filter(card -> card.type == CardType.ATTACK && card != this)
                .count();

        super.applyPowers();
        long count = attackCardsPlayedThisTurn;
        this.rawDescription = cardStrings.DESCRIPTION;
        if (count == 1) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count + cardStrings.EXTENDED_DESCRIPTION[1];
        }
        if (count > 1){
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count + cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    @Override
    public void upp() {
        upgradeDamage(2); // Upgrade to deal 8 damage instead of 6
        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
        this.exhaust = false;
    }
}
