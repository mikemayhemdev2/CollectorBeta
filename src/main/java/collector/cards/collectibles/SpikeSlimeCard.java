package collector.cards.collectibles;

import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import sneckomod.SneckoMod;
import static collector.CollectorMod.makeID;

public class SpikeSlimeCard extends AbstractCollectibleCard {
    public final static String ID = makeID(SpikeSlimeCard.class.getSimpleName());

    public SpikeSlimeCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        //baseMagicNumber = magicNumber = 1;
        baseBlock = 7;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new RemoveAllBlockAction(m, p));
        //applyToSelf(new ThornsPower(p, magicNumber));
    }

    public void upp() {
        upgradeBlock(2);
        //upgradeMagicNumber(1);
    }
}