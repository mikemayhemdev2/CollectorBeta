package collector.patches;

import collector.CollectorCollection;
import collector.actions.RemoveCardsFromCollectionAction;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;
import utilityClasses.DFL;
import utilityClasses.Later.LaterEffect;
import static collector.CollectorMod.makeID;

@SpirePatch(clz = com.megacrit.cardcrawl.screens.DungeonMapScreen.class, method = "open")
public class RemoveCollectiblesAmbushPatch {
    //Surprise! Card remove time.

    @SpirePostfixPatch
    public static void open(DungeonMapScreen __instance, boolean doScrollingAnimation) {
        CollectorCollection.testSize();//Checks if bag of tricks, if so max is 7, otherwise 5.
        DFL.atl(new LaterEffect(()->{
            if (CollectorCollection.collection.size() > CollectorCollection.MaxCollectionSize) {
                new RemoveCardsFromCollectionAction(CollectorCollection.collection.size() - CollectorCollection.MaxCollectionSize);
            }
        }));
    }

}