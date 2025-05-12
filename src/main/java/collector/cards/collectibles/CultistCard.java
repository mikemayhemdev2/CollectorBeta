package collector.cards.collectibles;

import collector.powers.StrengthOverTurnsPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class CultistCard extends AbstractCollectibleCard {
    public final static String ID = makeID(CultistCard.class.getSimpleName());
    // intellij stuff power, self, common, , , , , , 

    public CultistCard() {
        super(ID, 1, CardType.POWER, CardRarity.COMMON, CardTarget.SELF);
        isPyre();
        this.baseMagicNumber = magicNumber = 3;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthOverTurnsPower(magicNumber, 1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}