package kayncode.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import kayncode.powers.ReapPower;
import kayncode.util.Wiz;

@SpirePatch2(clz = EnergyPanel.class, method = "loseEnergy")
public class ReapPatchlose {
    @SpirePostfixPatch()
    public static void PostFix() {
        AbstractPower reap = Wiz.adp().getPower(ReapPower.POWER_ID);

        if (reap != null) {
            reap.updateDescription();
        }
    }
}