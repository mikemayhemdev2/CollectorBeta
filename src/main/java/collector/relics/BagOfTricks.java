package collector.relics;

import basemod.abstracts.CustomRelic;
import collector.CollectorCollection;
import collector.CollectorMod;
import collector.actions.DrawCardFromCollectionAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import downfall.util.TextureLoader;

import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.att;

public class BagOfTricks extends CustomRelic {
    public static final String ID = CollectorMod.makeID(BagOfTricks.class.getSimpleName());
    private static final String IMG_PATH = BagOfTricks.class.getSimpleName() + ".png";
    private static final String OUTLINE_IMG_PATH = BagOfTricks.class.getSimpleName() + ".png";

    private static final int EXTRA_CARDS = 2;

    public BagOfTricks() {
        super(ID, TextureLoader.getTexture(CollectorMod.makeRelicPath(IMG_PATH)), TextureLoader.getTexture(CollectorMod.makeRelicOutlinePath(OUTLINE_IMG_PATH)), RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        flash();
        for (int i = 0; i < EXTRA_CARDS; i++) {
            atb(new DrawCardFromCollectionAction());
            if (!CollectorCollection.combatCollection.isEmpty() || AbstractDungeon.player.hasRelic(HolidayCoal.ID)) {
                att(new DrawCardAction(1));
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + EXTRA_CARDS + DESCRIPTIONS[1];
    }
}

