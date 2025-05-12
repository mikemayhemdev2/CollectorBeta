package collector.cards.collectibles;

import collector.powers.collectioncards.WomanInBlueCardPower;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class WomanInBlueCard extends AbstractCollectibleCard {
    public final static String ID = makeID(WomanInBlueCard.class.getSimpleName());
    // intellij stuff power, self, uncommon, , , , , , 

    public WomanInBlueCard() {
        super(ID, 1, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
        tags.add(CardTags.HEALING);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ObtainPotionAction(
                AbstractDungeon.returnRandomPotion(
                        upgraded ? AbstractPotion.PotionRarity.UNCOMMON : AbstractPotion.PotionRarity.COMMON
                        ,true)
        ));
        /*
        if (!upgraded) {
           applyToSelf(new WomanInBlueCardPower());
        }
         */
    }

    public void upp() {
//        upgradeBaseCost(0);
        uDesc();
    }
}