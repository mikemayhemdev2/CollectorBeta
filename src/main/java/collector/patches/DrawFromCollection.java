/*
package collector.patches;

import collector.CollectorChar;
import collector.CollectorCollection;
//import collector.actions.DrawCardFromCollectionAction;
import collector.powers.IncreasedCollectionDrawPower;
import collector.relics.BlockedChakra;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static utilityClasses.Wiz.atb;

public class DrawFromCollection {

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "applyStartOfTurnPostDrawRelics"
    )
    public static class AbstractPlayerApplyStartOfTurnPostDrawRelicsPatch {
        public static void Prefix(AbstractPlayer __instance) {
            if (AbstractDungeon.player.chosenClass.equals(CollectorChar.Enums.THE_COLLECTOR) || !CollectorCollection.collection.isEmpty()) {
                if (AbstractDungeon.player.hasRelic(BlockedChakra.ID)) {
                    AbstractRelic r = AbstractDungeon.player.getRelic(BlockedChakra.ID);
                    if (r.counter == 567){//It will get stuck after 99 turns so people don't try to make it count to 0.
                        r.counter++;
                    }
                    if (r.counter > 0) {
                        r.counter -= 1;
                        r.flash();
                        return;
                    }
                }
                atb(new DrawCardFromCollectionAction());
                if (AbstractDungeon.player.hasPower(IncreasedCollectionDrawPower.POWER_ID)) {
                    for (int i = 0; i < AbstractDungeon.player.getPower(IncreasedCollectionDrawPower.POWER_ID).amount; i++) {
                        atb(new DrawCardFromCollectionAction());
                    }
                }
            }
        }
    }
}

 */
