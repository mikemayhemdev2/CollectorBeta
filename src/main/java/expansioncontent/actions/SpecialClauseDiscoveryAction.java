package expansioncontent.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import utilityClasses.DFL;
import java.util.ArrayList;
import static com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;

public class SpecialClauseDiscoveryAction extends AbstractGameAction {
    private static final float DURATION = ACTION_DUR_FAST;
    private boolean generateCards;
    private ArrayList<AbstractCard> cardsFinal;
    private final Random rngQue;
    private final int input;
    private final AbstractCard.CardType typeOfCardToMake;
    private final boolean restricted;
    private boolean retrieveCard = false;
    private final boolean discounting;

    public SpecialClauseDiscoveryAction(ArrayList<AbstractCard> cards, boolean discount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
        cardsFinal = cards;
        generateCards = false;
        rngQue = null;
        input = 0;
        typeOfCardToMake = null;
        restricted = true;
        discounting = discount;
    }

    public SpecialClauseDiscoveryAction(AbstractCard.CardType type, Random rng, int quantity, boolean limit, boolean discount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
        generateCards = true;
        rngQue = rng;
        input = quantity;
        typeOfCardToMake = type;
        restricted = limit;
        discounting = discount;
    }



    @Override
    public void update() {
        if (generateCards){
            cardsFinal = DFL.generateCardChoicesBetter(typeOfCardToMake, input, rngQue, restricted);
            generateCards = false;
        }

        if (this.duration == ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(cardsFinal, CardRewardScreen.TEXT[1], true);
            tickDuration();
            return;
        }

        if (!this.retrieveCard) {
            if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
                    disCard.upgrade();
                }
                if (discounting) {
                    disCard.setCostForTurn(0);
                }
                disCard.current_x = -1000.0F * Settings.xScale;
                if (this.amount == 1) {
                    if (AbstractDungeon.player.hand.size() < 10) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    } else {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    }
                    //disCard2 = null;
                } else if (AbstractDungeon.player.hand.size() + this.amount <= 10) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));

                } else if (AbstractDungeon.player.hand.size() == 9) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                } else {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                }
                AbstractDungeon.cardRewardScreen.discoveryCard = null;
            }
            this.retrieveCard = true;
        }
        tickDuration();
    }

}
