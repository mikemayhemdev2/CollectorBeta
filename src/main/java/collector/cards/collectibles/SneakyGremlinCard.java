package collector.cards.collectibles;

import collector.powers.collectioncards.GremlinGangPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import java.util.Objects;

import static collector.CollectorMod.GREMLINGANG;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;
import static utilityClasses.Wiz.*;

public class SneakyGremlinCard extends AbstractCollectibleCard {
    public final static String ID = makeID(SneakyGremlinCard.class.getSimpleName());
    // intellij stuff attack, enemy, common, 10, 5, , , , 

    public SneakyGremlinCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 12;
        this.baseMagicNumber = magicNumber = 1;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.tags.add(GREMLINGANG);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        atb(new DrawCardAction(magicNumber));
        if (!p.hasPower(GremlinGangPower.POWER_ID)) applyToSelf(new GremlinGangPower(this));
    }

    public void upp() {
        upgradeMagicNumber(1);
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