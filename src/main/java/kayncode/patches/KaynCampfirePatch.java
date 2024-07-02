package kayncode.patches;

import kayncode.relics.BaseForm;
import kayncode.relics.TheDarkinScythe;
import kayncode.ui.TransformRedOption;
import kayncode.ui.TransformBlueOption;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import javassist.CtBehavior;

import java.util.ArrayList;

public class KaynCampfirePatch {
	@SpirePatch(clz = CampfireUI.class, method = "initializeButtons")
	public static class AddTransformOptions {
		@SpireInsertPatch(locator = Locator.class)
		public static void patch(CampfireUI __instance, ArrayList<AbstractCampfireOption> ___buttons) {
			if (AbstractDungeon.player.hasRelic(TheDarkinScythe.ID)) {
					___buttons.add(new TransformRedOption());
					___buttons.add(new TransformBlueOption());

			}
		}
	}

	public static class Locator extends SpireInsertLocator {
		@Override
		public int[] Locate(CtBehavior ctBehavior) throws Exception {
			Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
			return LineFinder.findInOrder(ctBehavior, finalMatcher);
		}
	}
}
