package collector.cards.collectibles;

import collector.powers.collectioncards.GremlinGangPower;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
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
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 4;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new BetterDiscardPileToHandAction(1));
    }

    public void upp() {
        upgradeBlock(2);
    }

}