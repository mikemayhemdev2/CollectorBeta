package collector.cards.collectibles;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class CenturionCard extends AbstractCollectibleCard {
    public final static String ID = makeID(CenturionCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , 10, 4, 1, 1

    public CenturionCard() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
        baseMagicNumber = magicNumber = 2;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new MetallicizePower(p, magicNumber));
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}