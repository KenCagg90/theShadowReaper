package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static kayncode.KaynMod.makeID;

public class EternalCarnage extends AbstractEasyCard {
    public final static String ID = makeID(EternalCarnage.class.getSimpleName());

    public EternalCarnage() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 12;
        this.exhaust = false;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
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
        int damageToDeal = this.baseDamage + additionalDamage;

        // Check if the target is below 40% health and double the damage if true
        if (m != null && m.currentHealth <= m.maxHealth * 0.4) {
            damageToDeal *= 2;
        }

        // Perform the damage action
        DamageInfo info = new DamageInfo(p, damageToDeal, this.damageTypeForTurn);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, info, AbstractGameAction.AttackEffect.SLASH_HEAVY));

        // Check if the attack kills the target
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                if (m != null && (m.isDying || m.currentHealth <= 0) && !m.halfDead && !m.hasPower("Minion")) {
                    // Create an ethereal, 0-cost copy of the card and add it to the player's hand
                    AbstractCard copy = EternalCarnage.this.makeStatEquivalentCopy();
                    copy.cost = 0;
                    copy.costForTurn = 0;
                    copy.isEthereal = true;
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(copy, 1));
                }
                this.isDone = true;
            }
        });
    }


    @Override
    public void upp() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4); // Increases base damage to 16
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        boolean enemyLowHealth = false;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped() && mo.currentHealth <= mo.maxHealth * 0.40 && !mo.hasPower("Minion")) {
                enemyLowHealth = true;
                break;
            }
        }
        this.glowColor = enemyLowHealth ? AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy() : AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }


        @Override
    public AbstractCard makeCopy() {
        return new EternalCarnage();
    }
}
