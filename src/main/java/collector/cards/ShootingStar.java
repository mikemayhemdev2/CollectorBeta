package collector.cards;

import collector.powers.ShootingStarPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import static collector.CollectorMod.makeID;

public class ShootingStar extends AbstractCollectorCard {
    public final static String ID = makeID(ShootingStar.class.getSimpleName());
    // intellij stuff power, self, rare, , , , , 20, 5

    public ShootingStar() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ShootingStarPower());
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}