package collector.patches;

import collector.CollectorCollection;
import collector.actions.RemoveCardsFromCollectionAction;
import collector.util.CollectibleRemoveEffect;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;
import com.megacrit.cardcrawl.vfx.campfire.CampfireTokeEffect;
import slimebound.SlimeboundMod;
import utilityClasses.DFL;
import utilityClasses.Later.LaterEffect;
import static collector.CollectorMod.makeID;

@SpirePatch(clz = com.megacrit.cardcrawl.screens.DungeonMapScreen.class, method = "open")
public class RemoveCollectiblesAmbushPatch {
    //Surprise! Card remove time.

    @SpirePostfixPatch
    public static void open(DungeonMapScreen __instance, boolean doScrollingAnimation) {
        CollectorCollection.testSize();//Checks if bag of tricks, if so max is 7, otherwise 5.

        SlimeboundMod.logger.info("Map is opening");
        if (CollectorCollection.collection.size() > CollectorCollection.MaxCollectionSize) {
            SlimeboundMod.logger.info("Passed check, opening remove effect at value of " + (CollectorCollection.collection.size() - CollectorCollection.MaxCollectionSize));
            AbstractDungeon.effectList.add(new CollectibleRemoveEffect(CollectorCollection.collection.size() - CollectorCollection.MaxCollectionSize));
        }
    }

}