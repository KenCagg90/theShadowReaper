package kayncode.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.actions.EasyXCostAction;
import kayncode.relics.BaseForm;
import kayncode.relics.ShadowAssassin;
import kayncode.relics.TheDarkinScythe;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;
import static kayncode.KaynMod.makeImagePath;

public class FlurryOfBladesRhaast extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(FlurryOfBladesRhaast.class.getSimpleName());
    private int originalBaseDamage;

    public FlurryOfBladesRhaast() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 10;
        setBackgroundTexture(makeImagePath("512/attackRhaast.png"), makeImagePath("1024/attackRhaast.png"));
        setOrbTexture(makeImagePath("512/energyRhaast.png"), makeImagePath("1024/energyRhaast.png"));
        this.originalBaseDamage = this.baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToTop(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                this.applyPowers();
                this.addToTop(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
            return true;
        }));

    }

    @Override
    public void applyPowers() {
        AbstractPlayer p = AbstractDungeon.player;
        int tempHpBonus = TempHPField.tempHp.get(p);
        int additionalDamage = tempHpBonus / 4 ;
        this.baseDamage = this.originalBaseDamage + additionalDamage;
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.baseDamage = this.originalBaseDamage;
        this.applyPowers();
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        this.originalBaseDamage += 2;
    }

    @Override
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
        if (AbstractDungeon.player.hasRelic(ShadowAssassin.ID) || AbstractDungeon.player.hasRelic(BaseForm.ID) || AbstractDungeon.player.hasRelic(TheDarkinScythe.ID) ) {
            return false;
        }
        else {
            return true;
        }
    }
    @Override
    public boolean canSpawnShop(ArrayList<AbstractCard> currentRewardCards) {
        if (AbstractDungeon.player.hasRelic(ShadowAssassin.ID) || AbstractDungeon.player.hasRelic(BaseForm.ID) || AbstractDungeon.player.hasRelic(TheDarkinScythe.ID) ) {
            return false;
        }
        else {
            return true;
        }
    }

}
