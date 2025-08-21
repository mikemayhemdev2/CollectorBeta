package collector.patches.CollectiblesPatches;

import collector.CollectorChar;
import collector.CollectorCollection;
import collector.CollectorMod;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;

@SpirePatch(
        clz = AbstractMonster.class,
        method = "die",
        paramtypez = {
                boolean.class
        }
)
public class AddCollectibleRewardsPatch {
    public static void Postfix(AbstractMonster __instance, boolean triggerRelics) {
        if (triggerRelics) {
            if (AbstractDungeon.player.chosenClass.equals(CollectorChar.Enums.THE_COLLECTOR) || !CollectorCollection.collection.isEmpty()) {
                if (!(__instance instanceof SlimeBoss)) {//Slime boss has special handling rules.
                    CollectorCollection.collect(__instance);
                }
                if (CollectorMod.slimboInRoom){//Slime boss patch to stop feels-bad.
                    CollectorMod.slimboInRoom = false;
                    CollectorCollection.collect(__instance);
                }
            }
        }
    }
}
