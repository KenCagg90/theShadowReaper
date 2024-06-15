package code.cards;

import code.powers.UTPower;
import code.relics.Rhaast;
import code.relics.ShadowAssassin;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import static code.ModFile.makeID;

public class UmbralTrespass extends AbstractEasyCard {
    public final static String ID = makeID(UmbralTrespass.class.getSimpleName());
    private static final String RELIC_A_ID = Rhaast.ID;
    private static final String RELIC_B_ID = ShadowAssassin.ID;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public UmbralTrespass() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 20;
        this.magicNumber = this.baseMagicNumber = 1; // Used for Intangible
        this.secondMagic = this.baseSecondMagic = 20; // Used for card text
        this.exhaust = true; // Card exhausts after use
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Gain 1 Intangible
        this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.magicNumber), this.magicNumber));

        // Apply Umbral Trespass Power to the enemy
        boolean hasRhaast = p.hasRelic(RELIC_A_ID);
        boolean hasShadow = p.hasRelic(RELIC_B_ID);
        this.addToBot(new ApplyPowerAction(m, p, new UTPower(m, this.baseDamage, hasRhaast, hasShadow), this.baseDamage));

        if (hasRhaast) {
            addToBot(new SFXAction("UMBRAL_SLAYER"));
        } else if (hasShadow) {
            addToBot(new SFXAction("UMBRAL_ASSASSIN"));
        } else {
            addToBot(new SFXAction("UMBRAL_TRESPASS"));
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        updateTitleAndDescription(AbstractDungeon.player);
    }

    private void updateTitleAndDescription(AbstractPlayer p) {
        this.rawDescription = cardStrings.DESCRIPTION;
        boolean hasRelicA = p.hasRelic(RELIC_A_ID);
        boolean hasRelicB = p.hasRelic(RELIC_B_ID);

        if (hasRelicA) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[1];
        }
        if (hasRelicB) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[2];
        }

        initializeDescription();
    }

    @Override
    public void upp() {
        this.upgradeDamage(8); // Upgrade to increase the base damage by 8
        this.upgradeSecondMagic(8);
        this.exhaust = false; // Remove exhaust
        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.isExhaustiveUpgraded.set(this, true);
    }
}