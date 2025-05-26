package collector.cards.collectibles;

import collector.powers.collectioncards.LouseCardPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class LouseCard extends AbstractCollectibleCard {
    public final static String ID = makeID(LouseCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , 7, 3, , 

    public LouseCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
        this.baseMagicNumber = magicNumber = 1;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BlurPower(p, magicNumber));
        applyToSelf(new LouseCardPower(block));
    }

    public void upp() {
        upgradeBlock(3);
    }
}