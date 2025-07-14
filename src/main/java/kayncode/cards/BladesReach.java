package kayncode.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import kayncode.relics.Rhaast;
import kayncode.relics.ShadowAssassin;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;
import static kayncode.KaynMod.makeImagePath;

public class BladesReach extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(BladesReach.class.getSimpleName());
    private static final String RELIC_A_ID = Rhaast.ID; // Replace with the actual POWER_ID of relic A
    private static final String RELIC_B_ID = ShadowAssassin.ID; // Replace with the actual POWER_ID of relic B
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public BladesReach() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 7;
        this.baseMagicNumber = this.magicNumber = 1; // Used for Weak/Vulnerable
        setOrbTexture(makeImagePath("512/energyMix.png"), makeImagePath("1024/energyMix.png"));
        MultiCardPreview.add(this, new BladesReachRhaast(), new BladesReachAssassin());
        MultiCardPreview.horizontalOnly(this);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MonsterGroup monsters = AbstractDungeon.getMonsters();
        int count = 0;

        for (AbstractMonster monster : monsters.monsters) {
            if (monster.isDeadOrEscaped()) continue;
            this.addToBot(new DamageAction(monster, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            count++;
            if (count >= 2) break; // Only attack the first two alive monsters
        }

    }

    @Override
    public void upp() {
        upgradeDamage(3); // Upgrade to deal 12 damage instead of 8
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
//                return new BladesReachRhaast();
//            } else if (AbstractDungeon.player.hasRelic(ShadowAssassin.POWER_ID)) {
//                return new BladesReachAssassin();
//            }
//        }
//        return new BladesReach();
//    }
}
