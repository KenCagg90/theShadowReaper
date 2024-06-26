package kayncode.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import kayncode.powers.ReapPower;
import kayncode.relics.Rhaast;
import kayncode.relics.ShadowAssassin;
import kayncode.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import java.util.ArrayList;

import static kayncode.KaynMod.makeID;

public class ReapingSlash extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(ReapingSlash.class.getSimpleName());
    private static final String RELIC_A_ID = Rhaast.ID; // Replace with the actual ID of relic A
    private static final String RELIC_B_ID = ShadowAssassin.ID; // Replace with the actual ID of relic B
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    public ReapingSlash() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 4;
        baseBlock = 6;
        baseSecondDamage = 3;
        MultiCardPreview.add(this, new ReapingSlashRhaast(), new ReapingSlashAssassin());
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
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
        upgradeBlock(2);
        upgradeSecondDamage(1);
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
//            if (AbstractDungeon.player.hasRelic(Rhaast.ID)) {
//                return new ReapingSlashRhaast();
//            } else if (AbstractDungeon.player.hasRelic(ShadowAssassin.ID)) {
//                return new ReapingSlashAssassin();
//            }
//        }
//        return new ReapingSlash();
//    }
}