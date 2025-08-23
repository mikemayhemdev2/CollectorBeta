package collector.relics;

import basemod.abstracts.CustomRelic;
import collector.CollectorMod;
import collector.actions.DrawCardFromCollectionAction;
import collector.actions.GainReservesAction;
import collector.cards.Ember;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import downfall.util.TextureLoader;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static utilityClasses.Wiz.*;

public class EmeraldTorch extends CustomRelic {
    public static final String ID = CollectorMod.makeID("EmeraldTorch");
    private static final String IMG_PATH = "EmeraldTorch.png";
    private static final String OUTLINE_IMG_PATH = "EmeraldTorch.png";

    public EmeraldTorch() {
        super(ID, TextureLoader.getTexture(CollectorMod.makeRelicPath(IMG_PATH)), TextureLoader.getTexture(CollectorMod.makeRelicOutlinePath(OUTLINE_IMG_PATH)), RelicTier.STARTER, LandingSound.MAGICAL);
        this.counter = -1;
    }

    @Override
    public void atBattleStart() {

        this.counter = 3;
        this.grayscale = false;
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        if (counter > 0){
            flash();
            Wiz.atb(new DrawCardAction(1));
            counter--;
            if (counter == 0){
                this.grayscale = true;
            }
        }
    }

    



    @Override
    public void onVictory(){
        this.counter = -1;
        this.grayscale = false;
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

