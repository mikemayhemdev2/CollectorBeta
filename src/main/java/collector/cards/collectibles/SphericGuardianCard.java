package collector.cards.collectibles;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import sneckomod.SneckoMod;
import static collector.CollectorMod.makeID;

public class SphericGuardianCard extends AbstractCollectibleCard {
    public final static String ID = makeID(SphericGuardianCard.class.getSimpleName());
    // intellij stuff power, self, common, , , , , , 

    public SphericGuardianCard() {
        super(ID, 2, CardType.POWER, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        baseSecondMagic = secondMagic = 4;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BlurPower(p, this.magicNumber));
        applyToSelf(new MetallicizePower(p, this.secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}