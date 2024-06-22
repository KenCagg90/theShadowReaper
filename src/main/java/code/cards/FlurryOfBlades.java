package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static code.ModFile.makeID;

public class FlurryOfBlades extends AbstractEasyCard {
    public final static String ID = makeID(FlurryOfBlades.class.getSimpleName());

    public FlurryOfBlades() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = EnergyPanel.totalCount;

        for (int i = 0; i < effect; i++) {
            this.addToBot(new DamageRandomEnemyAction(new DamageInfo(p, effect, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }

        this.addToBot(new LoseEnergyAction(EnergyPanel.totalCount));
    }

    @Override
    public void upp() {
        upgradeDamage(1);
    }
}
