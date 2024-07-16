package kayncode.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import kayncode.powers.ReapPower;
import kayncode.relics.special.Rhaast;
import kayncode.relics.special.ShadowAssassin;
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
import static kayncode.KaynMod.makeImagePath;

public class ReapingSlash extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(ReapingSlash.class.getSimpleName());
    private static final String RELIC_A_ID = Rhaast.ID; // Replace with the actual ID of relic A
    private static final String RELIC_B_ID = ShadowAssassin.ID; // Replace with the actual ID of relic B
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    public ReapingSlash() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 5;
        baseBlock = 6;
        baseSecondDamage = 3;
        setOrbTexture(makeImagePath("512/energyMix.png"), makeImagePath("1024/energyMix.png"));
        MultiCardPreview.add(this, new ReapingSlashRhaast(), new ReapingSlashAssassin());
        MultiCardPreview.horizontalOnly(this);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        this.addToBot(new SFXAction("REAPING_SLASH"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        Wiz.forAllMonstersLiving(monster -> Wiz.applyToEnemy(monster, new ReapPower(monster, this.magicNumber)));
    }


    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
        upgradeBlock(2);
        upgradeSecondDamage(1);
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
//            if (AbstractDungeon.player.hasRelic(Rhaast.ID)) {
//                return new ReapingSlashRhaast();
//            } else if (AbstractDungeon.player.hasRelic(ShadowAssassin.ID)) {
//                return new ReapingSlashAssassin();
//            }
//        }
//        return new ReapingSlash();
//    }
}