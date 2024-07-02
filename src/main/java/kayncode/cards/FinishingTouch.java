package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static kayncode.KaynMod.makeID;

public class FinishingTouch extends AbstractEasyCard {
    public final static String ID = makeID(FinishingTouch.class.getSimpleName());
    private int originalBaseDamage;

    public FinishingTouch() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 20;
        this.magicNumber = this.baseMagicNumber = 1;
        this.originalBaseDamage = this.baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void applyPowers() {
        int additionalDamage = 0;

        // Check for Strength power and calculate extra damage
        if (AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)) {
            int strengthAmount = AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount;
            additionalDamage += strengthAmount;
        }

        // Check for Vigor power and calculate extra damage
        if (AbstractDungeon.player.hasPower(VigorPower.POWER_ID)) {
            int vigorAmount = AbstractDungeon.player.getPower(VigorPower.POWER_ID).amount;
            additionalDamage += vigorAmount;
        }

        // Apply additional damage twice
        this.baseDamage = this.originalBaseDamage + (additionalDamage * this.magicNumber);
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.baseDamage = this.originalBaseDamage;
        super.applyPowers();
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1); // Upgrade to deal 25 base damage instead of 20
    }
}
