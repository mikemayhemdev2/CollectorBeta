package collector.cards.collectibles;

import collector.powers.collectioncards.GremlinGangPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import java.util.Objects;

import static collector.CollectorMod.GREMLINGANG;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class ShieldGremlinCard extends AbstractCollectibleCard {
    public final static String ID = makeID(ShieldGremlinCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , 9, 3, , 

    public ShieldGremlinCard() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 6;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.tags.add(GREMLINGANG);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (!p.hasPower(GremlinGangPower.POWER_ID)) applyToSelf(new GremlinGangPower(this));
    }

    public void upp() {
        upgradeBlock(3);
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