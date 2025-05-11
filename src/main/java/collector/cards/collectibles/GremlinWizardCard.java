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
        baseMagicNumber = magicNumber = 5;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.tags.add(GREMLINGANG);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new NextTurnVigorPower(magicNumber));
        applyToSelf(new NextTurnReservePower(1));
        if (!p.hasPower(GremlinGangPower.POWER_ID)) applyToSelf(new GremlinGangPower(this));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }


    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(GremlinGangPower.POWER_ID)) {
            GremlinGangPower power = (GremlinGangPower) AbstractDungeon.player.getPower(GremlinGangPower.POWER_ID);
            if (!Objects.equals(power.lastKnownGremlinCard.name, this.name))
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR;
            return;
        }
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }
}