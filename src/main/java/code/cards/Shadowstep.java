package code.cards;

import code.relics.Rhaast;
import code.relics.ShadowAssassin;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.localization.CardStrings;

import static code.ModFile.makeID;

public class Shadowstep extends AbstractEasyCard {
    public final static String ID = makeID(Shadowstep.class.getSimpleName());
    private static final String RELIC_A_ID = Rhaast.ID; // Replace with the actual ID of relic A
    private static final String RELIC_B_ID = ShadowAssassin.ID; // Replace with the actual ID of relic B
    // intellij stuff skill, self, uncommon, , , , , ,

    public Shadowstep() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 3;
        this.baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean hasRelicA = p.hasRelic(RELIC_A_ID);
        boolean hasRelicB = p.hasRelic(RELIC_B_ID);
        this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.magicNumber), this.magicNumber));

        if (hasRelicA) {
            this.addToBot(new GainBlockAction(p, p, this.baseBlock));
        } else if (hasRelicB) {
            this.addToBot(new GainEnergyAction(1));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeBlock(2); // Optional: upgrade the block amount if you want
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        updateTitleAndDescription(AbstractDungeon.player);
    }

    private void updateTitleAndDescription(AbstractPlayer p) {
        boolean hasRelicA = p.hasRelic(RELIC_A_ID);
        boolean hasRelicB = p.hasRelic(RELIC_B_ID);

        if (hasRelicA) {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[1];
        } else if (hasRelicB) {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[2];
        } else {
            this.name = cardStrings.NAME;
            this.rawDescription = cardStrings.DESCRIPTION;
        }

        initializeTitle();
        initializeDescription();


    }
}


