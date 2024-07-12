package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static kayncode.KaynMod.makeID;

public class Distract extends AbstractEasyCard {
    public final static String ID = makeID(Distract.class.getSimpleName());

    public Distract() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Apply 2(3) Weak to an enemy
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));

        // Reduce the cost of a random card in your draw pile to 0 until played, but only if its cost is more than 0
        if (!AbstractDungeon.player.drawPile.isEmpty()) {
            ArrayList<AbstractCard> moreThanZeroCost = new ArrayList<>(AbstractDungeon.player.drawPile.group.stream()
                    .filter(card -> card.costForTurn > 0)
                    .collect(Collectors.toList()));

            if (!moreThanZeroCost.isEmpty()) {
                AbstractCard card = moreThanZeroCost.get(AbstractDungeon.cardRandomRng.random(moreThanZeroCost.size() - 1));
                card.freeToPlayOnce = true;
            }
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1); // Upgrade to apply 3 Weak instead of 2
    }
}
