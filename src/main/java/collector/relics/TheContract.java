package collector.relics;

import basemod.abstracts.CustomRelic;
import collector.CollectorMod;
import collector.util.EssenceSystem;
import downfall.util.TextureLoader;

public class TheContract extends CustomRelic {
    public static final String ID = CollectorMod.makeID(TheContract.class.getSimpleName());
    private static final String IMG_PATH = TheContract.class.getSimpleName() + ".png";
    private static final String OUTLINE_IMG_PATH = TheContract.class.getSimpleName() + ".png";

    public TheContract() {
        super(ID, TextureLoader.getTexture(CollectorMod.makeRelicPath(IMG_PATH)), TextureLoader.getTexture(CollectorMod.makeRelicOutlinePath(OUTLINE_IMG_PATH)), RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() {
        EssenceSystem.changeEssence(10);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

