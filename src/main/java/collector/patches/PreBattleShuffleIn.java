package collector.patches;

import basemod.helpers.CardModifierManager;
import collector.CollectorChar;
import collector.CollectorCollection;
import collector.cardmods.ActuallyCollectedCardMod;
import collector.powers.CollectDraw;
import collector.relics.BottledCollectible;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import downfall.downfallMod;
import utilityClasses.DFL;
import utilityClasses.Later.LaterAction;

@SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfCombatPreDrawLogic")
public class PreBattleShuffleIn {

    @SpireInsertPatch
    public static void Prefix(AbstractPlayer __instance) {

        if (AbstractDungeon.player.chosenClass.equals(CollectorChar.Enums.THE_COLLECTOR) && !CollectorCollection.collection.group.isEmpty()) {
            for (AbstractCard collectible : CollectorCollection.collection.group) {
                if (!CardModifierManager.hasModifier(collectible, ActuallyCollectedCardMod.ID)) {
                    CardModifierManager.addModifier(collectible, new ActuallyCollectedCardMod());
                }
                DFL.pl().drawPile.addToRandomSpot(collectible.makeSameInstanceOf());
            }

            //Dealing with innate
            DFL.atb(new LaterAction(() -> {
                for (AbstractCard inC : DFL.pl().drawPile.group) {
                    if (inC.isInnate || inC.inBottleFlame || inC.inBottleLightning || inC.inBottleTornado) {
                        DFL.att(new LaterAction(() -> {
                            DFL.pl().drawPile.removeCard(inC);
                            DFL.pl().drawPile.addToTop(inC);//Add innate cards to top.
                        }));
                    }
                    if (DFL.pl().hasRelic(BottledCollectible.ID)) {
                        AbstractCard tar = CollectorCollection.combatCollection.getTopCard();
                        if (inC.uuid == tar.uuid) {
                            DFL.att(new LaterAction(() -> {
                                DFL.pl().drawPile.removeCard(inC);
                                DFL.pl().drawPile.addToTop(inC);//Bottled brain.
                            }));
                        }
                    }
                }
            }));

            if (!downfallMod.makeCollectorWorse) {
                CollectDraw popme = new CollectDraw(CollectorCollection.collection.size());
                DFL.att(new ApplyPowerAction(__instance, __instance, popme));
                DFL.atb(new LaterAction(popme::atStartOfTurn));
            }
        }
    }

}