package collector.relics;

import basemod.AutoAdd;
import basemod.abstracts.CustomRelic;
import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import collector.CollectorCollection;
import collector.CollectorMod;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import downfall.downfallMod;
import downfall.util.TextureLoader;

@NoPools @NoCompendium @Deprecated @AutoAdd.Ignore
public class TotallyNotRoyalGoblet extends CustomRelic {
    public static final String ID = CollectorMod.makeID(TotallyNotRoyalGoblet.class.getSimpleName());
    private static final String IMG_PATH = TotallyNotRoyalGoblet.class.getSimpleName() + ".png";
    private static final String OUTLINE_IMG_PATH = TotallyNotRoyalGoblet.class.getSimpleName() + ".png";

    public TotallyNotRoyalGoblet() {
        super(ID, TextureLoader.getTexture(CollectorMod.makeRelicPath(IMG_PATH)), TextureLoader.getTexture(CollectorMod.makeRelicOutlinePath(OUTLINE_IMG_PATH)), RelicTier.DEPRECATED, AbstractRelic.LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster++;
        AbstractDungeon.player.masterHandSize--;
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
        AbstractDungeon.player.masterHandSize++;
    }



    @Override
    public boolean canSpawn() {
        return (!CollectorCollection.collection.isEmpty() && !downfallMod.makeCollectorWorse);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}