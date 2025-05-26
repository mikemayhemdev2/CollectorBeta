package collector.relics;

import basemod.abstracts.CustomRelic;
import collector.CollectorMod;
import collector.actions.DrawCardFromCollectionAction;
import collector.actions.GainReservesAction;
import collector.cards.Ember;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import downfall.util.TextureLoader;

import static utilityClasses.Wiz.*;

public class EmeraldTorch extends CustomRelic {
    public static final String ID = CollectorMod.makeID("EmeraldTorch");
    private static final String IMG_PATH = "EmeraldTorch.png";
    private static final String OUTLINE_IMG_PATH = "EmeraldTorch.png";

    public EmeraldTorch() {
        super
                (
                        ID,
                        TextureLoader.getTexture(CollectorMod.makeRelicPath(IMG_PATH)),
                        TextureLoader.getTexture(CollectorMod.makeRelicOutlinePath(OUTLINE_IMG_PATH)),
                        RelicTier.STARTER,
                        LandingSound.MAGICAL
                );
    }

    @Override
    public void atBattleStart() {
        flash();
        Ember em = new Ember();
        makeInHand(em.makeStatEquivalentCopy(), 1);
        atb(new DrawCardAction(1));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

