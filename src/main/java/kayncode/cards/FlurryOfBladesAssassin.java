package kayncode.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import kayncode.actions.EasyXCostAction;
import kayncode.relics.BaseForm;
import kayncode.relics.Rhaast;
import kayncode.relics.TheDarkinScythe;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;
import static kayncode.KaynMod.makeImagePath;

public class FlurryOfBladesAssassin extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(FlurryOfBladesAssassin.class.getSimpleName());
    private int originalBaseDamage;

    public FlurryOfBladesAssassin() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 10;
        this.originalBaseDamage = this.baseDamage;
        setBackgroundTexture(makeImagePath("512/attackAssassin.png"), makeImagePath("1024/attackAssassin.png"));
        setOrbTexture(makeImagePath("512/energyAssassin.png"), makeImagePath("1024/energyAssassin.png"));
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
        int energyAmount = EnergyPanel.totalCount;
        this.baseDamage = this.originalBaseDamage + (energyAmount * 2);
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
        return !AbstractDungeon.player.hasRelic(Rhaast.ID) && !AbstractDungeon.player.hasRelic(BaseForm.ID) && !AbstractDungeon.player.hasRelic(TheDarkinScythe.ID);
    }

    @Override
    public boolean canSpawnShop(ArrayList<AbstractCard> currentRewardCards) {
        return !AbstractDungeon.player.hasRelic(Rhaast.ID) && !AbstractDungeon.player.hasRelic(BaseForm.ID) && !AbstractDungeon.player.hasRelic(TheDarkinScythe.ID);
    }
}
