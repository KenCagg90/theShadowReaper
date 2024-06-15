package code.cards;

import code.powers.ReapPower;
import code.relics.Rhaast;
import code.relics.ShadowAssassin;
import code.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;



import static code.ModFile.makeID;

public class ReapingSlash extends AbstractEasyCard {
    public final static String ID = makeID(ReapingSlash.class.getSimpleName());
    private static final String RELIC_A_ID = Rhaast.ID; // Replace with the actual ID of relic A
    private static final String RELIC_B_ID = ShadowAssassin.ID; // Replace with the actual ID of relic B
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    public ReapingSlash() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 8;
        baseBlock = 6;
        baseSecondDamage = 3;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean hasRelicA = p.hasRelic(RELIC_A_ID);
        boolean hasRelicB = p.hasRelic(RELIC_B_ID);
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        Wiz.forAllMonstersLiving(monster -> Wiz.applyToEnemy(monster, new ReapPower(monster, this.magicNumber)));
        if (hasRelicA) {
        blck();
        }
        if (hasRelicB) {
            this.addToBot(new LoseHPAction(m, AbstractDungeon.player, baseSecondDamage, AbstractGameAction.AttackEffect.NONE));
        }

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

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
        upgradeBlock(2);
        upgradeSecondDamage(1);
    }
}