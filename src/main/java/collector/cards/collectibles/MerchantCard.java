package collector.cards.collectibles;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class MerchantCard extends AbstractCollectibleCard {
    public final static String ID = makeID(MerchantCard.class.getSimpleName());
    // intellij stuff skill, self, rare, , , , , 4, 2

    public MerchantCard() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            AbstractCard toGen = AbstractDungeon.returnTrulyRandomColorlessCardInCombat().makeCopy();
            toGen.costForTurn = 0;
            makeInHand(toGen);
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}