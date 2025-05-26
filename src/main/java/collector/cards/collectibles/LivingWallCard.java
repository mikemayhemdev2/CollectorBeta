package collector.cards.collectibles;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class LivingWallCard extends AbstractCollectibleCard {
    public final static String ID = makeID(LivingWallCard.class.getSimpleName());
    // intellij stuff skil, self, uncommon, , , , , , 

    public LivingWallCard() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        isPyre();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard toGen = AbstractDungeon.returnTrulyRandomCardInCombat();
        toGen.upgrade();
        if (this.upgraded && toGen.cost >= 1){
            toGen.cost = 0;
            toGen.isCostModified = true;
        }else if (toGen.cost >= 0){
            toGen.costForTurn = 0;
//            toGen.freeToPlayOnce = true;
            toGen.isCostModifiedForTurn = true;
        }
        makeInHand(toGen);
    }

    public void upp() {
//        upgradeBaseCost(0);
        uDesc();
    }
}