package collector.cards.collectibles;

import collector.powers.collectioncards.GremlinGangPower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import sneckomod.SneckoMod;

import java.util.Objects;

import static collector.CollectorMod.GREMLINGANG;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class MadGremlinCard extends AbstractCollectibleCard {
    public final static String ID = makeID(MadGremlinCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 2, 2

    public MadGremlinCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 2;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.tags.add(GREMLINGANG);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToSelf(new LoseStrengthPower(p, magicNumber));
        atb(new MakeTempCardInHandAction(new Shiv(), secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }

}