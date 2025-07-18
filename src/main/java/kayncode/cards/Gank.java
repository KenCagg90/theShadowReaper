package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static kayncode.KaynMod.makeID;

public class Gank extends AbstractEasyCard {
    public final static String ID = makeID(Gank.class.getSimpleName());

    public Gank() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 7;
        this.magicNumber = this.baseMagicNumber = 3;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Deal 7(10) damage
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        // Reduce enemy's strength by 3(4) for this turn
        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));

        // Apply GainStrengthPower only if the monster doesn't have Artifact
                if (!m.hasPower("Artifact")) {
                    addToTop(new ApplyPowerAction(m, p, new GainStrengthPower(m, magicNumber), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
                }
            }



    @Override
    public void upp() {
        upgradeDamage(3); // Upgrade to deal 10 damage instead of 7
        upgradeMagicNumber(1); // Upgrade to reduce strength by 4 instead of 3
    }
}