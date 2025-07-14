package kayncode.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import kayncode.powers.UTPower;
import kayncode.relics.Rhaast;
import kayncode.relics.ShadowAssassin;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;
import static kayncode.KaynMod.makeImagePath;

public class UmbralTrespass extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(UmbralTrespass.class.getSimpleName());
    private static final String RELIC_A_ID = Rhaast.ID;
    private static final String RELIC_B_ID = ShadowAssassin.ID;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    public UmbralTrespass() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 20;
        this.magicNumber = this.baseMagicNumber = 1; // Used for Intangible
        this.secondMagic = this.baseSecondMagic = 20; // Used for card text
        this.exhaust = true; // Card exhausts after use
        setOrbTexture(makeImagePath("512/energyMix.png"), makeImagePath("1024/energyMix.png"));
        MultiCardPreview.add(this, new UmbralTrespassRhaast(), new UmbralTrespassAssassin());
        MultiCardPreview.horizontalOnly(this);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Gain 1 Intangible
        this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.magicNumber), this.magicNumber));


        // Calculate the damage accounting for Strength
        int strength = p.hasPower(StrengthPower.POWER_ID) ? p.getPower(StrengthPower.POWER_ID).amount : 0;
        int totalDamage = this.baseDamage + strength;
        int totalSecondMagic = this.baseSecondMagic + strength;

        if (p.hasPower(WeakPower.POWER_ID)) {
            totalDamage *= 0.75;
            totalSecondMagic *= 0.75;
        }


// You might need to cast the results back to int if necessary
        int finalDamage = (int) Math.floor(totalDamage);
        int finalSecondMagic = (int) Math.floor(totalSecondMagic);

        // Apply Umbral Trespass Power to the enemy
        boolean hasRhaast = p.hasRelic(RELIC_A_ID);
        boolean hasShadow = p.hasRelic(RELIC_B_ID);
        this.addToBot(new ApplyPowerAction(m, p, new UTPower(m, totalDamage), totalDamage));

        if (hasRhaast) {
            addToBot(new SFXAction("UMBRAL_SLAYER"));
        } else if (hasShadow) {
            addToBot(new SFXAction("UMBRAL_ASSASSIN"));
        } else {
            addToBot(new SFXAction("UMBRAL_TRESPASS"));
        }
    }


    @Override
    public void upp() {
        this.upgradeDamage(8); // Upgrade to increase the base damage by 8
        this.upgradeSecondMagic(8);
        this.exhaust = false; // Remove exhaust
        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.isExhaustiveUpgraded.set(this, true);
        for (AbstractCard c : MultiCardPreview.multiCardPreview.get(this)) {
            c.upgrade();
        }
    }

    @Override
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
        if (AbstractDungeon.player.hasRelic(Rhaast.ID) || AbstractDungeon.player.hasRelic(ShadowAssassin.ID) ) {
            return false;
        }
        else {
            return true;
        }
    }
    @Override
    public boolean canSpawnShop(ArrayList<AbstractCard> currentRewardCards) {
        if (AbstractDungeon.player.hasRelic(Rhaast.ID) || AbstractDungeon.player.hasRelic(ShadowAssassin.ID) ) {
            return false;
        }
        else {
            return true;
        }
    }

//    @Override
//    public AbstractCard makeCopy() {
//        if (AbstractDungeon.player != null) {
//            if (AbstractDungeon.player.hasRelic(Rhaast.POWER_ID)) {
//                return new UmbralTrespassRhaast();
//            } else if (AbstractDungeon.player.hasRelic(ShadowAssassin.POWER_ID)) {
//                return new UmbralTrespassAssassin();
//            }
//        }
//        return new UmbralTrespass();
//    }

}
