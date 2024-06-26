package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.badlogic.gdx.graphics.Color;

import static kayncode.KaynMod.makeID;

public class Overpower extends AbstractEasyCard {
    public final static String ID = makeID(Overpower.class.getSimpleName());
    private static final Color GOLD_BORDER_GLOW_COLOR = CardHelper.getColor(255.0f, 215.0f, 0.0f); // Gold color

    public Overpower() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseBlock = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Deal 8 damage
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        
        // Check if a card costing 2 or more was played this turn
        boolean playedCostlyCard = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().anyMatch(card -> card.costForTurn >= 2);
        
        // Gain 8 Block if a card costing 2 or more was played
        if (playedCostlyCard) {
            this.addToBot(new GainBlockAction(p, p, this.block));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        // Check if a card costing 2 or more was played this turn
        boolean playedCostlyCard = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().anyMatch(card -> card.costForTurn >= 2);
        
        if (playedCostlyCard) {
            this.glowColor = GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        this.upgradeDamage(3); // Upgrade to increase the base damage by 3
        this.upgradeBlock(3);  // Upgrade to increase the base block by 3
    }
}
