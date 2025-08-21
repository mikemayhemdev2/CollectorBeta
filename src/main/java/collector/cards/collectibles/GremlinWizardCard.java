package collector.cards.collectibles;

import collector.powers.NextTurnReservePower;
import collector.powers.NextTurnVigorPower;
import collector.powers.collectioncards.GremlinGangPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import java.util.Objects;

import static collector.CollectorMod.*;
import static utilityClasses.Wiz.*;

public class GremlinWizardCard extends AbstractCollectibleCard {
    public final static String ID = makeID(GremlinWizardCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 10, 5

    public GremlinWizardCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 7;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new NextTurnVigorPower(magicNumber));
//        applyToSelf(new NextTurnReservePower(1));
    }

    public void upp() {
//        upgradeMagicNumber(3);
        upgradeBaseCost(0);
    }

}