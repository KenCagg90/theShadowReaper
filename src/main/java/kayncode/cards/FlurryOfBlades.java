package kayncode.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import kayncode.actions.EasyXCostAction;
import kayncode.relics.Rhaast;
import kayncode.relics.ShadowAssassin;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;

public class FlurryOfBlades extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(FlurryOfBlades.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public FlurryOfBlades() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 10;

        MultiCardPreview.add(this, new FlurryOfBladesRhaast(), new FlurryOfBladesAssassin());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                this.addToTop(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

            }
            return true;
        }));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
        return !(AbstractDungeon.player.hasRelic(Rhaast.ID) || AbstractDungeon.player.hasRelic(ShadowAssassin.ID));
    }

    @Override
    public boolean canSpawnShop(ArrayList<AbstractCard> currentRewardCards) {
        return !(AbstractDungeon.player.hasRelic(Rhaast.ID) || AbstractDungeon.player.hasRelic(ShadowAssassin.ID));
    }
}
