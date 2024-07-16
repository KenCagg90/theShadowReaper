package kayncode.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.relics.special.Rhaast;
import kayncode.relics.special.ShadowAssassin;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;
import static kayncode.KaynMod.makeImagePath;

public class FrenziedStrike extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(FrenziedStrike.class.getSimpleName());

    public FrenziedStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 3;
        setOrbTexture(makeImagePath("512/energyMix.png"), makeImagePath("1024/energyMix.png"));
        MultiCardPreview.add(this, new FrenziedStrikeRhaast(), new FrenziedStrikeAssassin());
        MultiCardPreview.horizontalOnly(this);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            this.addToBot(new DamageRandomEnemyAction(new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    public void upp() {
        upgradeBaseCost(1);
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
}
