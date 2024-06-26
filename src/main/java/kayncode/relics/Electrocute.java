package kayncode.relics;

import kayncode.TheShadowReaper;
import kayncode.vfx.RedLightningEffect;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;

public class Electrocute extends AbstractEasyRelic {
    public static final String ID = makeID("Electrocute");
    private static final int ATTACKS_NEEDED = 4;
    private int attackCounter = 0;
    private AbstractCreature lastTarget;
    private boolean hasTriggeredThisTurn = false;

    public Electrocute() {
        super(ID, RelicTier.COMMON, LandingSound.MAGICAL, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 10 + this.DESCRIPTIONS[1];
    }

    @Override
    public void onEquip() {
        this.counter = -1;
    }

    @Override
    public void atTurnStart() {
        this.counter = 0;
        this.lastTarget = null;
        this.hasTriggeredThisTurn = false;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && !this.hasTriggeredThisTurn) {
            ++this.counter;
            this.lastTarget = action.target;
            if (this.counter % 3 == 0) {
                this.flash();
                this.counter = 0;
                this.hasTriggeredThisTurn = true;
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                if (this.lastTarget != null && !this.lastTarget.isDeadOrEscaped()) {
                    this.addToBot(new DamageAction(this.lastTarget, new DamageInfo(AbstractDungeon.player, 10, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
                    this.addToBot(new VFXAction(new RedLightningEffect(this.lastTarget.drawX, this.lastTarget.drawY), 0.1F));
                    this.addToBot(new SFXAction("ELECTROCUTE_PROC"));
                } else {
                    ArrayList<AbstractMonster> aliveMonsters = new ArrayList<>();
                    for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                        if (!monster.isDeadOrEscaped()) {
                            aliveMonsters.add(monster);
                        }
                    }
                    if (!aliveMonsters.isEmpty()) {
                        AbstractMonster randomMonster = aliveMonsters.get(AbstractDungeon.cardRandomRng.random(aliveMonsters.size() - 1));
                        this.addToBot(new DamageAction(randomMonster, new DamageInfo(AbstractDungeon.player, 10, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
                        this.addToBot(new VFXAction(new RedLightningEffect(randomMonster.drawX, randomMonster.drawY), 0.1F));
                        this.addToBot(new SFXAction("ELECTROCUTE_PROC"));
                    }
                }
            }
        }
    }

    public void onVictory() {
        this.counter = -1;
    }
}
