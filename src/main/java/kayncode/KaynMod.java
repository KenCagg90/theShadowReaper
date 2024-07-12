package kayncode;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.DynamicVariable;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import kayncode.cards.AbstractEasyCard;
import kayncode.cards.cardvars.AbstractEasyDynamicVariable;
import kayncode.potions.AbstractEasyPotion;
import kayncode.relics.AbstractEasyRelic;
import kayncode.relics.special.Rhaast;
import kayncode.relics.special.ShadowAssassin;
import kayncode.util.ProAudio;
import java.nio.charset.StandardCharsets;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class KaynMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        AddAudioSubscriber,
        StartGameSubscriber {

    public static final String modID = "theshadowreaper";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Color characterColor = new Color (0, 0, 0, 1); // This should be changed eventually

    public static final String SHOULDERKAYN = makeCharacterPath("mainChar/shoulderkayn.png");
    public static final String SHOULDERKAYN2 = makeCharacterPath("mainChar/shoulderkayn2.png");
    public static final String CORPSEKAYN = makeCharacterPath("mainChar/corpse.png");
    public static final String SHOULDERRHAAST = makeCharacterPath("mainChar/shoulderrhaast.png");
    public static final String SHOULDERRHAAST2 = makeCharacterPath("mainChar/shoulderrhaast2.png");
    public static final String CORPSERHAAST = makeCharacterPath("mainChar/corpseRhaast.png");
    public static final String SHOULDERASSASSIN = makeCharacterPath("mainChar/shoulderassassin.png");
    public static final String SHOULDERASSASSIN2 = makeCharacterPath("mainChar/shoulderassassin2.png");
    public static final String CORPSEASSASSIN = makeCharacterPath("mainChar/corpseAssassin.png");
    public static final String KAYN_ATLAS_PATH = makeCharacterPath("mainChar/Kayn_spine.atlas");
    public static final String KAYN_JSON_PATH = makeCharacterPath("mainChar/Kayn_spine.json");
    public static final String RHAAST_ATLAS_PATH = makeCharacterPath("mainChar/Rhaast_spine.atlas");
    public static final String RHAAST_JSON_PATH = makeCharacterPath("mainChar/Rhaast_spine.json");
    public static final String ASSASSIN_ATLAS_PATH = makeCharacterPath("mainChar/Assassin_spine.atlas");
    public static final String ASSASSIN_JSON_PATH = makeCharacterPath("mainChar/Assassin_spine.json");
    private static final String ATTACK_S_ART = makeImagePath("512/attack.png");
    private static final String SKILL_S_ART = makeImagePath("512/skill.png");
    private static final String POWER_S_ART = makeImagePath("512/power.png");
    private static final String CARD_ENERGY_S = makeImagePath("512/energy.png");
    private static final String TEXT_ENERGY = makeImagePath("512/text_energy.png");
    private static final String ATTACK_L_ART = makeImagePath("1024/attack.png");
    private static final String SKILL_L_ART = makeImagePath("1024/skill.png");
    private static final String POWER_L_ART = makeImagePath("1024/power.png");
    private static final String CARD_ENERGY_L = makeImagePath("1024/energy.png");
    private static final String CHARSELECT_BUTTON = makeImagePath("charSelect/charButton.png");
    private static final String CHARSELECT_PORTRAIT = makeImagePath("charSelect/charBG.png");


    public static Settings.GameLanguage[] SupportedLanguages = {
            Settings.GameLanguage.ENG,
            Settings.GameLanguage.ZHS,
            Settings.GameLanguage.SPA
    };

    private String getLangString() {
        for (Settings.GameLanguage lang : SupportedLanguages) {
            if (lang.equals(Settings.language)) {
                return Settings.language.name().toLowerCase();
            }
        }
        return "eng";
    }

    public KaynMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(TheShadowReaper.Enums.SHADOWREAPER_COLOR, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }


    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCharacterPath(String resourcePath)
    {
        return modID + "Resources/images/char/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        KaynMod thismod = new KaynMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheShadowReaper(TheShadowReaper.characterStrings.NAMES[1], TheShadowReaper.Enums.THE_SHADOWREAPER),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheShadowReaper.Enums.THE_SHADOWREAPER);

        new AutoAdd(modID)
                .packageFilter(AbstractEasyPotion.class)
                .any(AbstractEasyPotion.class, (info, potion) -> {
                    if (potion.pool == null)
                        BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID);
                    else
                        BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID, potion.pool);
                });
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyDynamicVariable.class)
                .any(DynamicVariable.class, (info, var) ->
                        BaseMod.addDynamicVariable(var));
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/" + getLangString() + "/Cardstrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/" + getLangString() + "/Relicstrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/" + getLangString() + "/Charstrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/" + getLangString() + "/Powerstrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, modID + "Resources/localization/" + getLangString() + "/UIstrings.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, modID + "Resources/localization/" + getLangString() + "/Orbstrings.json");
        BaseMod.loadCustomStringsFile(StanceStrings.class, modID + "Resources/localization/" + getLangString() + "/Stancestrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, modID + "Resources/localization/" + getLangString() + "/Potionstrings.json");
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio("KAYN_SELECT", "theshadowreaperResources/audio/KaynSelect.wav");
        BaseMod.addAudio("UMBRAL_TRESPASS", "theshadowreaperResources/audio/UmbralTrespass.wav");
        BaseMod.addAudio("UMBRAL_ASSASSIN", "theshadowreaperResources/audio/UmbralAssassin.wav");
        BaseMod.addAudio("UMBRAL_SLAYER", "theshadowreaperResources/audio/UmbralSlayer.wav");
        BaseMod.addAudio("REAP", "theshadowreaperResources/audio/Reap.wav");
        BaseMod.addAudio("RHAAST_TRANSFORM", "theshadowreaperResources/audio/DarkinTransformation.wav");
        BaseMod.addAudio("ASSASSIN_TRANSFORM", "theshadowreaperResources/audio/AssassinTransformation.wav");
        BaseMod.addAudio("RED_TRANSFORM_READY", "theshadowreaperResources/audio/RedTransformReady.wav");
        BaseMod.addAudio("BLUE_TRANSFORM_READY", "theshadowreaperResources/audio/BlueTransformReady.wav");
        BaseMod.addAudio("ELECTROCUTE_PROC", "theshadowreaperResources/audio/ElectrocuteRelic.wav");
        BaseMod.addAudio("COLLECTOR", "theshadowreaperResources/audio/Collector.wav");
        BaseMod.addAudio("AFTERSHOCK", "theshadowreaperResources/audio/Aftershock.wav");
        BaseMod.addAudio("REAPING_SLASH", "theshadowreaperResources/audio/ReapingSlash.wav");
        for (ProAudio a : ProAudio.values())
            BaseMod.addAudio(makeID(a.name()), makePath("audio/" + a.name().toLowerCase() + ".ogg"));
    }



    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/" + getLangString() + "/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveStartGame() {
        applyTransformation();
    }

    private void applyTransformation() {
        if (AbstractDungeon.player instanceof TheShadowReaper) {
            TheShadowReaper player = (TheShadowReaper) AbstractDungeon.player;

            if (player.hasRelic(Rhaast.ID) && !player.isTransformedToRhaast()) {
                player.transformToRhaast();
            } else if (player.hasRelic(ShadowAssassin.ID) && !player.isTransformedToAssassin()) {
                player.transformToAssassin();
            }
        }
    }
}
