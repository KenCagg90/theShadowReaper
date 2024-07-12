package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.green.PiercingWail;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import static kayncode.KaynMod.makeID;

public class BladeSpin extends AbstractEasyCard {
    public final static String ID = makeID(BladeSpin.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public BladeSpin() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 5;
        isMultiDamage = true;
        selfRetain = true;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_DAGGER_6"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        allDmg(AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
