package collector.cards.collectibles;

import collector.cards.OnPyreCard;
import collector.util.CollectorOrangeTextInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;
import sneckomod.SneckoMod;
import utilityClasses.Later.LaterAction;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class LivingWallCard extends AbstractCollectibleCard implements OnPyreCard, CollectorOrangeTextInterface {
    public final static String ID = makeID(LivingWallCard.class.getSimpleName());
    // intellij stuff skil, self, uncommon, , , , , , 

    public LivingWallCard() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        isPyre();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard toGen = AbstractDungeon.returnTrulyRandomCardInCombat();
        toGen.upgrade();
        atb(new LaterAction(()->{
            if (pyredKindling) {
                if (this.upgraded && toGen.cost >= 1) {
                    toGen.cost = 0;
                    toGen.isCostModified = true;
                } else if (toGen.cost >= 0) {
                    toGen.costForTurn = 0;
                    toGen.isCostModifiedForTurn = true;
                }
            }
            makeInHand(toGen);
        }));
    }

    boolean pyredKindling = false;
    @Override
    public void onPyred(AbstractCard card) {
        pyredKindling = card.tags.contains(expansionContentMod.KINDLING);
    }

    public void upp() {
//        upgradeBaseCost(0);
        uDesc();
    }
}